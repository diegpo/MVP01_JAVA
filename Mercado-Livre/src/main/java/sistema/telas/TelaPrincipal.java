package sistema.telas;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sistema.teste.TelaTeste;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setResizable(false);
		setTitle("Sistema MVP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 560);
		 this.setLocationRelativeTo(null);
		 
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.darkShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 285, 410, -273);
		contentPane.add(desktopPane);
			
		JDesktopPane desktop = new JDesktopPane();
		desktop.setBackground(SystemColor.activeCaption);
		desktop.setBounds(0, 0, 754, 511);
		contentPane.add(desktop);
		
		JMenuBar menBar = new JMenuBar();
		setJMenuBar(menBar);
		
		JMenu menCad = new JMenu("Cadastros");
		menBar.add(menCad);
		
		JMenuItem menCli = new JMenuItem("Clientes");
		menCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				desktop.add(cliente);
				try {
					cliente.setMaximum(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
		menCad.add(menCli);
		
		this.setIconImage(new ImageIcon(getClass().getResource("/sistema/icones/mvp_logo.png")).getImage());
		
		JMenuItem MenPlan = new JMenuItem("Planos");
		MenPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPlano tabela = new TelaPlano();
				tabela.setVisible(true);
				desktop.add(tabela);
				try {
					tabela.setMaximum(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}			
			}
		});
		menCad.add(MenPlan);
		
		JMenuItem menContr = new JMenuItem("Contratos");
		menContr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaTeste contrato = new TelaTeste();
				contrato.setVisible(true);
				desktop.add(contrato);
				try {
					contrato.setMaximum(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
	});
		menCad.add(menContr);
		
		JMenu menHelp = new JMenu("Ajuda");
		menBar.add(menHelp);
		
		JMenuItem menSobre = new JMenuItem("Sobre");
		menSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAjuda sobre = new TelaAjuda();
				sobre.setVisible(true);
			}
		});
		menHelp.add(menSobre);
		
		JMenu mnApi = new JMenu("API");
		menBar.add(mnApi);
		
		JMenuItem mntmIniciarRest = new JMenuItem("Iniciar Rest");
		mntmIniciarRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Inicia servidor TomCat
			}
		});
		mnApi.add(mntmIniciarRest);
		
		JMenu menSair = new JMenu("Sair");
		menBar.add(menSair);
		
		JMenuItem menFechar = new JMenuItem("Fechar");
		menFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
				if (sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		menSair.add(menFechar);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(798, 356, 74, 14);
		contentPane.add(lblData);				
		lblData.setText(Date());
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(798, 381, 102, 94);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/sistema/icones/mvp_logo2.png")));
		
	}
	
	private String Date() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
		//System.out.println(formatador.format(data));
		return formatador.format(data);
	}
}
