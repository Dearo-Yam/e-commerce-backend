package com.example.frontend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class mainPageTest {
    private static WebDriver driver;

    private static By loginBtn = By.xpath("/html/body/app-root/div/app-login/div/div/div[2]/button");

    private static By email = By.xpath("//*[@id=\"identifierId\"]");

    private static By password = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");

    private static By emailNextBtn = By.xpath("//*[@id=\"identifierNext\"]/div/button");

    private static By passwordNextBtn = By.xpath("//*[@id=\"passwordNext\"]/div/button");

    By home1 = By.xpath("/html/body/app-root/div/app-login/div/div[1]/app-nav-bar/nav/div/a");

    By home2 = By.xpath("/html/body/app-root/div/app-pending/div/div[1]/app-nav-bar/nav/div/a");

    By backFromDetail = By.xpath("/html/body/app-root/div/app-order-detail-window/button");

    By order10 = By.xpath("/html/body/app-root/div/app-pending/div/div[4]/table/tbody/tr[1]/td[1]/a");

    By order11AfterSearch = By.xpath("/html/body/app-root/div/app-pending/div/div[4]/table/tbody/tr/td[1]/a");

    By orderNumber = By.xpath("/html/body/app-root/div/app-order-detail-window/div/h2");

    By signIO = By.xpath("/html/body/app-root/div/app-pending/div/div[1]/app-nav-bar/nav/div/div");

    By searchBox = By.xpath("/html/body/app-root/div/app-pending/div/div[3]/div/input");

    By sortDropdown = By.xpath("/html/body/app-root/div/app-pending/div/div[3]/div/div/div/app-filter-menu/div[1]");

    By sortDropdown2 = By.xpath("/html/body/app-root/div/app-shipped-orders/div/div[3]/div/div/div/app-filter-menu/div[1]");

    By shippedSort = By.xpath("/html/body/app-root/div/app-pending/div/div[3]/div/div/div/app-filter-menu/div[2]/div[3]/a");

    By pendingSort = By.xpath("/html/body/app-root/div/app-shipped-orders/div/div[3]/div/div/div/app-filter-menu/div[2]/div[2]/a");

    By statusVerify = By.xpath("/html/body/app-root/div/app-shipped-orders/div/table/tbody/tr[1]/td[2]");

    @BeforeAll
    public static void setup() throws Exception{
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_copy");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        ChromeOptions handlingSSL = new ChromeOptions();
        handlingSSL.setAcceptInsecureCerts(false);
        driver.get("http://localhost:4200/");
        Thread.sleep(3000);
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
        Thread.sleep(3000);
    }


    @Test
    public void orderDetailTest() throws Exception{
        driver.findElement(home1).click();
        Thread.sleep(2000);
        driver.findElement(order10).click();
        Thread.sleep(2000);
        Assertions.assertEquals("Order#: 10", driver.findElement(orderNumber).getText());
        driver.findElement(backFromDetail).click();
        Thread.sleep(2000);
        Assertions.assertEquals("Sign out", driver.findElement(signIO).getText());
    }

    @Test
    public void searchOrderTest() throws Exception{
        driver.findElement(home2).click();
        Thread.sleep(2000);
        driver.findElement(searchBox).sendKeys("11");
        Assertions.assertEquals("11", driver.findElement(order11AfterSearch).getText());
        driver.findElement(searchBox).sendKeys("");
        Thread.sleep(2000);
    }

    @Test
    public void sortTest() throws Exception{
        driver.findElement(home2).click();
        Thread.sleep(2000);
        driver.findElement(sortDropdown).click();
        Thread.sleep(2000);
        driver.findElement(shippedSort).click();
        Thread.sleep(2000);
        Assertions.assertEquals("Shipped", driver.findElement(statusVerify).getText());
        driver.findElement(sortDropdown2).click();
        Thread.sleep(2000);
        driver.findElement(pendingSort).click();
        Thread.sleep(2000);
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}
