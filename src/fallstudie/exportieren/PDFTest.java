/*
* ----------------------------------------------------------------------------------------------<br>
* Klassenname: MessungPDF<br>
* ----------------------------------------------------------------------------------------------<br>
* Beschreibung:<br>
* Die Klasse MessungPDF stellt Funktionalität zum Erzeugen eines PDFs aus einer Liste von
* Messung Objekten zur Verfügung. Zur Erzeugung des PDFs bedient sich die Klasse der Standard-Bibliothek itextpdf<br>
*
* @author Dieter Ebhart
* @version 1.0
*/


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFTest {

/**
 * Erzeugt die Überschrift des PDF-Dokuments<br>
 *
 * <br>
 * @exception keine
 * @return keine
 * @param Document: das PDF-Dokume nt für das die Überschrift erzeugt werden soll.
 * @author Dieter Ebhart
 * @version 1.0
 */
	protected void makeListeUeberschrift(Document document,String headline){
		try {
			Font ueberschrift=new Font(FontFamily.TIMES_ROMAN, 12,Font.BOLD);
			Paragraph header=new Paragraph(headline); 
			header.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(header);

		} catch (DocumentException e) {
		}
		
	}

/**
 * Erzeugt die Listendarstellung im PDF<br>
 * Aus den Attributnamen werden die Spaltenüberschriften erzeugt.
 * Die Attributwerte sind die Zeilen in der Tabelle.
 * <br>
 * @exception keine
 * @return keine
 * @param Document: Das PDF-Dokument in dem die Liste erzeugt werden soll.
 *		liste: Liste mit SK_Messung Objekten die in der Tabelle ausgedruckt werden sollen.
 * @author Dieter Ebhart
 * @version 1.0
 */
	
	protected void makeListeBody(Document document, String[][] tabellencontent, String[] headlines){
		try {
			Font text=new Font(FontFamily.TIMES_ROMAN, 8);
			Font cpy=new Font(FontFamily.TIMES_ROMAN, 6);
			Phrase phrase=new Phrase();
			phrase.setFont(text);
			PdfPTable table=new PdfPTable(12);
			table.setWidthPercentage(100);
			PdfPCell cell=new PdfPCell();
			for (int x = 0 ;x<headlines.length;x++)
			{
				phrase=new Phrase(headlines[x], text);
				cell.setPhrase(phrase);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table.addCell(phrase);
			}
			for (int i=0; i<tabellencontent.length;i++){
				for (int a=0; a<tabellencontent[i].length;a++)
				{
				phrase=new Phrase(tabellencontent[i][a], text);
				cell.setPhrase(phrase);
				cell.setBackgroundColor(BaseColor.WHITE);
				table.addCell(cell);
				}
			}
	
			document.add(table);
			

		} catch (Exception e) {
			}
		
	}

/**
 * Erzeugt das PDF als Datei mit dem Dateinamen filename<br>
 * Aus den Attributnamen werden die Spaltenüberschriften erzeugt.
 * Format ist A4 Hochkant.
 * Die Attributwerte sind die Zeilen in der Tabelle.
 * <br>
 * @exception keine
 * @return keine
 * @param filename: Name des PDF-Dokuments.
 *		liste: Liste mit SK_Messung Objekten die in der Tabelle ausgedruckt werden sollen.
 * @author Dieter Ebhart
 * @version 1.0
 */
	
	public static void main(String []args){
		Document document=new Document(PageSize.A4);
		PDFTest a = new PDFTest();
		
		String[][] b ={
				

				{"1","2","3","4"},
				{"1","2","3","4"},
				{"1","2","3","4"},
				{"1","2","3","4"},
				{"1","2","3","4"},
				
				
				
		};
		
		String[] headlines = {
				
				"Hallo","Hallo","Hallo","Hallo"
		};
		try {
			ImportedClasses.com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream("Test.pdf"));
			document.open();
			a.makeListeUeberschrift(document,"Jahresübersicht");
			a.makeListeBody(document, b,headlines);
			
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			}
		
		
	}


}
