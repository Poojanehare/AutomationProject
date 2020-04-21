package com.automationPrograms.FacebookTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FacebbokSelect {

	WebDriver driver;
	List<String> monthlist = new ArrayList<String>();
	List<WebElement> month1;

	@BeforeTest
	public void loginTest() {
		System.setProperty("webdriver.gecko.driver", "../selenium/exe/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		WebElement month = driver.findElement(By.xpath("//select[@name=\"birthday_month\"]"));
		Select s = new Select(month);
		month1 = s.getOptions();
		for (int i = 0; i < month1.size(); i++) {
			String text = month1.get(i).getText();
			monthlist.add(text);
		}

	}

	@Test
	public void duplicateTest() {
		Assert.assertEquals(monthlist.size(), month1.size());
		System.out.println("Duplicates are not present");
		System.out.println("================================================");
	}

	@Test
	public void sortedTest() {
		Set<String> sortedMonth = new TreeSet<String>();
		sortedMonth.addAll(monthlist);
		Assert.assertNotEquals(monthlist, sortedMonth);
		System.out.println("List of month are not in sorted order");
		System.out.println("================================================");
	}

	@Test
	public void containsAllEleTest() throws EncryptedDocumentException, FileNotFoundException, IOException {
		List<String> monthlist1 = new ArrayList<String>();
		Workbook book = WorkbookFactory.create(new FileInputStream("../com.automationPrograms/src/test/resources/Excel/FacebookMonthList.xlsx"));
		Sheet sh = book.getSheet("Sheet1");
		Row rw = sh.getRow(0);
		for (int i = 0; i <= 12; i++) {
			String data = rw.getCell(i).getStringCellValue();
			monthlist1.add(data);
		}
		Assert.assertEquals(monthlist, monthlist1);
		System.out.println("Month list contains all month");
		System.out.println("================================================");
	}

	@AfterTest
	public void logoutTest() {
		driver.close();
	}

}
