import mess.interface_db.DeleteDB;
import mess.interface_db.ExtractDB;
import mess.interface_db.FeeLoader;
import mess.interface_db.IDLoader;
import mess.interface_db.InsertDB;
import mess.interface_db.UpdateDB;
import mess.interface_db.UserLoader;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;




import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import com.mongodb.MongoClient;

import mess.pages.MessHome;
import employee.controller.ConnectionDB;
import employee.view.EmpMain;


@SuppressWarnings("serial")
public class HomePage extends JPanel implements ActionListener{
	private JButton bhome;
	private JButton bmess;
	private JButton bemp;
	private JButton bexp;
	private JButton bhotel;
	private JPanel bpanel;
	private JPanel cpanel;
	private CardLayout card;
	private JButton bexit;
	private MongoClient mongoClient;
	private Stack<String> MessStack;
	private Stack<String> EmpStack;
	private static Process p;
	
	public HomePage() {
		
		setLayout(null);
		

		ConnectionDB.getConnected1();
		
		mongoClient = new MongoClient( "localhost" , 27017 );
		InsertDB.db=mongoClient.getDatabase("Mess");
		ExtractDB.db=mongoClient.getDatabase("Mess");
		UpdateDB.db=mongoClient.getDatabase("Mess");
		DeleteDB.db=mongoClient.getDatabase("Mess");
		FeeLoader.db=mongoClient.getDatabase("Mess");
		IDLoader.db=mongoClient.getDatabase("Mess");
		UserLoader.db=mongoClient.getDatabase("Mess");
		
		MessStack=new Stack<String>();
		EmpStack=new Stack<String>();
		
		bpanel = new JPanel();
		bpanel.setBackground(new Color(153, 153, 153));
		bpanel.setBounds(0, 0, 220, 630);
		add(bpanel);
		bpanel.setLayout(null);
		
		bmess = new JButton("Mess");
		bmess.addActionListener(this);
		bmess.setForeground(new Color(255, 255, 255));
		bmess.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bmess.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bmess.setBackground(new Color(102, 102, 102));
		bmess.setBounds(0, 105, 219, 50);
		bpanel.add(bmess);
		
		bemp = new JButton("Employees");
		bemp.addActionListener(this);
		bemp.setForeground(new Color(255, 255, 255));
		bemp.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bemp.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bemp.setBackground(new Color(102, 102, 102));
		bemp.setBounds(0, 160, 219, 50);
		bpanel.add(bemp);
		
		bexp = new JButton("My Expenses");
		bexp.addActionListener(this);
		bexp.setForeground(new Color(255, 255, 255));
		bexp.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bexp.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bexp.setBackground(new Color(102, 102, 102));
		bexp.setBounds(0, 215, 219, 50);
		bpanel.add(bexp);
		
		bhotel = new JButton("Hotel");
		bhotel.addActionListener(this);
		bhotel.setForeground(new Color(255, 255, 255));
		bhotel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bhotel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bhotel.setBackground(new Color(102, 102, 102));
		bhotel.setBounds(0, 270, 219, 50);
		bpanel.add(bhotel);
		
		bhome = new JButton("Home");
		bhome.addActionListener(this);
		bhome.setForeground(new Color(255, 255, 255));
		bhome.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bhome.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bhome.setBackground(new Color(102, 102, 102));
		bhome.setBounds(0, 50, 219, 50);
		bpanel.add(bhome);
		
		bexit = new JButton("Exit");
		bexit.addActionListener(this);
		bexit.setForeground(Color.WHITE);
		bexit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bexit.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bexit.setBackground(new Color(153, 0, 0));
		bexit.setBounds(0, 325, 220, 50);
		bpanel.add(bexit);
		
		card=new CardLayout();
		
		cpanel = new JPanel();
		cpanel.setLayout(card);
		cpanel.setBounds(220, 0, 900, 630);
		add(cpanel);
		
		MessHome messHome = new MessHome(cpanel,card,MessStack);
		messHome.setBackground(new Color(255, 255, 255));
		cpanel.add(messHome,"1");
		MessStack.push("1");
		cpanel.add(new ComingSoon(), "0");
		
		
		
		
		EmpMain home = new EmpMain(cpanel,EmpStack,card);
		home.setBackground(new Color(255, 255, 255));
		cpanel.add(home,"2");
		EmpStack.push("2");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b=(JButton)e.getSource();
		if(b==bmess)
		{
			System.out.println("MessPEEK :" +MessStack.peek());
			card.show(cpanel,MessStack.peek());
		}
		else if(b==bexit)
		{
			
			try{
				p.destroyForcibly();
			}
			catch(Exception e1){
				
			}
			mongoClient.close();
			System.exit(0);
		}
		if(b==bemp)
		{
			System.out.println("MessPEEK :" +EmpStack.peek());
			card.show(cpanel,EmpStack.peek());
		}
	}

	public static void main(String[] args)
	{
		ProcessBuilder pb = new ProcessBuilder();
        pb.command("C:/Program Files/MongoDB/Server/3.4/bin/mongod", "--dbpath", "C:\\data\\db");
        
        try {
            p = pb.start();
        } catch (Exception e) {
        	System.exit(0);
            e.printStackTrace();
        }
		
        try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame jf= new JFrame();
		jf.setSize(1120,630);
		jf.getContentPane().add(new HomePage());
		jf.setUndecorated(true);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		
		Login obj=new Login(jf,true);
	}
}
