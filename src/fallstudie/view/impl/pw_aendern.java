package fallstudie.view.impl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JPasswordField;

public class pw_aendern {

private JFrame frame;
private JPasswordField passwordField;
private JPasswordField passwordField_1;
private JPasswordField passwordField_2;

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
	pw_aendern window = new pw_aendern();
	window.frame.setVisible(true);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
});
}

/**
* Create the application.
*/
public pw_aendern() {
initialize();
}

/**
* Initialize the contents of the frame.
*/
private void initialize() {
frame = new JFrame();
frame.setResizable(false);
frame.setSize(new Dimension(800, 600));
frame.setBounds(100, 100, 800, 600);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLocationRelativeTo(null);
frame.getContentPane().setLayout(null);

JPanel navigationsbereich = new JPanel();
navigationsbereich.setPreferredSize(new Dimension(190, 550));
navigationsbereich.setMinimumSize(new Dimension(190, 550));
navigationsbereich.setMaximumSize(new Dimension(190, 550));
navigationsbereich.setSize(new Dimension(190, 550));
navigationsbereich.setBackground(SystemColor.inactiveCaption);
navigationsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
navigationsbereich.setBounds(10, 11, 190, 550);
frame.getContentPane().add(navigationsbereich);

JTree navigationsbaum = new JTree();
navigationsbaum.setModel(new DefaultTreeModel(
new DefaultMutableTreeNode("Aktionen") {
{
add(new DefaultMutableTreeNode("Punkt1"));
add(new DefaultMutableTreeNode("Punkt2"));
add(new DefaultMutableTreeNode("Passwort \u00E4ndern"));
}
}
));
navigationsbereich.add(navigationsbaum);

JPanel arbeitsbereich = new JPanel();
arbeitsbereich.setPreferredSize(new Dimension(400, 550));
arbeitsbereich.setMinimumSize(new Dimension(400, 550));
arbeitsbereich.setMaximumSize(new Dimension(400, 550));
arbeitsbereich.setBackground(SystemColor.window);
arbeitsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
arbeitsbereich.setBounds(210, 11, 400, 550);
frame.getContentPane().add(arbeitsbereich);
arbeitsbereich.setLayout(null);

JLabel lblEineberschrift = new JLabel("Passwort \u00E4ndern");
lblEineberschrift.setBounds(10, 11, 153, 14);
arbeitsbereich.add(lblEineberschrift);
JLabel lblAltesPasswort = new JLabel("Altes Passwort:");
lblAltesPasswort.setBounds(35, 127, 128, 14);
arbeitsbereich.add(lblAltesPasswort);
passwordField = new JPasswordField();
passwordField.setBounds(219, 125, 97, 17);
arbeitsbereich.add(passwordField);
JLabel lblNeuesPasswort = new JLabel("Neues Passwort:");
lblNeuesPasswort.setBounds(35, 166, 128, 14);
arbeitsbereich.add(lblNeuesPasswort);
JLabel lblPasswortWiederholen = new JLabel("Neues Passwort wiederholen:");
lblPasswortWiederholen.setBounds(35, 211, 181, 17);
arbeitsbereich.add(lblPasswortWiederholen);
passwordField_1 = new JPasswordField();
passwordField_1.setBounds(219, 163, 97, 17);
arbeitsbereich.add(passwordField_1);
passwordField_2 = new JPasswordField();
passwordField_2.setBounds(219, 210, 97, 17);
arbeitsbereich.add(passwordField_2);
JButton btnNewButton = new JButton("Speichern");
btnNewButton.setBounds(188, 359, 128, 23);
arbeitsbereich.add(btnNewButton);

JButton btnLogout = new JButton("Logout");
btnLogout.setPreferredSize(new Dimension(150, 20));
btnLogout.setBounds(620, 11, 150, 20);
frame.getContentPane().add(btnLogout);

JButton btnHilfe = new JButton("Hilfe?");
btnHilfe.setBounds(620, 42, 150, 20);
frame.getContentPane().add(btnHilfe);

JTextArea txtNachrichtenfeld = new JTextArea();
txtNachrichtenfeld.setEditable(false);
txtNachrichtenfeld.setFont(new Font("Tahoma", Font.PLAIN, 11));
txtNachrichtenfeld.setSize(new Dimension(150, 488));
txtNachrichtenfeld.setMinimumSize(new Dimension(150, 488));
txtNachrichtenfeld.setMaximumSize(new Dimension(150, 488));
txtNachrichtenfeld.setBackground(SystemColor.control);
txtNachrichtenfeld.setBounds(620, 73, 150, 488);
frame.getContentPane().add(txtNachrichtenfeld);
txtNachrichtenfeld.setText("Nachrichtenfeld\r\n");
}
}