package employee.view;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.bson.Document;

import employee.controller.ConnectionDB;
import employee.controller.SelectDB;
import employee.model.Employee;


public class View extends JPanel implements ActionListener{
	private JLabel tfirstn;
	private JLabel tmiddle;
	private JLabel tsurname;
	private JLabel taddress;
	private JLabel tsalary;
	private JLabel tcontactno;
	private JLabel teid; 
	private Employee emp;
	public JPanel parent;
	public Stack<String> stk;
	CardLayout cl;
	private JButton bback;
	private String id ;

	/**
	 * Create the panel.
	 */
	public View(String id,JPanel parent,Stack<String>stk,CardLayout cl) {
		setForeground(new Color(153, 0, 0));
		setBackground(Color.WHITE);
		setLayout(null);
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		this.id=id;
		ConnectionDB.getConnected1();
		emp = new Employee();
		
		Document doc = SelectDB.getEmpData(id);
		
		emp.id = id; 
		
		JLabel lname = new JLabel("Name");
		lname.setForeground(Color.BLACK);
		lname.setFont(new Font("Arial", Font.BOLD, 22));
		lname.setBounds(97, 150, 128, 40);
		add(lname);
		
		JLabel lgender = new JLabel("Gender");
		lgender.setForeground(Color.BLACK);
		lgender.setFont(new Font("Arial", Font.BOLD, 22));
		lgender.setBounds(97, 210, 128, 40);
		add(lgender);
		
		JLabel leid = new JLabel("Employee ID");
		leid.setForeground(Color.BLACK);
		leid.setHorizontalAlignment(SwingConstants.TRAILING);
		leid.setFont(new Font("Arial", Font.BOLD, 22));
		leid.setBounds(97, 260, 128, 40);
		add(leid);
		
		JLabel laddress = new JLabel("Address");
		laddress.setForeground(Color.BLACK);
		laddress.setFont(new Font("Arial", Font.BOLD, 22));
		laddress.setBounds(97, 310, 138, 40);
		add(laddress);
		
		JLabel lblDateOfJoining = new JLabel("Date Of Joining");
		lblDateOfJoining.setForeground(Color.BLACK);
		lblDateOfJoining.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDateOfJoining.setFont(new Font("Arial", Font.BOLD, 22));
		lblDateOfJoining.setBounds(400, 260, 165, 40);
		add(lblDateOfJoining);
		
		JLabel lsalary = new JLabel("Salary");
		lsalary.setForeground(Color.BLACK);
		lsalary.setFont(new Font("Arial", Font.BOLD, 22));
		lsalary.setBounds(97, 360, 128, 40);
		add(lsalary);
		
		JLabel lcontactno = new JLabel("Contact No.");
		lcontactno.setForeground(Color.BLACK);
		lcontactno.setHorizontalAlignment(SwingConstants.TRAILING);
		lcontactno.setFont(new Font("Arial", Font.BOLD, 22));
		lcontactno.setBounds(437, 360, 128, 40);
		add(lcontactno);
		
		JLabel lshift = new JLabel("Shift");
		lshift.setForeground(Color.BLACK);
		lshift.setFont(new Font("Arial", Font.BOLD, 22));
		lshift.setBounds(97, 410, 128, 40);
		add(lshift);
		
		tfirstn = new JLabel(doc.getString("fname"));
		tfirstn.setBackground(Color.WHITE);
		tfirstn.setBorder(null);
		tfirstn.setFont(new Font("Arial", Font.PLAIN, 20));
		tfirstn.setBounds(250, 155, 150, 35);
		add(tfirstn);
		
		
		tmiddle = new JLabel(doc.getString("middle"));
		tmiddle.setBorder(null);
		tmiddle.setFont(new Font("Arial", Font.PLAIN, 20));
		
		tmiddle.setBounds(425, 155, 150, 35);
		add(tmiddle);
		
		
		tsurname = new JLabel(doc.getString("surname"));
		tsurname.setBorder(null);
		tsurname.setFont(new Font("Arial", Font.PLAIN, 20));
		
		tsurname.setBounds(600, 155, 150, 35);
		add(tsurname);
		
		
		
		teid = new JLabel(id);
		teid.setBorder(null);
		teid.setOpaque(true);
		teid.setBackground(Color.WHITE);
		teid.setFont(new Font("Arial", Font.PLAIN, 20));
		teid.setBounds(250, 265, 100, 35);
		add(teid);
		
		
		taddress = new JLabel(doc.getString("address"));
		taddress.setFont(new Font("Arial", Font.PLAIN, 20));
		taddress.setBorder(null);
		taddress.setBounds(250, 315, 500, 35);
		add(taddress);
		
		
		
		tsalary = new JLabel(""+doc.get("salary"));
		tsalary.setBorder(null);
		tsalary.setFont(new Font("Arial", Font.PLAIN, 20));
		
		tsalary.setBounds(250, 365, 175, 35);
		add(tsalary);
	
		
		
		tcontactno = new JLabel(doc.getString("contactno"));
		tcontactno.setBorder(null);
		tcontactno.setFont(new Font("Arial", Font.PLAIN, 20));
		
		tcontactno.setBounds(590, 365, 160, 35);
		add(tcontactno);
		
		
		JLabel ltitle = new JLabel("Employee Registration");
		ltitle.setOpaque(true);
		ltitle.setBackground(new Color(102, 102, 102));
		ltitle.setForeground(new Color(255, 255, 255));
		ltitle.setFont(new Font("Arial", Font.BOLD, 22));
		ltitle.setHorizontalAlignment(SwingConstants.CENTER);
		ltitle.setBounds(270, 50, 360, 50);
		add(ltitle);
		
		
		
		
		
		JLabel lblSalaryPaid = new JLabel("Salary paid");
		lblSalaryPaid.setForeground(Color.BLACK);
		lblSalaryPaid.setFont(new Font("Arial", Font.BOLD, 22));
		lblSalaryPaid.setBounds(97, 460, 138, 40);
		add(lblSalaryPaid);
		
		
		
		JLabel tgender = new JLabel("");
		tgender.setForeground(Color.BLACK);
		tgender.setFont(new Font("Arial", Font.PLAIN, 20));
		tgender.setBounds(250, 210, 128, 40);
		add(tgender);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		JLabel tdate = new JLabel(""+df.format(doc.get("date")));
		tdate.setForeground(Color.BLACK);
		tdate.setFont(new Font("Arial", Font.PLAIN, 20));
		tdate.setBounds(590, 260, 160, 40);
		add(tdate);
		
		JLabel tshift = new JLabel("");
		tshift.setForeground(Color.BLACK);
		tshift.setFont(new Font("Arial", Font.PLAIN, 20));
		tshift.setBounds(250, 410, 128, 40);
		add(tshift);
		
		JLabel tsalarypaid = new JLabel(""+doc.getInteger("salary"));
		tsalarypaid.setForeground(Color.BLACK);
		tsalarypaid.setFont(new Font("Arial", Font.PLAIN, 20));
		tsalarypaid.setBounds(250, 460, 128, 40);
		add(tsalarypaid);
		
		bback = new JButton("Back");
		bback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bback.setForeground(Color.WHITE);
		bback.addActionListener(this);
		bback.setFont(new Font("Arial", Font.BOLD, 22));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(375, 512, 120, 50);
		add(bback);
		
		if(doc.getInteger("gender")==1)
		{
			tgender.setText("Male");
		}
		else if(doc.getInteger("gender")==2)
		{
			tgender.setText("Female");
		}
		if(doc.getInteger("shift")==1)
		{
			tshift.setText("Single");
		}
		else if(doc.getInteger("shift")==2)
		{
			tshift.setText("Double");
		}

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton)arg0.getSource();
		
		if(b==bback)
		{
			stk.pop();
			cl.show(parent,"2.2");
			parent.remove(this);
		
		}
		
	}
}
