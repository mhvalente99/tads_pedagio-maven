package br.mhvalente.view;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import br.mhvalente.dao.PedagioDAO;
import br.mhvalente.domain.Pedagio;

import java.awt.Component;
import javax.swing.JEditorPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

public class PedagioView extends JFrame{
	private JTextField edValorTotal;
	private JTextField edValorPago;
	private JTextField edTroco;
	private JTextField edData;
	private JTextField edSituacao;
	
	private String vVlPedagio;
	private Date dataSistema;
	private SimpleDateFormat dateFormat;
	private JFormattedTextField edPlaca;
	
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public PedagioView(String nome) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataSistema = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		this.setLocationRelativeTo(null);
		this.setSize(880, 563);
		
		getContentPane().setLayout(null);
		
		JButton btnCarro = new JButton("1 - Carro R$ 20,80");
		btnCarro.setBounds(22, 87, 142, 45);
		getContentPane().add(btnCarro);
		
		btnCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVlPedagio = "20,80";
				edValorTotal.setText(vVlPedagio);
				edData.setText(dateFormat.format(dataSistema));
				
				edValorPago.requestFocus();
			}
		});
		
		JButton btnMoto = new JButton("2 - Moto R$ 5,50");
		btnMoto.setBounds(176, 87, 142, 45);
		getContentPane().add(btnMoto);
		
		btnMoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVlPedagio = "5,50";
				edValorTotal.setText(vVlPedagio);
				edData.setText(dateFormat.format(dataSistema));
				edValorPago.requestFocus();
			}
		});
		
		JButton btnOutros = new JButton("3 - Outros R$ 30,20");
		btnOutros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVlPedagio = "30,20";
				edValorTotal.setText(vVlPedagio);
				edData.setText(dateFormat.format(dataSistema));
				edValorPago.requestFocus();
			}
		});
		
		btnOutros.setBounds(330, 87, 142, 45);
		getContentPane().add(btnOutros);
		
		JLabel lblPedgio = new JLabel("Pedágio");
		lblPedgio.setForeground(SystemColor.activeCaption);
		lblPedgio.setBackground(SystemColor.controlHighlight);
		lblPedgio.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblPedgio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedgio.setBounds(0, 0, 880, 38);
		lblPedgio.setOpaque(true); 
		getContentPane().add(lblPedgio);
		
		JLabel lblNewLabel = new JLabel("Fechamento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 177, 500, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Valor Total R$");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(10, 225, 88, 16);
		getContentPane().add(lblNewLabel_1);
		
		edValorTotal = new JTextField();
		edValorTotal.setBackground(new Color(192, 192, 192));
		edValorTotal.setForeground(Color.BLUE);
		edValorTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		edValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		edValorTotal.setEditable(false);
		edValorTotal.setBounds(111, 220, 130, 26);
		getContentPane().add(edValorTotal);
		edValorTotal.setColumns(10);
		
		JLabel lblValorPagoR = new JLabel("Valor Pago R$");
		lblValorPagoR.setHorizontalAlignment(SwingConstants.TRAILING);
		lblValorPagoR.setBounds(10, 256, 88, 16);
		getContentPane().add(lblValorPagoR);
		
		edValorPago = new JTextField();
		edValorPago.setForeground(new Color(0, 128, 128));
		edValorPago.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		edValorPago.setHorizontalAlignment(SwingConstants.CENTER);
		edValorPago.setColumns(10);
		edValorPago.setBounds(111, 251, 130, 26);
		getContentPane().add(edValorPago);
		
		edValorPago.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Double valorTotal = Double.parseDouble(vVlPedagio.replace(",", "."));
				Double valorPago = Double.parseDouble(edValorPago.getText().replace(",", "." ));
				
				if (valorPago < valorTotal) {
					JOptionPane.showMessageDialog(null, "O valor pago deve ser maior que o valor total");
					edValorPago.requestFocusInWindow();
				}
				else {
				Double troco = valorPago - valorTotal;
				
				edTroco.setText(String.format("%.2f", troco).replace("." , ","));
				}
			}
		});
		
		JLabel lblTrocoR = new JLabel("Troco R$");
		lblTrocoR.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTrocoR.setBounds(10, 289, 88, 16);
		getContentPane().add(lblTrocoR);
		
		edTroco = new JTextField();
		edTroco.setBackground(new Color(192, 192, 192));
		edTroco.setEditable(false);
		edTroco.setForeground(new Color(165, 42, 42));
		edTroco.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		edTroco.setHorizontalAlignment(SwingConstants.CENTER);
		edTroco.setColumns(10);
		edTroco.setBounds(111, 283, 130, 26);
		getContentPane().add(edTroco);
		
		JLabel lblData = new JLabel("Data");
		lblData.setHorizontalAlignment(SwingConstants.TRAILING);
		lblData.setBounds(265, 256, 76, 16);
		getContentPane().add(lblData);
		
		edData = new JTextField();
		edData.setEditable(false);
		edData.setBackground(new Color(112, 128, 144));
		edData.setColumns(10);
		edData.setBounds(353, 251, 130, 26);
		getContentPane().add(edData);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPlaca.setBounds(265, 225, 76, 16);
		getContentPane().add(lblPlaca);
		
		JButton btnFinalizar = new JButton("Finalizar [F8]");
		btnFinalizar.setBounds(112, 337, 253, 45);
		getContentPane().add(btnFinalizar);
		
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double valorTotal = Double.parseDouble(vVlPedagio.replace(",", "."));
				Double valorPago = Double.parseDouble(edValorPago.getText().replace(",", "." ));
				Double troco = valorPago - valorTotal;
				String placa = edPlaca.getText();
				
				Date data = null;
				
				try {
					data = new SimpleDateFormat("dd/MM/yyyy").parse(edData.getText());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
//				edSituacao.setForeground(Color.GREEN);
//				edSituacao.setText("FINALIZADO");
				
				try {
					Thread.sleep(1000);
					
					edData.setText("");
					edPlaca.setText("");
					edValorPago.setText("");
					edValorTotal.setText("");
					edTroco.setText("");
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				//Inserir no banco
				Pedagio pedagio = new Pedagio();
			
				pedagio.setData(data);
				pedagio.setPlaca(placa);
				pedagio.setValorRecebido(valorPago);
				pedagio.setValorTotal(valorTotal);
				pedagio.setValorTroco(troco);
				
			
				PedagioDAO pedagioDAO = new PedagioDAO();
				
				try {
					pedagio = pedagioDAO.cadastrar(pedagio);
					JOptionPane.showMessageDialog(null, "Pedagio cadastrado com sucesso.");
					pesquisar();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		JLabel lblSituao = new JLabel("Situação");
		lblSituao.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSituao.setBounds(265, 284, 76, 16);
		getContentPane().add(lblSituao);
		
		edSituacao = new JTextField();
		edSituacao.setForeground(Color.RED);
		edSituacao.setHorizontalAlignment(SwingConstants.CENTER);
		edSituacao.setText("PENDENTE");
		edSituacao.setColumns(10);
		edSituacao.setBounds(353, 279, 130, 26);
		getContentPane().add(edSituacao);
	
		MaskFormatter maskPlaca = null;
		try {
			maskPlaca = new MaskFormatter("UUU-####");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		edPlaca = new JFormattedTextField(maskPlaca);
		edPlaca.setBounds(354, 220, 130, 26);
		
		getContentPane().add(edPlaca);
		
		JPanel panel = new JPanel();
		panel.setBounds(501, 208, 379, 333);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		final JTable tabela = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(tabela);
		
		//Desabilita a edicao da celula da tabela
		tabela.setDefaultEditor(Object.class, null);
		
		modelo.addColumn("Cód.");
		modelo.addColumn("Data");
		modelo.addColumn("Placa");
		modelo.addColumn("Vl. Total");
		modelo.addColumn("Vl. Recebido");
		modelo.addColumn("Vl. Troco");
		
		
		scrollPane.setBounds(0, 0, 379, 333);
		panel.add(scrollPane);
		
		JLabel lblUltimosFechados = new JLabel("Ultimos Fechados");
		lblUltimosFechados.setOpaque(true);
		lblUltimosFechados.setHorizontalAlignment(SwingConstants.CENTER);
		lblUltimosFechados.setForeground(Color.WHITE);
		lblUltimosFechados.setBackground(Color.BLUE);
		lblUltimosFechados.setBounds(501, 177, 379, 31);
		getContentPane().add(lblUltimosFechados);
		
		JLabel lblUsurio = new JLabel("Operador");
		lblUsurio.setOpaque(true);
		lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsurio.setForeground(Color.WHITE);
		lblUsurio.setBackground(new Color(0, 206, 209));
		lblUsurio.setBounds(0, 404, 500, 31);
		getContentPane().add(lblUsurio);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 432, 500, 109);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(6, 6, 91, 97);
		panel_1.add(panel_2);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBackground(UIManager.getColor("RadioButton.disabledText"));
		lblNome.setBounds(109, 6, 61, 16);
		panel_1.add(lblNome);
		lblNome.setText(nome);
		
		JLabel lblPraa = new JLabel("Praça");
		lblPraa.setBounds(109, 25, 61, 16);
		panel_1.add(lblPraa);
		
		JLabel lblPista = new JLabel("Pista");
		lblPista.setBounds(109, 47, 61, 16);
		panel_1.add(lblPista);
		
		JLabel label = new JLabel("Fechamento");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBackground(SystemColor.textHighlight);
		label.setBounds(0, 177, 500, 31);
		getContentPane().add(label);
		
		JLabel lblVeiculo = new JLabel("Veiculo");
		lblVeiculo.setOpaque(true);
		lblVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeiculo.setForeground(Color.WHITE);
		lblVeiculo.setBackground(new Color(0, 191, 255));
		lblVeiculo.setBounds(0, 37, 500, 31);
		getContentPane().add(lblVeiculo);
		
		JLabel lblOpes = new JLabel("Opções");
		lblOpes.setOpaque(true);
		lblOpes.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpes.setForeground(Color.WHITE);
		lblOpes.setBackground(new Color(0, 206, 209));
		lblOpes.setBounds(501, 37, 379, 31);
		getContentPane().add(lblOpes);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(501, 69, 379, 109);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				int index = tabela.getSelectedRow();
				if (JOptionPane.showConfirmDialog(null, "Deseja realmente EXCLUIR o lançamento ?", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			
					PedagioDAO pedagioDAO = new PedagioDAO();
				
					try {
						if (index >= 0) {
							Integer codigo = (Integer) tabela.getModel().getValueAt(index, 0);
							
					 		Pedagio pedagio = new Pedagio();
					 		pedagio = pedagioDAO.buscarPedagio(codigo);
					 		
							pedagioDAO.excluir(pedagio);
					 		pesquisar();
						} else {
							 throw new Exception("Nenhum item selecionado !");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro ao excluir pedagio \n\n" + e1.getMessage());
					}
				}
				
				
			}
		});
		
		btnExcluir.setBounds(256, 31, 103, 42);
		panel_3.add(btnExcluir);
		//getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnCarro, btnMoto, btnOutros, edValorPago, edData, btnFinalizar}));
		pesquisar();
		this.setVisible(true);
	}
	
	
	private void pesquisar() {
		PedagioDAO dao = new PedagioDAO();
		
		try {
			List<Pedagio> lista = dao.buscarPedagio();
		
			modelo.setNumRows(0);
		
			for (Pedagio p : lista) {
				String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(p.getData());
				modelo.addRow(new Object [] {
					p.getCodigo(),
					dataFormatada,
					p.getPlaca(),
					p.getValorTotal(),
					p.getValorRecebido(),
					p.getValorTroco()
				});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar \n\n" + e.getMessage());
		}
	}
}