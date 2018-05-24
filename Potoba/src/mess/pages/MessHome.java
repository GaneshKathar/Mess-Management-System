package mess.pages;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.border.MatteBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MessHome extends JPanel implements ActionListener {
	private JButton bstud;
	private JButton bprof;
	private JButton bpay;
	private JButton bmember;
	private JButton bslip;
	private JPanel parent;
	private CardLayout card;
	private Stack<String> MessStack;
	
	public MessHome(JPanel par,CardLayout cl,Stack stk) {
		
		MessStack=stk;
		parent=par;
		card=cl;
		
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(new Color(204, 255, 255));
		setLayout(null);
		
		JPanel apanel = new JPanel();
		apanel.setBackground(new Color(102, 102, 102));
		apanel.setBounds(35, 196, 250, 300);
		add(apanel);
		apanel.setLayout(null);
		
		JPanel ppanel = new JPanel();
		ppanel.setBackground(new Color(102, 102, 102));
		ppanel.setBounds(325, 198, 250, 200);
		add(ppanel);
		ppanel.setLayout(null);
		
		JPanel spanel = new JPanel();
		spanel.setBackground(new Color(102, 102, 102));
		spanel.setBounds(615, 196, 250, 300);
		add(spanel);
		spanel.setLayout(null);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		lblSearch.setBounds(35, 13, 180, 40);
		spanel.add(lblSearch);
		
		JLabel lblMakePayments = new JLabel("Make Payments");
		lblMakePayments.setForeground(Color.WHITE);
		lblMakePayments.setHorizontalAlignment(SwingConstants.CENTER);
		lblMakePayments.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		lblMakePayments.setBounds(35, 0, 180, 40);
		ppanel.add(lblMakePayments);
		
		JLabel lblAddMembers = new JLabel("Add Members");
		lblAddMembers.setForeground(Color.WHITE);
		lblAddMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMembers.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		lblAddMembers.setBounds(35, 12, 180, 40);
		apanel.add(lblAddMembers);
		
		bstud = new JButton("Student");
		bstud.addActionListener(this);
		bstud.setBackground(new Color(255, 255, 255));
		bstud.setForeground(new Color(102, 102, 102));
		bstud.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bstud.setBounds(50, 160, 150, 40);
		apanel.add(bstud);
		
		bprof = new JButton("Professional");
		bprof.addActionListener(this);
		bprof.setBackground(new Color(255, 255, 255));
		bprof.setForeground(new Color(102, 102, 102));
		bprof.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bprof.setBounds(50, 230, 150, 40);
		apanel.add(bprof);
		
		bmember = new JButton("Members");
		bmember.addActionListener(this);
		bmember.setBackground(new Color(255, 255, 255));
		bmember.setForeground(new Color(102, 102, 102));
		bmember.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bmember.setBounds(50, 160, 150, 40);
		spanel.add(bmember);
		
		bslip = new JButton("Payment Slip");
		bslip.addActionListener(this);
		bslip.setBackground(new Color(255, 255, 255));
		bslip.setForeground(new Color(102, 102, 102));
		bslip.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bslip.setBounds(50, 230, 150, 40);
		spanel.add(bslip);
		
		bpay = new JButton("Pay");
		bpay.addActionListener(this);
		bpay.setForeground(new Color(102, 102, 102));
		bpay.setBackground(new Color(255, 255, 255));
		bpay.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bpay.setBounds(50, 130, 150, 40);
		ppanel.add(bpay);

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton btn=(JButton)e.getSource();
		if(btn==bpay)
		{
			parent.add(new APayment(parent,card,MessStack),"1.3");
			MessStack.push("1.3");
			card.show(parent, "1.3");
		}
		else if(btn==bstud)
		{
			parent.add(new AStudent(parent,card,MessStack),"1.1");
			MessStack.push("1.1");
			card.show(parent, "1.1");
		}
		else if(btn==bprof)
		{
			parent.add(new AProfessional(parent,card,MessStack),"1.2");
			MessStack.push("1.2");
			card.show(parent, "1.2");
		}
		else if(btn==bmember)
		{
			parent.add(new SearchM(parent,card,MessStack),"1.4");
			MessStack.push("1.4");
			card.show(parent, "1.4");
		}
		else if(btn==bslip)
		{
			parent.add(new SearchP(parent,card,MessStack),"1.5");
			MessStack.push("1.5");
			card.show(parent, "1.5");
		}
	}
}
