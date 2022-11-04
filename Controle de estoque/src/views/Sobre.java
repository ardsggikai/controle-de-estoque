package views;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class Sobre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblGithub;
	private JTextField txtVersion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	private void link (String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI (site);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public Sobre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/icone-sobre.png")));
		setTitle("Sobre");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLicencaMit = new JLabel("New label");
		lblLicencaMit.setIcon(new ImageIcon(Sobre.class.getResource("/img/MIT-License-transparent.png")));
		lblLicencaMit.setHorizontalAlignment(SwingConstants.CENTER);
		lblLicencaMit.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLicencaMit.setBounds(296, 138, 128, 92);
		contentPanel.add(lblLicencaMit);
		
		lblGithub = new JLabel("");
		lblGithub.setToolTipText("Clique aki para abrir o Repositorio");
		lblGithub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			link ("https://github.com/ardsggikai/controle-de-estoque");
		}
			
		});
		
		
		lblGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/GitIco.png")));
		lblGithub.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGithub.setBounds(304, 27, 100, 100);
		contentPanel.add(lblGithub);
		
		JTextPane txtpnEmDesenvolvimento = new JTextPane();
		txtpnEmDesenvolvimento.setText("Prazer Meu Nome \u00E9 Allan e eu estou estudando no senac tatuape \r\nTurma TI/Senac      \r\nClique no Logo do Github para mostrar os arquivos do repositorio");
		txtpnEmDesenvolvimento.setFont(new Font("Arial", Font.PLAIN, 11));
		txtpnEmDesenvolvimento.setEditable(false);
		txtpnEmDesenvolvimento.setBackground(Color.LIGHT_GRAY);
		txtpnEmDesenvolvimento.setBounds(10, 43, 286, 69);
		contentPanel.add(txtpnEmDesenvolvimento);
		
		txtVersion = new JTextField();
		txtVersion.setEditable(false);
		txtVersion.setText("Version 1.1");
		txtVersion.setHorizontalAlignment(SwingConstants.CENTER);
		txtVersion.setFont(new Font("Arial", Font.PLAIN, 11));
		txtVersion.setBounds(318, 235, 86, 20);
		contentPanel.add(txtVersion);
		txtVersion.setColumns(10);
		
	}
}
