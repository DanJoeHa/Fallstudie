package fallstudie.view.impl;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class DatenAnzeigenArbeitsgruppeView extends JPanel {
	private JTable TA_ArbeitsgruppeTabelle;

	/**
	 * Create the panel.
	 */
	public DatenAnzeigenArbeitsgruppeView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		JButton B_Drucken = new JButton("Drucken");
		B_Drucken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Drucken.setBounds(440, 600, 150, 30);
		add(B_Drucken);
		
		JLabel lblDatenVonKwkj = new JLabel("Daten f\u00FCr Kalenderwoche __ im Kalenderjahr ____");
		lblDatenVonKwkj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatenVonKwkj.setBounds(30, 50, 520, 30);
		add(lblDatenVonKwkj);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 100, 520, 480);
		add(scrollPane);
		
		TA_ArbeitsgruppeTabelle = new JTable();
		TA_ArbeitsgruppeTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(TA_ArbeitsgruppeTabelle);
		TA_ArbeitsgruppeTabelle.setBorder(new LineBorder(new Color(0, 0, 0)));
		TA_ArbeitsgruppeTabelle.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Art", "AG1", "New column", "New column", "Summe"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		TA_ArbeitsgruppeTabelle.getColumnModel().getColumn(0).setResizable(false);
		TA_ArbeitsgruppeTabelle.getColumnModel().getColumn(1).setResizable(false);
		TA_ArbeitsgruppeTabelle.getColumnModel().getColumn(2).setResizable(false);
		TA_ArbeitsgruppeTabelle.getColumnModel().getColumn(3).setResizable(false);
		TA_ArbeitsgruppeTabelle.getColumnModel().getColumn(4).setResizable(false);
		TA_ArbeitsgruppeTabelle.setFont(new Font("Tahoma", Font.PLAIN, 11));

	}
}