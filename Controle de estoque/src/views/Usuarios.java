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
		getContentPane().setBackground(Color.LIGHT_GRAY);

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

		
		getRootPane().setDefaultButton(btnSearch);

		
		RestrictedTextField usuario = new RestrictedTextField(txtUsuario);
		usuario.setOnlyText(true);
		usuario.setAcceptSpace(true);
		usuario.setLimit(50);
		
		RestrictedTextField login = new RestrictedTextField(txtLog);
		login.setLimit(40);

		btnUpdate = new JButton("");
		btnUpdate.setToolTipText("Alterar Dados");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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

		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblHoras.setText(formatador.format(data));

			}
		});

		
		RestrictedTextField log = new RestrictedTextField(txtLog);
		log.setLimit(20);
		
		RestrictedTextField senha = new RestrictedTextField(txtPassword);
		senha.setOnlyAlphaNumeric(true);
		senha.setLimit(250);
		
		RestrictedTextField usuario1 = new RestrictedTextField(txtUsuario);
		usuario1.setOnlyText(true);
		usuario1.setAcceptSpace(true);
		usuario1.setLimit(50);

	}

	DAO dao = new DAO();

	
	private void pesquisarFuncionario() {
		
		if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o seu login");
			txtLog.requestFocus();
		} else {

			String read = "select * from usuarios where login = ?";
			try {

				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(read);
				
				pst.setString(1, txtLog.getText());
				
				ResultSet rs = pst.executeQuery();
				
				if (rs.next()) {
					
					txtId.setText(rs.getString(1));
					txtUsuario.setText(rs.getString(2));
					txtLog.setText(rs.getString(3));
					txtPassword.setText(rs.getString(4));
					cboPerfil.setSelectedItem(rs.getString(5));
					
					chckbxSenha.setVisible(true);
					
					txtPassword.setEditable(false);
					
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Funcionario inexistente");
					limpar();
					txtUsuario.requestFocus();
					
					txtUsuario.setText(null);
					txtPassword.setText(null);
					btnCreate.setEnabled(true);
					btnSearch.setEnabled(false);

				}
				
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}


	void adicionarFuncionario() {
		
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuario");
			txtUsuario.requestFocus();
		} else if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLog.requestFocus();
		} else {
			
			String create = "insert into usuarios (Usuario,Login,Senha,perfil) values (?,?,md5(?),?)";
			try {
				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());
				
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				
				int confirma = pst.executeUpdate();
		
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Funcionario adicionado.");
					limpar();
				} else {
					
					JOptionPane.showMessageDialog(null, "Funcionario Não Adicionado");
					limpar();
				}
				con.close();
			}

			catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showConfirmDialog(null, "Usuario não adicionado - Login Existente");
				txtLog.setText(null);
				txtLog.requestFocus();
			}

			catch (Exception e2) {
				System.out.println(e2);
				
				limpar();
			}
		}
	}

	private void alterarFuncionario() {

		
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Usuario");
			txtUsuario.requestFocus();
		} else if (txtLog.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Numero do Login");
			txtLog.requestFocus();
		} else {

			
			String update = "update usuarios set usuario = ?, login = ?, perfil = ? where id = ?";

			try {
				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());
				
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());
				
				int confirma = pst.executeUpdate();
				
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informaçoes do Funcionario Atualizados com Sucesso.");
					limpar();
				}

				
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Funcionario Não Atualizado");
				limpar();
			}
		}
	}

	

	private void alterarUsuarioSenha() {

		
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

			
			String update = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where id = ?";

			try {
				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLog.getText());
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				
				int confirma = pst.executeUpdate();
				
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informaçoes do Funcionario Atualizados com Sucesso.");
					limpar();
				}

				
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Funcionario Não Atualizado");
				limpar();
			}
		}
	}

	private void deleteByID() {

		
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão deste Funcionario?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from usuarios where id = ?";
			try {
				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Funcionario excluido com sucesso");
				}
				
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Funcionario Não Foi Excluido");
				limpar();
			}

		}

	}

	
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
}