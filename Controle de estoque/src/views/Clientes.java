package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Clientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCliente;
	private JTable table;
	private JTextField txtID;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblCpf;
	private JTextField txtCpf;
	private JLabel lblCep;
	private JTextField txtCep;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblN;
	private JTextField txtN;
	private JLabel lblComplemento;
	private JTextField txtComplemento;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblUf;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnLimpar;
	private JComboBox<Object> cboUf;

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
		setBounds(100, 100, 740, 375);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 42, 630, 60);
		getContentPane().add(scrollPane);

		table = new JTable();
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
		txtID.setBounds(65, 113, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNome.setBounds(10, 145, 46, 14);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(65, 142, 86, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCpf.setBounds(161, 145, 33, 14);
		getContentPane().add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCpf.setBounds(189, 143, 86, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);

		lblCep = new JLabel("Cep");
		lblCep.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCep.setBounds(306, 148, 46, 14);
		getContentPane().add(lblCep);

		txtCep = new JTextField();
		txtCep.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCep.setBounds(332, 143, 86, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		lblEndereco = new JLabel("Endereço");
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEndereco.setBounds(10, 170, 46, 14);
		getContentPane().add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(65, 167, 240, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		lblN = new JLabel("N");
		lblN.setFont(new Font("Arial", Font.PLAIN, 11));
		lblN.setBounds(312, 170, 20, 14);
		getContentPane().add(lblN);

		txtN = new JTextField();
		txtN.setBounds(332, 167, 39, 20);
		getContentPane().add(txtN);
		txtN.setColumns(10);

		lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Arial", Font.PLAIN, 11));
		lblComplemento.setBounds(375, 170, 72, 14);
		getContentPane().add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(457, 167, 147, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBairro.setBounds(422, 148, 46, 14);
		getContentPane().add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		txtBairro.setBounds(457, 142, 86, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCidade.setBounds(550, 145, 46, 14);
		getContentPane().add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(589, 142, 106, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblUf.setBounds(614, 169, 20, 14);
		getContentPane().add(lblUf);

		cboUf = new JComboBox<Object>();
		cboUf.setToolTipText("Coloque o Estado");
		cboUf.setFont(new Font("Arial", Font.PLAIN, 11));
		cboUf.setBounds(635, 166, 60, 22);
		getContentPane().add(cboUf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmail.setBounds(10, 200, 46, 14);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEmail.setBounds(65, 198, 210, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTelefone.setBounds(285, 198, 46, 14);
		getContentPane().add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(332, 197, 86, 20);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnCreate.setIcon(new ImageIcon(Clientes.class.getResource("/img/btnCreate.png")));
		btnCreate.setToolTipText("Adicionar Cliente");
		btnCreate.setEnabled(false);
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setBounds(405, 228, 64, 64);
		getContentPane().add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnUpdate.setIcon(new ImageIcon(Clientes.class.getResource("/img/btnUpdate.png")));
		btnUpdate.setToolTipText("Atualizar Cliente");
		btnUpdate.setEnabled(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBounds(491, 228, 64, 64);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteByidFor();
			}
		});
		btnDelete.setIcon(new ImageIcon(Clientes.class.getResource("/img/btnDelete.png")));
		btnDelete.setToolTipText("Remover Cliente");
		btnDelete.setEnabled(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(568, 228, 64, 64);
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
		btnLimpar.setBounds(631, 229, 64, 64);
		getContentPane().add(btnLimpar);

	}// Fim do Construtor

	/**
	 * Conexao
	 */
	DAO dao = new DAO();

	/**
	 * Metodo Responsavel pela pesquisa avancada do Cliente usando filtro
	 */
	private void pesquisarCliente() {

		String read2 = "select idFor as ID, fantasia as Cliente, fone1 as Telefone, fone2 as Whatsapp, nomeContato as Contato from clientes where fantasia like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtCliente.getText() + "%"); // Atencao "%"
			ResultSet rs = pst.executeQuery();
			// Uso da Biblioteca rs2xml para "popular" da tabela //(popula��o)
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void pesquisar() {

		/**
		 * validacao
		 */
		if (txtID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Id do Cliente");
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
					txtNome.setText(rs.getString(2));
					txtCpf.setText(rs.getString(3));
					txtCep.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtN.setText(rs.getString(6));
					txtComplemento.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));
					txtTelefone.setText(rs.getString(11));
					txtEmail.setText(rs.getString(12));

					/**
					 * Habilitar botoes alterar e excluir
					 */
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnLimpar.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Cliente n�o cadastrado");
					btnCreate.setEnabled(true);
					limpar();
					txtID.requestFocus();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void adicionar() {
		/**
		 * Validacao
		 */

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira O Nome");
			txtNome.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira O CPF");
			txtCpf.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cep");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Endereço");
			txtEndereco.requestFocus();
		} else if (txtN.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Numero");
			txtN.requestFocus();
		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Complemento");
			txtComplemento.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira A Cidade");
			txtCidade.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Telefone");
			txtTelefone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Email");
			txtEmail.requestFocus();
		} else {
		
			// Logica Principal
			String create = "insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone, Email) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substitui�ao de parametros)
				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtID.getText());
				pst.setString(2, txtNome.getText());
				pst.setString(3, txtCpf.getText());
				pst.setString(4, txtCep.getText());
				pst.setString(5, txtEndereco.getText());
				pst.setString(6, txtN.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtBairro.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, cboUf.getSelectedItem().toString());
				pst.setString(11, txtTelefone.getText());
				pst.setString(12, txtEmail.getText());

				// Executar a query e atualizar as informa�oes no banco
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
					limpar();
					// Encerrar a conexao
					con.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void atualizar() {

		// Valida�ao
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira O Nome");
			txtNome.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira O CPF");
			txtCpf.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cep");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Endereço");
			txtEndereco.requestFocus();
		} else if (txtN.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Numero");
			txtN.requestFocus();
		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Complemento");
			txtComplemento.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira A Cidade");
			txtCidade.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Telefone");
			txtTelefone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Email");
			txtEmail.requestFocus();
		} else {

			// Logica Principal
			String update = "update clientes set Nome = ?, CPF = ?, Cep = ?, Endereco = ?, Numero = ?, Complemento = ?, Bairro = ?, Cidade = ?, Uf = ?, Telefone = ?, Email = ?, where idFor = ?";

			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substitui�ao de parametros)
				PreparedStatement pst = con.prepareStatement(update);
				
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtN.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtTelefone.getText());
				pst.setString(11, txtEmail.getText());
				pst.setString(12, txtID.getText());
				// Executar a query e atualizar as informa�oes no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informaçoes do clientes Atualizados com Sucesso.");
					limpar();
				}

				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "clientes Nao Atualizado");
				limpar();
			}
		}
	}

	/**
	 * Metodo De Deletar
	 */

	public void deleteByidFor() {

		// Valida��o
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclus�o deste Cliente?", "Atençao",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from clientes where idFor = ?";
			try {
				// abrir a conexao
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				// executar o comando sql e confirmar a exclus�o
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
				}
				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Cliente Nao Foi Excluido");
				limpar();
			}

		}

	}

	/**
	 * Metodo De Limpar Campos
	 */
	public void limpar() {
		txtCliente.setText(null);
		txtID.setText(null);
		txtNome.setText(null);
		txtCpf.setText(null);
		txtCep.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtN.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem("");
		txtTelefone.setText(null);
		txtEmail.setText(null);
		// Limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);

	}
}
