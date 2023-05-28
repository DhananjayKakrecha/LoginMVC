import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginModuleTest {
    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dhananjay\\Desktop\\chromedriver_win32\\chromedriver.exe");

        // Create an instance of ChromeDriver
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Navigate to the login page
        driver.get("http://localhost:8080/LoginMVC/login.jsp");

        // Find the username and password input fields
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        // Enter the credentials
        usernameInput.sendKeys("user1");
        passwordInput.sendKeys("password1");

        // Find and click the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Wait for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the login result
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("http://localhost:8080/LoginMVC/success.jsp")) {
            System.out.println("Login successful!");
        } else if (currentUrl.equals("http://localhost:8080/LoginMVC/error.jsp")) {
            System.out.println("Login failed!");
        } else {
            System.out.println("Unknown page!");
        }

        // Close the browser
        driver.quit();
    }
}
