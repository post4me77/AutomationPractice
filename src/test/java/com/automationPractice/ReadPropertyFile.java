package com.automationPractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ReadPropertyFile {
	private static Properties prop;
	private final String propertyFilePath = "src/test/java/AutomationPractice/enviroment.properties";

	public ReadPropertyFile() {
		prop = new Properties();
		File file = new File(propertyFilePath);
		FileInputStream ip;
		try {
			ip = new FileInputStream(file);

			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public String getChromeVersion() {
		String driverPath = prop.getProperty("browser");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}

	public static List<Integer> getVallueWithComma(String value) {
		List<Integer> list = new ArrayList<Integer>();
		String implicitlyWait = prop.getProperty(value);
		List<String> elephantList = Arrays.asList(implicitlyWait.split(","));
		for (String item : elephantList) {
			list.add(Integer.parseInt(item.trim()));
		}
		return list;
	}
}
