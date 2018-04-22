package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Test extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JButton login = new JButton("登录");
	JButton signin = new JButton("注册");
	JButton exit = new JButton("退出");
	JLabel name = new JLabel("用户名:");
	JLabel password = new JLabel("     密码:");
	JTextField jname = new JTextField(15);
	JPasswordField jpassword = new JPasswordField(15);
	
	public final String username = "admin";
	public final String word = "admin";
	
	public Test(){
		this.setLayout(new GridLayout(3, 2));
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		password.setHorizontalAlignment(SwingConstants.RIGHT);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		jp1.add(name);
		jp1.add(jname);
		jp2.add(password);
		jp2.add(jpassword);
		jp3.add(login);
		jp3.add(signin);
		jp3.add(exit);
		
		login.addActionListener(this);
		exit.addActionListener(this);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		
		this.setTitle("登录系统");
		this.setLocation(300, 300);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == login){
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
	}
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Test();
	}
}


