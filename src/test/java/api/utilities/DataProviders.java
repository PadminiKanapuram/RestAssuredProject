package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Dataprovider method is responsible to get data from excel and store in two dimensional array
	
	@DataProvider(name="getData")
	public String[][] getAllData() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//userTestData.xlsx";
		
		XLUtility xlutility= new XLUtility(path);
		int row = xlutility.getRowCount("Sheet1");
		int col = xlutility.getColCount("Sheet1", 1);
		
		String data[][]= new String[row][col];
		int i,j;
		for(i=1;i<=row;i++)
		{
			for(j=0;j<col;j++)
			{
				data[i-1][j]=xlutility.getCellData("Sheet1", i, j);
			}
		}
		return data;	
	}
	
	
	@DataProvider(name="getUserNames")
	public String[] getUserNames() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//userTestData.xlsx";
		
		XLUtility xlutility= new XLUtility(path);
		int row = xlutility.getRowCount("Sheet1");
				
		String data[]= new String[row];
		int i;
		for(i=1;i<=row;i++)
		{
			data[i-1]=xlutility.getCellData("Sheet1", i, 1);
			}

		return data;	
	}
	
	
	
	
	

}
