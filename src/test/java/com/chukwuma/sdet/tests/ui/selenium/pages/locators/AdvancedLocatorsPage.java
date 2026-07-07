package com.chukwuma.sdet.tests.ui.selenium.pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AdvancedLocatorsPage {

    private final WebDriver driver;
    // CSS selectors:
    private final By header = By.cssSelector("h1"); // tag
    private final By buyMeACoffee = By.cssSelector("#support-coffee"); // #id
    private final By container = By.cssSelector(".container"); // .class
    private final By emailInput = By.cssSelector("input#newsletterEmail"); // tag#id
    private final By addItemButton = By.cssSelector("button.btn-primary"); // tag.class
    private final By inputFields = By.cssSelector("input[type='text']"); // [attribute=value]
    private final By buyMeACoffeeLink = By.cssSelector("a[href^='https://www.buymeacoffee.com']"); // [attribute^=value]
                                                                                                   // (starts with)
    private final By logo = By.cssSelector("img[src$='.svg']");// [attribute$=value] (ends with)
    private final By avatarImage = By.cssSelector("img[src*='avatar']");// [attribute*=value] (contains)
    private final By practiceLink = By.cssSelector("nav.container > a"); // parent > child (direct child)
    private final By navTabs = By.cssSelector("nav.container li"); // ancestor descendant .menu a (any depth)
    private final By navTab2 = By.cssSelector("nav.container li:nth-child(1)"); // :nth-child(n)

    // XPath selectors:
    private final By searchInput = By.xpath("//input[@placeholder='Search the site']"); // relative path
    private final By reloadButton = By.xpath("//button[text()='Reload']"); // Text match
    private final By altTextHeader = By.xpath("//h4[contains(text(), 'AltText')]"); // Partial text
    private final By formControlInput = By.xpath("//input[contains(@class, 'form-control')]"); // Attribute contains
    private final By filterInput = By.xpath("//input[@type='text' and @placeholder='Filter by tag']"); // Multiple
                                                                                                       // attributes
    private final By parentOfSearchInput = By.xpath("//input[@placeholder='Search the site']/parent::div"); // Parent
                                                                                                            // axis
    private final By ancestorOfSearchInput = By.xpath("//input[@placeholder='Search the site']/ancestor::div"); // Ancestor
                                                                                                                // axis
    private final By siblingOfSearchInput = By
            .xpath("//input[@placeholder='Search the site']/following-sibling::input"); // Following sibling
    private final By tableRow = By.xpath("(//table//tr)[2]"); // Index

    public AdvancedLocatorsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmCssSelectors() {
        assert (driver.findElement(header).getText()).contains("Locators");
        assert (driver.findElement(buyMeACoffee).getText()).contains("Buy us a coffee");
        assert (!driver.findElements(container).isEmpty());

        WebElement newsletterEmailInput = driver.findElement(emailInput);
        String emailInputString = "This input field CSS selector is input#newsletterEmail";
        newsletterEmailInput.sendKeys("This input field CSS selector is input#newsletterEmail");
        assert (newsletterEmailInput.getAttribute("value")).equals(emailInputString);

        assert (driver.findElement(addItemButton)).isDisplayed();
        assert (!driver.findElements(inputFields).isEmpty());
        assert (driver.findElement(buyMeACoffeeLink)).isDisplayed();
        assert (driver.findElement(logo)).isDisplayed();
        assert (driver.findElement(avatarImage)).isDisplayed();

        assert (driver.findElement(practiceLink)).isDisplayed();
        driver.findElement(practiceLink).click();
        driver.navigate().back();

        assert (!driver.findElements(navTabs).isEmpty());
        assert (driver.findElement(navTab2)).isDisplayed();

    }

    public void confirmXpathSelectors() {
        driver.findElement(searchInput).sendKeys("Xpath");
        driver.findElement(filterInput).sendKeys("multiple attributes");

        assert (driver.findElement(reloadButton)).isDisplayed();
        assert (driver.findElement(parentOfSearchInput)).isDisplayed();
        assert (driver.findElement(ancestorOfSearchInput).getAttribute("id")).equals("contentbody");
        assert (driver.findElement(altTextHeader)).isDisplayed();
        assert (!driver.findElements(formControlInput).isEmpty());
        assert (driver.findElement(siblingOfSearchInput).getAttribute("value")).equals("multiple attributes");
        assert (driver.findElement(tableRow).getText()).contains("Headphones");
    }

    public void testRelativeLocators() {
        By centerCell = By.xpath("(//table//td)[5]");

        WebElement above = driver.findElement(with(By.tagName("td")).above(centerCell));
        assert (above.getText()).equals("Available");

        WebElement below = driver.findElement(with(By.tagName("td")).below(centerCell));
        assert (below.getText()).equals("Available");

        // WebElement left =
        // driver.findElement(with(By.tagName("td")).toLeftOf(centerCell));
        // assert (left.getText()).equals("Monitor");

        // WebElement right =
        // driver.findElement(with(By.tagName("td")).toRightOf(centerCell));
        // assert (right.getText()).equals("0");

        assert (driver.findElement(centerCell).getText()).equals("Out of stock");
    }

}
