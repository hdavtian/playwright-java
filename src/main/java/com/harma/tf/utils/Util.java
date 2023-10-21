package com.harma.tf.utils;

import java.nio.file.Paths;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.AddStyleTagOptions;

public final class Util {

	// Constructor
	private Util() {
		
	}
	
	// Methods
	public static String removeSpacesAndSpecialCharacters(String str) {
		String _str = str.replaceAll("[\\t\\n\\r]+"," ").replaceAll(" +", " ").replaceAll(" ", "").replaceAll("[^a-zA-Z0-9]", "");
		return _str;
	}
	
	public static void addTFStylesheetToPageIfNecessasry(Page page) {
		
		// haven't incorporated the if necessary part yet, need to figure out how to detect if this stylesheet has been added or not
		AddStyleTagOptions options = new AddStyleTagOptions();
		StringBuilder css = new StringBuilder();
		css.append(".ictf-highlight-element {border: 1px solid red!important;}");	
		options.content = css.toString();
		page.addStyleTag(options);
	}
	
	public static void loadJsFile(Page page, String _path, String functionNameToCheck) {
		// first check if function exists if not load the file
		String existsJs = String.format("() => typeof %s === 'function'", functionNameToCheck);
		boolean isScriptLoaded = (boolean) page.evaluate(existsJs);
		
		if (!isScriptLoaded) {
			page.addScriptTag(new Page.AddScriptTagOptions().setPath(Paths.get(_path)));
		}
	}
	
	
	
}
