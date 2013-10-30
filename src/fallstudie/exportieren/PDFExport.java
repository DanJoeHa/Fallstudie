package fallstudie.exportieren;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
/**
 * 
 * @author Phil,Angelos,Dieter Ebhard
 * @date 29.10.2013
 * Erzeugt eine PDF mithilfe der iText-Bibliothek.
 */
public class PDFExport {
	/**
	 * @author Phil,Angelos,Dieter Ebhard
	 * Erzeuge anhand der Übergabeparameter die PDF-Tabelle und speichert sie im Ordner 
	 * Desktop\Export_Stippler\PDF
	 * @param String [][] tabellencontent (Inhalt der Tabelle)
	 * @param String [] headlines (Tabellenüberschriften)
	 * @param String dokumentUeberschrift (Dokumentüberschrift des PDFs=
	 * @throws DocumentException, wenn das PDF nicht erstellt werden kann (bereits geöffnet/verwendet).
	 * @throws IOException, wenn die PDF nicht geöffnet werden kann durch die Methode.
	 */
	public static void generateTablePDF(String[][] tabellencontent,
			String[] headlines, String dokumentUeberschrift) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4.rotate());

		long milliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy'_'HH.mm.ss");
		Date resultdate = new Date(milliseconds);
		File dir = new File(System.getProperty("user.home")
				+ "\\Desktop\\Export_Stippler");
		
		File dir2 = new File(System.getProperty("user.home")
				+ "\\Desktop\\Export_Stippler\\PDF");
		
		if (!dir.exists())
				{
					dir.mkdir();
				}
		if(!dir2.exists())
		{
			dir2.mkdir();
		}

		PdfWriter.getInstance(
				document,
				new FileOutputStream(System.getProperty("user.home")
						+ "\\Desktop\\Export_Stippler\\PDF\\PDFExport_"
						+ sdf.format(resultdate) + ".pdf"));
		document.open();

		makeListeUeberschrift(document, dokumentUeberschrift);
		makeListeBody(document, tabellencontent, headlines);

		document.close();

		File PDF = new File(System.getProperty("user.home")
				+ "\\Desktop\\Export_Stippler\\PDF\\PDFExport_"
				+ sdf.format(resultdate) + ".pdf");
		Desktop.getDesktop().open(PDF);
	}
	/**
	 * @author Phil,Angelos,Dieter Ebhard
	 * Methodes schreibt die Dokumentüberschrift auf dem PDF-Dokument
	 * @param Document document (Das erzeugte PDF Dokument)
	 * @param String headline (Die Dokumentüberschrift)
	 */
	private static void makeListeUeberschrift(Document document, String headline) {
		try {
			Font ueberschrift = FontFactory.getFont("Times-Roman", 12);
			Paragraph header = new Paragraph(headline);
			Paragraph header2 = new Paragraph(" ");
			header.setFont(ueberschrift);
			header.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(header);
			document.add(header2);

		} catch (DocumentException e) {
		}

	}
	/**
	 * @author Phil,Angelos,Dieter Ebhard
	 * Methode erzeugt die Tabelle und befüllt sie mit Inhalt aus den Übergabeparametern.
	 * @param Document document (Das erzeugte PDF Dokument)
	 * @param String [][] tabellencontent (Der Inhalt der Jtable als String Array)
	 * @param String [] headlines (Spaltenüberschriften der JTable als String Array)
	 */
	private static void makeListeBody(Document document, String[][] tabellencontent,
			String[] headlines) {
		try {
			Font text = FontFactory.getFont("Times-Roman", 8);
			
			Font ueberschrift = FontFactory.getFont("Times-Roman", 10);
			ueberschrift.setColor(Color.BLACK);
			Phrase phrase = new Phrase();
			phrase.setFont(text);
			PdfPTable table = new PdfPTable(headlines.length);
			table.setWidthPercentage(105);
			PdfPCell cell = new PdfPCell();
			
			Color colr = new Color(176,226,255);
			cell.setBackgroundColor(colr);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			for (int x = 0; x < headlines.length; x++) {
				
				phrase = new Phrase(headlines[x], ueberschrift);
				
				cell.setBackgroundColor(colr);
				cell.setPhrase(phrase);
				table.addCell(cell);

			}
			
			for (int i = 0; i < tabellencontent.length; i++) {
				
				for (int a = 0; a < tabellencontent[i].length; a++) {
					
					if(a==0)
					{
						cell.setBackgroundColor(colr);
						phrase=new Phrase(tabellencontent[i][a],ueberschrift);
						
					}
					else
					{	cell.setBackgroundColor(Color.WHITE);
						phrase = new Phrase(tabellencontent[i][a], text);
					}
					
					cell.setPhrase(phrase);
					table.addCell(cell);
				}
				
			}

			document.add(table);

		} catch (Exception e) {
		}

	}

}
