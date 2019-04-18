package sistema.telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sistema.dao.ModuloConexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class TelaLogin extends JFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;

@SuppressWarnings("deprecation")
public void logar() {
	
	String SQL ="select * from  tb_usuarios where login=? and senha=?";
	try {
		pst = conexao.prepareStatement(SQL);
		pst.setString(1, txtLogin.getText());
		pst.setString(2, txtSenha.getText());
		
		rs = pst.executeQuery();
		if(rs.next()) {
			String perfil = rs.getString(6);
			if(perfil.equals("admin")) {
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
				this.dispose();
				conexao.close();
			}else {
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
				this.dispose();
				conexao.close();
			}
			}else {
				JOptionPane.showMessageDialog(null, "usuário e ou senha incorretos!");
			}
			
	}catch (Exception e){
		JOptionPane.showMessageDialog(null, e);
	}	
}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JLabel lblStatus;
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		conexao = ModuloConexao.conector();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 367);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(35, 113, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(35, 204, 46, 14);
		contentPane.add(lblSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(34, 132, 123, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(35, 229, 123, 20);
		contentPane.add(txtSenha);
		
		this.setIconImage(new ImageIcon(getClass().getResource("/sistema/icones/mvp_logo.png")).getImage());
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				logar();
			}
		});
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(215, 228, 89, 23);
		contentPane.add(btnAcessar);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/sistema/icones/dbok.png")));
		lblStatus.setBounds(503, 259, 57, 59);
		contentPane.add(lblStatus);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TelaLogin.class.getResource("/sistema/icones/mvp_logo.png")));
		lblNewLabel.setBounds(428, 11, 132, 139);
		contentPane.add(lblNewLabel);
		
		if(conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema/icones/dbok.png")));
		}else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema/icones/dbnok.png")));
		}
		
	}
}