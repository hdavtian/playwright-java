package com.harma.tf.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFactory {
	
	private Properties prop;
	public Properties getProperties() {
		return this.prop;
	}

	// Constructor
	public PropertiesFactory(String fileName) {
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/" + fileName);
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
