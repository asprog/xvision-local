package com.xvision.lesson5;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class CreateSkylineWebCams {
    static WebDriver driver;
    //private static final String CRM_URL = "http://lab.xvision.space/xVisionTVService/?tv=e2d4fff7-c981-4a4e-9d53-1adaf4068473";private static final
    private static final String CRM_URL = "https://www.skylinewebcams.com/ru/webcam/ellada/makedonia/thessaloniki/plateia-aristotelous.html";
    private static final String browser_path = "C:/Users/xVisionTV/AppData/Roaming/Mozilla/Firefox/Profiles/wrhozify.default-release";
    public static void main(String[] args) {
       try {
           setProperties();
           login();
           openCam();
           /*createNewProject();
           fillNewProject();*/
           Thread.sleep(10000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally{
           System.exit(0);
         //if(driver != null) driver.quit();
       }
    }

    static void setProperties() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxProfile profile = new FirefoxProfile(new File("C:\\xVision\\firefox\\profile-default"));
        //profile.addExtension(new File("c:/dist/uBlock0@raymondhill.net.xpi"));
        FirefoxOptions options = new FirefoxOptions()
                .addPreference("network.automatic-ntlm-auth.trusted-uris", "http://,https://")
                .addPreference("browser.startup.page", 1)
                .addPreference("media.autoplay.default", 0)
                .addPreference("media.volume_scale", "0.0")
                .addPreference("security.csp.enable", false)
                .addPreference("browser.startup.homepage", CRM_URL);
        options.setAcceptInsecureCerts(true);
        options.setProfile(profile);
        options.addArguments("--kiosk");
        driver = new FirefoxDriver(options);
    }
    static void login() {
      //driver.get(CRM_URL);
      /*driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
      driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
      driver.findElement(By.id("_submit")).click();*/
    }
     static void openCam() throws InterruptedException {
         //driver.manage().timeouts().implicitlyWait().implicitlyWait(new Duration(3000));
         Thread.sleep(3000);
         driver.findElement(By.xpath("//button[@aria-label='fullscreen']")).click();
    }

    static void createNewProject() throws InterruptedException {
       // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      //  WebElement weProjects = driver.findElement(By.xpath("//a[.='Создать проект']")).click();
    }
    private static void fillNewProject() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='crm_project[name]']")).sendKeys("test2609210321");
        driver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[.='1234']")).click();
        /*driver.findElement(By.xpath("//div[contains(@id,'project_contactMain-uid')]//span[@class='select2-arrow']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[.='Bon Jovi Jhon']")).click();*/
        new Select(
           driver.findElement(By.xpath("//select[@name='crm_project[businessUnit]']")))
                .selectByVisibleText("Research & Development");
        new Select(
                driver.findElement(By.xpath("//select[@name='crm_project[curator]']")))
                .selectByVisibleText("Гумённый Пётр");
        new Select(
                driver.findElement(By.xpath("//select[@name='crm_project[rp]']")))
                .selectByVisibleText("Воденеев Денис");
        new Select(
                driver.findElement(By.xpath("//select[@name='crm_project[manager]']")))
                .selectByVisibleText("Прохорова Алла");
        driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
        Thread.sleep(10000);

    }
}
