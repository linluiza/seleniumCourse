package br.ce.selenium.seubarriga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class TesteGoogle 
{
	@Test
    public void teste()
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
        
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
}
