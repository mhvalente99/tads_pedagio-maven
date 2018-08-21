package br.mhvalente.view;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.mhvalente.dao.LoginDAO;
import br.mhvalente.domain.Usuario;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LoginView extends JFrame{
	private JTextField txLogin;
	private JPasswordField txSenha;
	DefaultTableModel modelo = new DefaultTableModel();
	
	public LoginView() {
		getContentPane().setLayout(null);
		this.setSize(384, 497);
		this.setTitle("Login Pedagio");
		
		txLogin = new JTextField();
		txLogin.setBounds(40, 146, 303, 38);
		getContentPane().add(txLogin);
		txLogin.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(45, 129, 61, 16);
		getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(47, 196, 61, 16);
		getContentPane().add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDAO login = new LoginDAO();
				
				String usuario = txLogin.getText();
				String senha = new String(txSenha.getPassword());
				//List<Usuario> lista = new ArrayList<Usuario>();
				Usuario user = new Usuario();
				
				if (usuario.equals("")) {
					JOptionPane.showMessageDialog(null, "Login deve ser informado");
					txLogin.requestFocus();
				} else {
					try {
						user = login.consultar(usuario, senha);
						
						if (user == null) {
							JOptionPane.showMessageDialog(null, "Login ou senha incorreto(s)");
							throw new Exception("Login ou senha incorreto(s)");
						} else {
							//JOptionPane.showMessageDialog(null, "Bem vindo " + lista.get(0).getNome());						
							JOptionPane.showMessageDialog(null, "Bem vindo " + user.getNome());
							
							PedagioView pedagio = new PedagioView(user.getNome());
							pedagio.setLocationRelativeTo(null);
					
							//dispose();
							setVisible(false);
						}
					} catch (Exception e1) {
						//JOptionPane.showMessageDialog(null, "Erro ao buscar usuario \n\n" + e1.getMessage());
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});
		btnEntrar.setBounds(129, 288, 117, 29);
		getContentPane().add(btnEntrar);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.light"));
		panel.setForeground(UIManager.getColor("Button.light"));
		panel.setBounds(0, 0, 384, 58);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPedagio = new JLabel("Pedagio");
		lblPedagio.setForeground(UIManager.getColor("Button.highlight"));
		lblPedagio.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblPedagio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedagio.setBounds(96, 19, 198, 31);
		panel.add(lblPedagio);
		
		txSenha = new JPasswordField();
		txSenha.setBounds(40, 223, 303, 38);
		getContentPane().add(txSenha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 330, 384, 139);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		modelo.addColumn("Cod .");
		modelo.addColumn("Nome");
		modelo.addColumn("Login");
		
		final JTable tabela = new JTable(modelo);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(0, 0, 384, 139);
		panel_1.add(scrollPane);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabela.getSelectedRow();
				String login = (String) tabela.getModel().getValueAt(index, 2);
				txLogin.setText(login);
			}
		});
		btnSelecionar.setBounds(261, 288, 117, 29);
		getContentPane().add(btnSelecionar);
		
		pesquisar();
		this.setVisible(true);
		this.repaint();
	}
	
	public void pesquisar() {
		LoginDAO dao = new LoginDAO();
		
		try {
			List<Usuario> users = dao.getUsers();
			
			for (Usuario u : users ) {
				modelo.addRow(new Object[] {
						u.getCodigo(),
						u.getNome(),
						u.getLogin()
				});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
