package elements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {
    protected WebElement webElement;

    public BaseElement (WebElement webElement) {
        this.webElement = webElement;
    }

    public void click() {
        if (isAvailable()) {
            this.webElement.click();
        }
    }

    public boolean isPresent() {
        try
        {
            return this.webElement.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

    }

    private boolean isAvailable() {
        return this.webElement.isEnabled();
    }

    public String getText() {
        return this.webElement.getText();
    }
}
