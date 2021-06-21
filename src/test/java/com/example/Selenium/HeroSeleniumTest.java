package com.example.Selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //If there are port conflicts, auto start on a new port.
@Sql(scripts = {"classpath:heroes-schema.sql",
		"classpath:heroes-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class HeroSeleniumTest {
	
	private static WebDriver driver;
	private static WebElement targ;
	
	@BeforeAll
	static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kev30\\eclipse-workspace\\Hobby-Project\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/hero.html");
	}
	
	@Test
	public void testCreate() {
		
		targ = driver.findElement(By.id("addCharacterBtn"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("AddCharacterModal")));
		targ = driver.findElement(By.id("name"));
		targ.click();
		targ.sendKeys("Ganyu");
		
		targ = driver.findElement(By.id("element"));
		targ.click();
		
		targ = driver.findElement(By.xpath("//*[@id=\"element\"]/option[3]"));
		targ.click();
		
		targ = driver.findElement(By.id("weapon"));
		targ.click();
		
		targ = driver.findElement(By.xpath("//*[@id=\"weapon\"]/option[1]"));
		targ.click();
		
		targ = driver.findElement(By.id("level"));
		targ.click();
		
		targ = driver.findElement(By.xpath("//*[@id=\"level\"]"));
		targ.click();
		targ.sendKeys("90");
		
		targ = driver.findElement(By.xpath("//*[@id=\"submitBtn\"]"));
		targ.click();
		
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr[1]")));
		assertEquals("Ganyu", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[2]")).getText());
		assertEquals("Cryo", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[3]")).getText());
		assertEquals("Bow", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[4]")).getText());
		assertEquals("90", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[5]")).getText());
	}
	
	@Test
	public void testRead() {
		
		//Insert heroes-data.sql and schema do not work
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr[1]")));
		
		assertEquals("1", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[1]")).getText());
		assertEquals("Zhongli", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[2]")).getText());
		assertEquals("Geo", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[3]")).getText());
		assertEquals("Polearm", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[4]")).getText());
		assertEquals("90", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[5]")).getText());
	}
	
	@Test
	public void testUpdate() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr")));

		targ = driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/a[1]/button"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("UpdateCharacterModal")));
		
		targ = driver.findElement(By.id("updateName"));
		targ.click();
		targ.clear();
		targ.sendKeys("Eula");
		
		targ = driver.findElement(By.id("updateElement"));
		targ.click();
		
		targ = driver.findElement(By.xpath("//*[@id=\"updateElement\"]"));
		targ.click();
		
		targ = driver.findElement(By.id("updateWeapon"));
		targ.click();
		
		targ = driver.findElement(By.xpath("//*[@id=\"updateElement\"]/option[3]"));
		targ.click();
		
		targ = driver.findElement(By.id("updateLevel"));
		targ.click();
		
		targ = driver.findElement(By.xpath("//*[@id=\"updateLevel\"]"));
		targ.click();
		targ.clear();
		targ.sendKeys("90");
		
		targ = driver.findElement(By.xpath("//*[@id=\"updateBtn\"]"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr")));
		assertEquals("Eula", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[2]")).getText());
		assertEquals("Cryo", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[3]")).getText());
		assertEquals("Claymore", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[4]")).getText());
		assertEquals("90", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[5]")).getText());

	}
	
	@Test
	public void testDelete() {
		targ = driver.findElement(By.xpath("//*[@id=\"deleteBtn\"]"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr")));
		assertTrue(driver.findElements(By.xpath("//*[@id=\\\"mainbody\\\"]/tr")).size() < 1);
	
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
