package ExcelConnection;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConnection2 {
	
	FileInputStream FIS;
	XSSFWorkbook xBook;
	XSSFSheet xSheet;
	
	
	
	public ExcelConnection2(String FilePath) {
		File xFile = new File(FilePath);
		try {
			FIS = new FileInputStream(xFile);
			xBook= new XSSFWorkbook(FIS);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		}
	public String[][] getExcelConnection(int SheetNum)
	{
		xSheet= xBook.getSheetAt(SheetNum);
		
		int Row_Num = xSheet.getLastRowNum();
		int cal_num=xSheet.getRow(0).getLastCellNum();
		String[][] xData= new String[Row_Num][cal_num];
		
		for(int i=0; i<Row_Num;i++) {
			XSSFRow Row = xSheet.getRow(i+1);
			for(int j=0; j<cal_num;j++) {
				XSSFCell xCell=Row.getCell(j);
				String value =ValueChange(xCell);
				xData[i][j]= value;
						
			}
		}
		
		return xData;
	}
	private String ValueChange(XSSFCell xCell) {
		Object xValue;
		String Result = null;
		if(xCell == null)
		{
			Result ="";
		}
		else
		{
			if(xCell.getCellTypeEnum()==CellType.STRING) {
				Result = xCell.getStringCellValue();
			}
			else if(xCell.getCellTypeEnum()==CellType.NUMERIC) {
				xValue =(int) xCell.getNumericCellValue();
				Result = xValue.toString();
			}
		}
		try {
			FIS.close();
			xBook.close();
		} catch (Exception e) {
			
		}
		return Result;
	}

}
