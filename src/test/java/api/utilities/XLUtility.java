package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	
	public FileOutputStream fos;
	public FileInputStream fis;
	public XSSFWorkbook xlworkbook;
	public XSSFSheet xlsheet;
	public XSSFRow xlrow;
	public XSSFCell xlcell;
	public CellStyle cellStyle;
	String filepath;
	
	XLUtility(String path)
	{
		this.filepath = path;
	}
	
	public int getRowCount(String SheetName) throws IOException 
	{
		fis = new FileInputStream(filepath);
		xlworkbook = new XSSFWorkbook(fis);
		xlsheet = xlworkbook.getSheet(SheetName);
		int rowcount = xlsheet.getLastRowNum();
		xlworkbook.close();
		fis.close();
		return rowcount;
		
	}
	
	public int getColCount(String SheetName, int rownum) throws IOException 
	{
		fis = new FileInputStream(filepath);
		xlworkbook = new XSSFWorkbook(fis);
		xlsheet = xlworkbook.getSheet(SheetName);
		xlrow = xlsheet.getRow(rownum);
		int colcount = xlrow.getLastCellNum();
		xlworkbook.close();
		fis.close();
		return colcount;
		
	}
	
	public String getCellData(String SheetName, int rownum, int colnum) throws IOException
	{
		fis = new FileInputStream(filepath);
		xlworkbook = new XSSFWorkbook(fis);
		xlsheet = xlworkbook.getSheet(SheetName);
		xlrow = xlsheet.getRow(rownum);
		xlcell = xlrow.getCell(colnum);
		
		String data;
		
		DataFormatter Formatter = new DataFormatter();// Required to format cell value 
		
		try
		{
			data = Formatter.formatCellValue(xlcell); // this formats the cell value as string regardless of type of data
		}
		catch(Exception e)
		{ 
			data =" " ;
		}
		xlworkbook.close();
		fis.close();
		return data;
		
	}
	
	
	public void setCellData(String SheetName, int row, int col, String data) throws IOException
	{
		File xlfile = new File(filepath);
		if (!xlfile.exists()) // if file does not exist then create file
		{	
			xlworkbook= new XSSFWorkbook();
			fos = new FileOutputStream(filepath);
			xlworkbook.write(fos);
		}
		fis = new FileInputStream(filepath);
		xlworkbook = new XSSFWorkbook(fis);
		
		//if sheet does not exist create sheet
		
		if(xlworkbook.getSheetIndex(SheetName)==-1)
			xlworkbook.createSheet(SheetName);
		xlsheet = xlworkbook.getSheet(SheetName);
		
		// if row does not exist create row
		
		if (xlsheet.getRow(row)==null)
			xlsheet.createRow(row);
		xlrow = xlsheet.getRow(row);
		
		xlcell = xlrow.createCell(col);
		xlcell.setCellValue(data); // write data to the cell
		
		fos=new FileOutputStream(filepath);
		xlworkbook.write(fos);
		xlworkbook.close();
		fis.close();
		fos.close();
				
	}
	
	public void fillGreenColor(String SheetName, int row, int col) throws IOException
	{
		fis = new FileInputStream(filepath);
		xlworkbook = new XSSFWorkbook(fis);
		xlsheet = xlworkbook.getSheet(SheetName);
		xlrow = xlsheet.getRow(row);
		xlcell = xlrow.getCell(col);
		
		cellStyle = xlworkbook.createCellStyle();
		cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		xlcell.setCellStyle(cellStyle);
		xlworkbook.write(fos);
		xlworkbook.close();
		fis.close();
		fos.close();// have created object
		
	}
	
	
	
	

}
