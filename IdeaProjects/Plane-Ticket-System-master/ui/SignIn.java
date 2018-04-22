package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
  注册界面
 */
class SignIn extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	/*
	 * 连接数据库
	 * Facade fcd=new Facade();
	 */
	JFrame jf;
	JPanel jp;
	JLabel jl,jl2,jl3;
	static JTextField realName,identityID;
	static JPasswordField password;
	JButton jb1,jb2;
	
	public SignIn(){
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void signIn(){
		/*boolean flagName = 
		 * 这一段要写 realName.getText.matches(name);
		 *	正则表达式判断是否输入正确；
		 * 可能存在正则表达式错误；
		 */
		String name = "[\\u4e00-\\u9fa5]{2,4}";
		boolean flagName = realName.getText().matches(name);
		String identity = "^\\d{17}([0-9]|X|x)$";
		boolean flagIdentity = this.identityID.getText().matches(identity);
		String pass = "^\\w{6,}$";
		boolean flagPassword = password.getPassword().toString().matches(pass);
		if(flagName == false){
			JOptionPane.showMessageDialog(null, "用户名填写错误，必须为2-4位的汉字","提示消息",JOptionPane.WARNING_MESSAGE);
		}else if(flagIdentity == false){
			JOptionPane.showMessageDialog(null, "身份证号填写错误，必须为18位的数字或字母","提示消息",JOptionPane.WARNING_MESSAGE);
		}else if(flagPassword == false){
			JOptionPane.showMessageDialog(null, "密码填写错误，必须为6位以上的字母或数字","提示消息",JOptionPane.WARNING_MESSAGE);
		}else{
			/*
			 * 调用注册方法，先检查注册用户是否存在
			 * 如果用户存在，将输入框设为"";
			 */
		}
		
	}
	
	
}
