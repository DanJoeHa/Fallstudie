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
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class TabelleView extends JPanel implements View {
	private JTable TA_Tabelle;
	private JButton B_AuswaehlenLoeschen;
	private JButton B_Abbrechen;
	
	/**
	 * Create the panel.
	 */
	public TabelleView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		B_AuswaehlenLoeschen = new JButton("Löschen");
		B_AuswaehlenLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_AuswaehlenLoeschen.setBounds(440, 600, 150, 30);
		add(B_AuswaehlenLoeschen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 100, 520, 480);
		add(scrollPane);
		
		TA_Tabelle = new JTable();
		TA_Tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(TA_Tabelle);
		TA_Tabelle.setBorder(new LineBorder(new Color(0, 0, 0)));
		//TA_Tabelle.setEnabled(false);
		TA_Tabelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		B_Abbrechen = new JButton("Abbrechen");
		B_Abbrechen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Abbrechen.setBounds(30, 600, 150, 30);
		add(B_Abbrechen);
		
		
	}
	
	public void setButtonName(String buttonbezeichnung){
		this.B_AuswaehlenLoeschen.setText(buttonbezeichnung);
	}
	
	public String getAuswahl(){
		int row = TA_Tabelle.getSelectedRow();
		String auswahl = TA_Tabelle.getValueAt(row, 0).toString();
		System.out.println(auswahl);
		return auswahl;
	}
	
	//Tabelle befüllen
	public void setTabelle(String[] tabellenheadline,Object[][] tabellenwerte){
		
		
		
		
		
		
		//for(int i = 0; i < tabellenwerte.length; i ++){
		//		System.out.println("bla");
		//		tabellenwerte[i][6] = new JRadioButton("A");
		//		System.out.println("blub");
		//}
		
		
		
		TA_Tabelle.setModel(new DefaultTableModel(tabellenwerte, tabellenheadline)
		{
			public boolean isCellEditable(int x, int y) {
                return false;
            }
		}
				);
		
		
		
		
		
	}
	
	@Override
	public void setController(Controller c) {
		// TODO Auto-generated method stub
		this.B_Abbrechen.addActionListener(c);
		this.B_AuswaehlenLoeschen.addActionListener(c);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}
	
	
}