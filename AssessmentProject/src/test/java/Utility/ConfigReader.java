package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;

public class ConfigReader {
//this class reads data from the config.property  file in the configuration folder
	Properties pro;
	public ConfigReader()
	{
		

		 try {
			File src = new File("./Configuration/Config.property");
			 FileInputStream fis = new FileInputStream(src);
			 
			 pro = new Properties();
			 pro.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception==========="+e.getMessage());
		}
	}
	public String getChrmePath()
	{
	
		return pro.getProperty("ChromeDriver");
	}
	public String geturl()
	{
		
		return pro.getProperty("URL");
	}
	public String getNameOnList()
	{
		
		return pro.getProperty("NameOnList");
	}
	public String getexcelldata()
	{
		
		return pro.getProperty("ExcelPath");
	}
}
