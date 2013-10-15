package fallstudie.exportieren;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang3.ArrayUtils;


public class CSVExport {

	public static void exportCSV(String[][] contentTable, String[] ueberschriften) throws IOException 
	{PrintWriter writer = new PrintWriter(
			"ExportFiles\\CSVExport.csv");
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
		     writer.println();
		 }
		 writer.close();
		 File CSV = new File("ExportFiles\\CSVExport.csv");
		    Desktop.getDesktop().open(CSV);
	
	}

}
		 
		         
		        		           


