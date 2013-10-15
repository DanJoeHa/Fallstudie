package fallstudie.exportieren;

 
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
 
public class PDFWithText {
 
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
	 				{"14","b", "1","D"}, 
	 				{"15","b", "1","D"}, 
	 				{"16","b", "1","D"}, 
	 				{"17","d", "2","D"}, 
	 				{"18","b", "1","D"}, 
	 				{"19","b", "1","D"}, 
	 				{"20","b", "1","D"}, 
	 				{"21","b", "1","D"}, 
	 				{"22","b", "1","D"}, 
	 				{"23","b", "1","D"}, 
	 				{"24","b", "1","D"}, 
	 				{"25","b", "1","D"}, 
	 				{"26","b", "1","D"}, 
	 				{"27","b", "1","D"}, 
	 				{"28","b", "1","D"},
	 				{"29","b", "1","D"}, 
	 				{"30","b", "1","D"}, 
	 				{"31","b", "1","D"}, 
	 				{"32","b", "1","D"}, 
	 				{"33","b", "1","D"},
	 				{"34","b", "1","D"}, 
	 				{"35","b", "1","D"},
	 				{"36","b", "1","D"}, 
	 				{"37","b", "1","D"}, 
	 				{"38","b", "1","D"}, 
	 				{"39","b", "1","D"}, 
	 				{"40","b", "1","D"},
	 				{"41","b", "1","D"},
	 				{"42","b", "1","D"},
	 				{"43","b", "1","D"}, 
	 				{"44","b", "1","D"}, 
	 				{"45","b", "1","D"}, 
	 				{"46","b", "1","D"}, 
	 				{"47","b", "1","D"},
	 				{"48","b", "1","D"},
	 				{"49","b", "1","D"},
	 				{"50","b", "1","D"},
	 				{"51","b", "1","D"},
	 				{"52","b", "1","D"},
	 				{"53","b", "1","D"},
	 				{"54","b", "1","D"},
	 				{"55","b", "1","D"},
	 				{"56","b", "1","D"},
	 				{"57","b", "1","D"},
	 				{"58","b", "1","D"},
	 				{"59","b", "1","D"},
	 				{"60","b", "1","D"},
	 				{"61","b", "1","D"},
	 				{"62","b", "1","D"},
	 				{"63","b", "1","D"},
	 				{"64","b", "1","D"},
	 				{"65","b", "1","D"},
	 				{"66","b", "1","D"},
	 				{"67","b", "1","D"},
	 				{"68","b", "1","D"},
	 				{"69","b", "1","D"},
	 				{"70","b", "1","D"},
	 				{"71","b", "1","D"},
	 				{"72","b", "1","D"},
	 				{"73","b", "1","D"},
	 				{"74","b", "1","D"},
	 				{"75","b", "1","D"},
	 				{"LETZTE","b", "1","D"},

	 				
	 				
		 };
	

		
CSVExport.exportCSV(content);
		/*
		 	drawTable.generateTablePDF(content,"Jahresuebersicht");
	        // uses the corect path separator for the OS
	        File videos = new File("C:\\Users\\Phil\\Documents\\Uni Zeug\\Java Sachen\\eclipse\\Java\\PDFTest\\pdf\\test.pdf");
	        Desktop.getDesktop().open(videos);
	 */
	 }
	 
}
	 


   
 
