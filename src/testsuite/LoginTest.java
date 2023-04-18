package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";
    @Before
    public void setup() {
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        driver.findElement(By.id("username")).sendKeys("tomsmith"); //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"); //Enter password
        driver.findElement(By.xpath("//button[@type = 'submit']")).click(); //click on login button
        //Verify the 'Secure Area' test is display
        String expectedMessage = "Secure Area";
        //Xpath for 'Secure Area'
        WebElement actualTextElements = driver.findElement(By.xpath("//h2[text() = ' Secure Area']"));

        String actualMessage = actualTextElements.getText();
        //comparing actualMessage with expectedMessage
        Assert.assertEquals( expectedMessage, actualMessage);
    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        driver.findElement(By.id("username")).sendKeys("tomsmith1"); //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"); //Enter password
        driver.findElement(By.xpath("//button[@type = 'submit']")).click(); //click on login button
        //Verify the 'Secure Area' test is display
        String expectedErrorMessage = "Your username is invalid!\n"+ "×";
        //Xpath for 'Your username is invalid!'
        WebElement actualTextElements = driver.findElement(By.id("flash"));

        String actualMessage = actualTextElements.getText();
        //comparing actualMessage with expectedMessage
        Assert.assertEquals(expectedErrorMessage, actualMessage);

    }
    @Test
    public void verifyThePasswordErrorMessage(){
        driver.findElement(By.id("username")).sendKeys("tomsmith"); //Enter username
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword"); //Enter password
        driver.findElement(By.xpath("//button[@type = 'submit']")).click(); //click on login button
        //Verify the 'Your password is invalid!' errorMessage
        String expectedErrorMessage = "Your password is invalid!\n"+ "×";
        //Xpath for 'Your password is invalid!'
        WebElement actualTextElements = driver.findElement(By.id("flash"));

        String actualMessage = actualTextElements.getText();
        //comparing actualMessage with expectedMessage
        Assert.assertEquals(expectedErrorMessage, actualMessage);
    }
    @After
    public void testDown() {

        closeBrowser();
    }

}
