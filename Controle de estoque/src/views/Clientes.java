package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCliente;
	private JTable table;
	private JTextField txtID;
	private JLabel lblNome;
	private JTextField txtNomeCliente;
	private JLabel lblCpf;
	private JTextField txtCpf;
	private JLabel lblCep;
	private JTextField txtCep;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblN;
	private JTextField txtNumber;
	private JLabel lblComplemento;
	private JTextField txtComplemento;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblUf;
	private JTextField txtEmail;
	private JTextField txtTel;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnLimpar;
	private JComboBox<Object> cboUf;
	private JButton btnBuscar;
	private JButton btnCep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setModal(true);
		setTitle("Controle de Estoque - Clientes");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/IcoClientes.png")));
		setBounds(100, 100, 740, 375);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 42, 630, 60);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int setar = table.getSelectedRow();
				txtID.setText(table.getModel().getValueAt(setar, 0).toString());
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblClientes = new JLabel("Cliente");
		lblClientes.setBounds(10, 11, 46, 14);
		getContentPane().add(lblClientes);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtCliente.setBounds(65, 8, 86, 20);
		getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 116, 28, 14);
		getContentPane().add(lblId);

		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtID.setBounds(65, 113, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNome.setBounds(10, 145, 46, 14);
		getContentPane().add(lblNome);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(65, 142, 147, 20);
		getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCpf.setBounds(214, 144, 33, 14);
		getContentPane().add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "0987654321-.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCpf.setBounds(236, 142, 147, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);

		lblCep = new JLabel("Cep");
		lblCep.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCep.setBounds(404, 146, 46, 14);
		getContentPane().add(lblCep);

		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCep.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCep.setBounds(429, 146, 86, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		lblEndereco = new JLabel("Endereço");
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEndereco.setBounds(10, 170, 46, 14);
		getContentPane().add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtEndereco.setBounds(65, 167, 240, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		lblN = new JLabel("N");
		lblN.setFont(new Font("Arial", Font.PLAIN, 11));
		lblN.setBounds(312, 170, 20, 14);
		getContentPane().add(lblN);

		txtNumber = new JTextField();
		txtNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtNumber.setBounds(332, 167, 49, 20);
		getContentPane().add(txtNumber);
		txtNumber.setColumns(10);

		lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Arial", Font.PLAIN, 11));
		lblComplemento.setBounds(395, 173, 72, 14);
		getContentPane().add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtComplemento.setBounds(477, 170, 147, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBairro.setBounds(10, 231, 46, 14);
		getContentPane().add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		txtBairro.setBounds(65, 229, 147, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCidade.setBounds(286, 231, 46, 14);
		getContentPane().add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCidade.setBounds(329, 228, 72, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblUf.setBounds(589, 146, 20, 14);
		getContentPane().add(lblUf);

		cboUf = new JComboBox<Object>();
		cboUf.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setToolTipText("Coloque o Estado");
		cboUf.setFont(new Font("Arial", Font.PLAIN, 11));
		cboUf.setBounds(610, 141, 60, 22);
		getContentPane().add(cboUf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmail.setBounds(10, 200, 46, 14);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890@.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEmail.setBounds(65, 198, 210, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTelefone.setBounds(285, 198, 46, 14);
		getContentPane().add(lblTelefone);

		txtTel = new JTextField();
		txtTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtTel.setBounds(332, 197, 108, 20);
		getContentPane().add(txtTel);
		txtTel.setColumns(10);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnCreate.setIcon(new ImageIcon(Clientes.class.getResource("/img/BtnCadicionar.png")));
		btnCreate.setToolTipText("Adicionar Cliente");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setBounds(404, 228, 64, 64);
		getContentPane().add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnUpdate.setIcon(new ImageIcon(Clientes.class.getResource("/img/BtnCupdate.png")));
		btnUpdate.setToolTipText("Atualizar Cliente");
		btnUpdate.setEnabled(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBounds(483, 228, 64, 64);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteByidFor();
			}
		});
		btnDelete.setIcon(new ImageIcon(Clientes.class.getResource("/img/BtnCdelete.png")));
		btnDelete.setToolTipText("Remover Cliente");
		btnDelete.setEnabled(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(560, 228, 64, 64);
		getContentPane().add(btnDelete);

		btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setIcon(new ImageIcon(Clientes.class.getResource("/img/BtnEraser.png")));
		btnLimpar.setToolTipText("Botão Limpar");
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnLimpar.setEnabled(false);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.setBounds(635, 229, 64, 64);
		getContentPane().add(btnLimpar);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnBuscar.setIcon(new ImageIcon(Clientes.class.getResource("/img/btnSeach.png")));
		btnBuscar.setToolTipText("Buscar ID");
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBounds(160, 106, 32, 32);
		getContentPane().add(btnBuscar);

		
		
		RestrictedTextField id = new RestrictedTextField(txtID);
		id.setLimit(7);
		
		RestrictedTextField Nome = new RestrictedTextField(txtNomeCliente);
		Nome.setOnlyText(true);
		Nome.setAcceptSpace(true);
		Nome.setLimit(50);
		
		RestrictedTextField CPF = new RestrictedTextField(txtCpf);
		CPF.setLimit(15);
		
		RestrictedTextField CEP = new RestrictedTextField(txtCep);
		CEP.setLimit(10);
		
		RestrictedTextField Endereco = new RestrictedTextField(txtEndereco);
		Endereco.setLimit(50);
		
		RestrictedTextField NumeroCasa = new RestrictedTextField(txtNumber);
		NumeroCasa.setLimit(6);
		
		RestrictedTextField Complemento = new RestrictedTextField(txtComplemento);
		Complemento.setLimit(20);
		
		RestrictedTextField Bairro = new RestrictedTextField(txtBairro);
		Bairro.setLimit(50);
		
		RestrictedTextField Cidade = new RestrictedTextField(txtCidade);
		Cidade.setLimit(50);
		
		RestrictedTextField Whatsapp = new RestrictedTextField(txtTel);
		Whatsapp.setLimit(12);
		
		RestrictedTextField Email = new RestrictedTextField(txtEmail);
		Email.setLimit(50);

		btnCep = new JButton("");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setContentAreaFilled(false);
		btnCep.setBorderPainted(false);
		btnCep.setIcon(new ImageIcon(Clientes.class.getResource("/img/btnSeach.png")));
		btnCep.setBounds(520, 141, 32, 32);
		getContentPane().add(btnCep);

	}

	DAO dao = new DAO();

	private void pesquisarCliente() {

		String read2 = "select idFor as ID, Nome, CPF, Cep as CEP, Telefone, Email from clientes where Nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtCliente.getText() + "%"); 
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void pesquisar() {

		if (txtID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o ID do cliente");
			txtID.requestFocus();
		} else {
			String read = "select * from clientes where idFor = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtID.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					txtID.setText(rs.getString(1));
					txtNomeCliente.setText(rs.getString(2));
					txtCpf.setText(rs.getString(3));
					txtCep.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtNumber.setText(rs.getString(6));
					txtComplemento.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));
					txtTel.setText(rs.getString(11));
					txtEmail.setText(rs.getString(12));

					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnBuscar.setEnabled(false);
					btnCep.setEnabled(true);
					btnLimpar.setEnabled(true);
					
					
                    btnCreate.setEnabled(false);

					
					txtNomeCliente.setEnabled(true);
					txtCpf.setEnabled(true);
					txtNomeCliente.setEnabled(true);
					txtCep.setEnabled(true);
					txtEndereco.setEnabled(true);
					txtComplemento.setEnabled(true);
					txtBairro.setEnabled(true);
					txtCidade.setEnabled(true);
					cboUf.setEnabled(true);
					txtTel.setEnabled(true);
					txtEmail.setEnabled(true);
					txtID.setEnabled(false);
					

				} else {
					JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
					
					btnCreate.setEnabled(true);
					btnBuscar.setEnabled(false);
					btnCep.setEnabled(true);
					txtCpf.setEnabled(true);
					txtNomeCliente.setEnabled(true);
					txtCep.setEnabled(true);
					txtEndereco.setEnabled(true);
					txtComplemento.setEnabled(true);
					txtBairro.setEnabled(true);
					txtCidade.setEnabled(true);
					cboUf.setEnabled(true);
					txtTel.setEnabled(true);
					txtEmail.setEnabled(true);
					txtNomeCliente.requestFocus();
					txtID.setEnabled(false);
					btnCep.setEnabled(true);
					txtID.setText(null);

				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} 

	public void adicionar() {
		

		if (txtNomeCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o nome do Cliente");
			txtNomeCliente.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CNPJ ou o CPF");
			txtCpf.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CEP");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o endereco");
			txtEndereco.requestFocus();
		} else if (txtTel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o numero");
			txtTel.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a cidade");
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Selecione o estado");
			cboUf.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o e-mail do cliente");
			txtEmail.requestFocus();
		} else {

			String create = "insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone, Email) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNomeCliente.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumber.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtTel.getText());
				pst.setString(11, txtEmail.getText());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
					limpar();
					con.close();
				}

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {

				JOptionPane.showMessageDialog(null, "CPF duplicado");

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	public void atualizar() {

		
		if (txtNomeCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira O Nome");
			txtNomeCliente.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira O CPF");
			txtCpf.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cep");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Endereço");
			txtEndereco.requestFocus();
		} else if (txtNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Numero");
			txtNumber.requestFocus();
		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Complemento");
			txtComplemento.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira A Cidade");
			txtCidade.requestFocus();
		} else if (txtTel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Telefone");
			txtTel.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Email");
			txtEmail.requestFocus();
		} else {

			
			String update = "update clientes set Nome = ?, CPF = ?, Cep = ?, Endereco = ?, Numero = ?, Complemento = ?, Bairro = ?, Cidade = ?, Uf = ?, Telefone = ?, Email = ? where idFor = ?";

			try {
				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtNomeCliente.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumber.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtTel.getText());
				pst.setString(11, txtEmail.getText());
				pst.setString(12, txtID.getText());
				
				int confirma = pst.executeUpdate();
				
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente Atualizado.");
					limpar();
					btnCreate.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
				} else {
					
					JOptionPane.showMessageDialog(null, "Cliente Não Atualizado");
					limpar();
				}

				
				con.close();
			}

			catch (java.sql.SQLIntegrityConstraintViolationException e1) {

				JOptionPane.showMessageDialog(null, "CPF duplicado");
				txtCpf.setText(null);
				txtCpf.requestFocus();

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	
	public void deleteByidFor() {

		
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusao deste Cliente?", "Atençao",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from clientes where idFor = ?";
			try {
				
				Connection con = dao.conectar();
				
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso");
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
				}
				
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Cliente Nao Foi Excluido");
				limpar();
			}

		}

	}

	
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
				
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public void limpar() {
		txtCliente.setText(null);
		txtID.setText(null);
		txtNomeCliente.setText(null);
		txtCpf.setText(null);
		txtCep.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumber.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem("");
		txtTel.setText(null);
		txtEmail.setText(null);
		txtID.setEnabled(true);
		btnBuscar.setEnabled(true);
		btnCreate.setEnabled(true);
		
		((DefaultTableModel) table.getModel()).setRowCount(0);

	}
}
