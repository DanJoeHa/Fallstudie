package fallstudie.exportieren;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * @author Angelos, Philipp Erstellt aus der JTable eine CSV-Datei und legt
 *         diese auf den Desktop(Ordner DatenExport) des Benutzers ab.
 */
public class CSVExport {

	/**
	 * Methode zum exportieren der CSV
	 */
	public static void exportCSV(String[][] contentTable,
			String[] ueberschriften) throws IOException {
		// Vorbereiten des CSV Dateinamens und anlegen des DatenExport Ordners
		// auf dem Desktop
		long milliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy'_'HH.mm.ss");
		Date resultdate = new Date(milliseconds);
		File dir = new File(System.getProperty("user.home")
				+ "\\Desktop\\DatenExport");
		if (!dir.exists())
			dir.mkdir();

		// Erzeugen eines OutputStreams im UTF-8 Format mit dem Pfad zur CSV
		// Datei
		FileOutputStream output = new FileOutputStream(
				System.getProperty("user.home")
						+ "\\Desktop\\DatenExport\\CSVExport"
						+ sdf.format(resultdate) + ".csv");
		output.write(239);
		output.write(187);
		output.write(191);
		String outputEncoding = "UTF-8";
		Charset charsetOutput = Charset.forName(outputEncoding);
		CharsetEncoder encoder = charsetOutput.newEncoder();

		OutputStreamWriter writer1 = new OutputStreamWriter(output, encoder);
		BufferedWriter writer = new BufferedWriter(writer1);

		// Vorbereiten des Arrays zum späteren befüllen der CSV Datei
		int zeilen = contentTable.length;
		int spalten = contentTable[0].length;

		String[][] ueber2D = new String[1][ueberschriften.length];
		ueber2D[0] = ueberschriften;
		String[][] komplettArray = new String[zeilen][spalten];
		komplettArray = ArrayUtils.addAll(ueber2D, contentTable);

		// Schreiben der Einträge in die CSV Datei.
		for (int k = 0; k < komplettArray.length; ++k) {
			for (int l = 0; l < komplettArray[k].length; ++l) {
				String s = komplettArray[k][l] + ";";
				writer.write(s);
			}
			writer.newLine();
		}
		// Schließen des Writers
		writer.close();
		// Öffnen der fertigen CSV Datei.
		File CSV = new File(System.getProperty("user.home")
				+ "\\Desktop\\DatenExport\\CSVExport" + sdf.format(resultdate)
				+ ".csv");
		Desktop.getDesktop().open(CSV);

	}

}
