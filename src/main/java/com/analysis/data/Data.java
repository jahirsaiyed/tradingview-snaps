package com.analysis.data;

import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.csv.CsvConfiguration;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.excel.ExcelConfiguration;
import org.apache.metamodel.query.SelectItem;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Data {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger( "Data" );

	public static Object[][] getExcelData( File excelFile, String sheetName ) 
	{	
		ExcelConfiguration conf = new ExcelConfiguration( 1, true, false );
		DataContext dataContext = DataContextFactory.createExcelDataContext( excelFile, conf );
		DataSet dataSet = dataContext.query()
				.from( sheetName )
				.selectAll()
//				.where("run").eq("Y")
				.execute();
		List<Row> rows = dataSet.toRows();
		Object[][] myArray = get2ArgArrayFromRows( rows );
		return myArray;
	}
	
	/**
	 * Gets a 2D Object array from a List of Row objects that is only 2 args wide.
	 * @param rows
	 * @return
	 */
	public static Object[][] get2ArgArrayFromRows( List<Row> rows ) {
		Object[][] myArray = new Object[rows.size()][2];
		int i = 0;
		SelectItem[] cols = rows.get(0).getSelectItems();
		for ( Row r : rows ) {
			Object[] data = r.getValues();
			for ( int j = 0; j < cols.length; j++ ) {
				if ( data[j] == null ) data[j] = ""; // force empty string where there are NULL values
			}
			myArray[i][0] = cols;
			myArray[i][1] = data;
			i++;
		}
		logger.info( "Row count: " + rows.size() );
		logger.info( "Column names: " + Arrays.toString( cols ) );
		return myArray;
	}

	public static Object[][] getCsvData( File csvFile ) 
	{	
		CsvConfiguration conf = new CsvConfiguration( 1 );
		DataContext csvContext = DataContextFactory.createCsvDataContext( csvFile, conf );
		Schema schema = csvContext.getDefaultSchema();
		Table[] tables = schema.getTables();
		Table table = tables[0]; // a representation of the csv file name including extension
		DataSet dataSet = csvContext.query()
				.from( table )
				.selectAll()
//				.where("run").eq("Y")
				.execute();
		List<Row> rows = dataSet.toRows();
		Object[][] myArray = get2ArgArrayFromRows( rows );
		return myArray;
	}
	
}
