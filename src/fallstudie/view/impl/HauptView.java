package fallstudie.view.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
import java.awt.Toolkit;
/**
 * Hauptframe
 * Innerhalb diesem Frame werden die Views geladen
 * Stellt Navigationsbaum, Hilfe und Logout Button, InfoBox zur Verfügung
 * Beinhaltet Überschrift
 * @author Marc
 *
 */
public class HauptView extends JFrame implements View{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 999907361524491947L;
	/**
	 * Panel, in dem die Views geladen werden
	 */
	private JPanel Content, NavigationBackground;
	/**
	 * aktuelle, letzte und vorletzte View
	 */
	private View activeView, lastView, lastlastView;
	/**
	 * Logout und Hilfe Button
	 */
	private JButton B_Logout, B_Hilfe;
	/**
	 * InfoBox Text
	 */
	private JTextPane InfoBox, LoginInfo;
	/**
	 * Navigationsbaum
	 */
	private JTree Navigation;
	/**
	 * Überschriften Label
	 */
	private JLabel L_Ueberschrift;
	/**
	 * Listener auf Navigationsbaum
	 */
	private TreeSelectionListener tl;
	/**
	 * Create the frame.
	 */
	public HauptView() {
		
		//Frame Styling
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.setSize(1000, 750);
		this.setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Stippler");
		
		//Icon
		try{
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(HauptView.class.getResource("/fallstudie/images/Stippler.jpg")));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Panel für allen Inhalt des Frames
		JPanel Panel = new JPanel();
		Panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);
		
		//Hintergrundpanel Navigation
		NavigationBackground = new JPanel();
		NavigationBackground.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NavigationBackground.setBackground(SystemColor.inactiveCaption);
		NavigationBackground.setBorder(new LineBorder(new Color(0, 0, 0)));
		NavigationBackground.setBounds(9, 12, 190, 700);
		Panel.add(NavigationBackground);
		
		//Logout-Button
		B_Logout = new JButton("Logout");
		B_Logout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Logout.setBounds(839, 12, 150, 30);
		B_Logout.setVisible(false);
		Panel.add(B_Logout);
		
		//Hilfe-Button
		B_Hilfe = new JButton("Hilfe?");
		B_Hilfe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Hilfe.setBounds(839, 52, 150, 30);
		Panel.add(B_Hilfe);
		
		//InfoBox
		InfoBox = new JTextPane();
		InfoBox.setText("");
		InfoBox.setBackground(SystemColor.menu);
		InfoBox.setBounds(840, 164, 150, 548);
		InfoBox.setEditable(false);
		Panel.add(InfoBox);
		
		//Titelzeile
		L_Ueberschrift = new JLabel("Überschrift");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Ueberschrift.setBounds(209, 12, 620, 30);
		Panel.add(L_Ueberschrift);
		
		//Inhaltsbereich
		Content = new JPanel();
		Content.setAutoscrolls(true);
		Content.setBounds(210, 51, 620, 660);
		Panel.add(Content);
		
		LoginInfo = new JTextPane();
		LoginInfo.setBackground(SystemColor.menu);
		LoginInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LoginInfo.setEditable(false);
		LoginInfo.setBounds(839, 93, 150, 60);
		Panel.add(LoginInfo);
		
		//Fenster anzeigen
		this.setVisible(true);
	}
	
	/**
	 * Erstellt Navigationsbaum anhand der Rechte des Users
	 * 
	 * @author Johannes
	 * @version 0.1
	 * 
	 * @param r2datenerfassen
	 * @param r2datenanzeigen
	 * @param r2arbeitsgruppe
	 * @param r2art
	 * @param r2bereich
	 * @param r2mitarbeiter
	 * @param r2jobconfig
	 */
	public void createNavTree(boolean r2datenerfassen, boolean r2datenanzeigen, boolean r2arbeitsgruppe, boolean r2art, boolean r2bereich, boolean r2mitarbeiter, boolean r2jobconfig){
		
		this.B_Logout.setVisible(true);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Aktionen");
		
		if( r2datenerfassen ){
			DefaultMutableTreeNode erfassen = new DefaultMutableTreeNode("Daten erfassen");
			root.add(erfassen);
		}
		
		if( r2datenanzeigen ){
			DefaultMutableTreeNode anzeigen = new DefaultMutableTreeNode("Daten anzeigen");
			root.add(anzeigen);
		}
		
		if( r2arbeitsgruppe ){
			DefaultMutableTreeNode arbeitsgruppe = new DefaultMutableTreeNode("Arbeitsgruppe");
			root.add(arbeitsgruppe);
			DefaultMutableTreeNode anlegenAG = new DefaultMutableTreeNode("anlegen");
			arbeitsgruppe.add(anlegenAG);
			DefaultMutableTreeNode bearbeitenAG = new DefaultMutableTreeNode("bearbeiten");
			arbeitsgruppe.add(bearbeitenAG);
			DefaultMutableTreeNode loescheAG = new DefaultMutableTreeNode("löschen");
			arbeitsgruppe.add(loescheAG);
		}
		
		if( r2art ){
			DefaultMutableTreeNode art = new DefaultMutableTreeNode("Art");
			root.add(art);
			DefaultMutableTreeNode anlegenArt = new DefaultMutableTreeNode("anlegen");
			art.add(anlegenArt);
			DefaultMutableTreeNode loescheArt = new DefaultMutableTreeNode("löschen");
			art.add(loescheArt);
		}
		
		if( r2bereich ){
			DefaultMutableTreeNode bereich = new DefaultMutableTreeNode("Bereich");
			root.add(bereich);
			DefaultMutableTreeNode anlegenBereich = new DefaultMutableTreeNode("anlegen");
			bereich.add(anlegenBereich);
			DefaultMutableTreeNode bearbeitenBereich = new DefaultMutableTreeNode("bearbeiten");
			bereich.add(bearbeitenBereich);
			DefaultMutableTreeNode loescheBereich = new DefaultMutableTreeNode("löschen");
			bereich.add(loescheBereich);
		}
		
		if( r2mitarbeiter ){
			DefaultMutableTreeNode mitarbeiter = new DefaultMutableTreeNode("Mitarbeiter");
			root.add(mitarbeiter);
			DefaultMutableTreeNode anlegenMA = new DefaultMutableTreeNode("anlegen");
			mitarbeiter.add(anlegenMA);
			DefaultMutableTreeNode bearbeiteMA = new DefaultMutableTreeNode("bearbeiten");
			mitarbeiter.add(bearbeiteMA);
			DefaultMutableTreeNode loescheMA = new DefaultMutableTreeNode("löschen");
			mitarbeiter.add(loescheMA);
		}
		
		DefaultMutableTreeNode changepw = new DefaultMutableTreeNode("Passwort ändern");
		root.add(changepw);
		
		if( r2jobconfig ){
			DefaultMutableTreeNode jobconfig = new DefaultMutableTreeNode("Job-Einstellungen");
			root.add(jobconfig);
		}
		
		this.Navigation = new JTree(root);
		this.Navigation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.Navigation.addTreeSelectionListener( this.tl );
		NavigationBackground.add(this.Navigation);
	}
	/**
	 * Navigationsmenü und Logout Button ausblenden
	 */
	public void setLoggedOut(){
		this.B_Logout.setVisible( false );
		this.Navigation.setVisible(false);
	}
	/**
	 * Aktuelle Überschrift setzen
	 * @param ueberschrift
	 */
	public void setUeberschrift(String ueberschrift){
		this.L_Ueberschrift.setText( ueberschrift );
	}
	/**
	 * Aktuelle Info in die InfoBox schreiben
	 * @param message
	 */
	public void setInfoBox(String message){
		this.InfoBox.setText( message );
	}
	
	/**
	 * Login Info schreiben
	 * 
	 */
	public void setLoginInfo(String info){
		this.LoginInfo.setText(info);
	}
	
	/**
	 * View setzen
	 * @param vi
	 */
	public void setContent(View vi){
		
		JPanel v = (JPanel) vi;
		
		try{
			this.Content.removeAll();
		}catch(NullPointerException e){
			System.out.println("error while removing acitve view");
		}
		this.Content.add(v);
		v.repaint();
		this.lastlastView = this.lastView;
		this.lastView = this.activeView;
		this.activeView = vi;
		this.Content.doLayout();
		this.revalidate();
		
	}
	/**
	 * Geht eine Maske zurück.
	 * @return
	 */
	public View zurueck(){
		if( this.lastView.equals(this.activeView) ){
			return this.zurueck(2);
		}else{
			return this.zurueck(1);
		}
	}
	/**
	 * Geht die übergebene Anzahl an Masken zurück.
	 * @param anz
	 * @return
	 */
	public View zurueck(int anz){
		if(anz <= 2 && anz > 0){
			if( anz == 1){
				this.setContent(this.lastView);
				return this.lastView;
			}
			if( anz == 2){
				this.setContent(this.lastlastView);
				return this.lastlastView;
			}
		}
		return null;
	}
	
	@Override
	/**
	 * setzt Controller
	 */
	public void setController(Controller c) {
		
		this.B_Logout.addActionListener(c);
		this.B_Hilfe.addActionListener(c);
		this.tl = (TreeSelectionListener) c;
		//this.Content.addKeyListener(c);
		//this.InfoBox.addKeyListener(c);
	}

	@Override
	/**
	 * setzt Text zurück der Infobox
	 */
	public void reset() {
		this.InfoBox.setText("");
	}
}

