package creqaExcelHelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelpers {
	private FileInputStream fis;
	private FileOutputStream fileOut;
	private XSSFWorkbook wb;
	private Sheet sh;
	private Cell cell;
	private Row row;
	private CellStyle cellstyle;
	private Color mycolor;
	private String excelFilePath;
	private Map<String, Integer> columns = new HashMap<>();

	public void setExcelFile(String ExcelPath, String SheetName) throws Exception{
		try {
			File f = new File(ExcelPath);
			if (!f.exists()) {
				f.createNewFile();
				System.out.println("File doesn't exist, created a new one.");
			}
			fis = new FileInputStream(ExcelPath);
			wb = new XSSFWorkbook();
			sh = wb.getSheet(SheetName);
			if (sh == null) {
                sh = wb.createSheet(SheetName);
            }
			this.excelFilePath = ExcelPath;

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setCellData(String text, int rownum, int colnum) throws Exception {
        try{
            row  = sh.getRow(rownum);
            if(row ==null)
            {
                row = sh.createRow(rownum);
            }
            cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch(Exception e){
            throw (e);
        }
    }

	
	public void fillAsTable(List<List<String>> tableData) {
		try {
			int counter = 0;
			for(List<String> rows : tableData) {
				for (int i = 0; i < rows.size(); i++) {
					setCellData(rows.get(i), counter, i);
				}
				counter++;
			}
		}catch(Exception e) {
			System.out.println("Error");
		}
	}
}
