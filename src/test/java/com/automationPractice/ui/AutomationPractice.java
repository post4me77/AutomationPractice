package com.automationPractice.ui;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automationPractice.BasePageObject;

public class AutomationPractice extends BasePageObject {

	public AutomationPractice(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='contact-link']")
	WebElement contactUsButton;

	@FindBy(xpath = "//*[@id='id_contact']")
	WebElement subjectHeadingField;

	@FindBy(xpath = "//*[@id='email']")
	WebElement emailField;

	@FindBy(xpath = "//*[@id='id_order']")
	WebElement orderReferenceField;

	@FindBy(xpath = "//*[@id='fileUpload']")
	WebElement fileUploadButton;

	@FindBy(xpath = "//*[@id='message']")
	WebElement messageField;

	@FindBy(xpath = "//*[@id='submitMessage']")
	WebElement submitMessageButton;

	@FindBy(xpath = "//*[contains(text(),'Your message has been successfully sent to our team.')]")
	WebElement succesfullySendMessage;

	@FindBy(xpath = "//*[contains(text(),'The message cannot be blank.')]")
	WebElement notSuccesfullySendMessage;

	@FindBy(xpath = "//*[@class='alert-danger']")
	WebElement alertDangerMessage;

	@FindBy(xpath = "//*[@class='product-name']")
	List<WebElement> productsNameTitle;

	@FindBy(xpath = "//*[contains(text(),'Add to cart')]")
	WebElement addToCartButton;

	@FindBy(xpath = "//*[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckoutButton;

	@FindBy(xpath = "//*[@id='quantity_wanted']")
	WebElement quantityWantedField;

	@FindBy(xpath = "//*[@id='group_1']")
	WebElement sizeSelector;

	public void setElementText(WebElement element, String text) throws IOException, InterruptedException {
		waitUntilElementIsLoaded(element);
		element.sendKeys(text);
	}

	public void waitForElement(WebElement element) throws IOException, InterruptedException {
		waitUntilElementIsLoaded(element);
	}

	public void selectDropDownElement(WebElement element, String value) {
		new Select(element).selectByVisibleText(value);
	}

	public void scrollIntoView(WebElement element) throws InterruptedException {
		scrollToElement(element);
	}

	public void setText(WebElement textField, String text) throws IOException, InterruptedException {
		waitForElement(textField);
		// Set focus on field.
		textField.click();
		// Select or text on filed.
		textField.sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
	}
}
