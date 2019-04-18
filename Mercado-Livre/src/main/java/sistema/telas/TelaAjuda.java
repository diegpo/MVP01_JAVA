package sistema.telas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class TelaAjuda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAjuda frame = new TelaAjuda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAjuda() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 482);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.darkShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 this.setLocationRelativeTo(null);
		 
		this.setIconImage(new ImageIcon(getClass().getResource("/sistema/icones/mvp_logo.png")).getImage());
		
		JLabel lblSistemaMvp = new JLabel("Sistema MVP");
		lblSistemaMvp.setFont(new Font("Arial Black", Font.BOLD, 26));
		lblSistemaMvp.setBounds(100, 11, 240, 49);
		contentPane.add(lblSistemaMvp);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaAjuda.class.getResource("/sistema/icones/mvp_logo.png")));
		lblNewLabel.setBounds(144, 0, 258, 256);
		contentPane.add(lblNewLabel);
		
		JLabel lblDesenvolvidoPor = new JLabel("Desenvolvido por");
		lblDesenvolvidoPor.setBounds(166, 371, 142, 14);
		contentPane.add(lblDesenvolvidoPor);
		
		JLabel lblDiegoRochaOliveira = new JLabel("Diego Rocha OLiveira");
		lblDiegoRochaOliveira.setBounds(166, 385, 121, 14);
		contentPane.add(lblDiegoRochaOliveira);
		
		JLabel lblAbrilDe = new JLabel("Abril de 2019 ®");
		lblAbrilDe.setBounds(166, 400, 97, 14);
		contentPane.add(lblAbrilDe);
	}
}
