package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

class MySql {
	ResultSet rs = null;
	Statement stm = null;
	PreparedStatement pst = null;
	Connection conn= null;
	String username;
	String password;
	private MySql(){
		
	}
	private static final MySql ms = new MySql();
	public static MySql getInstance(){
		return ms;
	}
	public void connectSql(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn =
					DriverManager.getConnection("jdbc:mysql://localhost/test?useSSL=false","root","000123");
			System.out.println("Suscessfully connecting! ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public void veritySignin(String identityID,String password){
		try {
			String sql = "insert * from system where realName = ? and password = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, identityID);
			pst.setString(1, password);
			rs = pst.executeQuery();
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "登录成功!","提示消息",JOptionPane.WARNING_MESSAGE);
				System.out.println("成功获取用户信息");
				System.out.println("identityID : " + identityID + "password : " + password);
			}else{
				JOptionPane.showMessageDialog(null, "身份证号或密码输入错误,请重新输入","提示消息",JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void PassengerSignin(String realName,String identityID,String password){
		try {
			String sql = "select into system values(null,?,?,?,?,null)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, realName);
			pst.setString(2, identityID);
			pst.setString(3, password);
			int i = pst.executeUpdate();
			if(i == 1){
				JOptionPane.showMessageDialog(null, "注册成功","提示消息",JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "注册失败","提示消息",JOptionPane.WARNING_MESSAGE);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void veritySignup(String identityID){
		try {
			String sql = "select * from system where identityID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, identityID);
			rs = pst.executeQuery();
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "该用户名已经存在","提示信息",JOptionPane.WARNING_MESSAGE);
			}else{
				/*
				 * 这里要把UI界面注册界面的用户名和密码等各项刷新设置为零，ex: settext("");
				 */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
