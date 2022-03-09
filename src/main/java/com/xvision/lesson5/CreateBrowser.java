package com.xvision.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.Collections;


public class CreateBrowser {
    static WebDriver driver;
    private static String URL = null,USER_TOKEN = null,PROFILE_PATH = null;
    private static final Logger LOG = Logger.getLogger(CreateBrowser.class.getName());
    public static void main(String[] args) {
      try{
        if(getArgs(args) && checkUser(URL,USER_TOKEN)) {
           setProperties();
           openBrower();
        }//else
           /*while(1) {
             LOG.info();
             Thread.sleep(2000);
           }*/
       } finally{
           System.exit(0);
         //if(driver != null) driver.quit();
       }
    }
    private static boolean getArgs(String[] args) {
     try {
         if (args[0] != null && args[1] != null && args[2] != null
                 && !args[0].trim().isEmpty()
                 && !args[1].trim().isEmpty()
                 && !args[2].trim().isEmpty()) {
             URL = args[0].trim().toLowerCase();
             USER_TOKEN = args[1].trim().toLowerCase();
             PROFILE_PATH = args[2].trim().toLowerCase();
             LOG.info("open url: " + URL
                     + " user token: " + USER_TOKEN
                     + " profile path: " + PROFILE_PATH
             );
             return true;
         }
      }catch (ArrayIndexOutOfBoundsException ex){
         LOG.warning("open url: not found params: usr or token or profile");
      }
      return false;
    }
    private static boolean checkUser(final String url, final String userToken) {
      //check validate url && token
      return true;
    }



    static void setProperties() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxProfile profile = new FirefoxProfile(new File(PROFILE_PATH));
        //profile.addExtension(new File("c:/dist/uBlock0@raymondhill.net.xpi"));
        //profile.setPreference("dom.disable_beforeunload", true);
        FirefoxOptions options = new FirefoxOptions()
                .addPreference("dom.webnotifications.enabled", false)
                .addPreference("dom.webnotifications.serviceworker.enabled", false)
                .addPreference("network.automatic-ntlm-auth.trusted-uris", "http://,https://")
                .addPreference("browser.startup.page", 1)
                .addPreference("media.autoplay.default", 0)
                .addPreference("media.volume_scale", "0.0")
                .addPreference("security.csp.enable", false)
                .addPreference("browser.startup.homepage", URL);
        options.setAcceptInsecureCerts(true);
        options.setProfile(profile);
        options.addArguments("--kiosk");
        driver = new FirefoxDriver(options);
    }
    static void openBrower() {
      //driver.get(CRM_URL);
      /*driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
      driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
      driver.findElement(By.id("_submit")).click();*/
    }
     static void openMyProjects() throws InterruptedException {
        WebElement weProjects = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li/a/span[.='Проекты']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(weProjects).build().perform();
        driver.findElement(By.xpath("//span[.='Мои проекты']")).click();

    }
    static void createNewProject() throws InterruptedException {
       // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[.='Создать проект']")).click();
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
