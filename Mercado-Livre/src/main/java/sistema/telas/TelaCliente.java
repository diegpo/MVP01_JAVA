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
import sistema.tabelas.Cliente;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCliente extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNomeCli;
	private JTextField txtDocCli;
	private JTable table;
	
	
	public ArrayList<Cliente> clienteList(){
		ArrayList<Cliente> clientesList = new ArrayList<>();
		try {
			Connection conexao = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			conexao = ModuloConexao.conector();
			
			String sql = "select * from tb_clientes";
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			Cliente cliente;
			
			while(rs.next()) {
				cliente = new Cliente(rs.getInt("id"), rs.getString("nomecli"), rs.getString("documentocli")); 
				clientesList.add(cliente);
			}				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return clientesList;
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	private JTextField txtConsultaId;
	private JTextField txtConsulta;
	
	public TelaCliente() {
		
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
		
		txtNomeCli = new JTextField();
		txtNomeCli.setFont(new Font("Arial", Font.BOLD, 13));
		txtNomeCli.setColumns(10);
		txtNomeCli.setBounds(96, 85, 373, 20);
		getContentPane().add(txtNomeCli);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setFont(new Font("Arial", Font.BOLD, 14));
		lblDocumento.setBounds(9, 119, 123, 14);
		getContentPane().add(lblDocumento);
		
		txtDocCli = new JTextField();
		txtDocCli.setFont(new Font("Arial", Font.BOLD, 13));
		txtDocCli.setColumns(10);
		txtDocCli.setBounds(96, 113, 373, 20);
		getContentPane().add(txtDocCli);
		
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
			public void mouseClicked(MouseEvent arg0) {
				SetarCampo();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {
			
		},new String[] {"Codigo", "Nome", "Documento"}) {

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
		show_client();
		
		
		
		JLabel lblCamposObrigatrios = new JLabel("* Campos obrigatórios");
		lblCamposObrigatrios.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCamposObrigatrios.setBounds(611, 11, 133, 14);
		getContentPane().add(lblCamposObrigatrios);
		
		JLabel lblConsulta = new JLabel("Consulta ID");
		lblConsulta.setFont(new Font("Arial", Font.BOLD, 14));
		lblConsulta.setBounds(10, 12, 122, 14);
		getContentPane().add(lblConsulta);
		
		txtConsultaId = new JTextField();
		txtConsultaId.setFont(new Font("Arial", Font.PLAIN, 13));
		txtConsultaId.setColumns(10);
		txtConsultaId.setBounds(103, 8, 184, 20);
		getContentPane().add(txtConsultaId);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
		lblCliente.setBounds(10, 196, 122, 14);
		getContentPane().add(lblCliente);
		
		txtConsulta = new JTextField();
		txtConsulta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			
			}
		});
		txtConsulta.setFont(new Font("Arial", Font.PLAIN, 13));
		txtConsulta.setColumns(10);
		txtConsulta.setBounds(96, 193, 373, 20);
		getContentPane().add(txtConsulta);
	}	
	
	private void Adicionar() {		
				String sql = "insert into tb_clientes (nomecli, documentocli) values (?,?)";
				try {
					pst = conexao.prepareStatement(sql);
					pst.setString(1, txtNomeCli.getText());
					pst.setString(2, txtDocCli.getText());
					if (txtNomeCli.getText() .isEmpty()) {
							JOptionPane.showMessageDialog(null, "Campos obrigatórios não foram preenchidos!");
						} else {
						int adicionado = pst.executeUpdate();
						if (adicionado > 0) {
							JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
							txtNomeCli.setText(null);
							txtDocCli.setText(null);
							
						} else {
							
						}
					}
				}catch(Exception e){					
					JOptionPane.showMessageDialog(null, e);
				}				
			}
	
	private void Consultar() {
		String sql = "select * from tb_clientes where id = ?";				
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(txtConsultaId.getText()));			
			rs = pst.executeQuery();
			if (rs.next()) {
				txtNomeCli.setText(rs.getString(2));
				txtDocCli.setText(rs.getString(3));
			} else {
				JOptionPane.showMessageDialog(null, "Id não encontrado!");
				txtNomeCli.setText(null);
				txtDocCli.setText(null);
				
			}			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void Alterar() {		
		String sql = "update tb_clientes set nomecli = ?, documentocli = ? where id = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(3, Integer.parseInt(txtConsultaId.getText()));
			pst.setString(1, txtNomeCli.getText());
			pst.setString(2, txtDocCli.getText());						
			if (txtConsultaId.getText() .isEmpty() || (txtNomeCli.getText() .isEmpty())) {
				JOptionPane.showMessageDialog(null, "Campos obrigatórios não foram preenchidos!");
			} else {
			int adicionado = pst.executeUpdate();
			if (adicionado > 0) {
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
				txtConsultaId.setText(null);
				txtNomeCli.setText(null);
				txtDocCli.setText(null);
				
			} else {

			}
		}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}		
	}
	private void Excluir() {
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
		if(confirma==JOptionPane.YES_OPTION) {
			String sql = "delete from tb_clientes where id = ?";
			try {
				pst = conexao.prepareStatement(sql);				
				pst.setInt(1, Integer.parseInt(txtConsultaId.getText()));
				int apagado = pst.executeUpdate();
				if(apagado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
					txtNomeCli.setText(null);
					txtDocCli.setText(null);
					txtConsultaId.setText(null);
					
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}			
		}			
	}
	
	public void show_client() {
		ArrayList<Cliente> list = clienteList();
  		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row = new Object[3];
		for(int i = 0; i < list.size(); i++) {
			row[0]=list.get(i).getidcliente();
			row[1]=list.get(i).getnomecli();
			row[2]=list.get(i).getdocumentocli();
			model.addRow(row);
		}
	}
	
	public void PesquisaA() {
		String sql = "select * from tb_clientes where nomecli like ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtConsulta.getText()+ "%");
			rs = pst.executeQuery();
			table.getModel();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void SetarCampo() {
		int setar = table.getSelectedRow();
		txtNomeCli.setText(table.getModel().getValueAt(setar, 1).toString());
		txtDocCli.setText(table.getModel().getValueAt(setar,2).toString());
		txtConsultaId.setText(table.getModel().getValueAt(setar, 0).toString());
	}
}
