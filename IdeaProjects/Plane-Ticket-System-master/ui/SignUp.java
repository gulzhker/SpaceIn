package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField jname;
	private JPasswordField jpassword;
	JButton login = new JButton("\u767B\u5F55");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SignUp();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		
		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7: ");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(27, 22, 108, 42);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801: ");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(52, 74, 73, 49);
		contentPane.add(label_2);
		
		jname = new JTextField();
		jname.setBackground(Color.WHITE);
		jname.setBounds(135, 27, 289, 35);
		contentPane.add(jname);
		jname.setColumns(10);
		
		jpassword = new JPasswordField();
		jpassword.setBounds(135, 82, 289, 36);
		contentPane.add(jpassword);
		
		
		login.setBounds(28, 169, 97, 56);
		contentPane.add(login);
		
		JButton btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(178, 169, 87, 56);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u8FD4\u56DE");
		btnNewButton_2.setBounds(317, 169, 87, 56);
		contentPane.add(btnNewButton_2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			/*if(e.getSource() == login){
				if(jname.getText().equals(username) && String.valueOf(jpassword.getPassword()).equals(word)){
					JOptionPane.showMessageDialog(null, "您已登录");
					System.exit(0);
				}
				else{
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
					jname.setText("");
					jpassword.setText("");			
					}
			}
			else{
				int i = JOptionPane.showConfirmDialog(null, "确认要退出吗？","确认",JOptionPane.YES_NO_OPTION);
				if(i == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
			这里要连接数据库查数据库中;
			
			*/
		
		}
		
}
