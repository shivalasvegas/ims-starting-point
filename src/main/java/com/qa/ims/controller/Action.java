package com.qa.ims.controller;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * Action is a collection of commands which are used to determine the type of
 * function to apply to an entity.
 *
 */
public enum Action {
	CREATE("To save a new item into the database"), READ("To read an item from the database"),
	UPDATE("To change an item already in the database"), DELETE("To remove an item from the database"),
	RETURN("To return to database selection"), EXIT("Exit the program"), CALC("To calculate your order total");

	public static final Logger LOGGER = Logger.getLogger(Action.class);

	private String description;
	private static String utilString;

	private Action() {
	}

	Action(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	
	/**
	 * Prints out all posible actions
	 */
	public static void printActions() {
		for (Action action : Action.values()) {
			LOGGER.info(action.getDescription());
		}
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static Action getAction() {
		Action action;
		while (true) {
			try {
				utilString = Utils.getInput().toUpperCase();
				action = Action.valueOf(utilString);
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}
	
	public static Action getReadAction(){
		Action action;
		while (true) {
			try {
				utilString = Utils.getRead();
				action = Action.valueOf(utilString);
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}
	
	
	public static Action getCalcAction(){
		Action action;
		while (true) {
			try {
				utilString = Utils.getCalc();
				action = Action.valueOf(utilString);
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}
	
	//To check input in ims class 
	public static String getStringAction() {
		
		return utilString.toLowerCase();
	}
	
}
