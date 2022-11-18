package views;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Fornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFornecedor;
	private JTextField txtId;
	private JTextField txtCnpj;
	private JTextField txtIe;
	private JTextField txtRazaoSocial;
	private JTextField txtNomeFantasia;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtContato;
	private JTextField txtFone;
	private JTextField txtWhatsapp;
	private JTextField txtSite;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedores dialog = new Fornecedores();
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
	public Fornecedores() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		setResizable(false);
		setModal(true);
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/supliers.png")));
		setTitle("Controle de Estoque - Fornecedores");
		setBounds(100, 100, 784, 511);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblFornecedor = new JLabel("Cliente");
		lblFornecedor.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblFornecedor.setBounds(10, 14, 74, 14);
		getContentPane().add(lblFornecedor);

		txtFornecedor = new JTextField();
		txtFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtFornecedor.setToolTipText("Colocar Nome Fantasia");
		txtFornecedor.setBounds(55, 11, 189, 20);
		getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JLabel lblObservacao = new JLabel("Observa\u00E7\u00E3o");
		lblObservacao.setFont(new Font("Arial", Font.PLAIN, 11));
		lblObservacao.setBounds(10, 416, 70, 14);
		getContentPane().add(lblObservacao);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Arial", Font.PLAIN, 11));
		lblId.setBounds(74, 205, 20, 14);
		getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtId.setToolTipText("Coloque o Id");
		txtId.setFont(new Font("Arial", Font.PLAIN, 11));
		txtId.setBounds(94, 202, 50, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCnpj.setBounds(205, 202, 35, 14);
		getContentPane().add(lblCnpj);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setToolTipText("Buscar ID");
		btnBuscar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/btnSeach.png")));
		btnBuscar.setBounds(163, 190, 32, 32);
		getContentPane().add(btnBuscar);

		txtCnpj = new JTextField();
		txtCnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321./-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCnpj.setToolTipText("Coloque o CNPJ");
		txtCnpj.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(245, 199, 150, 20);
		getContentPane().add(txtCnpj);

		JLabel lblIe = new JLabel("IE");
		lblIe.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblIe.setBounds(470, 205, 20, 14);
		getContentPane().add(lblIe);

		txtIe = new JTextField();
		txtIe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtIe.setToolTipText("Coloque a Incri\u00E7\u00E3o Estadual");
		txtIe.setFont(new Font("Arial", Font.PLAIN, 11));
		txtIe.setColumns(10);
		txtIe.setBounds(495, 202, 150, 20);
		getContentPane().add(txtIe);

		JLabel lblRazaoSocial = new JLabel("Raz\u00E3o Social");
		lblRazaoSocial.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRazaoSocial.setBounds(10, 232, 80, 14);
		getContentPane().add(lblRazaoSocial);

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtRazaoSocial.setToolTipText("Coloque o Raz\u00E3o Social");
		txtRazaoSocial.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(95, 229, 300, 20);
		getContentPane().add(txtRazaoSocial);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		lblNomeFantasia.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNomeFantasia.setBounds(400, 232, 90, 14);
		getContentPane().add(lblNomeFantasia);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtNomeFantasia.setToolTipText("Coloque o Nome Fantasia");
		txtNomeFantasia.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(495, 229, 250, 20);
		getContentPane().add(txtNomeFantasia);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCep.setBounds(10, 267, 25, 14);
		getContentPane().add(lblCep);

		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCep.setToolTipText("Coloque o CEP");
		txtCep.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCep.setColumns(10);
		txtCep.setBounds(55, 265, 100, 20);
		getContentPane().add(txtCep);

		btnCep = new JButton("");
		btnCep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCep.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/btnSeach.png")));
		btnCep.setToolTipText("Buscar CEP");
		btnCep.setContentAreaFilled(false);
		btnCep.setBorderPainted(false);
		btnCep.setBounds(160, 260, 32, 32);
		getContentPane().add(btnCep);

		JLabel lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblEndereco.setBounds(192, 266, 55, 14);
		getContentPane().add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtEndereco.setToolTipText("Coloque o Endere\u00E7o");
		txtEndereco.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(250, 265, 250, 20);
		getContentPane().add(txtEndereco);

		JLabel lblNumero = new JLabel("N");
		lblNumero.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNumero.setBounds(505, 266, 20, 14);
		getContentPane().add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtNumero.setToolTipText("Coloque o Numero da Casa");
		txtNumero.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNumero.setColumns(10);
		txtNumero.setBounds(525, 265, 40, 20);
		getContentPane().add(txtNumero);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblComplemento.setBounds(575, 266, 80, 14);
		getContentPane().add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtComplemento.setToolTipText("Coloque o Complemento");
		txtComplemento.setFont(new Font("Arial", Font.PLAIN, 11));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(665, 265, 78, 20);
		getContentPane().add(txtComplemento);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBairro.setBounds(10, 302, 40, 14);
		getContentPane().add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtBairro.setToolTipText("Coloque o Bairro");
		txtBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		txtBairro.setColumns(10);
		txtBairro.setBounds(55, 299, 250, 20);
		getContentPane().add(txtBairro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCidade.setBounds(328, 301, 40, 14);
		getContentPane().add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCidade.setToolTipText("Coloque a Cidade");
		txtCidade.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCidade.setColumns(10);
		txtCidade.setBounds(378, 300, 250, 20);
		getContentPane().add(txtCidade);

		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblUf.setBounds(638, 301, 20, 14);
		getContentPane().add(lblUf);

		cboUf = new JComboBox<Object>();
		cboUf.setToolTipText("Coloque o Estado");
		cboUf.setFont(new Font("Arial", Font.PLAIN, 11));
		cboUf.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(663, 297, 60, 22);
		getContentPane().add(cboUf);

		JLabel lblContato = new JLabel("Contato");
		lblContato.setFont(new Font("Arial", Font.PLAIN, 11));
		lblContato.setBounds(10, 337, 50, 14);
		getContentPane().add(lblContato);

		txtContato = new JTextField();
		txtContato.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtContato.setToolTipText("Coloque o Contato");
		txtContato.setFont(new Font("Arial", Font.PLAIN, 11));
		txtContato.setColumns(10);
		txtContato.setBounds(55, 335, 250, 20);
		getContentPane().add(txtContato);

		JLabel lblFone = new JLabel("Telefone");
		lblFone.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblFone.setBounds(320, 337, 50, 14);
		getContentPane().add(lblFone);

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtFone.setToolTipText("Coloque o Telefone");
		txtFone.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFone.setColumns(10);
		txtFone.setBounds(375, 334, 105, 20);
		getContentPane().add(txtFone);

		JLabel lblWhatsapp = new JLabel("Whatsapp");
		lblWhatsapp.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblWhatsapp.setBounds(495, 333, 60, 20);
		getContentPane().add(lblWhatsapp);

		txtWhatsapp = new JTextField();
		txtWhatsapp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtWhatsapp.setToolTipText("Coloque o Whatsapp");
		txtWhatsapp.setFont(new Font("Arial", Font.PLAIN, 11));
		txtWhatsapp.setColumns(10);
		txtWhatsapp.setBounds(565, 335, 105, 20);
		getContentPane().add(txtWhatsapp);

		JLabel lblSite = new JLabel("Site");
		lblSite.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSite.setBounds(10, 372, 25, 14);
		getContentPane().add(lblSite);

		txtSite = new JTextField();
		txtSite.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtSite.setToolTipText("Coloque o Site");
		txtSite.setFont(new Font("Arial", Font.PLAIN, 11));
		txtSite.setColumns(10);
		txtSite.setBounds(55, 370, 250, 20);
		getContentPane().add(txtSite);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblEmail.setBounds(330, 371, 40, 14);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtEmail.setToolTipText("Coloque o E-mail");
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEmail.setColumns(10);
		txtEmail.setBounds(375, 365, 295, 20);
		getContentPane().add(txtEmail);

		txtObservacao = new JTextArea();
		txtObservacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "AaBbCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtObservacao.setToolTipText("Modifique a Observa\u00E7\u00E3o");
		txtObservacao.setBounds(85, 403, 360, 40);
		getContentPane().add(txtObservacao);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnCreate.setEnabled(false);
		btnCreate.setToolTipText("Adicionar fornecedor");
		btnCreate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/btnCreate.png")));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setBounds(455, 391, 64, 64);
		getContentPane().add(btnCreate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteByidFor();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Remover fornecedor");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/btnDelete.png")));
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(618, 391, 64, 64);
		getContentPane().add(btnDelete);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setToolTipText("Atualizar fornecedor");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/btnUpdate.png")));
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBounds(541, 391, 64, 64);
		getContentPane().add(btnUpdate);

		/**
		 * Uso da tecla <Enter> junto com um botao
		 */
		getRootPane().setDefaultButton(btnBuscar);

		btnLimpar = new JButton("");
		btnLimpar.setEnabled(false);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setToolTipText("Bot\u00E3o Limpar");
		btnLimpar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/BtnEraser.png")));
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnLimpar.setBounds(681, 392, 64, 64);
		getContentPane().add(btnLimpar);

		// Uso da biblioteca atxy2k para restringir o maximo de caracteres
		// txtId
		RestrictedTextField id = new RestrictedTextField(txtId);
		id.setLimit(7);
		// txtCnpj
		RestrictedTextField cnpj = new RestrictedTextField(txtCnpj);
		cnpj.setLimit(18);
		// txtIe
		RestrictedTextField Ie = new RestrictedTextField(txtIe);
		Ie.setLimit(12);
		// txtRazaoSocial
		RestrictedTextField RS = new RestrictedTextField(txtRazaoSocial);
		RS.setLimit(50);
		// txtNomeFantasia
		RestrictedTextField NF = new RestrictedTextField(txtNomeFantasia);
		NF.setLimit(50);
		// txtCep
		RestrictedTextField CEP = new RestrictedTextField(txtCep);
		CEP.setLimit(8);
		// txtEndereco
		RestrictedTextField Endereco = new RestrictedTextField(txtEndereco);
		Endereco.setLimit(50);
		// txtNumero
		RestrictedTextField NumeroCasa = new RestrictedTextField(txtNumero);
		NumeroCasa.setLimit(4);
		// txtComplemento
		RestrictedTextField Complemento = new RestrictedTextField(txtComplemento);
		Complemento.setLimit(15);
		// txtCep
		RestrictedTextField Cep = new RestrictedTextField(txtComplemento);
		Cep.setLimit(9);
		// txtBairro
		RestrictedTextField Bairro = new RestrictedTextField(txtBairro);
		Bairro.setLimit(20);
		// txtCidade
		RestrictedTextField Cidade = new RestrictedTextField(txtCidade);
		Cidade.setLimit(20);
		// txtContato
		RestrictedTextField Contato = new RestrictedTextField(txtContato);
		Contato.setLimit(20);
		// txtFone
		RestrictedTextField Fone = new RestrictedTextField(txtFone);
		Fone.setLimit(20);
		// txtWhatsapp
		RestrictedTextField Whatsapp = new RestrictedTextField(txtWhatsapp);
		Whatsapp.setLimit(20);
		// txtSite
		RestrictedTextField Site = new RestrictedTextField(txtSite);
		Site.setLimit(100);
		// txtEmail
		RestrictedTextField Email = new RestrictedTextField(txtEmail);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Informa\u00E7\u00F5es Cruciais");
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(10, 42, 735, 137);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		Email.setLimit(30);

	} // Fim do construtor

	DAO dao = new DAO();
	private JButton btnBuscar;
	private JButton btnCep;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JComboBox<Object> cboUf;
	private JTextArea txtObservacao;
	private JButton btnLimpar;
	private JTable table;

	/**
	 * Metodo Responsavel pela pesquisa avancada do fornecedor usando filtro
	 */
	private void pesquisarCliente() {

		String read2 = "select idFor as ID, fantasia as Fornecedor, fone1 as Telefone, fone2 as Whatsapp, nomeContato as Contato from fornecedores where fantasia like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtFornecedor.getText() + "%"); // Atencao "%"
			ResultSet rs = pst.executeQuery();
			// Uso da Biblioteca rs2xml para "popular" da tabela //(população)
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
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Id do fornecedor");
			txtId.requestFocus();
		} else {
			String read = "select * from fornecedores where idFor = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtId.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					txtId.setText(rs.getString(1));
					txtRazaoSocial.setText(rs.getString(2));
					txtNomeFantasia.setText(rs.getString(3));
					txtCnpj.setText(rs.getString(4));
					txtIe.setText(rs.getString(5));
					txtCep.setText(rs.getString(6));
					txtEndereco.setText(rs.getString(7));
					txtNumero.setText(rs.getString(8));
					txtComplemento.setText(rs.getString(9));
					txtBairro.setText(rs.getString(10));
					txtCidade.setText(rs.getString(11));
					cboUf.setSelectedItem(rs.getString(12));
					txtContato.setText(rs.getString(13));
					txtFone.setText(rs.getString(14));
					txtWhatsapp.setText(rs.getString(15));
					txtEmail.setText(rs.getString(16));
					txtSite.setText(rs.getString(17));
					txtObservacao.setText(rs.getString(18));
					/**
					 * Habilitar botoes alterar e excluir
					 */
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnLimpar.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado");
					btnCreate.setEnabled(true);
					limpar();
					txtId.requestFocus();
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

		if (txtRazaoSocial.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Razao Social");
			txtRazaoSocial.requestFocus();
		} else if (txtNomeFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Nome Fantasia");
			txtNomeFantasia.requestFocus();
		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CNPJ");
			txtCnpj.requestFocus();
		} else if (txtIe.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Inscricao Social");
			txtIe.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CEP");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o endereco");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o numero");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a cidade");
			txtCidade.requestFocus();
		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtContato.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtFone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtEmail.requestFocus();
		} else {
			// Logica Principal
			String create = "insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone1, fone2, email, site, obs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituiçao de parametros)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtRazaoSocial.getText());
				pst.setString(2, txtNomeFantasia.getText());
				pst.setString(3, txtCnpj.getText());
				pst.setString(4, txtIe.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setString(11, cboUf.getSelectedItem().toString());
				pst.setString(12, txtContato.getText());
				pst.setString(13, txtFone.getText());
				pst.setString(14, txtWhatsapp.getText());
				pst.setString(15, txtEmail.getText());
				pst.setString(16, txtSite.getText());
				pst.setString(17, txtObservacao.getText());

				// Executar a query e atualizar as informaçoes no banco
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

		// Validaçao
		if (txtRazaoSocial.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Razao Social");
			txtRazaoSocial.requestFocus();
		} else if (txtNomeFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Nome Fantasia");
			txtNomeFantasia.requestFocus();
		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CNPJ");
			txtCnpj.requestFocus();
		} else if (txtIe.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Inscricao Social");
			txtIe.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CEP");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o endereco");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o numero");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a cidade");
			txtCidade.requestFocus();
		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtContato.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtFone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtEmail.requestFocus();
		} else {

			// Logica Principal
			String update = "update fornecedores set razaoSocial = ?, fantasia = ?, cnpj = ?, ie = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, nomeContato = ?, fone1 = ?, fone2 = ?, email = ?, site = ?, obs = ? where idFor = ?";

			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituiçao de parametros)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtRazaoSocial.getText());
				pst.setString(2, txtNomeFantasia.getText());
				pst.setString(3, txtCnpj.getText());
				pst.setString(4, txtIe.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setString(11, cboUf.getSelectedItem().toString());
				pst.setString(12, txtContato.getText());
				pst.setString(13, txtFone.getText());
				pst.setString(14, txtWhatsapp.getText());
				pst.setString(15, txtEmail.getText());
				pst.setString(16, txtSite.getText());
				pst.setString(17, txtObservacao.getText());
				pst.setString(18, txtId.getText());
				// Executar a query e atualizar as informaçoes no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informaçoes do Fornecedores Atualizados com Sucesso.");
					limpar();
				}

				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Fornecedores Não Atualizado");
				limpar();
			}
		}
	}

	public void deleteByidFor() {

		// Validação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão deste Fornecedor?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from fornecedores where idFor = ?";
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
					JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
				}
				// Encerrar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Fornecedor Não Foi Excluido");
				limpar();
			}

		}

	}

	public void limpar() {
		txtFornecedor.setText(null);
		txtId.setText(null);
		txtCnpj.setText(null);
		txtIe.setText(null);
		txtRazaoSocial.setText(null);
		txtNomeFantasia.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem("");
		txtContato.setText(null);
		txtFone.setText(null);
		txtWhatsapp.setText(null);
		txtSite.setText(null);
		txtEmail.setText(null);
		txtObservacao.setText(null);

	}
} // Fim do codigo
