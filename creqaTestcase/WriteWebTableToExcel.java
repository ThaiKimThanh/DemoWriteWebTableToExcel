package creqaTestcase;

import java.util.List;

import org.testng.annotations.Test;

import creqaBase.BaseSetup;
import creqaExcelHelpers.ExcelHelpers;

public class WriteWebTableToExcel {

	@Test
	public void Demo() throws Exception{
		List<List<String>> tableData = new BaseSetup().getTableData();
		ExcelHelpers excel = new ExcelHelpers();
		try {
        	excel.setExcelFile("D:/Downloads/ratingDemoSheet.xlsx", "Sheet1");
        	excel.fillAsTable(tableData);
        }catch(Exception e) {
        	System.err.println("Error initializing Sheets service ");
        }
		
	}
}
