package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Produtos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBarcode;
	private JTextField txtCodigo;
	private JTextField txtFornecedor;
	private JTextField txtProduto;
	private JTextField txtCusto;
	private JTextField txtLucro;
	private JTextField txtFabricante;
	private JTextField txtEstoque;
	private JTextField txtEstoquemin;
	private JTextField txtLocal;
	private JTextField txtIdFor;
	private JComboBox<Object> cboUnidade;
	private JButton btnAddProduto;
	private JButton btnUpdateProduto;
	private JButton btnDeleteProduto;
	private JButton btnPesquisar;
	private JLabel lblLupaPesquisar;
	private JTextArea txtaDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
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
	public Produtos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/IcoProdutos.png")));
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		setTitle("Produtos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 800, 485);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JDateChooser dateEntrada = new JDateChooser();
		dateEntrada.setBounds(397, 191, 134, 20);
		getContentPane().add(dateEntrada);

		JDateChooser dateValidade = new JDateChooser();
		dateValidade.setBounds(611, 191, 134, 20);
		getContentPane().add(dateValidade);

		JLabel lblCodigoBarras = new JLabel("");
		lblCodigoBarras.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		lblCodigoBarras.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCodigoBarras.setBounds(0, 24, 64, 45);
		getContentPane().add(lblCodigoBarras);

		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtBarcode.setFont(new Font("Arial", Font.PLAIN, 11));
		txtBarcode.setBounds(73, 37, 240, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCodigo.setBounds(10, 96, 46, 14);
		getContentPane().add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCodigo.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCodigo.setBounds(73, 93, 134, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnPesquisar.setBounds(217, 92, 96, 23);
		getContentPane().add(btnPesquisar);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Fornecedor", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(335, 24, 410, 137);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblLupaPesquisar = new JLabel("");
		lblLupaPesquisar.setIcon(new ImageIcon(Produtos.class.getResource("/img/pesquisar.png")));
		lblLupaPesquisar.setBounds(165, 11, 32, 32);
		panel.add(lblLupaPesquisar);

		txtFornecedor = new JTextField();
		txtFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarProdutos();
			}
		});
		txtFornecedor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFornecedor.setBounds(10, 24, 86, 20);
		panel.add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 390, 76);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtIdFor = new JTextField();
		txtIdFor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtIdFor.setBounds(290, 23, 86, 20);
		panel.add(txtIdFor);
		txtIdFor.setColumns(10);

		JLabel lblIdFor = new JLabel("ID");
		lblIdFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdFor.setFont(new Font("Arial", Font.PLAIN, 11));
		lblIdFor.setBounds(231, 26, 46, 14);
		panel.add(lblIdFor);

		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEntrada.setBounds(341, 191, 46, 14);
		getContentPane().add(lblEntrada);

		JLabel lblValidade = new JLabel("Validade");
		lblValidade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblValidade.setBounds(553, 191, 46, 14);
		getContentPane().add(lblValidade);

		btnAddProduto = new JButton("");
		btnAddProduto.setEnabled(false);
		btnAddProduto.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxadd.png")));
		btnAddProduto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAddProduto.setBounds(341, 358, 64, 64);
		getContentPane().add(btnAddProduto);

		btnUpdateProduto = new JButton("");
		btnUpdateProduto.setEnabled(false);
		btnUpdateProduto.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxupdate.png")));
		btnUpdateProduto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnUpdateProduto.setBounds(451, 358, 64, 64);
		getContentPane().add(btnUpdateProduto);

		btnDeleteProduto = new JButton("");
		btnDeleteProduto.setEnabled(false);
		btnDeleteProduto.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxdel.png")));
		btnDeleteProduto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDeleteProduto.setBounds(561, 358, 64, 64);
		getContentPane().add(btnDeleteProduto);

		txtaDescricao = new JTextArea();
		txtaDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtaDescricao.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtaDescricao.setBounds(73, 215, 242, 64);
		getContentPane().add(txtaDescricao);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDescricao.setBounds(10, 220, 64, 14);
		getContentPane().add(lblDescricao);

		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Arial", Font.PLAIN, 11));
		lblProduto.setBounds(10, 159, 46, 14);
		getContentPane().add(lblProduto);

		txtProduto = new JTextField();
		txtProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtProduto.setFont(new Font("Arial", Font.PLAIN, 11));
		txtProduto.setBounds(73, 156, 240, 20);
		getContentPane().add(txtProduto);
		txtProduto.setColumns(10);

		JLabel lblCusto = new JLabel("Custo");
		lblCusto.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCusto.setBounds(341, 234, 46, 14);
		getContentPane().add(lblCusto);

		txtCusto = new JTextField();
		txtCusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCusto.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCusto.setBounds(397, 231, 114, 20);
		getContentPane().add(txtCusto);
		txtCusto.setColumns(10);

		JLabel lblLucro = new JLabel("Lucro");
		lblLucro.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLucro.setBounds(553, 234, 46, 14);
		getContentPane().add(lblLucro);

		txtLucro = new JTextField();
		txtLucro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtLucro.setFont(new Font("Arial", Font.PLAIN, 11));
		txtLucro.setBounds(611, 231, 114, 20);
		getContentPane().add(txtLucro);
		txtLucro.setColumns(10);

		JLabel lblPorcentagem = new JLabel("%");
		lblPorcentagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentagem.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPorcentagem.setBounds(716, 233, 46, 14);
		getContentPane().add(lblPorcentagem);

		JLabel lblFabricante = new JLabel("Fabricante");
		lblFabricante.setFont(new Font("Arial", Font.PLAIN, 11));
		lblFabricante.setBounds(10, 311, 64, 14);
		getContentPane().add(lblFabricante);

		txtFabricante = new JTextField();
		txtFabricante.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtFabricante.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFabricante.setBounds(73, 308, 242, 20);
		getContentPane().add(txtFabricante);
		txtFabricante.setColumns(10);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEstoque.setBounds(10, 361, 46, 14);
		getContentPane().add(lblEstoque);

		txtEstoque = new JTextField();
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtEstoque.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEstoque.setBounds(73, 358, 75, 20);
		getContentPane().add(txtEstoque);
		txtEstoque.setColumns(10);

		txtEstoquemin = new JTextField();
		txtEstoquemin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtEstoquemin.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEstoquemin.setColumns(10);
		txtEstoquemin.setBounds(240, 356, 75, 20);
		getContentPane().add(txtEstoquemin);

		JLabel lblEstoquemin = new JLabel("Estoque Minimo");
		lblEstoquemin.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEstoquemin.setBounds(158, 361, 84, 14);
		getContentPane().add(lblEstoquemin);

		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUnidade.setBounds(10, 406, 46, 14);
		getContentPane().add(lblUnidade);

		cboUnidade = new JComboBox<Object>();
		cboUnidade.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "CX", "UN", "k\t", "h", "da\t ", "d\t ", "c", "m", "kl", "hl", "dal", "l", "dl", "cl", "ml", "km", "hm", "dam", "m", "dm", "cm", "ml", "kg", "hg", "dag", "g", "dg", "cg", "mg", "km3", "hm3", "dam3", "m3", "dm3", "cm3", "mm3"}));
		cboUnidade.setFont(new Font("Arial", Font.PLAIN, 11));
		cboUnidade.setBounds(73, 398, 64, 22);
		getContentPane().add(cboUnidade);

		JLabel lblLocal = new JLabel("Local");
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLocal.setBounds(158, 406, 39, 14);
		getContentPane().add(lblLocal);

		txtLocal = new JTextField();
		txtLocal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtLocal.setFont(new Font("Arial", Font.PLAIN, 11));
		txtLocal.setBounds(205, 400, 108, 20);
		getContentPane().add(txtLocal);
		txtLocal.setColumns(10);

		/**
		 * Uso da tecla <Enter> junto com um botao
		 */
		getRootPane().setDefaultButton(btnPesquisar);

		// Uso da biblioteca atxy2k para restringir o maximo de caracteres
		// txtBarcode
		RestrictedTextField Barcode = new RestrictedTextField(txtBarcode);
		Barcode.setLimit(255);
		// txtCodigo
		RestrictedTextField Codigo = new RestrictedTextField(txtCodigo);
		Codigo.setLimit(30);
		// txtProduto
		RestrictedTextField Produto = new RestrictedTextField(txtProduto);
		Produto.setLimit(55);
		// txtFabricante
		RestrictedTextField Fabricante = new RestrictedTextField(txtFabricante);
		Fabricante.setLimit(50);
		// txtEstoque
		RestrictedTextField Estoque = new RestrictedTextField(txtEstoque);
		Estoque.setLimit(1000);
		// txtEstoque
		RestrictedTextField EstoqueMin = new RestrictedTextField(txtEstoquemin);
		EstoqueMin.setLimit(1000);
		// txtLocal
		RestrictedTextField Local = new RestrictedTextField(txtLocal);
		Local.setLimit(50);
		// txtFornecedor
		RestrictedTextField Fornecedor = new RestrictedTextField(txtFornecedor);
		Fornecedor.setLimit(50);

		// txtIdFor
		RestrictedTextField IdFor = new RestrictedTextField(txtIdFor);
		IdFor.setLimit(8);

		// txtCusto
		RestrictedTextField Custo = new RestrictedTextField(txtCusto);
		Custo.setLimit(99);

		// txtLucro
		RestrictedTextField Lucro = new RestrictedTextField(txtLucro);
		
		btnLimpar = new JButton("");
		btnLimpar.setIcon(new ImageIcon(Produtos.class.getResource("/img/BtnEraser.png")));
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnLimpar.setBounds(671, 358, 64, 64);
		getContentPane().add(btnLimpar);
		Lucro.setLimit(99);

	}// Fim Construtor

	DAO dao = new DAO();
	private JTable table;
	private JButton btnLimpar;

	/**
	 * Metodo Responsavel pela pesquisa avancada do fornecedor usando filtro
	 */
	private void pesquisarProdutos() {

		String read3 = "select idFor as ID, fantasia as Fornecedor from fornecedores where fantasia like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read3);
			pst.setString(1, txtFornecedor.getText() + "%"); // Atencao "%"
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
		if (txtCodigo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Id do Produto");
			txtCodigo.requestFocus();
		} else {
			String read = "select * from produtos where idFor = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtCodigo.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					
					
					txtBarcode.setText(rs.getString(2));
					txtProduto.setText(rs.getString(3));
					txtaDescricao.setText(rs.getString(4));
					txtFabricante.setText(rs.getString(5));	
					//dateValidade numero 6
					//dateEntrada numero 7
					txtEstoque.setText(rs.getString(8));
					txtEstoquemin.setText(rs.getString(9));
					cboUnidade.setSelectedItem(rs.getString(10));
					txtLocal.setText(rs.getString(11));
					txtCusto.setText(rs.getString(12));;
					txtLucro.setText(rs.getString(13));
					txtIdFor.setText(rs.getString(14));
					
				
					
					/**
					 * Habilitar botoes alterar e excluir
					 */
					btnUpdateProduto.setEnabled(true);
					btnDeleteProduto.setEnabled(true);
					btnLimpar.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Produto não cadastrado");
					txtCodigo.setEnabled(true);
					limpar();
					txtCodigo.requestFocus();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void limpar() {
		txtFornecedor.setText(null);
		txtBarcode.setText(null);
		txtCodigo.setText(null);
		txtProduto.setText(null);
		txtaDescricao.setText(null);
		txtFabricante.setText(null);
		txtEstoque.setText(null);
		txtEstoquemin.setText(null);
		cboUnidade.setSelectedItem("");
		txtLocal.setText(null);
		txtFornecedor.setText(null);
		txtIdFor.setText(null);
		txtCusto.setText(null);
		txtLucro.setText(null);
		
		// Limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);

	}

}