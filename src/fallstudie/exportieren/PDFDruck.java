package fallstudie.exportieren;
/**
 * @param page
 * @param contentStream
 * @param y the y-coordinate of the first row
 * @param margin the padding on left and right of table
 * @param content a 2d array containing the table data
 * @throws IOException
 */
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
public class PDFDruck
{
	
	public PDFDruck()
	{
		
	}
public static void drawTablePDF(PDPage page, PDPageContentStream contentStream, 
                            float y, float margin, 
                            String[][] content) throws IOException {
	final int rows = content.length;
    final int cols = content[0].length;
    final float rowHeight = 20f;
    final float tableWidth = page.getMediaBox().getWidth() -margin -margin;
    final float tableHeight = rowHeight * rows;
    final float colWidth = tableWidth/(float)cols;
    final float cellMargin=5f;
    

    
    //draw the rows
    float nexty = y ;
    for (int i = 0; i <= rows; i++) {
        contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
        nexty-= rowHeight;
    }
    //draw the columns
    float nextx = margin;
    for (int i = 0; i <= cols; i++) {
        contentStream.drawLine(nextx, y, nextx, y-tableHeight);
        nextx += colWidth;
    }

    //now add the text        
    contentStream.setFont( PDType1Font.HELVETICA_BOLD , 9 );        

    float textx = margin+cellMargin;
    float texty = y-15;        
    for(int i = 0; i < content.length; i++){
        for(int j = 0 ; j < content[i].length; j++){
            String text = content[i][j];
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(textx,texty);
            contentStream.drawString(text);
            contentStream.endText();
            textx += colWidth;
        }
        texty-=rowHeight;
        textx = margin+cellMargin;
    }
}
public static void generateTablePDF(String[][] TabellenContent, String PDFUeberschrift, String[] ueberschriften) 
									throws IOException, COSVisitorException, IllegalArgumentException, PrinterException {
	 
	int zeilen = TabellenContent.length;
	int spalten = TabellenContent[0].length;
	//Neues Doc
	PDDocument doc = new PDDocument();
	//Uebrschriften Setzen
	
		
	String[][] ueber2D = new String[1][ueberschriften.length];
	ueber2D[0] = ueberschriften;
	
	
	
	int a=33;
	int i= 0;
	int zeilenubrig=zeilen;
	int zahler=0;
		do{

			if(zeilenubrig>35){
			a=i+33;
			}
			else{
				a=i+zeilenubrig;
			}
		PDPage page = new PDPage();
		doc.addPage( page );
		
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);
		String[][] part = new String[35][spalten]; 
		
		part = Arrays.copyOfRange(TabellenContent, i, a);
		String [][] komplettArray = new String[36][spalten];
		
		
		if(zahler!=0)
		{
		komplettArray = ArrayUtils.addAll(ueber2D, part);
			
		PDFDruck.drawTablePDF(page, contentStream, 720,10, komplettArray);
		 //Überschrift
					 contentStream.beginText();
					 contentStream.setNonStrokingColor(0, 0, 255);
					 contentStream.setFont( PDType1Font.HELVETICA_BOLD_OBLIQUE, 11 ); 
					 contentStream.moveTextPositionByAmount(10, 732);
					 contentStream.drawString(PDFUeberschrift);
					 contentStream.endText();
		 //Überschrift
					 contentStream.close();
		}
		else
		{
			komplettArray = ArrayUtils.addAll(ueber2D, part);
			
			PDFDruck.drawTablePDF(page, contentStream, 720,10, komplettArray);
			 //Überschrift
						 contentStream.beginText();
						 contentStream.setNonStrokingColor(0, 0, 255);
						 contentStream.setFont( PDType1Font.HELVETICA_BOLD_OBLIQUE, 11 ); 
						 contentStream.moveTextPositionByAmount(10, 732);
						 contentStream.drawString(PDFUeberschrift);
						 contentStream.endText();
			 //Überschrift
						 contentStream.close();
						 zahler=1;
		}
		zeilenubrig = zeilen-a; 
		System.out.println(zeilenubrig);
		i=a;
		}
		while(zeilenubrig!=0);
	    long milliseconds = System.currentTimeMillis(); 
	    SimpleDateFormat sdf = new SimpleDateFormat( "dd.MM.yyyy'_'HH.mm.ss" ); 
		Date resultdate = new Date(milliseconds);
		File dir = new File(System.getProperty("user.home")+ "\\Desktop\\PDF aus Stippler");
		if(!dir.exists())dir.mkdir();
		
	doc.save(System.getProperty("user.home")+ "\\Desktop\\PDF aus Stippler\\PDFExport_"+sdf.format(resultdate)+".pdf");
	doc.close();
	File PDF = new File(System.getProperty("user.home")+ "\\Desktop\\PDF aus Stippler\\PDFExport_"+sdf.format(resultdate)+".pdf");
    Desktop.getDesktop().open(PDF);

}
}