package com.example.frontend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class loginTest {
    private static WebDriver driver;

    By loginBtn = By.xpath("/html/body/app-root/div/app-login/div/div/div[2]/button");

    By email = By.xpath("//*[@id=\"identifierId\"]");

    By password = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");

    By emailNextBtn = By.xpath("//*[@id=\"identifierNext\"]/div/button");

    By passwordNextBtn = By.xpath("//*[@id=\"passwordNext\"]/div/button");

    By signIO = By.xpath("/html/body/app-root/div/app-login/div/div[1]/app-nav-bar/nav/div/div");


    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_copy");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        ChromeOptions handlingSSL = new ChromeOptions();
        handlingSSL.setAcceptInsecureCerts(false);

    }

    @BeforeEach
    public void getPage() throws Exception{
        driver.get("http://localhost:4200/");
        Thread.sleep(3000);
    }


    @Test
    public void testLogin() throws Exception{
        driver.findElement(loginBtn).click();
        Thread.sleep(2000);
        String handle = driver.getWindowHandle();
        for(String handles : driver.getWindowHandles()) {
            if(handles.equals(handle)) {
                continue;
            }
            driver.switchTo().window(handles);
        }
        Thread.sleep(2000);

        driver.findElement(email).sendKeys("nisumtester@gmail.com");
        Thread.sleep(2000);

        driver.findElement(emailNextBtn).click();
        Thread.sleep(2000);

        driver.findElement(password).sendKeys("Zjy123243");
        Thread.sleep(2000);

        driver.findElement(passwordNextBtn).click();
        Thread.sleep(2000);

        handle = driver.getWindowHandle();
        for(String handles : driver.getWindowHandles()) {
            if(handles.equals(handle)) {
                continue;
            }
            driver.switchTo().window(handles);
        }
        Thread.sleep(2000);

        Assertions.assertEquals("Sign out", driver.findElement(signIO).getText());

    }


    @AfterEach
    public void tearDown() {
        driver.close();
        System.out.println("Test closed");
    }
}
