package fallstudie.exportieren;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class CSVExport {

	public static void exportCSV(String[][] contentTable) throws FileNotFoundException, UnsupportedEncodingException 
	{PrintWriter writer = new PrintWriter(
    		"C:\\test1.csv");
	 for (int k = 0; k < contentTable.length; ++k) {
		     for (int l = 0; l < contentTable[k].length; ++l) {
		    	 String s = contentTable[k][l]+";";
		        writer.write(s);
		         }
		     writer.println();
		 }
		 writer.close();
	}
}
		 
		         
		        		           


