package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements IBrowserSettings {

    @Override
    public AbstractDriverOptions settings() {
        ChromeOptions chromeOptions = new ChromeOptions();
        return chromeOptions;
    }

    @Override
    public AbstractDriverOptions settings(String mode) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(String.format("--start%s", mode));

        return chromeOptions;
    }
}