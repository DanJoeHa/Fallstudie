package fallstudie.exportieren;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class CSVExport {

	public static void exportCSV(String[][] contentTable) throws IOException 
	{PrintWriter writer = new PrintWriter(
			"ExportFiles\\CSVExport.csv");
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
		 
		         
		        		           


