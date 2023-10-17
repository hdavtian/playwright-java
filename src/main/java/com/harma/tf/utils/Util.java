package com.harma.tf.utils;

import com.microsoft.playwright.Page;

public final class Util {

	// constructor
	private Util() {
		
	}
	
	public static String removeSpacesAndSpecialCharacters(String str) {
		
		String _str = str.replaceAll("[\\t\\n\\r]+"," ").replaceAll(" +", " ").replaceAll(" ", "").replaceAll("[^a-zA-Z0-9]", "");
		return _str;
	}
	
}
