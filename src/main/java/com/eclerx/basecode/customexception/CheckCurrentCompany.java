package com.eclerx.basecode.customexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckCurrentCompany extends RuntimeException {

	Logger logger = LoggerFactory.getLogger(CheckCurrentCompany.class);

	public CheckCurrentCompany(String message) {
		super(message);
		logger.info("line no : 17 CheckCurrentCompany() method, message : "+message);
	}

	
	
}
