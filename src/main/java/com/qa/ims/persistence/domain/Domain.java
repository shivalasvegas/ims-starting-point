package com.qa.ims.persistence.domain;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Domain {

	CUSTOMER("Information about customers"),
	ITEM("Individual Products"),
	ORDER("Purchases of items"),
	EXIT("Close the application");
	
	public static final Logger LOGGER = Logger.getLogger(Domain.class);

	private String description;
	private static String utilString;
	
	private Domain(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.name() + ": " +this.description;
	}
	
	public static void printDomains() {
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.getDescription());
		}
	}
	

	
	public static Domain getDomain() {
		Domain domain;
		while (true) {
			try {
				utilString = Utils.getInput().toUpperCase();
				domain = Domain.valueOf(utilString);
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection, please try again");
			}
		}
		return domain;
	}
	
	//To check input in ims class 
	public static String getStringDomain() {
		
	return utilString.toLowerCase();
	}
	
}
