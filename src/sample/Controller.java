package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

public class Controller {

    @FXML
    private Button mainButton;

    @FXML
    public void onButtonClicked(ActionEvent e) throws InterruptedException {
        if (e.getSource().equals(mainButton)) {
            nextVideo();
        }
    }

    public static WebDriver driver;
    private String geckoPath = "Resources/geckodriver.exe";
    private String youtubePath = "https://www.youtube.com";

    private String nextVidPath = "//*[@class='ytp-left-controls']/a[2]";
    private String skipAdPath = "//div[@class='ytp-ad-skip-button-slot']/span/button";
    private String smallAdPath = "//div[@class='ytp-ad-image-overlay']/div[2]/button";

    private static String repeatPath = "//div[@class='style-scope ytd-playlist-panel-renderer']/ytd-menu-renderer/div/ytd-toggle-button-renderer[1]";
    private static String maximizePath = "//div[@class='ytp-right-controls']/button[9]";
    private static String stopPath = "//div[@class='ytp-left-controls']/button";

    public Controller() {
        setUp();
        skipAd();
        smallAdSkip();
    }

    private void setUp() {
        System.setProperty("webdriver.gecko.driver", geckoPath);
        ProfilesIni profIni = new ProfilesIni();
        FirefoxProfile newFirefoxProf = profIni.getProfile("default");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(newFirefoxProf);
        driver = new FirefoxDriver(firefoxOptions);
        driver.get(youtubePath);
    }

    public static void maximizeView() {
        driver.getCurrentUrl();
        if (!driver.findElements(By.xpath(maximizePath)).isEmpty()) {
            if (driver.findElement(By.xpath(maximizePath)).isEnabled()) {
                driver.findElement(By.xpath(maximizePath)).click();
            }
        }
    }

    public static void stopPlay() {
        driver.getCurrentUrl();
        if (!driver.findElements(By.xpath(stopPath)).isEmpty()) {
            if (driver.findElement(By.xpath(stopPath)).isEnabled()) {
                driver.findElement(By.xpath(stopPath)).click();
            }
        }
    }

    public static void choosePlaylist(String playlist) {
        driver.get(playlist);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.getCurrentUrl();
        if (!driver.findElements(By.xpath(repeatPath)).isEmpty()) {
            if (driver.findElement(By.xpath(repeatPath)).isDisplayed()) {
                driver.findElement(By.xpath(repeatPath)).click();
            }
        }
        maximizeView();
    }

    private void nextVideo() {
        driver.getCurrentUrl();
        if (!driver.findElements(By.xpath(nextVidPath)).isEmpty()) {
            driver.findElement(By.xpath(nextVidPath)).click();
        }
    }

    private void smallAdSkip() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    driver.getCurrentUrl();
                    if (!driver.findElements(By.xpath(smallAdPath)).isEmpty()) {
                        if (driver.findElement(By.xpath(smallAdPath)).isDisplayed()) {
                            driver.findElement(By.xpath(smallAdPath)).click();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void skipAd() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    driver.getCurrentUrl();
                    if (!driver.findElements(By.xpath(skipAdPath)).isEmpty()) {
                        if (driver.findElement(By.xpath(skipAdPath)).isDisplayed()) {
                            driver.findElement(By.xpath(skipAdPath)).click();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}