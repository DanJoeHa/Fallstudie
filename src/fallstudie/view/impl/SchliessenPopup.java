package fallstudie.view.impl;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
import java.awt.SystemColor;

public class SchliessenPopup extends JDialog implements View{

	private static final long serialVersionUID = 8389558871657416016L;
	private final JPanel contentPanel = new JPanel();
	private JButton B_ok;
	private JTextPane T_Hinweis;

	/**
	 * Create the dialog.
	 */
	public SchliessenPopup() {
		setModal(false);
		setTitle("");
		//setEnabled(true);
		setResizable(false);
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);	
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		T_Hinweis = new JTextPane();
		T_Hinweis.setBackground(SystemColor.menu);
		T_Hinweis.setText("");
		contentPanel.add(T_Hinweis);
		T_Hinweis.setEditable(false);
		T_Hinweis.setPreferredSize(new Dimension(450, 200));
		T_Hinweis.revalidate();
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		
		
		B_ok = new JButton("Schließen");
		B_ok.setActionCommand("Schließen");
		B_ok.setEnabled(true);
		buttonPane.add(B_ok);
		getRootPane().setDefaultButton(B_ok);
	
		this.setVisible(false);
		
			
	
	}
	
	
	public void setHinweis(String Hinweis){
		T_Hinweis.setText(Hinweis);
	}

	@Override
	public void setController(Controller c) {
		
		this.B_ok.addActionListener(c);
	}

	@Override
	public void reset() {
		T_Hinweis.setText("");
		setTitle("");
		
	}

}
