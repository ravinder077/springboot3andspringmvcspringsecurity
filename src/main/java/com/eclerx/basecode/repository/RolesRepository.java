package com.eclerx.basecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eclerx.basecode.entity.Roles;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
