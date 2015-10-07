import java.io.IOException;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.util.List;  
import java.util.concurrent.TimeUnit;  

import org.openqa.selenium.*;  
import org.openqa.selenium.firefox.FirefoxDriver;  
  
public class BrokenLinks {  
  
    private static int statusCode;  
  
    public static void main(String[] args) throws IOException {  
       // Initialize web driver    
        WebDriver db = new FirefoxDriver();  
       //Maximize browser window  
        db.manage().window().maximize();  
       //Go to URL    
        db.get("http://m.radionow1057.com");  
       //Set  timeout   
    db.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  

    // Get all links web driver  
 List<org.openqa.selenium.WebElement> links = db.findElements(By.tagName("a"));  
  
        for (int i = 0; i < links.size(); i++) {  
//remove null and empty links  
if (!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))) {  
   
 if (links.get(i).getAttribute("href").contains("http://m")) {  
 // Find HTTP Status-Code  
	 
statusCode= getResponseCode(links.get(i).getAttribute("href").trim());  
// Check broken link  


if (statusCode== 404) 

{  
	System.out.println("Broken of Link# "+i+" "
		+ ""+links.get(i).getAttribute("href"));  
                    }  
                }  
  
            }  
        }  
  
        db.close();  
    }  
  
public static int getResponseCode(String urlString) throws MalformedURLException, IOException {         
        URL u = new URL(urlString);  
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();  
        huc.setRequestMethod("GET");  
        huc.connect();  
        return huc.getResponseCode();  
  }  
}  