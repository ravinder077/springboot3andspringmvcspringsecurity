package com.eclerx.basecode;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eclerx.basecode.entity.Roles;
import com.eclerx.basecode.entity.User;
import com.eclerx.basecode.repository.RolesRepository;
import com.eclerx.basecode.repository.UserRepository;


@SpringBootApplication
public class BasecodeApplication extends SpringBootServletInitializer implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BasecodeApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Set<Roles> roles = new HashSet<Roles>();
		Roles roles1 = new Roles();
		roles1.setId(1L);
		roles1.setName("ROLE_USER");
		roles.add(roles1);
		this.rolesRepository.saveAll(roles);
		
		if(!userRepository.existsByUsername("user@user.com")) {
			
			User user = new User();
			user.setId(1L);
			user.setEmail("user@user.com");
			user.setUsername("user@user.com");
			user.setPassword(passwordEncoder.encode("Admin!@#45"));
			
			Roles adminRole = this.rolesRepository.findById(1L).get();

			Set<Roles> roleSet = new HashSet<Roles>();
			Roles roleS = new Roles();
			roleS.setId(adminRole.getId());
			roleS.setName(adminRole.getName());
			roleSet.add(roleS);
			user.setRoles(roleSet);
			
			User save2 = this.userRepository.save(user);

			System.err.println(save2.toString());
		}
		
	}

}
