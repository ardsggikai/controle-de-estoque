package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSenha;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JTextField txtId;
	private JLabel lblId;
	private JTextField txtUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/icoUsuarios.png")));
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		getContentPane().setLayout(null);

		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLogin.setBounds(23, 61, 57, 14);
		getContentPane().add(lblLogin);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSenha.setBounds(23, 92, 57, 14);
		getContentPane().add(lblSenha);

		lblId = new JLabel("ID");
		lblId.setBounds(316, 32, 46, 14);
		getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setFont(new Font("Arial", Font.PLAIN, 11));
		txtId.setBounds(348, 30, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtSenha = new JTextField();
		txtSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		txtSenha.setBounds(80, 89, 190, 20);
		getContentPane().add(txtSenha);
		txtSenha.setColumns(10);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dboff.png")));
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStatus.setBounds(444, 120, 64, 64);
		getContentPane().add(lblStatus);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUsuario.setBounds(20, 36, 46, 14);
		getContentPane().add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		txtUsuario.setBounds(80, 30, 190, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		setResizable(false);
		setTitle("Usuarios");
		setBounds(100, 100, 600, 280);
		setLocationRelativeTo(null);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.setToolTipText("Adicionar Usuario");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/btnRead.png")));
		btnCreate.setFocusPainted(false);
		btnCreate.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setBounds(23, 120, 64, 64);
		getContentPane().add(btnCreate);

		// btn delete
		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteByID();
			}
		});
		btnDelete.setToolTipText("Deletar Usuario");
		btnDelete.setIcon(new ImageIcon(Usuarios.class.getResource("/img/btnDelete.png")));
		btnDelete.setFocusPainted(false);
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(255, 120, 64, 64);
		getContentPane().add(btnDelete);

		// btn search
		btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarContato();
			}
		});
		btnSearch.setIcon(new ImageIcon(Usuarios.class.getResource("/img/btnSeach.png")));
		btnSearch.setToolTipText("Pesquisar Pelo ID");
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSearch.setBounds(444, 11, 64, 64);
		getContentPane().add(btnSearch);

		txtLog = new JTextField();
		txtLog.setBounds(80, 58, 190, 20);
		getContentPane().add(txtLog);
		txtLog.setColumns(10);

		// Uso da tecla <Enter> junto com um botao
		getRootPane().setDefaultButton(btnSearch);

		// Uso da biblioteca atxy2k para restringir o maximo de caracteres
		// txtUsuario
		RestrictedTextField usuario = new RestrictedTextField(txtUsuario);
		usuario.setOnlyText(true);
		usuario.setAcceptSpace(true);
		usuario.setLimit(50);
		// txt login
		RestrictedTextField login = new RestrictedTextField(txtLog);
		login.setLimit(40);
		// txt senha
		RestrictedTextField senha = new RestrictedTextField(txtSenha);

		btnUpdate = new JButton("");
		btnUpdate.setToolTipText("Alterar Dados");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/btnUpdate.png")));
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 11));
		btnUpdate.setBounds(139, 120, 64, 64);
		getContentPane().add(btnUpdate);
		senha.setLimit(250);

	}// Fim do construtor

	// Criar um objeto para acessar o metodo conectar() da classe DAO
	DAO dao = new DAO();
	private JButton btnCreate;
	private JButton btnSearch;
	private JButton btnDelete;
	private JTextField txtLog;
	private JLabel lblStatus;
	private JButton btnUpdate;

	/**
	 * Metodo responsavel por verificar o status da conexao com o banco
	 */
	private void status() {
		// System.out.println("Teste - Janela Ativada");
		// uso da classe connection (JDBC) para estabelecer a conexão
		try {
			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("Erro de Conexão");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dboff.png")));
			} else {
				System.out.println("Banco Conectado!");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dbon.png")));
			}
			// Nunca esquecer de encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	} // Fim do Status

	/**
	 * Metodo responsavel pela pesquisa (select)
	 */

	private void pesquisarContato() {
		// Validaçao
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Numero do Id");
			txtId.requestFocus();
		} else {

			// System.out.println("Teste Pesquisar"); (pra testar)
			// selecione um parametro a ser substituido
			String read = "select * from usuarios where id = ?";
			try {

				// Estabelecer a conexÃ£o ("abrir a porta da geladeira")
				Connection con = dao.conectar();
				// Preparar o codigo sql para execução
				PreparedStatement pst = con.prepareStatement(read);
				// A Linha abaixo substituir o ? pelo conteudo da caixa de texto txtUsuario, o
				// 1
				// faz referencia a interrogacao
				pst.setString(1, txtId.getText());
				// Obter os dados do contato (resultado)
				ResultSet rs = pst.executeQuery();
				// Verificar se existe um contato cadastrado
				// rs.next() significa ter um contato correspondente ao nome pesquisado
				if (rs.next()) {
					// setar as caixas de texto com o resultado da pesquisa
					txtUsuario.setText(rs.getString(2));
					txtLog.setText(rs.getString(3));
					txtSenha.setText(rs.getString(4));
					// habilitar botoes (alterar e excluir)
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Contato inexistente");
					txtUsuario.requestFocus();
					// setar campos e botoes (UX)
					txtUsuario.setText(null);
					txtSenha.setText(null);
					btnCreate.setEnabled(true);
					btnSearch.setEnabled(false);

				}
				// fechar a conexÃ£o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Metodo responsavel pelo cadastro de um novo contato
	 */
	void adicionarContato() {
		// validadaçao de campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuario");
			txtUsuario.requestFocus();
		} else if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLog.requestFocus();
		} else {
			// System.out.println("teste adicionar");
			String create = "insert into usuarios (Usuario,Login,Senha) values (?,?,?)";
			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituição de parametros)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());
				pst.setString(3, txtSenha.getText());
				// Executar a query e confirmar a inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato adicionado.");
					limpar();

				}

				// Encerrar a conexÃ£o
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Metodo Responsavel por alterar informacoes do contato
	 */

	private void alterarContato() {

		// Validaçao
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Usuario");
			txtUsuario.requestFocus();
		} else if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Numero do Login");
			txtLog.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Senha");
			txtSenha.requestFocus();
		} else {

			// Logica Principal
			String update = "update usuarios set usuario = ?, login = ?, senha = ? where id = ?";

			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituiçao de parametros)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, txtId.getText());
				// Executar a query e atualizar as informa�oes no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informaçoes do Contato Atualizados com Sucesso.");
					limpar();
				}

				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}
		}
	}

	/**
	 * Metodo Responsavel por deletar informa�oes do contato
	 */

	private void deleteByID() {

		// Valida��o
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão deste contato?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from usuarios where id = ?";
			try {
				// abrir a conexao
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// executar o comando sql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Contato excluido com sucesso");
				}
				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	/**
	 * Metodo usado para limpar os campos
	 */
	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLog.setText(null);
		txtSenha.setText(null);
		txtUsuario.requestFocus();
		btnCreate.setEnabled(false);
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnSearch.setEnabled(true);
	}

}// Fim do codigo
