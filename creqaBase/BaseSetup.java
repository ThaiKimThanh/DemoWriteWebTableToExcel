package creqaBase;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseSetup {
		
		static String driverPath = "C:/Program Files (x86)/";
		
		public List<List<String>> getTableData(){
			return scrapeTableData();
		}

		private static List<List<String>> scrapeTableData() {
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://ratings.fide.com/topfed.phtml?ina=1&country=AUS");
			// Locate the table
	        WebElement table = driver.findElement(By.xpath("//table//table//table//table[2]//tbody"));
	        // Extract rows and columns
	        List<WebElement> rows = table.findElements(By.xpath("//table//table//table//table[2]//tbody//tr"));
	        List<List<String>> tableData = new ArrayList<>();
	        for (int i = 1; i<=rows.size(); i++) {
	        	List<WebElement> cells = table.findElements(By.xpath("//table//table//table//table[2]//tbody//tr["+i+"]//td")); // Use "th" for headers
	            List<String> rowData = new ArrayList<>();
	        	if (i > 1) 
	        	{
		        	String rating = table.findElement(By.xpath("//table//table//table//table[2]//tbody//tr["+i+"]//td[5]")).getText();
		        	int ratingVal = Integer.parseInt(rating.trim());
		        	if (ratingVal < 2500) {
		        		continue;
		        	}
		        	else {
		        		for (WebElement cell : cells) {
		                    rowData.add(cell.getText());
		                }
		                tableData.add(rowData);
		        	}
	        	}
		        else {
		        	for (WebElement cell : cells) {
		                rowData.add(cell.getText());
		            }
		            tableData.add(rowData);
		        }
	        }
	        driver.quit();
	        return tableData;
		}

}
