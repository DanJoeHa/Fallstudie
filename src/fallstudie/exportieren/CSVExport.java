package fallstudie.exportieren;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;


public class CSVExport {

	public static void exportCSV(String[][] contentTable, String[] ueberschriften) throws IOException 
	{
	    long milliseconds = System.currentTimeMillis(); 
	    SimpleDateFormat sdf = new SimpleDateFormat( "dd.MM.yyyy'_'HH.mm.ss" ); 
		Date resultdate = new Date(milliseconds);
		File dir = new File(System.getProperty("user.home")+ "\\Desktop\\DatenExport");
		if(!dir.exists())dir.mkdir();
		FileOutputStream output = new FileOutputStream(
			System.getProperty("user.home")+ "\\Desktop\\DatenExport\\CSVExport"+sdf.format(resultdate)+".csv");
		
		output.write(239);
		output.write(187);
		output.write(191);
		String outputEncoding = "UTF-8";
		
		Charset charsetOutput = Charset.forName(outputEncoding);
		CharsetEncoder encoder = charsetOutput.newEncoder();
		OutputStreamWriter writer1 = new OutputStreamWriter(output,encoder);
		BufferedWriter writer = new BufferedWriter(writer1);
		
		int zeilen = contentTable.length;
	int spalten = contentTable[0].length;
	
	String[][] ueber2D = new String[1][ueberschriften.length];
	ueber2D[0] = ueberschriften;
	String [][] komplettArray = new String[zeilen][spalten];
	komplettArray = ArrayUtils.addAll(ueber2D, contentTable);
	
	
	 for (int k = 0; k < komplettArray.length; ++k) {
		     for (int l = 0; l < komplettArray[k].length; ++l) {
		    	 String s = komplettArray[k][l]+";";
		        writer.write(s);
		         }
		     writer.newLine();
		 }
		 writer.close();
		 File CSV = new File(System.getProperty("user.home")+ "\\Desktop\\DatenExport\\CSVExport"+sdf.format(resultdate)+".csv");
		    Desktop.getDesktop().open(CSV);
	
	}

}
		 
		         
		        		           


