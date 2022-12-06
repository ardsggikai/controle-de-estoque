package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.JButton;

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
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		setTitle("Relat\u00F3rios");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btnReposicao = new JButton("Reposi\u00E7\u00E3o");
		btnReposicao.setFocusPainted(false);
		btnReposicao.setContentAreaFilled(false);
		btnReposicao.setBounds(29, 40, 105, 23);
		getContentPane().add(btnReposicao);

	}
}
