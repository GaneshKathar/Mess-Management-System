import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import mess.interface_db.UserLoader;


public class Login extends JDialog implements ActionListener {
	private JTextField tuser;
	private JTextField tpass;
	private JButton bok;
	private JLabel lerror1;
	private JLabel lerror2;
	private JButton bexit;
	public JFrame parent;
	public Login(JFrame par,boolean bm) {
		
		super(par,bm);
		
		parent=par;
		
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 560, 630);
		getContentPane().add(panel);
		
		ImageIcon ico=new ImageIcon("Assets\\Login2.jpg");
		JLabel lblimg = new JLabel(ico);
		lblimg.setOpaque(true);
		lblimg.setBounds(0, 0, 600, 675);
		panel.add(lblimg);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(8, 65, 95));
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBounds(560, 0, 560, 630);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(250, 285, 150, 10);
		panel_1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(250, 345, 150, 10);
		panel_1.add(separator_1);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Century Gothic", Font.PLAIN, 26));
		lblLogin.setBounds(230, 114, 100, 50);
		panel_1.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblUsername.setBounds(80, 250, 150, 40);
		panel_1.add(lblUsername);
		
		JLabel lblPasword = new JLabel("Password");
		lblPasword.setForeground(new Color(255, 255, 255));
		lblPasword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPasword.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblPasword.setBounds(80, 310, 150, 40);
		panel_1.add(lblPasword);
		
		tuser = new JTextField();
		tuser.setForeground(new Color(255, 255, 255));
		tuser.setBackground(new Color(8, 65, 95));
		tuser.setBorder(null);
		tuser.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tuser.setBounds(250, 255, 150, 30);
		panel_1.add(tuser);
		tuser.setColumns(10);
		
		bok = new JButton("Okay");
		bok.setBackground(new Color(204, 255, 255));
		bok.setForeground(new Color(0, 0, 0));
		bok.addActionListener(this);
		bok.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bok.setBounds(230, 407, 100, 35);
		panel_1.add(bok);
		
		tpass = new JPasswordField();
		tpass.setForeground(new Color(255, 255, 255));
		tpass.setBackground(new Color(8, 65, 95));
		tpass.setBorder(null);
		tpass.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tpass.setBounds(250, 315, 150, 30);
		panel_1.add(tpass);
		
		lerror1 = new JLabel("Do not leave the field empty");
		lerror1.setForeground(new Color(255, 255, 255));
		lerror1.setHorizontalAlignment(SwingConstants.CENTER);
		lerror1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lerror1.setBounds(155, 379, 250, 20);
		panel_1.add(lerror1);
		
		lerror2 = new JLabel("Invalid User Details");
		lerror2.setHorizontalAlignment(SwingConstants.CENTER);
		lerror2.setForeground(new Color(255, 255, 255));
		lerror2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lerror2.setBounds(155, 380, 250, 20);
		panel_1.add(lerror2);
		
		bexit = new JButton("");
		bexit.setBackground(new Color(153, 0, 0));
		bexit.setForeground(new Color(153, 0, 0));
		bexit.addActionListener(this);
		bexit.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		bexit.setHorizontalAlignment(SwingConstants.LEFT);
		bexit.setBounds(522, 0, 38, 38);
		panel_1.add(bexit);
		
		lerror1.setVisible(false);
		lerror2.setVisible(false);
		
		setUndecorated(true);
		setSize(1120,630);
		setLocationRelativeTo(null);
		setResizable(false);
		parent.setVisible(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b=(JButton)e.getSource();
		if(b==bexit){
			System.exit(0);
		}
		lerror1.setVisible(false);
		lerror2.setVisible(false);
		if(tuser.getText().trim().isEmpty()||tpass.getText().trim().isEmpty())
		{	
			lerror1.setVisible(true);
			clear();
			return;
		}
		String user=tuser.getText().trim();
		String pass=tpass.getText().trim();
		MongoCursor<Document> cursor=UserLoader.loadUser();
		Document doc;
		int flag=0;
		while(cursor.hasNext()){
			doc=cursor.next();
			if(user.equals(doc.getString("username"))&&pass.equals(doc.getString("password")))
			{
				flag=1;
			}
		}
		if(flag==0){
			clear();
			lerror2.setVisible(true);
			return;
		}
		parent.setVisible(true);
		this.dispose();
		clear();
	}
	
	public void clear(){
		tuser.setText("");
		tpass.setText("");
	}
	public static void main(String []args)
	{
		JFrame jf=new JFrame();
		jf.setSize(1120,630);
		jf.getContentPane().add(new Login(null,true));
		jf.setUndecorated(true);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	}
}
