package fallstudie.exportieren;

 
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
 
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
				 {"SC1","SC1","SC1","SC1","SC1","SC1"},
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
	

		
				CSVExport.exportCSV(content);
		 		
		
		 	PDFDruck.generateTablePDF(content,"Jahresuebersicht");
	       
	 
	 }
	 
}
	 


   
 
