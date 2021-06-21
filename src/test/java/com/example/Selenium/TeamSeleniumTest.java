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
@Sql(scripts = {"classpath:teams-schema.sql",
		"classpath:teams-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class TeamSeleniumTest {
	
	private static WebDriver driver;
	private static WebElement targ;
	
	@BeforeAll
	static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kev30\\eclipse-workspace\\Hobby-Project\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/team.html");
	}
	
	@Test
	public void testCreate() {
		
		targ = driver.findElement(By.xpath("//*[@id=\"addTeamBtn\"]"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("AddTeamModal")));
		targ = driver.findElement(By.id("teamName"));
		targ.click();
		targ.sendKeys("Team Smoke");
		
		targ = driver.findElement(By.xpath("//*[@id=\"submitBtn\"]"));
		targ.click();
		
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr")));
		assertEquals("Team Smoke", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[2]")).getText());

	}
	
	@Test
	public void testRead() {
		
		//Insert heroes-data.sql and schema do not work
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr")));
		
		assertEquals("Team Smoke", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[2]")).getText());
	}
	
	@Test
	public void testUpdate() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr")));

		targ = driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/a[2]/button"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("UpdateTeamModal")));
		
		targ = driver.findElement(By.id("updateTeamName"));
		targ.click();
		targ.clear();
		targ.sendKeys("Team we da best");
		
		
		targ = driver.findElement(By.xpath("//*[@id=\"updateBtn\"]"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainbody\"]/tr")));
		assertEquals("Team we da best", driver.findElement(By.xpath("//*[@id=\"mainbody\"]/tr/td[2]")).getText());
	}
	
	@Test
	public void testDelete() {
		targ = driver.findElement(By.xpath("//*[@id=\"deleteBtn\"]"));
		targ.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\\\"mainbody\\\"]")));
		assertTrue(driver.findElements(By.xpath("//*[@id=\"mainbody\"]")).size() < 1);
	
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
