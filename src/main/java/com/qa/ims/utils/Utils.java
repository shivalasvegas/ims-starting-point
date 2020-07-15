package com.qa.ims.utils;

import java.util.Scanner;

public class Utils {

	public static final String MYSQL_URL = "34.105.145.205:3306";
	public static final Scanner SCANNER = new Scanner(System.in);
	public static final String READ = "READ";
	public static final String CALC = "CALC";
	
	private Utils() {

	}

	public static String getInput() {
		return SCANNER.nextLine();
	}

	public static String getRead() {
		return READ;
	}
	
	public static String getCalc() {
		return CALC;
	}
	
}
