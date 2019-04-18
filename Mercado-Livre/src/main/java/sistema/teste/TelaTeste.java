package sistema.teste;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sistema.dao.ModuloConexao;
import sistema.tabelas.Contrato;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TelaTeste extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtConsulta;
	private JTextField txtIdCli;
	private JTextField txtValor;
	private JTable table;
	private JTextField txtVenc;
	private JTextField txtObs;
	
	public ArrayList<Contrato> contratoList(){
		ArrayList<Contrato> contratoList = new ArrayList<>();
		try {
			Connection conexao = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			conexao = ModuloConexao.conector();
			
			String sql = "select * from tb_contratos "
					+ "inner join tb_clientes on tb_clientes.id = tb_contratos.idcliente "
					+ "inner join tb_planos on tb_planos.id = tb_contratos.idplano";
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			Contrato contrato;
			
			while(rs.next()) {
				contrato = new Contrato(rs.getInt("id"), rs.getString("nomecli"), rs.getString("nomeplano"), rs.getFloat("valorplano")); 
				contratoList.add(contrato);
			}				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return contratoList;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTeste frame = new TelaTeste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField ajuda;
	private JTextField textField;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaTeste() {
		
		conexao = ModuloConexao.conector();
		
		setBorder(null);
		setClosable(true);
		setBackground(UIManager.getColor("Button.shadow"));
		setIconifiable(true);

		setTitle("Cadastro Contratos");
		setBounds(100, 100, 754, 511);
		
		getContentPane().setBackground(UIManager.getColor("InternalFrame.resizeIconHighlight"));
		getContentPane().setForeground(UIManager.getColor("CheckBox.light"));
		getContentPane().setLayout(null);
		
		JLabel lblIdcliente = new JLabel("* Id Contrato");
		lblIdcliente.setFont(new Font("Arial", Font.BOLD, 14));
		lblIdcliente.setBounds(10, 40, 95, 14);
		getContentPane().add(lblIdcliente);
		
		txtConsulta = new JTextField();
		txtConsulta.setFont(new Font("Arial", Font.BOLD, 13));
		txtConsulta.setBounds(115, 37, 99, 20);
		getContentPane().add(txtConsulta);
		txtConsulta.setColumns(10);
		
		JLabel lblNome = new JLabel("* Id Cliente");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(454, 40, 76, 14);
		getContentPane().add(lblNome);
		
		txtIdCli = new JTextField();
		txtIdCli.setFont(new Font("Arial", Font.BOLD, 13));
		txtIdCli.setColumns(10);
		txtIdCli.setBounds(531, 37, 92, 20);
		getContentPane().add(txtIdCli);
		
		JLabel lblDocumento = new JLabel("* Valor");
		lblDocumento.setFont(new Font("Arial", Font.BOLD, 14));
		lblDocumento.setBounds(10, 173, 60, 14);
		getContentPane().add(lblDocumento);
		
		txtValor = new JTextField();
		txtValor.setFont(new Font("Arial", Font.BOLD, 13));
		txtValor.setColumns(10);
		txtValor.setBounds(115, 170, 180, 20);
		getContentPane().add(txtValor);
		
		JButton btnNewButton = new JButton("Incluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnNewButton.setBounds(438, 261, 104, 35);
		getContentPane().add(btnNewButton);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnAlterar.setBounds(159, 261, 104, 35);
		getContentPane().add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String receber = JOptionPane.showInputDialog(null, "Código do contrato: ");
				String sql = "select * from tb_contratos where id = ?";
				try {
					pst = conexao.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(receber));			
					rs = pst.executeQuery();
					if (rs.next()) {
						txtConsulta.setText(rs.getString(1));
						txtIdCli.setText(rs.getString(2));
						txtValor.setText(rs.getString(5));
						SimpleDateFormat dataconv = new SimpleDateFormat("dd-MM-yyyy");				
						txtVenc.setText(dataconv.format(rs.getDate(4)));	
						//Validação do plano
						if(rs.getInt(3) == 1) {
							ajuda.setText("A");	
						}else if(rs.getInt(3) == 2){
							ajuda.setText("B");
						}else if(rs.getInt(3) == 3){
							ajuda.setText("C");
						}						
					} else {
						JOptionPane.showMessageDialog(null, "Contrato não encontrado!");
						txtIdCli.setText(null);
						txtValor.setText(null);
						txtObs.setText(null);
						txtVenc.setText(null);
						ajuda.setText(null);
					}			
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnConsultar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnConsultar.setBounds(10, 261, 123, 35);
		getContentPane().add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnExcluir.setBounds(299, 261, 104, 35);
		getContentPane().add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 307, 734, 167);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {
			
		},new String[] {"Código", "Nome", "Plano", "Valor"}) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		Show_contrato();

		
		
		JLabel lblPlano = new JLabel("* Plano");
		lblPlano.setFont(new Font("Arial", Font.BOLD, 14));
		lblPlano.setBounds(211, 81, 65, 14);
		getContentPane().add(lblPlano);
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"", "A", "B", "C"}));
		comboBox1.setBounds(275, 79, 110, 20);
		getContentPane().add(comboBox1);
		
		
		JLabel lblVencimento = new JLabel("* Vencimento");
		lblVencimento.setFont(new Font("Arial", Font.BOLD, 14));
		lblVencimento.setBounds(10, 137, 92, 14);
		getContentPane().add(lblVencimento);
		
		txtVenc = new JTextField();
		txtVenc.setFont(new Font("Arial", Font.BOLD, 13));
		txtVenc.setColumns(10);
		txtVenc.setBounds(115, 134, 180, 20);
		getContentPane().add(txtVenc);
		
		JLabel lblCamposObrigatrios = new JLabel("* Campos obrigatórios");
		lblCamposObrigatrios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCamposObrigatrios.setBounds(613, 12, 131, 14);
		getContentPane().add(lblCamposObrigatrios);
		
		txtObs = new JTextField();
		txtObs.setFont(new Font("Arial", Font.BOLD, 13));
		txtObs.setColumns(10);
		txtObs.setBounds(115, 203, 376, 20);
		getContentPane().add(txtObs);
		
		JLabel lblObs = new JLabel("Obs");
		lblObs.setFont(new Font("Arial", Font.BOLD, 14));
		lblObs.setBounds(10, 206, 44, 14);
		getContentPane().add(lblObs);
		
		ajuda = new JTextField();
		ajuda.setEditable(false);
		ajuda.setBounds(115, 79, 60, 20);
		getContentPane().add(ajuda);
		ajuda.setColumns(10);
		
		JLabel lblPlano_1 = new JLabel("* Plano");
		lblPlano_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblPlano_1.setBounds(10, 81, 76, 14);
		getContentPane().add(lblPlano_1);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
		lblCliente.setBounds(454, 77, 71, 14);
		getContentPane().add(lblCliente);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(531, 75, 213, 20);
		getContentPane().add(textField);
	}
	/*
	private void Consultar() {
		String sql = "select * from tb_contratos where id = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(txtConsulta.getText()));			
			rs = pst.executeQuery();
			if (rs.next()) {
				txtIdCli.setText(rs.getString(2));
				txtValor.setText(rs.getString(3));
				txtObs.setText(rs.getString(5));
				SimpleDateFormat dataconv = new SimpleDateFormat("dd-MM-yyyy");				
				txtVenc.setText(dataconv.format(rs.getDate(5)));				
				if(rs.getInt(3) == 1) {
					ajuda.setText("A");	
				}else if(rs.getInt(3) == 2){
					ajuda.setText("B");
				}else if(rs.getInt(3) == 3){
					ajuda.setText("C");
				}else {
					//
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Contrato não encontrado!");
				txtIdCli.setText(null);
				txtValor.setText(null);
				txtObs.setText(null);
				txtVenc.setText(null);
				ajuda.setText(null);
			}			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	*/
	public void Show_contrato() {
		ArrayList<Contrato> list = contratoList();
  		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row = new Object[11];
		for(int i = 0; i < list.size(); i++) {
			row[0]=list.get(i).getidcontrato();
			row[1]=list.get(i).getnomecli();
			row[2]=list.get(i).getnomeplano();
			row[3]=list.get(i).getValorplano();
			model.addRow(row);
		}
	}
	
	public void SetarCampo() {
		int setar = table.getSelectedRow();
		txtConsulta.setText(table.getModel().getValueAt(setar, 1).toString());
		txtIdCli.setText(table.getModel().getValueAt(setar,2).toString());
		
	}
}
