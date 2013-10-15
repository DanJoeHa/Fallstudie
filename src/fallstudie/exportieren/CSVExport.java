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
	
	String[][] ueber2D = new String[1][ueberschriften.length];
	ueber2D[0] = ueberschriften;
	String [][] komplettArray = new String[][contentTable.length];
	komplettArray = ArrayUtils.addAll(ueber2D, part);
	
	
	 for (int k = 0; k < contentTable.length; ++k) {
		     for (int l = 0; l < contentTable[k].length; ++l) {
		    	 String s = contentTable[k][l]+";";
		        writer.write(s);
		         }
		     writer.println();
		 }
		 writer.close();
		 File CSV = new File("ExportFiles\\CSVExport.csv");
		    Desktop.getDesktop().open(CSV);
	
	}

}
		 
		         
		        		           


