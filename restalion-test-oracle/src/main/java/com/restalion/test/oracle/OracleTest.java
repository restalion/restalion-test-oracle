package com.restalion.test.oracle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleTest {

	public static void main(String[] args) {
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
			
			Properties properties = new Properties();
			InputStream inputStream = new FileInputStream("./src/main/resources/connection.properties");
			properties.load(inputStream);
 
			String host = properties.getProperty("host");
			String port = properties.getProperty("port");
			String sid = properties.getProperty("sid");
			String user = properties.getProperty("user");
			String pass = properties.getProperty("password");
			
			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + sid, user, pass);
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		} catch (FileNotFoundException fnfe) {
			 
			System.out.println("Properties file not found!");
			fnfe.printStackTrace();
			return;
 
		} catch (IOException ioe) {
			 
			System.out.println("Properties file not readed!");
			ioe.printStackTrace();
			return;
 
		}

 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}
