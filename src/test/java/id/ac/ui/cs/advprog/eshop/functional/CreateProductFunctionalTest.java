package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;
    private String productListPageUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        productListPageUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        String dummyName = "Granger Prawono";
        int dummyQty = 3;

        driver.get(baseUrl);

        WebElement inputName = driver.findElement(By.id("nameInput"));
        inputName.clear();
        inputName.sendKeys(dummyName);

        WebElement inputQty = driver.findElement(By.id("quantityInput"));
        inputQty.clear();
        inputQty.sendKeys(String.valueOf(dummyQty));

        WebElement buttonSubmit = driver.findElement(By.id("submitCreate"));
        buttonSubmit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set a timeout of 10 seconds

        assertEquals(productListPageUrl, driver.getCurrentUrl());

        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + dummyName + "')]")));
        WebElement productQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + dummyQty + "')]")));

        assertTrue(productName.isDisplayed());
        assertTrue(productQuantity.isDisplayed());
        assertEquals(productName.getText(), dummyName);
        assertEquals(productQuantity.getText(), String.valueOf(dummyQty));
    }
}
