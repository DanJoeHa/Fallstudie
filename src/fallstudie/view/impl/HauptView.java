package fallstudie.view.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionListener;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class HauptView extends JFrame implements View{

	private static final long serialVersionUID = 999907361524491947L;
	private JPanel Content;
	private JButton B_Logout, B_Hilfe;
	private JTextPane InfoBox;
	private JTree Navigation;
	private JLabel L_Ueberschrift;

	/**
	 * Create the frame.
	 */
	public HauptView() {
		
		//Frame Styling
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 1000, 750));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		
		//Panel für allen Inhalt des Frames
		JPanel Panel = new JPanel();
		Panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);
		
		//Hintergrundpanel Navigation
		JPanel NavigationBackground = new JPanel();
		NavigationBackground.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NavigationBackground.setBackground(SystemColor.inactiveCaption);
		NavigationBackground.setBorder(new LineBorder(new Color(0, 0, 0)));
		NavigationBackground.setBounds(9, 12, 190, 700);
		Panel.add(NavigationBackground);
		
		//Navigationsbaum
		this.Navigation = new JTree();
		this.Navigation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DefaultMutableTreeNode aktionMA = new DefaultMutableTreeNode("Mitarbeiter"){
			private static final long serialVersionUID = -4459245092488416529L;

			{
				add(new DefaultMutableTreeNode("anlegen"));
				add(new DefaultMutableTreeNode("bearbeiten"));
				add(new DefaultMutableTreeNode("löschen"));
			}
		};
		DefaultTreeModel treeModel = new DefaultTreeModel(aktionMA);
		this.Navigation.setModel(treeModel);
		NavigationBackground.add(this.Navigation);
		
		//Logout-Button
		B_Logout = new JButton("Logout");
		B_Logout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Logout.setBounds(839, 12, 150, 30);
		Panel.add(B_Logout);
		
		//Hilfe-Button
		B_Hilfe = new JButton("Hilfe?");
		B_Hilfe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Hilfe.setBounds(839, 52, 150, 30);
		Panel.add(B_Hilfe);
		
		//InfoBox
		InfoBox = new JTextPane();
		InfoBox.setText("Nachrichtenfeld");
		InfoBox.setBackground(SystemColor.menu);
		InfoBox.setBounds(840, 92, 150, 620);
		Panel.add(InfoBox);
		
		//Titelzeile
		L_Ueberschrift = new JLabel("\u00DCberschrift");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Ueberschrift.setBounds(209, 12, 620, 30);
		Panel.add(L_Ueberschrift);
		
		//Inhaltsbereich
		Content = new JPanel();
		Content.setAutoscrolls(true);
		Content.setBounds(210, 51, 620, 660);
		Panel.add(Content);
		
		//Fenster anzeigen
		this.setLoggedOut();
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
		this.B_Logout.setVisible( false );
		this.Navigation.setVisible(false);
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
			this.Content.removeAll();
		}catch(NullPointerException e){
			System.out.println("error while removing acitve view");
		}
		this.Content.add( v );
		this.Content.doLayout();
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

