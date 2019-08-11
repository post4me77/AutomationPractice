package com.automationPractice.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.automationPractice.DriverFactory;
import com.automationPractice.ReadPropertyFile;

public class AutomationPracticeTest {
	DriverFactory objDriver = new DriverFactory();
	ReadPropertyFile readPropertyFile = new ReadPropertyFile();
	AutomationPractice apt;
	String BASEURL = "http://automationpractice.com";
	File file = new File("src/test/java/LICENSE.txt");

	@Before
	public void setUp() throws IOException, InterruptedException {
		apt = new AutomationPractice(objDriver.getDriver());
		apt.setWindowsSize(ReadPropertyFile.getVallueWithComma("size").get(0),
				ReadPropertyFile.getVallueWithComma("size").get(1));
		objDriver.getDriver().navigate().to(BASEURL);
	}
	
	@After
	public void tearDown() {
		objDriver.quitDriver();
	}

	@Test
	public void verifyFormSendsSuccessfully() throws InterruptedException, IOException {
		apt.contactUsButton.click();
		apt.selectDropDownElement(apt.subjectHeadingField, "Webmaster");
		apt.waitForElement(apt.emailField);
		apt.setText(apt.emailField, "djondo@djondo.com");
		apt.setText(apt.orderReferenceField, "123456789");
		apt.setText(apt.messageField, "Hello");
		// enter the file path onto the file-selection input field
		apt.fileUploadButton.sendKeys(file.getAbsolutePath());
		apt.submitMessageButton.click();
		assertTrue(apt.succesfullySendMessage.isDisplayed());
	}
	
	@Test
	public void verifyFormSendsWithoutMessageField() throws IOException, InterruptedException {
		apt.contactUsButton.click();
		apt.selectDropDownElement(apt.subjectHeadingField, "Webmaster");
		apt.waitForElement(apt.emailField);
		apt.setText(apt.emailField, "djanedo@djanedo.com");
		apt.setText(apt.orderReferenceField, "123456789");
		// enter the file path onto the file-selection input field
		apt.fileUploadButton.sendKeys(file.getAbsolutePath());
		apt.submitMessageButton.click();
		assertTrue(apt.notSuccesfullySendMessage.isDisplayed());
	}
	
	@Test
	public void proceedToCheckout() throws InterruptedException, IOException {
		String ItemName = apt.productsNameTitle.get(1).getText();
		System.out.println(ItemName);
		apt.scrollIntoView(apt.productsNameTitle.get(1));
		apt.waitForElement(apt.productsNameTitle.get(1));
		apt.productsNameTitle.get(1).click();
		apt.waitForElement(apt.addToCartButton);
		apt.waitForElement(apt.quantityWantedField);
		apt.setText(apt.quantityWantedField, "2");
		apt.selectDropDownElement(apt.sizeSelector, "L");
		apt.addToCartButton.click();
		apt.waitForElement(apt.proceedToCheckoutButton);
		apt.proceedToCheckoutButton.click();
		apt.waitForElement(apt.productsNameTitle.get(3));
		String ItemNameFromMyOrder = apt.productsNameTitle.get(3).getText();
		assertEquals(ItemNameFromMyOrder, ItemName);
	}
}
