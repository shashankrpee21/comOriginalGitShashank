package practice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Misc_BrokenImages {

    public static void main(String[] args) {
        // Set up WebDriver (make sure you have the correct WebDriver for your browser)
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage you want to check for broken images
        driver.get("https://practice.expandtesting.com/broken-images"); // Replace with the actual URL

        // Find all image tags
        List<WebElement> images = driver.findElements(By.tagName("img"));

        // Iterate through all image elements
        for (WebElement image : images) {
            String imageUrl = image.getDomAttribute("src");  // Get the image URL

            if (imageUrl != null && !imageUrl.isEmpty()) {
                checkImageStatus(imageUrl);
            }
        }

        // Close the browser
        driver.quit();
    }

    // Method to check the HTTP status of the image URL
    public static void checkImageStatus(String imageUrl) {
        try {
            // Create a URL object for the image URL
            URL url = new URL(imageUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Set a timeout for the connection (optional)
          connection.setConnectTimeout(5000);
          connection.setReadTimeout(5000);

            // Get the response code (e.g., 200 OK, 404 Not Found)
            int responseCode = connection.getResponseCode();

            // If the response code is not 200 (OK), it's a broken image
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Broken image: " + imageUrl + " (Status code: " + responseCode + ")");
            } else {
                System.out.println("Valid image: " + imageUrl + " (Status code: " + responseCode + ")");
            }
        } catch (Exception e) {
            // Handle any exceptions (e.g., malformed URL, connection errors)
            System.out.println("Error checking image URL: " + imageUrl);
            e.printStackTrace();
        }
    }
}
