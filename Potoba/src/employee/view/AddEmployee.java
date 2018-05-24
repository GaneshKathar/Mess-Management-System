package employee.view;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;

import com.mongodb.MongoClient;
import com.toedter.calendar.JDateChooser;

import employee.controller.InsertDB;
import employee.controller.SelectDB;
import employee.controller.UpdateDB;
import employee.model.Employee;

public class AddEmployee extends JPanel implements FocusListener,ActionListener {
	private JTextField tfirstn,tmiddle,tsurname,taddress,tsalary,tcontactno;
	private JLabel teid; 
	JRadioButton rbmale,rbfemale,rbdouble,rbsingle;
	JLabel lnotice1,lnotice2,lnotice3;
	JButton badd ;
	ButtonGroup bg1,bg2;
	JDateChooser dateChooser ;
	String firstname,contactno,ids ;
	int eid,salary,year;
	Employee emp;
	MongoClient mongoClient ;
	Calendar c;
	Date date;
	public JPanel parent;
	public Stack<String> stk;
	CardLayout cl;
	private JButton bback;

	/**
	 * Create the panel.
	 */
	public AddEmployee(JPanel parent,Stack<String>stk,CardLayout cl) {
		setForeground(new Color(153, 0, 0));
		setBackground(Color.WHITE);
		setLayout(null);
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		emp =new Employee();
		c = Calendar.getInstance();
		year=c.get(Calendar.YEAR);
		
		
		
		eid=SelectDB.getECount();
		
        ids = year+"E"+eid;
		
		
		
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
		
		tfirstn = new JTextField();
		tfirstn.setBackground(Color.WHITE);
		tfirstn.setBorder(null);
		tfirstn.setFont(new Font("Arial", Font.BOLD, 22));
		tfirstn.setBounds(250, 155, 150, 35);
		add(tfirstn);
		tfirstn.setColumns(10);
		tfirstn.addFocusListener(this);
		
		tmiddle = new JTextField();
		tmiddle.setBorder(null);
		tmiddle.setFont(new Font("Arial", Font.BOLD, 22));
		tmiddle.setColumns(10);
		tmiddle.setBounds(425, 155, 150, 35);
		add(tmiddle);
		tmiddle.addFocusListener(this);
		
		tsurname = new JTextField();
		tsurname.setBorder(null);
		tsurname.setFont(new Font("Arial", Font.BOLD, 22));
		tsurname.setColumns(10);
		tsurname.setBounds(600, 155, 150, 35);
		add(tsurname);
		tsurname.addFocusListener(this);
		
		rbmale = new JRadioButton("Male");
		rbmale.setForeground(Color.BLACK);
		rbmale.setBackground(Color.WHITE);
		rbmale.setFont(new Font("Arial", Font.BOLD, 22));
		rbmale.setBounds(250, 210, 75, 40);
		add(rbmale);
		
		
		rbfemale = new JRadioButton("Female");
		rbfemale.setForeground(Color.BLACK);
		rbfemale.setFont(new Font("Arial", Font.BOLD, 22));
		rbfemale.setBackground(Color.WHITE);
		rbfemale.setBounds(350, 210, 125, 40);
		add(rbfemale);
		
		
		teid = new JLabel(ids);
		teid.setBorder(null);
		teid.setOpaque(true);
		teid.setBackground(Color.WHITE);
		teid.setHorizontalAlignment(SwingConstants.TRAILING);
		teid.setFont(new Font("Arial", Font.BOLD, 22));
		teid.setBounds(250, 265, 100, 35);
		add(teid);
		
		
		taddress = new JTextField();
		taddress.setFont(new Font("Arial", Font.BOLD, 22));
		taddress.setBorder(null);
		taddress.setBounds(250, 315, 500, 35);
		add(taddress);
		taddress.setColumns(10);
		taddress.addFocusListener(this);
		
		tsalary = new JTextField();
		tsalary.setBorder(null);
		tsalary.setFont(new Font("Arial", Font.BOLD, 22));
		tsalary.setColumns(10);
		tsalary.setBounds(250, 365, 175, 35);
		add(tsalary);
		tsalary.addFocusListener(this);
		
		
		tcontactno = new JTextField();
		tcontactno.setBorder(null);
		tcontactno.setFont(new Font("Arial", Font.BOLD, 22));
		tcontactno.setColumns(10);
		tcontactno.setBounds(590, 365, 160, 35);
		add(tcontactno);
		tcontactno.addFocusListener(this);
		
		
		rbsingle = new JRadioButton("Single");
		rbsingle.setForeground(Color.BLACK);
		rbsingle.setFont(new Font("Arial", Font.BOLD, 22));
		rbsingle.setBackground(Color.WHITE);
		rbsingle.setBounds(250, 410, 91, 40);
		add(rbsingle);
		
		
		rbdouble = new JRadioButton("Double");
		rbdouble.setForeground(Color.BLACK);
		rbdouble.setFont(new Font("Arial", Font.BOLD, 22));
		rbdouble.setBackground(Color.WHITE);
		rbdouble.setBounds(366, 410, 125, 40);
		add(rbdouble);
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(250, 190, 150, 15);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBounds(425, 190, 150, 1);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 0, 0));
		separator_2.setBounds(600, 190, 150, 1);
		add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(0, 0, 0));
		separator_4.setBounds(250, 350, 500, 1);
		add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(0, 0, 0));
		separator_5.setBounds(250, 400, 175, 1);
		add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(new Color(0, 0, 0));
		separator_6.setBounds(590, 400, 160, 1);
		add(separator_6);
		
		badd = new JButton("Add");
		badd.setForeground(new Color(255, 255, 255));
		badd.setBackground(new Color(102, 102, 102));
		badd.setFont(new Font("Arial", Font.BOLD, 22));
		badd.setBounds(270, 508, 120, 50);
		add(badd);
		badd.addActionListener(this);
		
		JLabel ltitle = new JLabel("Employee Registration");
		ltitle.setOpaque(true);
		ltitle.setBackground(new Color(102, 102, 102));
		ltitle.setForeground(new Color(255, 255, 255));
		ltitle.setFont(new Font("Arial", Font.BOLD, 22));
		ltitle.setHorizontalAlignment(SwingConstants.CENTER);
		ltitle.setBounds(270, 50, 360, 50);
		add(ltitle);
		
		lnotice1 = new JLabel("Some field are empty");
		lnotice1.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice1.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice1.setForeground(new Color(153, 0, 0));
		lnotice1.setBackground(new Color(255, 255, 255));
		lnotice1.setOpaque(true);
		lnotice1.setBounds(225, 462, 438, 24);
		add(lnotice1);
		lnotice1.setVisible(false);
		
		lnotice2 = new JLabel("Connection problem,Please Try again");
		lnotice2.setOpaque(true);
		lnotice2.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice2.setForeground(new Color(153, 0, 0));
		lnotice2.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice2.setBackground(Color.WHITE);
		lnotice2.setBounds(225, 462, 438, 24);
		add(lnotice2);
		lnotice2.setVisible(false);
		
		bg1=new ButtonGroup();
		bg1.add(rbmale);
		bg1.add(rbfemale);
		
		bg2=new ButtonGroup();
		bg2.add(rbsingle);
		bg2.add(rbdouble);
		
		lnotice3 = new JLabel("Invalide Contact number");
		lnotice3.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice3.setForeground(new Color(153, 0, 0));
		lnotice3.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice3.setOpaque(true);
		lnotice3.setBackground(Color.WHITE);
		lnotice3.setBounds(225, 462, 438, 24);
		add(lnotice3);
		
		
		
		dateChooser = new JDateChooser();
		dateChooser.setDate(emp.date);
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 20));
		dateChooser.setForeground(new Color(153, 153, 153));
		dateChooser.setBackground(new Color(153, 153, 153));
		dateChooser.setBounds(600, 260, 150, 40);
		add(dateChooser);
		
		bback = new JButton("Back");
		bback.addActionListener(this);
		bback.setForeground(Color.WHITE);
		bback.setFont(new Font("Arial", Font.BOLD, 22));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(510, 508, 120, 50);
		add(bback);
		lnotice3.setVisible(false);
	}
	public boolean isEmpty()
	{
		if(tfirstn.getText().isEmpty())
			return true;
		else if(tmiddle.getText().isEmpty())
			return true;
		else if(teid.getText().isEmpty())
			return true;
		else if(tsurname.getText().isEmpty())
			return true;
		else if(teid.getText().isEmpty())
			return true;
		else if(taddress.getText().isEmpty())
			return true;
		else if(tsalary.getText().isEmpty())
			return true;
		else if(tcontactno.getText().isEmpty())
			return true;
		else if(tfirstn.getText().isEmpty())
			return true;
		else if(bg1.getSelection()==null||bg2.getSelection()==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void addToDatabase()
	{
		if(InsertDB.insertEmp(emp))
		{   
			eid=SelectDB.getECount();       
	        year=c.get(Calendar.YEAR);
	        ids = year+"E"+eid;
			clear();
		}
		else
		{
			lnotice2.setVisible(true);
		}
	}
	private void clear()
	{
		tfirstn.setText("");
		tmiddle.setText("");
		tsurname.setText("");
		teid.setText(ids);
		taddress.setText("");
		tsalary.setText("");
		tcontactno.setText("");
		bg1.clearSelection(); 
		bg2.clearSelection();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		lnotice2.setVisible(false);
		JButton b= (JButton)arg0.getSource();
		if(b==badd)
		{
			if(isEmpty())
			{
				lnotice1.setVisible(true);
			}
			else
			{
				if(rbmale.isSelected())
					emp.gender=1;	
				else if(rbfemale.isSelected())
					emp.gender=2;
				if(rbsingle.isSelected())
					emp.shift=1;
				else if(rbdouble.isSelected())
					emp.shift=2;
					
				emp.fname=tfirstn.getText();
				emp.middle=tmiddle.getText();
				emp.surname=tsurname.getText();
				emp.address=taddress.getText();
				emp.mobileno=tcontactno.getText();
				emp.id=teid.getText();
				emp.salary=salary;
				eid+=1;
				emp.date=dateChooser.getDate();
				UpdateDB.updateECount();
				addToDatabase();
			}
		}
		if(b==bback)
		{
			stk.pop();
			cl.show(parent,"2");
			parent.remove(this);
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		JTextField text= (JTextField)arg0.getSource();
		if(text==tcontactno)
			lnotice3.setVisible(false);
		
		lnotice1.setVisible(false);
		lnotice2.setVisible(false);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField text= (JTextField)arg0.getSource();
		if(text==tsalary)
		{
			if(!(tsalary.getText().isEmpty()))
			{
				try
				{
					salary = Integer.parseInt(tsalary.getText());
				}
				
				catch(Exception e)
				{
					tsalary.setText("");
					tsalary.requestFocus();
					
				}
			} 
		}
		if(text==tcontactno)
		{
			if(Pattern.matches("[789]{1}\\d{9}",tcontactno.getText()))
			{
				contactno=tcontactno.getText();
			}
			else
			{
				tcontactno.setText("");
				
				lnotice3.setVisible(true);
				
			}
		}
		
	}
	
}
