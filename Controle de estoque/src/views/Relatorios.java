package views;

import java.awt.EventQueue;

import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.DAO;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Relatorios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnReposicao;

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
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		setTitle("Relat\u00F3rios");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		btnReposicao = new JButton("Reposi\u00E7\u00E3o");
		btnReposicao.setFont(new Font("Arial", Font.PLAIN, 11));
		btnReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reposicaoEstoque();
			}
		});
		btnReposicao.setFocusPainted(false);
		btnReposicao.setContentAreaFilled(false);
		btnReposicao.setBounds(29, 40, 105, 23);
		getContentPane().add(btnReposicao);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnClientes.setFont(new Font("Arial", Font.PLAIN, 11));
		btnClientes.setFocusPainted(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(29, 83, 105, 23);
		getContentPane().add(btnClientes);

	}// fim do Construtor

	DAO dao = new DAO();

	//método responsável pela impressão do relatório de Reposicao
	private void reposicaoEstoque() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("Reposicao.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// documento.add adiciona um paragrafo
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Reposição de estoque"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(5); // 5 Colunas
			// Cabecalho da tabela
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
			// Acessar o banco de dados // Logica Principal
			String relReposicao = "select codigo,produto,date_format(dataval,'%d/%m/%Y'), estoque, estoquemin from produtos where estoque < estoquemin";
			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituicao de parametros)
				PreparedStatement pst = con.prepareStatement(relReposicao);
				ResultSet rs = pst.executeQuery();
				/**
				 *  enquanto houver dados na tabela no banco, obter valor
				 */
				while (rs.next()) {
					//tablea.addCell1 adiciona a celula
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("reposicao.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//método responsável pela impressão do relatório de clientes
		private void relatorioClientes() {
			//criar objeto para construir a página pdf
			Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
			//gerar o documento pdf
			try {
				//cria um documento pdf em branco de nome clientes.pdf
				PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
				document.open();
				//gerar o conteúdo do documento
				Date data = new Date();			
		        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				document.add(new Paragraph(new Paragraph(formatador.format(data))));
				document.add(new Paragraph(" "));
				document.add(new Paragraph("Clientes cadastrados"));
				document.add(new Paragraph(" "));
				//... Demais conteúdos (imagem, tabela, gráfico, etc)
				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Email"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				// Acessar o banco de dados
				String relClientes = "select nome,Telefone,cpf,email from clientes";
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
				//Adicionar a tabela ao documento pdf
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			} finally { //executa o código independente do resultado OK ou não
				document.close();
			}
			
			//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
			try {
				Desktop.getDesktop().open(new File("clientes.pdf"));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
}