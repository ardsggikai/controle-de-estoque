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
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JTextField txtId;
	private JLabel lblId;
	private JTextField txtUsuario;
	private JButton btnCreate;
	private JButton btnSearch;
	private JButton btnDelete;
	private JTextField txtLog;
	private JButton btnUpdate;
	private JPasswordField txtPassword;
	@SuppressWarnings("rawtypes")
	private JComboBox cboPerfil;
	private JCheckBox chckbxSenha;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Usuarios() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/icoUsuarios.png")));
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		getContentPane().setLayout(null);

		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLogin.setBounds(23, 14, 57, 14);
		getContentPane().add(lblLogin);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSenha.setBounds(23, 42, 57, 14);
		getContentPane().add(lblSenha);

		lblId = new JLabel("ID");
		lblId.setBounds(382, 32, 46, 14);
		getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setFont(new Font("Arial", Font.PLAIN, 11));
		txtId.setBounds(414, 30, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUsuario.setBounds(18, 81, 46, 14);
		getContentPane().add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		txtUsuario.setBounds(78, 75, 190, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		setResizable(false);
		setTitle("Usuarios");
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.setToolTipText("Adicionar Usuario");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionario();
			}
		});
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/btnSeach.png")));
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
				pesquisarFuncionario();
			}
		});
		btnSearch.setIcon(new ImageIcon(Usuarios.class.getResource("/img/btnSeach.png")));
		btnSearch.setToolTipText("Pesquisar Pelo Login");
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSearch.setBounds(272, 5, 32, 32);
		getContentPane().add(btnSearch);

		txtLog = new JTextField();
		txtLog.setFont(new Font("Arial", Font.PLAIN, 11));
		txtLog.setBounds(80, 11, 190, 20);
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

		btnUpdate = new JButton("");
		btnUpdate.setToolTipText("Alterar Dados");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificar se o checkbox está selecionado
				if (chckbxSenha.isSelected()) {
					alterarUsuarioSenha();
				} else {
					alterarFuncionario();
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/btnUpdate.png")));
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 11));
		btnUpdate.setBounds(139, 120, 64, 64);
		getContentPane().add(btnUpdate);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 211, 584, 50);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblHoras = new JLabel("");
		lblHoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoras.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHoras.setBounds(186, 11, 204, 28);
		panel.add(lblHoras);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPassword.setForeground(SystemColor.textInactiveText);
		txtPassword.setBounds(80, 36, 190, 20);
		getContentPane().add(txtPassword);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setFont(new Font("Arial", Font.PLAIN, 11));
		cboPerfil.setBounds(414, 57, 64, 22);
		getContentPane().add(cboPerfil);

		JLabel lblPerfil = new JLabel("Perfil :");
		lblPerfil.setBounds(368, 61, 46, 14);
		getContentPane().add(lblPerfil);

		chckbxSenha = new JCheckBox("Alterar Senha");
		chckbxSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// fazer o check na caixa Jcheckbox
				txtPassword.setEditable(true);
				txtPassword.setText(null);
				txtPassword.requestFocus();
				txtPassword.setBackground(Color.BLACK);
			}
		});
		chckbxSenha.setVisible(false);
		chckbxSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSenha.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSenha.setBounds(268, 35, 97, 23);
		getContentPane().add(chckbxSenha);

		// Ativar Janela inferior
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblHoras.setText(formatador.format(data));

			}
		});

		// Uso da biblioteca atxy2k para restringir o maximo de caracteres
		// txtLogin
		RestrictedTextField log = new RestrictedTextField(txtLog);
		log.setLimit(50);
		// txtPassword
		RestrictedTextField senha = new RestrictedTextField(txtPassword);
		senha.setOnlyAlphaNumeric(true);
		senha.setLimit(50);
		// txtUsuario
		RestrictedTextField usuario1 = new RestrictedTextField(txtUsuario);
		usuario1.setOnlyText(true);
		usuario1.setAcceptSpace(true);
		usuario1.setLimit(50);

	}// Fim do construtor

	// Criar um objeto para acessar o metodo conectar() da classe DAO
	DAO dao = new DAO();

	/**
	 * Metodo responsavel pela pesquisa (select)
	 */

	private void pesquisarFuncionario() {
		// Validaçao
		if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o seu login");
			txtLog.requestFocus();
		} else {

			// System.out.println("Teste Pesquisar"); (pra testar)
			// selecione um parametro a ser substituido
			String read = "select * from usuarios where login = ?";
			try {

				// Estabelecer a conexÃ£o ("abrir a porta da geladeira")
				Connection con = dao.conectar();
				// Preparar o codigo sql para execução
				PreparedStatement pst = con.prepareStatement(read);
				// A Linha abaixo substituir o ? pelo conteudo da caixa de texto txtUsuario, o
				// 1
				// faz referencia a interrogacao
				pst.setString(1, txtLog.getText());
				// Obter os dados do Funcionario (resultado)
				ResultSet rs = pst.executeQuery();
				// Verificar se existe um Funcionario cadastrado
				// rs.next() significa ter um Funcionario correspondente ao nome pesquisado
				if (rs.next()) {
					// setar as caixas de texto com o resultado da pesquisa
					txtId.setText(rs.getString(1));
					txtUsuario.setText(rs.getString(2));
					txtLog.setText(rs.getString(3));
					txtPassword.setText(rs.getString(4));
					cboPerfil.setSelectedItem(rs.getString(5));
					// exibir a caixa checkbox
					chckbxSenha.setVisible(true);
					// desativar a edição da senha
					txtPassword.setEditable(false);
					// habilitar botoes (alterar e excluir)
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Funcionario inexistente");
					limpar();
					txtUsuario.requestFocus();
					// setar campos e botoes (UX)
					txtUsuario.setText(null);
					txtPassword.setText(null);
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
	 * Metodo responsavel pelo cadastro de um novo Funcionario
	 */
	void adicionarFuncionario() {
		// validadaçao de campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuario");
			txtUsuario.requestFocus();
		} else if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLog.requestFocus();
		} else {
			// System.out.println("teste adicionar");
			String create = "insert into usuarios (Usuario,Login,Senha,perfil) values (?,?,md5(?),?)";
			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituição de parametros)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());

				// captura segura de senha
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				// CboPerfil
				pst.setString(4, cboPerfil.getSelectedItem().toString());

				// Executar a query e confirmar a inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Funcionario adicionado.");
					limpar();
				} else {
					// System.out.println(e1);
					JOptionPane.showMessageDialog(null, "Funcionario Não Adicionado");
					limpar();
				}

				// Encerrar a conexÃ£o
				con.close();
			}

			catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showConfirmDialog(null, "Usuario não adicionado - Login Existente");
				txtLog.setText(null);
				txtLog.requestFocus();
			}

			catch (Exception e2) {
				System.out.println(e2);
				// JOptionPane.showConfirmDialog(null, e2);
				limpar();
			}
		}
	}

	/**
	 * Metodo Responsavel por alterar informacoes do Funcionario
	 */

	private void alterarFuncionario() {

		// Validaçao
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Usuario");
			txtUsuario.requestFocus();
		} else if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Numero do Login");
			txtLog.requestFocus();
		} else {

			// Logica Principal
			String update = "update usuarios set usuario = ?, login = ?, perfil = ? where id = ?";

			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituiçao de parametros)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());
				// CboPerfil
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());
				// Executar a query e atualizar as informaçoes no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informaçoes do Funcionario Atualizados com Sucesso.");
					limpar();
				}

				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Funcionario Não Atualizado");
				limpar();
			}
		}
	}

	// Metodo para trocar senha

	private void alterarUsuarioSenha() {

		// Validaçao
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Usuario");
			txtUsuario.requestFocus();
		} else if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Numero do Login");
			txtLog.requestFocus();
		} else if (txtPassword.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a Senha");
			txtPassword.requestFocus();
		} else {

			// Logica Principal
			String update = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where id = ?";

			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituiçao de parametros)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				// CboPerfil
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				// Executar a query e atualizar as informaçoes no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informaçoes do Funcionario Atualizados com Sucesso.");
					limpar();
				}

				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Funcionario Não Atualizado");
				limpar();
			}
		}
	}

	/**
	 * Metodo Responsavel por deletar informa�oes do Funcionario
	 */

	private void deleteByID() {

		// Validação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão deste Funcionario?", "Atenção",
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
					JOptionPane.showMessageDialog(null, "Funcionario excluido com sucesso");
				}
				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Funcionario Não Foi Excluido");
				limpar();
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
		txtPassword.setEditable(true);
		txtPassword.setText(null);
		txtPassword.setBackground(null);
		cboPerfil.setSelectedItem("");
		chckbxSenha.setSelected(false);
		chckbxSenha.setVisible(false);
		txtUsuario.requestFocus();
		btnCreate.setEnabled(false);
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnSearch.setEnabled(true);
	}
}// Fim do codigo