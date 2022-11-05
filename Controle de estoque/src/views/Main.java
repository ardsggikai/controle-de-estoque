package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/icone-controle-estoque.png")));
		setTitle("Controle de estoque");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUsuarios = new JButton("");
		btnUsuarios.setIcon(new ImageIcon(Main.class.getResource("/img/users.png")));
		btnUsuarios.setToolTipText("Aba Us\u00FAarios");
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios ();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setFont(new Font("Arial", Font.PLAIN, 11));
		btnUsuarios.setFocusPainted(false);
		btnUsuarios.setBounds(57, 33, 70, 70);
		contentPane.add(btnUsuarios);
		
		JButton btnFornecedores = new JButton("");
		btnFornecedores.setIcon(new ImageIcon(Main.class.getResource("/img/supliers.png")));
		btnFornecedores.setToolTipText("Aba Fornecedores");
		btnFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedores.setFont(new Font("Arial", Font.PLAIN, 11));
		btnFornecedores.setBounds(179, 33, 64, 64);
		contentPane.add(btnFornecedores);
		
		JButton btnProdutos = new JButton("");
		btnProdutos.setIcon(new ImageIcon(Main.class.getResource("/img/products.png")));
		btnProdutos.setToolTipText("Aba Produtos");
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.setFont(new Font("Arial", Font.PLAIN, 11));
		btnProdutos.setBounds(320, 33, 64, 64);
		contentPane.add(btnProdutos);
		
		JButton btnClientes = new JButton("");
		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/img/clientes.png")));
		btnClientes.setToolTipText("Aba Clientes");
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setFont(new Font("Arial", Font.PLAIN, 11));
		btnClientes.setBounds(57, 173, 64, 64);
		contentPane.add(btnClientes);
		
		JButton btnRelatorios = new JButton("");
		btnRelatorios.setIcon(new ImageIcon(Main.class.getResource("/img/report.png")));
		btnRelatorios.setToolTipText("Aba Relat\u00F3rios");
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRelatorios.setBounds(179, 173, 64, 64);
		contentPane.add(btnRelatorios);
		
		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre ();
				sobre.setVisible(true);
			}
		});
		btnSobre.setIcon(new ImageIcon(Main.class.getResource("/img/about.png")));
		btnSobre.setToolTipText("Aba Sobre");
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSobre.setBounds(320, 173, 70, 70);
		contentPane.add(btnSobre);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 259, 434, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHoras = new JLabel("");
		lblHoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoras.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHoras.setBounds(220, 11, 204, 28);
		panel.add(lblHoras);
		
		JLabel lblUsuarios = new JLabel("");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUsuarios.setBounds(10, 12, 112, 27);
		panel.add(lblUsuarios);
		
		// Ativar Janela inferior
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblHoras.setText(formatador.format(data));
				
				lblUsuarios.setText(getName());
				
				
			}
		});
		
	}// Fim do construtor
}// Fim Do Codigo
