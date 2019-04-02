package com.cpw.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Example {
 
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//  FileReader reader=new FileReader("cpw/src/main/resources/path.properties");  
		Properties property=new Properties();
		//InputStream stream=ClassLoader.getSystemResourceAsStream("cpw/src/main/resources/path.properties");
		InputStream stream=Example.class.getResourceAsStream("/path.properties");
		System.out.println(stream);
		try {
			property.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(property.getProperty("username"));
	

	
		

	}
	
	

}
