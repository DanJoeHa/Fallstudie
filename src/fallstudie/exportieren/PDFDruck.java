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
import java.util.Arrays;

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
    contentStream.setFont( PDType1Font.HELVETICA_BOLD , 12 );        

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
public static void generateTablePDF(String[][] TabellenContent, String PDFUeberschrift) 
									throws IOException, COSVisitorException, IllegalArgumentException, PrinterException {
	 
	int zeilen = TabellenContent.length;
	int spalten = TabellenContent[0].length;
	//Neues Doc
	PDDocument doc = new PDDocument();
	//Uebrschriften Setzen
	
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
		
		
		System.out.println("i: "+i+"a: "+a);
		part = Arrays.copyOfRange(TabellenContent, i, a);
		String [][] komplettArray = new String[36][spalten];
		String [][] tabellenUeberschrift = new String[1][spalten];
		tabellenUeberschrift[0] = TabellenContent[0];
		if(zahler!=0)
		{
		komplettArray = ArrayUtils.addAll(tabellenUeberschrift, part);
		
		
		PDFDruck.drawTablePDF(page, contentStream, 720,10, komplettArray);
		 //Überschrift
					 contentStream.beginText();
					 contentStream.setNonStrokingColor(0, 0, 255);
					 contentStream.setFont( PDType1Font.HELVETICA_BOLD_OBLIQUE, 15 ); 
					 contentStream.moveTextPositionByAmount(250, 732);
					 contentStream.drawString(PDFUeberschrift);
					 contentStream.endText();
		 //Überschrift
					 contentStream.close();
		}
		else
		{
			komplettArray = ArrayUtils.addAll(tabellenUeberschrift, part);
			
			
			PDFDruck.drawTablePDF(page, contentStream, 720,10, part);
			 //Überschrift
						 contentStream.beginText();
						 contentStream.setNonStrokingColor(0, 0, 255);
						 contentStream.setFont( PDType1Font.HELVETICA_BOLD_OBLIQUE, 15 ); 
						 contentStream.moveTextPositionByAmount(250, 732);
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

	doc.save("ExportFiles\\PDFExport.pdf");
	doc.close();
	File PDF = new File("ExportFiles\\PDFExport.pdf");
    Desktop.getDesktop().open(PDF);

}
}