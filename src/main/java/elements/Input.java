package elements;

import org.openqa.selenium.WebElement;

public class Input extends BaseElement {
    public Input(WebElement webElement) {
        super(webElement);
    }

    public void write (String str) {
        webElement.clear();
        webElement.sendKeys(str);
    }
}
