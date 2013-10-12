package fallstudie.view.impl;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionListener;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
import java.awt.Font;

public class HauptView extends JFrame implements View{

	private static final long serialVersionUID = 5459006429059912338L;
	private JButton B_Logout, B_Hilfe;
	private JTextArea InfoBox;
	private JTree Navigation;
	private JLabel L_Ueberschrift;
	private JPanel arbeitsbereich;

	public HauptView() {
		//Hauptfenster
		this.setResizable(false);
		this.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.setSize(new Dimension(1000, 750));
		this.setBounds(100, 100, 1000, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		
		//Navigationsbereich
		JPanel navigationsbereich = new JPanel();
		navigationsbereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		navigationsbereich.setPreferredSize(new Dimension(190, 700));
		navigationsbereich.setMinimumSize(new Dimension(190, 700));
		navigationsbereich.setMaximumSize(new Dimension(190, 700));
		navigationsbereich.setSize(new Dimension(190, 700));
		navigationsbereich.setBackground(SystemColor.inactiveCaption);
		navigationsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
		navigationsbereich.setBounds(10, 11, 190, 700);
		this.getContentPane().add(navigationsbereich);
		
		//Menuebaum
		this.Navigation = new JTree();
		this.Navigation.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Aktionen") {

				private static final long serialVersionUID = -4459245092488416529L;

				{
					add(new DefaultMutableTreeNode("Punkt1"));
					add(new DefaultMutableTreeNode("Punkt2"));
					add(new DefaultMutableTreeNode("Passwort \u00E4ndern"));
				}
			}
		));
		this.Navigation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		navigationsbereich.add(this.Navigation);
		
		//Content
		arbeitsbereich = new JPanel();
		arbeitsbereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		arbeitsbereich.setPreferredSize(new Dimension(600, 700));
		arbeitsbereich.setMinimumSize(new Dimension(600, 700));
		arbeitsbereich.setMaximumSize(new Dimension(600, 700));
		arbeitsbereich.setBackground(SystemColor.window);
		arbeitsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
		arbeitsbereich.setBounds(210, 11, 600, 700);
		this.getContentPane().add(arbeitsbereich);
		arbeitsbereich.setLayout(null);
		
		//Ueberschrift
		L_Ueberschrift = new JLabel("");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.BOLD, 14));
		L_Ueberschrift.setBounds(10, 11, 580, 24);
		arbeitsbereich.add(L_Ueberschrift);
		
		//Logout-Button
		B_Logout = new JButton("Logout");
		B_Logout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Logout.setPreferredSize(new Dimension(150, 25));
		B_Logout.setBounds(824, 11, 150, 25);
		B_Logout.setVisible(false);
		this.getContentPane().add(B_Logout);
		
		//Hilfe-Button
		B_Hilfe = new JButton("Hilfe?");
		B_Hilfe.setPreferredSize(new Dimension(59, 25));
		B_Hilfe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Hilfe.setBounds(824, 42, 150, 25);
		this.getContentPane().add(B_Hilfe);
		
		//Info-Box
		InfoBox = new JTextArea();
		InfoBox.setEditable(false);
		InfoBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		InfoBox.setSize(new Dimension(150, 488));
		InfoBox.setMinimumSize(new Dimension(150, 488));
		InfoBox.setMaximumSize(new Dimension(150, 488));
		InfoBox.setBackground(SystemColor.control);
		InfoBox.setBounds(824, 73, 150, 628);
		this.getContentPane().add(InfoBox);
		InfoBox.setText("Nachrichtenfeld\r\n");
		
		//Fenster anzeigen
		this.setVisible(true);
	}
	
	public void setSachbearbeiter(){
		this.B_Logout.setVisible(true);
	}
	
	public void setGruppenleiter(){
		this.B_Logout.setVisible(true);
	}
	
	public void setBereichsleiter(){
		this.B_Logout.setVisible(true);
	}
	
	public void setZentralbereichsleiter(){
		this.B_Logout.setVisible(true);
	}
	
	public void setStabstelle(){
		this.B_Logout.setVisible(true);
	}
	
	public void setLoggedOut(){
		this.B_Logout.setVisible(false);
	}
	
	public void setUeberschrift(String ueberschrift){
		this.L_Ueberschrift.setText( ueberschrift );
	}
	
	public void setInfoBox(String message){
		this.InfoBox.setText( message );
	}
	
	public void setContent(View vi){
		
		JPanel v = (JPanel) vi;
		
		try{
			this.arbeitsbereich.removeAll();
		}catch(NullPointerException e){
			System.out.println("error while removing acitve view");
		}
		this.arbeitsbereich.add( v );
		this.arbeitsbereich.doLayout();
		this.revalidate();
		
	}
	
	@Override
	public void setController(Controller c) {
		this.B_Logout.addActionListener(c);
		this.B_Hilfe.addActionListener(c);
		this.Navigation.addTreeSelectionListener((TreeSelectionListener) c);
	}

	@Override
	public void reset() {
		this.InfoBox.setText("");
	}
}
