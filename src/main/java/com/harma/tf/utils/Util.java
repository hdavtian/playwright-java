package com.harma.tf.utils;

import java.nio.file.Paths;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.AddStyleTagOptions;
import com.microsoft.playwright.options.BoundingBox;

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
	
	public static void loadLocalCssFileIfNecessary(Page page, String _path) {
		
		boolean isStyleIncluded = (boolean) page.evaluate("() => " +
				 "document.querySelectorAll('#ictf-stylesheet-injected-at-runtime').length != 0");
		
		if (!isStyleIncluded) {
			page.addStyleTag(new Page.AddStyleTagOptions().setPath(Paths.get(_path)));
			
			page.evaluate("() => {" +
					"const styleTags = Array.from(document.getElementsByTagName('style'));" + 
					"const lastIndex = styleTags.length - 1; " +
					"styleTags[lastIndex].setAttribute('id', 'ictf-stylesheet-injected-at-runtime');" +
					"};"
			);
		}
	}
	
	public static BoundingBox getAppBoundingBox(ElementHandle el) {
		return el.boundingBox();
	}
	
}
