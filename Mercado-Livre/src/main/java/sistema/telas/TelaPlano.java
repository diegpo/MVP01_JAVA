package sistema.telas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistema.dao.ModuloConexao;
import sistema.tabelas.Plano;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TelaPlano extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNomePlano;
	private JTextField txtValorPlano;
	private JTable table;
	
	public ArrayList<Plano> planoList(){
		ArrayList<Plano> planoList = new ArrayList<>();
		try {
			Connection conexao = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			conexao = ModuloConexao.conector();
			
			String sql = "select * from tb_planos";
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			Plano plano;
			
			while(rs.next()) {
				plano = new Plano(rs.getInt("id"), rs.getString("nomeplano"), rs.getFloat("valorplano")); 
				planoList.add(plano);
			}				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return planoList;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPlano frame = new TelaPlano();
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
	private JTextField txtConsultaPlano;
	
	public TelaPlano() {
		
		conexao = ModuloConexao.conector();			
		
		setBorder(null);
		setClosable(true);
		setBackground(UIManager.getColor("Button.shadow"));
		setIconifiable(true);

		setTitle("Cadastro Clientes");
		setBounds(100, 100, 754, 511);
		
		getContentPane().setBackground(UIManager.getColor("InternalFrame.resizeIconHighlight"));
		getContentPane().setForeground(UIManager.getColor("CheckBox.light"));
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("* Nome");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(10, 91, 76, 14);
		getContentPane().add(lblNome);
		
		txtNomePlano = new JTextField();
		txtNomePlano.setFont(new Font("Arial", Font.BOLD, 13));
		txtNomePlano.setColumns(10);
		txtNomePlano.setBounds(96, 85, 184, 20);
		getContentPane().add(txtNomePlano);
		
		JLabel lblDocumento = new JLabel("* Valor");
		lblDocumento.setFont(new Font("Arial", Font.BOLD, 14));
		lblDocumento.setBounds(9, 119, 123, 14);
		getContentPane().add(lblDocumento);
		
		txtValorPlano = new JTextField();
		txtValorPlano.setFont(new Font("Arial", Font.BOLD, 13));
		txtValorPlano.setColumns(10);
		txtValorPlano.setBounds(96, 113, 184, 20);
		getContentPane().add(txtValorPlano);
		
		JButton btnNewButton = new JButton("Incluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Adicionar();
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 402, 104, 35);
		getContentPane().add(btnNewButton);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alterar();
			}
		});
		btnAlterar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnAlterar.setBounds(149, 402, 104, 35);
		getContentPane().add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Consultar();
			}
		});
		btnConsultar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnConsultar.setBounds(288, 402, 123, 35);
		getContentPane().add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnExcluir.setBounds(448, 402, 104, 35);
		getContentPane().add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 229, 734, 143);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SetarCampo();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {
			
		},new String[] {"Código", "Nome", "Valor"}) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		Show_plano();
		
		JLabel lblCamposObrigatrios = new JLabel("* Campos obrigatórios");
		lblCamposObrigatrios.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCamposObrigatrios.setBounds(611, 11, 133, 14);
		getContentPane().add(lblCamposObrigatrios);
		
		JLabel lblConsulta = new JLabel("Consulta ID");
		lblConsulta.setFont(new Font("Arial", Font.BOLD, 14));
		lblConsulta.setBounds(10, 12, 122, 14);
		getContentPane().add(lblConsulta);
		
		txtConsultaPlano = new JTextField();
		txtConsultaPlano.setFont(new Font("Arial", Font.PLAIN, 13));
		txtConsultaPlano.setColumns(10);
		txtConsultaPlano.setBounds(103, 8, 184, 20);
		getContentPane().add(txtConsultaPlano);
	}	
	
	private void Adicionar() {		
				String sql = "insert into tb_planos (nomeplano, valorplano) values (?,?)";
				try {
					pst = conexao.prepareStatement(sql);
					pst.setString(1, txtNomePlano.getText());
					pst.setFloat(2, Float.parseFloat(txtValorPlano.getText()));
					if (txtNomePlano.getText() .isEmpty()) {
							JOptionPane.showMessageDialog(null, "Campos obrigatórios não foram preenchidos!");
						} else {
						int adicionado = pst.executeUpdate();
						if (adicionado > 0) {
							JOptionPane.showMessageDialog(null, "Plano adicionado com sucesso!");
							txtNomePlano.setText(null);
							txtValorPlano.setText(null);
						} else {
							
						}
					}
				}catch(Exception e){					
					JOptionPane.showMessageDialog(null, e);
				}						
			}
	
	private void Consultar() {
		String sql = "select * from tb_planos where id = ?";				
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(txtConsultaPlano.getText()));			
			rs = pst.executeQuery();
			if (rs.next()) {
				txtNomePlano.setText(rs.getString(2));
				txtValorPlano.setText(rs.getString(3));
			} else {
				JOptionPane.showMessageDialog(null, "Plano não encontrado!");
				txtNomePlano.setText(null);
				txtValorPlano.setText(null);
			}			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void Alterar() {		
		String sql = "update tb_planos set nomeplano = ?, valorplano = ? where id = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(3, Integer.parseInt(txtConsultaPlano.getText()));
			pst.setString(1, txtNomePlano.getText());
			//pst.setString(2, txtValorPlano.getText());
			pst.setFloat(2, Float.parseFloat(txtValorPlano.getText()));
			if (txtConsultaPlano.getText() .isEmpty() || (txtNomePlano.getText() .isEmpty()) || txtValorPlano.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Campos obrigatórios não foram preenchidos!");
			} else {
			int adicionado = pst.executeUpdate();
			if (adicionado > 0) {
				JOptionPane.showMessageDialog(null, "Plano alterado com sucesso!");
				txtConsultaPlano.setText(null);
				txtNomePlano.setText(null);
				txtValorPlano.setText(null);
			} else {

			}
		}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}		
	}
	private void Excluir() {
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este plano?", "Atenção", JOptionPane.YES_NO_OPTION);
		if(confirma==JOptionPane.YES_OPTION) {
			String sql = "delete from tb_planos where id = ?";
			try {
				pst = conexao.prepareStatement(sql);				
				pst.setInt(1, Integer.parseInt(txtConsultaPlano.getText()));
				int apagado = pst.executeUpdate();
				if(apagado > 0) {
					JOptionPane.showMessageDialog(null, "Plano removido com sucesso!");
					txtNomePlano.setText(null);
					txtValorPlano.setText(null);
					txtConsultaPlano.setText(null);
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}			
		}			
	}
	
	public void Show_plano() {
		ArrayList<Plano> list = planoList();
  		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row = new Object[3];
		for(int i = 0; i < list.size(); i++) {
			row[0]=list.get(i).idplano();
			row[1]=list.get(i).nomeplano();
			row[2]=list.get(i).valorplano();
			model.addRow(row);
		}
	}
	
	public void SetarCampo() {
		int setar = table.getSelectedRow();
		txtConsultaPlano.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNomePlano.setText(table.getModel().getValueAt(setar, 1).toString());
		txtValorPlano.setText(table.getModel().getValueAt(setar,2).toString());
	}
}
