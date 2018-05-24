package employee.view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.util.Stack;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EmpMain extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	public JPanel parent;
	public Stack<String> stk;
	CardLayout cl;
	private JButton baddemp;
	private JButton bsearchemp;
	private JPanel panel;
	private JLabel lblAddEmployee;
	private JButton bpayment;
	private JButton bsearchpay;
	private JLabel lblEmployee;
	public EmpMain(JPanel parent,Stack<String>stk,CardLayout cl) {
		setBackground(new Color(204, 255, 255));
		setLayout(null);
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		
		panel = new JPanel();
		panel.setBackground(new Color(102, 102, 102));
		panel.setBounds(35, 196, 250, 300);
		add(panel);
		panel.setLayout(null);
		
		baddemp = new JButton("ADD EMP");
		baddemp.setBounds(45, 140, 150, 40);
		panel.add(baddemp);
		baddemp.setBackground(new Color(255, 255, 255));
		baddemp.setForeground(new Color(102, 102, 102));
		baddemp.setFont(new Font("Arial", Font.BOLD, 18));
		baddemp.addActionListener(this);
		
		bsearchemp = new JButton("search emp");
		bsearchemp.setBounds(45, 210, 150, 40);
		panel.add(bsearchemp);
		bsearchemp.setForeground(new Color(102, 102, 102));
		bsearchemp.setBackground(new Color(255, 255, 255));
		bsearchemp.setFont(new Font("Arial", Font.BOLD, 18));
		bsearchemp.addActionListener(this);
		
		lblAddEmployee = new JLabel("ADD EMPLOYEE");
		lblAddEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddEmployee.setForeground(new Color(255, 255, 255));
		lblAddEmployee.setFont(new Font("Dialog", Font.BOLD, 19));
		lblAddEmployee.setBounds(26, 10, 200, 50);
		panel.add(lblAddEmployee);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 102, 102));
		panel_1.setLayout(null);
		panel_1.setBounds(604, 196, 250, 300);
		add(panel_1);
		
		bpayment = new JButton("payment");
		bpayment.addActionListener(this);
		bpayment.setBounds(58, 140, 150, 40);
		panel_1.add(bpayment);
		bpayment.setForeground(new Color(102, 102, 102));
		bpayment.setBackground(new Color(255, 255, 255));
		bpayment.setFont(new Font("Arial", Font.BOLD, 18));
		
		bsearchpay = new JButton("search pay");
		bsearchpay.setBounds(58, 210, 150, 40);
		panel_1.add(bsearchpay);
		bsearchpay.setForeground(new Color(102, 102, 102));
		bsearchpay.setBackground(new Color(255, 255, 255));
		bsearchpay.setFont(new Font("Arial", Font.BOLD, 18));
		bsearchpay.addActionListener(this);
		
		JLabel lblPayment = new JLabel("PAYMENT");
		lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayment.setForeground(Color.WHITE);
		lblPayment.setFont(new Font("Dialog", Font.BOLD, 19));
		lblPayment.setBounds(26, 10, 200, 50);
		panel_1.add(lblPayment);
		
		lblEmployee = new JLabel("EMPLOYEE");
		lblEmployee.setOpaque(true);
		lblEmployee.setBackground(new Color(102, 102, 102));
		lblEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployee.setForeground(new Color(255, 255, 255));
		lblEmployee.setFont(new Font("Arial", Font.BOLD, 22));
		lblEmployee.setBounds(338, 28, 200, 50);
		add(lblEmployee);

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton)arg0.getSource();
		if(b==baddemp)
		{
			parent.add(new AddEmployee(parent,stk,cl),"2.1");
			stk.push("2.1");
			cl.show(parent, "2.1");
		}
		if(b==bsearchemp)
		{
			parent.add(new Query(parent,stk,cl),"2.2");
			stk.push("2.1");
			cl.show(parent, "2.2");
		}
		if(b==bpayment)
		{
			parent.add(new EPayment(parent,stk,cl),"2.3");
			stk.push("2.3");
			cl.show(parent, "2.3");
		}
		if(b==bsearchpay)
		{
			parent.add(new PaymentQuery(parent,stk,cl),"2.4");
			stk.push("2.4");
			cl.show(parent, "2.4");
		}
	}
	public static void main(String [] args)
	{
		
	}
}
