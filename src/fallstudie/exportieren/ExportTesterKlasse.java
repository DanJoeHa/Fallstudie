package fallstudie.exportieren;

 
import java.awt.print.PrinterException;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
 
public class ExportTesterKlasse {
 
 /**
  * @param args
 * @return 
 * @throws IOException 
 * @throws COSVisitorException 
 * @throws PrinterException 
 * @throws IllegalArgumentException 
  */
	
	

	 
	 public static void main(String[] args) throws IOException, COSVisitorException, IllegalArgumentException, PrinterException
	 {
		 String[][] content = {
				 
	 				{"1","b", "1","D"}, 
	 				{"2","b", "1","D"}, 
	 				{"3","b", "1","D"}, 
	 				{"4","b", "1","D"}, 
	 				{"5","b", "1","D"}, 
	 				{"6","b", "1","D"}, 
	 				{"7","b", "1","D"}, 
	 				{"8","b", "1","D"}, 
	 				{"9","b", "1","D"}, 
	 				{"10","b", "1","D"}, 
	 				{"11","b", "1","D"}, 
	 				{"12","b", "1","D"}, 
	 				{"13","b", "1","D"},
	 				 				
		 };
	

		String[] ueberschriften = {"1","2","3","4","5","6","7","7","7","7","7"};
		String[][] ueber2D = new String[1][ueberschriften.length];
		
		for (int count = 0; count < ueberschriften.length; count++) {
			ueber2D[0][0] = ueberschriften[count];
			}
		
		
	
				CSVExport.exportCSV(content, ueberschriften);
		


			
	//	 PDFDruck.generateTablePDF(content,"Jahresuebersicht",ueberschriften);
	       
	 
	 }
	 
}
	 


   
 
