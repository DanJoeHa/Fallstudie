package fallstudie.view.impl;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

import javax.swing.JComboBox;

public class TabelleView extends JPanel implements View {

	private static final long serialVersionUID = 1L;
	private JTable TA_Tabelle;
	private JButton B_AuswaehlenLoeschen;
	private JButton B_Abbrechen;
	private JComboBox<String> C_DrillDown;
	private JButton B_DrillDown;
	
	
	/**
	 * Create the panel.
	 */
	public TabelleView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		B_AuswaehlenLoeschen = new JButton("Löschen");
		B_AuswaehlenLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_AuswaehlenLoeschen.setBounds(440, 600, 150, 30);
		add(B_AuswaehlenLoeschen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 50, 560, 540);
		add(scrollPane);
		
		TA_Tabelle = new JTable();
		TA_Tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(TA_Tabelle);
		TA_Tabelle.setBorder(new LineBorder(new Color(0, 0, 0)));
		//TA_Tabelle.setEnabled(false);
		TA_Tabelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TA_Tabelle.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION); 
		
		B_Abbrechen = new JButton("Abbrechen");
		B_Abbrechen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Abbrechen.setBounds(30, 600, 150, 30);
		add(B_Abbrechen);
		
		C_DrillDown = new JComboBox<String>();
		C_DrillDown.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_DrillDown.setBounds(380, 13, 100, 30);
		C_DrillDown.setVisible(false);
		add(C_DrillDown);
		
		B_DrillDown = new JButton("DrillDown");
		B_DrillDown.setBounds(500, 13, 90, 30);
		B_DrillDown.setVisible(false);
		add(B_DrillDown);
		
		
	}
	/**
	 * Button umbenennen
	 * @param Buttonbezeichnung
	 */
	public void setButtonName(String buttonbezeichnung){
		this.B_AuswaehlenLoeschen.setText(buttonbezeichnung);
	}
	/**
	 * Ausgewählte Zeile der Tabelle
	 * @return auswahl
	 */
	public String getAuswahl(){
		int row = TA_Tabelle.getSelectedRow();
		if(row == -1)
		{
			return "";
		}
		else
		{
			String auswahl = TA_Tabelle.getValueAt(row, 0).toString();
			return auswahl;	
		}
	}
	
	//Tabelle befüllen
	/**
	 * Tabelle befüllen
	 * @param tabellenheadline
	 * @param tabellenwerte
	 */
	public void setTabelle(String[] tabellenheadline,Object[][] tabellenwerte){
		
		TA_Tabelle.setModel(new DefaultTableModel(tabellenwerte, tabellenheadline){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int x, int y) {
                return false;
            }
		});	
		
	}
	/**
	 * Button und ComboBox für DrillDown Möglichkeit anzeigen
	 * @param visible
	 */
	public void setDrillDown(boolean visible){
		this.C_DrillDown.setVisible(visible);
		this.B_DrillDown.setVisible(visible);
	}
	/**
	 * DrillDown AuswahlBox mit Bereichen füllen
	 * @param bereiche
	 */
	public void setBereiche(String[] bereiche){
		for( int i = 0; i < bereiche.length; i++){
			this.C_DrillDown.addItem( bereiche[i] );
		}
		
	}
	/**
	 * ausgewählter Bereich aus DrillDown
	 * @return DrillDown Auswahl
	 */
	public String getDrillDownBereich(){
		return this.C_DrillDown.getSelectedItem().toString();
	}
	
	@Override
	public void setController(Controller c) {
		this.B_Abbrechen.addActionListener(c);
		this.B_AuswaehlenLoeschen.addActionListener(c);
		this.B_DrillDown.addActionListener(c);
		this.TA_Tabelle.addMouseListener((MouseListener) c);
	}

	@Override
	public void reset() {
		this.TA_Tabelle.removeAll();
	}
}