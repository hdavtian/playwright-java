package com.harma.tf.commands;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

public class BaseCommand {
	
	// Properties
	protected String commandName;
	protected Page page;
	protected ExtentTest test;
	
	// Methods
	public String getCommandName() {
		return this.commandName;
	}
	
//	public void execute() {
//		Logger logger = LogManager.getLogger();
//		logger.info("Hello World by way of log4j, .info()");
//		logger.log(Level.ERROR,"This is an error");
//	}
	
	// Constructor(s)
	public BaseCommand(String commandName, Page page, ExtentTest test) {
		this.commandName = commandName;
		this.page = page;
		this.test = test;
	}
	
//	public BaseCommand(String commandName) {
//		this.commandName = commandName;
//	}

}
