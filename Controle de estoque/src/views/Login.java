package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.DAO;

import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/icon-login-supeior.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.setToolTipText("Acessar");
		btnAcessar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar ();
			}
		});
		btnAcessar.setFocusPainted(false);
		btnAcessar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAcessar.setBounds(226, 134, 89, 23);
		contentPane.add(btnAcessar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff - 32.png")));
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(344, 175, 32, 32);
		contentPane.add(lblStatus);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLogin.setBounds(170, 68, 46, 14);
		contentPane.add(lblLogin);

		JLabel lblNewLabel = new JLabel("Senha");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(170, 93, 46, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Arial", Font.PLAIN, 11));
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setBounds(226, 65, 89, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		txtSenha.setBounds(226, 90, 89, 20);
		contentPane.add(txtSenha);

		lblImgEsq = new JLabel("");
		lblImgEsq.setIcon(new ImageIcon(Login.class.getResource("/img/Login-img-lateral.png")));
		lblImgEsq.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgEsq.setFont(new Font("Arial", Font.PLAIN, 11));
		lblImgEsq.setBounds(23, 37, 128, 128);
		contentPane.add(lblImgEsq);

		lblImgDireita = new JLabel("");
		lblImgDireita.setIcon(new ImageIcon(Login.class.getResource("/img/Login-img-lateral-direito.png")));
		lblImgDireita.setFont(new Font("Arial", Font.PLAIN, 11));
		lblImgDireita.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgDireita.setBounds(325, 55, 64, 64);
		contentPane.add(lblImgDireita);

		// tecla enter associada ao bot�o
		getRootPane().setDefaultButton(btnAcessar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 212, 404, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblHoras = new JLabel("");
		lblHoras.setBounds(160, 11, 234, 27);
		lblHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoras.setFont(new Font("Arial", Font.PLAIN, 11));
		panel.add(lblHoras);
		
		lblUsuarios = new JLabel("");
		lblUsuarios.setBounds(10, 11, 112, 27);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Arial", Font.PLAIN, 11));
		panel.add(lblUsuarios);
		
		// Ativar Janela inferior
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowActivated(WindowEvent e) {

						Date data = new Date();
						DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
						lblHoras.setText(formatador.format(data));
						
						
						
					}
				});

	}// Fim do construtor

	private JLabel lblImgEsq;
	private JLabel lblImgDireita;

	/**
	 * Criar um objeto para acessar o metodo conectar() da classe DAO
	 */
	DAO dao = new DAO();
	private JLabel lblHoras;
	private JLabel lblUsuarios;

	/**
	 * Metodo responsavel por verificar o status da conexao com o banco
	 */
	private void status() {
		// System.out.println("Teste - Janela Ativada");
		// uso da classe connection (JDBC) para estabelecer a conex�o
		try {
			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("Erro de Conex�o");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dboff - 32.png")));
			} else {
				System.out.println("Banco Conectado!");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dbon - 32.png")));
			}
			// Nunca esquecer de encerrar a conex�o
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	} // Fim do Status

	/**
	 * Metodo Logar
	 */

	private void logar() {
    Main main = new Main ();
    main.setVisible(true);
    //fechar o JFrame
    this.dispose();
    
	}
}// Fim Do Codigo
