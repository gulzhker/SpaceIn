package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u673A\u7968\u9884\u8BA2\u7CFB\u7EDF");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		label.setBounds(111, 53, 192, 41);
		contentPane.add(label);
		this.setVisible(true);
		
		
		JButton Search = new JButton("\u67E5\u8BE2");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Search.setBounds(35, 125, 129, 75);
		contentPane.add(Search);
		
		JButton LogInUp = new JButton("\u767B\u5F55/\u6CE8\u518C");
		LogInUp.addActionListener(this);
		LogInUp.setBounds(266, 125, 121, 75);
		contentPane.add(LogInUp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()== "µÇÂ¼/×¢²á") {
			this.dispose();
			System.out.println("close");
			new SignUp();
		}
	}
}
