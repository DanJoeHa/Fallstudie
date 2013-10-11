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

public class Login_Screen {

private JFrame frame;
private JPasswordField passwordField_1;
private JTextField textField;

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
	Login_Screen window = new Login_Screen();
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
public Login_Screen() {
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

JLabel lblEineberschrift = new JLabel("Anmeldung");
lblEineberschrift.setBounds(35, 27, 97, 14);
arbeitsbereich.add(lblEineberschrift);
JLabel lblAltesPasswort = new JLabel("Benutzername:");
lblAltesPasswort.setBounds(35, 127, 128, 14);
arbeitsbereich.add(lblAltesPasswort);
JLabel lblNeuesPasswort = new JLabel("Passwort:");
lblNeuesPasswort.setBounds(35, 166, 128, 14);
arbeitsbereich.add(lblNeuesPasswort);
passwordField_1 = new JPasswordField();
passwordField_1.setBounds(219, 163, 97, 17);
arbeitsbereich.add(passwordField_1);
JButton btnNewButton = new JButton("Login");
btnNewButton.setBounds(188, 218, 128, 23);
arbeitsbereich.add(btnNewButton);
JButton btnPasswortVergessen = new JButton("Passwort vergessen?");
btnPasswortVergessen.setBounds(119, 252, 197, 23);
arbeitsbereich.add(btnPasswortVergessen);
textField = new JTextField();
textField.setBounds(219, 124, 97, 20);
arbeitsbereich.add(textField);
textField.setColumns(10);

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