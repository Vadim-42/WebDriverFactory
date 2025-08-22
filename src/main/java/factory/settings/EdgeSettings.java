package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class EdgeSettings implements IBrowserSettings {

    @Override
    public AbstractDriverOptions settings() {
        EdgeOptions edgeOptions = new EdgeOptions();
        return edgeOptions;
    }

    @Override
    public AbstractDriverOptions settings(String mode) {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments(String.format("--start%s", mode));

        return edgeOptions;
    }
}