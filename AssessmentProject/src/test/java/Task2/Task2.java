package Task2;

import org.testng.annotations.Test;

import Utility.ConfigReader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Task2 {

	ConfigReader config;
	public static WebDriver driver;

	// sets up chrome driver
	@BeforeTest
	public void setup() {
		config = new ConfigReader();
		System.setProperty("webdriver.chrome.driver", config.getChrmePath());
		System.out.println("==============setup is running==============");
	}

	// navigate to the url and open browser
	@Test(priority = 0)
	public void openbrowser() throws InterruptedException, Exception {

		driver = new ChromeDriver();
		driver.get(config.geturl());
		driver.manage().window().maximize();
		System.out.println("==============Browser is Opened==============");
		// Thread.sleep(300);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// verify that you are on the user list table
	@Test(priority = 1)
	public void verifyuser() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement myname = driver.findElement(By.xpath("/html/body/table/thead/tr[2]/td/button"));
		if (myname.equals(config.getNameOnList())) {
			System.out.println("your name is present" + myname);

		} else {
			System.out.println("=====================your name is not present on the table========================");
		}
		Assert.assertTrue(true);
		System.out.println("==============Verified username on table==============");
	}

	// click adduser button
	@Test(priority = 2)
	public static void adduserbutton() throws InterruptedException {// Thread.sleep(300);

		WebElement adduser = driver.findElement(By.cssSelector("body > table > thead > tr:nth-child(2) > td > button"));
		adduser.click();
		Assert.assertTrue(true);
		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		System.out.println("==============add userbutton working==============");
	}

	@Test(priority = 4)
	// close add user button
	public static void closeadduserbutton() {

		driver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-footer > button.btn.btn-danger"))
				.click();
		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

	}

	// Reading data from an excel file
	@DataProvider(name = "exceldatareader")
	public Object[][] testDataExcel() { // instatiating the excelconfig class to be able to call the methods
		ExcelDataConfig confige = new ExcelDataConfig(config.getexcelldata());
		int rows = confige.getRowCount(0);

		// populating data from excel file
		Object[][] data = new Object[rows][7];
		for (int i = 0; i < rows; i++) {
			data[i][0] = confige.getData(0, i, 0);
			data[i][1] = confige.getData(0, i, 1);
			data[i][2] = confige.getData(0, i, 2);
			data[i][3] = confige.getData(0, i, 3);
			data[i][4] = confige.getData(0, i, 4);
			data[i][5] = confige.getData(0, i, 5);
			data[i][6] = confige.getData(0, i, 6);
			System.out.println("==============Reading data from excell file=============="+i);
		}
		System.out.println("==============Reading data from excell file==============");
		return data;

	}

	// method to add users to the table retrieving data from excell file
	@Test(dataProvider = "exceldatareader", priority = 3)
	public void adduser(String firstname, String lastname, String username, String password, String email, String cell,
			String role) throws InterruptedException {

		System.out.println("==============adding user==============");

		// WebElement adduserbutton =
		// driver.findElement(By.xpath("/html/body/table/thead/tr[2]/td/button"));
		// adduserbutton.click();
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > input"))
				.clear();
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > input"))
				.sendKeys(firstname);
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input"))
				.clear();
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input"))
				.sendKeys(lastname);
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > input"))
				.clear();
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > input"))
				.sendKeys(username);
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input"))
				.clear();
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input"))
				.sendKeys(password);
		Thread.sleep(300);
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//

		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > label:nth-child(1) > input"))
				.click();

		// select company B
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > label:nth-child(2) > input"))
				.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(300);
		String roe = role;
		if (role.contains("Customer")) {
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(6) > td:nth-child(2) > select")))
							.selectByVisibleText("Customer");
			Thread.sleep(300);
		//	body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(6) > td:nth-child(2) > select
		} else if (role.contains("Admin")) {
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(6) > td:nth-child(2) > select")))
							.selectByVisibleText("Admin");
			Thread.sleep(300);
		}

		// body > div.modal.ng-scope > div.modal-body > form > table > tbody >
		// tr:nth-child(6) > td:nth-child(2) > select
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(7) > td:nth-child(2) > input"))
				.clear();
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(7) > td:nth-child(2) > input"))
				.sendKeys(email);
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(8) > td:nth-child(2) > input"))
				.clear();
		driver.findElement(By.cssSelector(
				"body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(8) > td:nth-child(2) > input"))
				.sendKeys(cell);
		driver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-footer > button.btn.btn-success"))
				.click();
		// driver.findElement(By.xpath("/html/body/div[3]/div[3]/button[2]")).click();
		Thread.sleep(300);
		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		System.out.println("==============user added successfully==============");
		adduserbutton();
		// closeadduserbutton();
		
	}


//	@Test(priority = 4)*/
	public void usernamevalidation(String username) {

		int tablerow[] = new int[7];
		System.out.println("username:   " + username);
		for (int i = 0; i < tablerow.length; i++) {
			tablerow[i] = i;
			String reusername = driver.findElement(By.xpath("/html/body/table/tbody/tr[" + tablerow[i] + "/td[3]")).getText();
//
			if (username.contains(reusername)) {
				System.out.println("=================================Username is duplicated=========================");
				break;
			}
		}
		System.out.println("=============================User is added successfully====================================");

	}

//closes the browser
	@AfterTest
	public void closebrowser() throws InterruptedException {
		Thread.sleep(300);
		driver.close();
		Assert.assertTrue(true);
		System.out.println("==============browser closed==============");
	}

}
