package views;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.DAO;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Relatorios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
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
	public Relatorios() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/icoRelatorios.png")));
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Relatórios");
		setModal(true);
		setBounds(100, 100, 663, 160);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JButton btnReposicao = new JButton("");
		btnReposicao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReposicao.setIcon(new ImageIcon(Relatorios.class.getResource("/img/btnReposicao.png")));
		btnReposicao.setToolTipText("Reposição");
		btnReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reposicaoEstoque();
			}
		});
		btnReposicao.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReposicao.setBounds(530, 32, 64, 64);
		getContentPane().add(btnReposicao);

		JButton btnClientes = new JButton("");
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setIcon(new ImageIcon(Relatorios.class.getResource("/img/btnClientes.png")));
		btnClientes.setToolTipText("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnClientes.setFont(new Font("Verdana", Font.BOLD, 12));
		btnClientes.setBounds(330, 32, 64, 64);
		getContentPane().add(btnClientes);

		JButton btnPrecoDeVenda = new JButton("");
		btnPrecoDeVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrecoDeVenda.setIcon(new ImageIcon(Relatorios.class.getResource("/img/btnPrecoDeVenda.png")));
		btnPrecoDeVenda.setToolTipText("Preço de Venda");
		btnPrecoDeVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				precoDeVenda();
			}
		});
		btnPrecoDeVenda.setFont(new Font("Arial", Font.BOLD, 12));
		btnPrecoDeVenda.setBounds(30, 32, 64, 64);
		getContentPane().add(btnPrecoDeVenda);

		JButton btnProdutosVencidos = new JButton("");
		btnProdutosVencidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutosVencidos.setIcon(new ImageIcon(Relatorios.class.getResource("/img/btnProdutosVencidos.png")));
		btnProdutosVencidos.setToolTipText("Produtos Vencidos");
		btnProdutosVencidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtosVencidos();
			}
		});
		btnProdutosVencidos.setFont(new Font("Verdana", Font.BOLD, 12));
		btnProdutosVencidos.setBounds(130, 32, 64, 64);
		getContentPane().add(btnProdutosVencidos);

		JButton btnValorTotalMercadorias = new JButton("");
		btnValorTotalMercadorias.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValorTotalMercadorias.setIcon(new ImageIcon(Relatorios.class.getResource("/img/btnValorTotalMercadorias.png")));
		btnValorTotalMercadorias.setToolTipText("Valor Total Mercadorias");
		btnValorTotalMercadorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valorTotalMercadorias();
			}
		});
		btnValorTotalMercadorias.setFont(new Font("Verdana", Font.BOLD, 12));
		btnValorTotalMercadorias.setBounds(230, 32, 64, 64);
		getContentPane().add(btnValorTotalMercadorias);

		JButton btnUsuarios = new JButton("");
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setIcon(new ImageIcon(Relatorios.class.getResource("/img/btnUsuarios.png")));
		btnUsuarios.setToolTipText("Usuários");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioUsuarios();
			}
		});
		btnUsuarios.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUsuarios.setBounds(430, 32, 64, 64);
		getContentPane().add(btnUsuarios);

	}

	DAO dao = new DAO();

	
	DecimalFormat moeda = new DecimalFormat("0.00");

	
	private void reposicaoEstoque() {
		
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

		
		try {
			
			PdfWriter.getInstance(document, new FileOutputStream("reposicao.pdf"));
			document.open();
			
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Reposição de estoque"));
			document.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(5);

			
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Validade"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Estoque"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Estoque mínimo"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);

			
			String relReposicao = "select codigo,produto,date_format(dataval,'%d/%m/%Y'), estoque, estoquemin from produtos where estoque < estoquemin";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relReposicao);
				ResultSet rs = pst.executeQuery();

				
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}
	
		try {
			Desktop.getDesktop().open(new File("reposicao.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	} 

	
	private void relatorioClientes() {
		
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

		
		try {
		
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();

		
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Clientes cadastrados"));
			document.add(new Paragraph(" "));

			
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

		
			String relClientes = "select Nome,Telefone,CPF,email from clientes";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relClientes);
				ResultSet rs = pst.executeQuery();

			
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	} 

	
	private void precoDeVenda() {
		
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

	
		try {
		
			PdfWriter.getInstance(document, new FileOutputStream("preco-de-venda.pdf"));
			document.open();

			
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Preço de Venda dos Produtos"));
			document.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Preço de Custo"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Preço de Venda"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			
			String venda = "select codigo, produto, custo, (custo + (custo * lucro) / 100) from produtos;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(venda);
				ResultSet rs = pst.executeQuery();

				
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("preco-de-venda.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	} 

	
	private void produtosVencidos() {
	
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

		
		try {
		
			PdfWriter.getInstance(document, new FileOutputStream("produtos-vencidos.pdf"));
			document.open();

			
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Produtos Vencidos"));
			document.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(5);
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Localização"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Data de Validade"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Dias Vencidos"));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);

			
			String vencidos = "select codigo, produto, localizacao, date_format(dataval, '%d/%m/%Y'), datediff(dataval,curdate()) from produtos where datediff(dataval,curdate()) < 0;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(vencidos);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("produtos-vencidos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	} 

	
	private void valorTotalMercadorias() {
		
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

		
		try {
			
			PdfWriter.getInstance(document, new FileOutputStream("total-mercadorias.pdf"));
			document.open();

			
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Valor total das Mercadorias em Estoque"));
			document.add(new Paragraph(" "));

			
			PdfPTable tabela = new PdfPTable(1);
			PdfPCell col1 = new PdfPCell(new Paragraph("Total"));

			tabela.addCell(col1);

			
			String estoque = "select sum(estoque * custo) from produtos;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(estoque);
				ResultSet rs = pst.executeQuery();

				
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("total-mercadorias.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	} 

	
	private void relatorioUsuarios() {
		
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);

		
		try {
			
			PdfWriter.getInstance(document, new FileOutputStream("usuarios.pdf"));
			document.open();

			
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Relatório de Usuários"));
			document.add(new Paragraph(" "));


			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("ID"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Nome completo"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Login"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Perfil"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			
			String usuarios = "select id, usuario, login, perfil from usuarios;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(usuarios);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("usuarios.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	} 

} 
