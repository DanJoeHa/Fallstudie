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

public class layout {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					layout window = new layout();
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
	public layout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.setSize(new Dimension(1000, 750));
		frame.setBounds(100, 100, 1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel navigationsbereich = new JPanel();
		navigationsbereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		navigationsbereich.setPreferredSize(new Dimension(190, 700));
		navigationsbereich.setMinimumSize(new Dimension(190, 700));
		navigationsbereich.setMaximumSize(new Dimension(190, 700));
		navigationsbereich.setSize(new Dimension(190, 700));
		navigationsbereich.setBackground(SystemColor.inactiveCaption);
		navigationsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
		navigationsbereich.setBounds(10, 11, 190, 700);
		frame.getContentPane().add(navigationsbereich);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Aktionen") {
				{
					add(new DefaultMutableTreeNode("Punkt1"));
					add(new DefaultMutableTreeNode("Punkt2"));
					add(new DefaultMutableTreeNode("Passwort \u00E4ndern"));
				}
			}
		));
		tree.setFont(new Font("Tahoma", Font.PLAIN, 14));
		navigationsbereich.add(tree);
		
		JPanel arbeitsbereich = new JPanel();
		arbeitsbereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		arbeitsbereich.setPreferredSize(new Dimension(600, 700));
		arbeitsbereich.setMinimumSize(new Dimension(600, 700));
		arbeitsbereich.setMaximumSize(new Dimension(600, 700));
		arbeitsbereich.setBackground(SystemColor.window);
		arbeitsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
		arbeitsbereich.setBounds(210, 11, 600, 700);
		frame.getContentPane().add(arbeitsbereich);
		arbeitsbereich.setLayout(null);
		
		JLabel lblberschrift = new JLabel("\u00DCberschrift");
		lblberschrift.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblberschrift.setBounds(10, 11, 580, 24);
		arbeitsbereich.add(lblberschrift);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setPreferredSize(new Dimension(150, 25));
		btnLogout.setBounds(824, 11, 150, 25);
		frame.getContentPane().add(btnLogout);
		
		JButton btnHilfe = new JButton("Hilfe?");
		btnHilfe.setPreferredSize(new Dimension(59, 25));
		btnHilfe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHilfe.setBounds(824, 42, 150, 25);
		frame.getContentPane().add(btnHilfe);
		
		JTextArea txtNachrichtenfeld = new JTextArea();
		txtNachrichtenfeld.setEditable(false);
		txtNachrichtenfeld.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNachrichtenfeld.setSize(new Dimension(150, 488));
		txtNachrichtenfeld.setMinimumSize(new Dimension(150, 488));
		txtNachrichtenfeld.setMaximumSize(new Dimension(150, 488));
		txtNachrichtenfeld.setBackground(SystemColor.control);
		txtNachrichtenfeld.setBounds(824, 73, 150, 628);
		frame.getContentPane().add(txtNachrichtenfeld);
		txtNachrichtenfeld.setText("Nachrichtenfeld\r\n");
	}
}
