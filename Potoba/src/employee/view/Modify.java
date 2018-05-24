package employee.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;





import org.bson.Document;

import com.toedter.calendar.JDateChooser;

import employee.controller.ConnectionDB;
import employee.controller.SelectDB;
import employee.controller.UpdateDB;
import employee.model.Employee;

public class Modify extends JPanel implements FocusListener,ActionListener {
	private JTextField tfirstn;
	private JTextField tmiddle;
	private JTextField tsurname;
	private JTextField taddress;
	private JTextField tsalary;
	private JTextField tcontactno;
	private JLabel teid; 
	JRadioButton rbmale ;
	JRadioButton rbfemale;
	JRadioButton rbdouble;
	JRadioButton rbsingle;
	JLabel lnotice1;
	JLabel lnotice2;
	JButton bsave ;
	ButtonGroup bg1;
	ButtonGroup bg2;
	JLabel lnotice3;
	Employee emp;
	JDateChooser dateChooser;
	CardLayout cl;
	
	String firstname,middle,surname,gender,address,contactno,shift;
	int eid,salary;
	private JRadioButton rbresume;
	private JRadioButton rbsuspend;
	private ButtonGroup bg3;
	public JPanel parent;
	public Stack<String> stk;
	private JButton bback;


	
	public Modify(String id,JPanel parent,Stack<String>stk,CardLayout cl) 
	{
		setForeground(new Color(153, 0, 0));
		setBackground(Color.WHITE);
		setLayout(null);
		ConnectionDB.getConnected1();
		emp = new Employee();
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		
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
		
		tfirstn = new JTextField(doc.getString("fname"));
		tfirstn.setBackground(Color.WHITE);
		tfirstn.setBorder(null);
		tfirstn.setFont(new Font("Arial", Font.BOLD, 22));
		tfirstn.setBounds(250, 155, 150, 35);
		add(tfirstn);
		tfirstn.setColumns(10);
		tfirstn.addFocusListener(this);
		
		tmiddle = new JTextField(doc.getString("middle"));
		tmiddle.setBorder(null);
		tmiddle.setFont(new Font("Arial", Font.BOLD, 22));
		tmiddle.setColumns(10);
		tmiddle.setBounds(425, 155, 150, 35);
		add(tmiddle);
		tmiddle.addFocusListener(this);
		
		tsurname = new JTextField(doc.getString("surname"));
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
		
		
		teid = new JLabel(id);
		teid.setBorder(null);
		teid.setOpaque(true);
		teid.setBackground(Color.WHITE);
		teid.setHorizontalAlignment(SwingConstants.TRAILING);
		teid.setFont(new Font("Arial", Font.BOLD, 22));
		teid.setBounds(250, 265, 100, 35);
		add(teid);
		
		
		taddress = new JTextField(doc.getString("address"));
		taddress.setFont(new Font("Arial", Font.BOLD, 22));
		taddress.setBorder(null);
		taddress.setBounds(250, 315, 500, 35);
		add(taddress);
		taddress.setColumns(10);
		taddress.addFocusListener(this);
		
		salary=doc.getInteger("salary");
		tsalary = new JTextField(""+doc.getInteger("salary"));
		tsalary.setBorder(null);
		tsalary.setFont(new Font("Arial", Font.BOLD, 22));
		tsalary.setColumns(10);
		tsalary.setBounds(250, 365, 175, 35);
		add(tsalary);
		tsalary.addFocusListener(this);
		
		
		tcontactno = new JTextField(doc.getString("contactno"));
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
		
		bsave = new JButton("Save");
		bsave.setForeground(new Color(255, 255, 255));
		bsave.setBackground(new Color(102, 102, 102));
		bsave.setFont(new Font("Arial", Font.BOLD, 22));
		bsave.setBounds(270, 544, 120, 50);
		add(bsave);
		bsave.addActionListener(this);
		
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
		lnotice1.setBounds(225, 508, 438, 24);
		add(lnotice1);
		lnotice1.setVisible(false);
		
		lnotice2 = new JLabel("Connection problem,Please Try again");
		lnotice2.setOpaque(true);
		lnotice2.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice2.setForeground(new Color(153, 0, 0));
		lnotice2.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice2.setBackground(Color.WHITE);
		lnotice2.setBounds(225, 508, 438, 24);
		add(lnotice2);
		lnotice2.setVisible(false);
		rbresume = new JRadioButton("Resume");
		rbresume.setForeground(Color.BLACK);
		rbresume.setFont(new Font("Arial", Font.BOLD, 22));
		rbresume.setBackground(Color.WHITE);
		rbresume.setBounds(250, 460, 109, 40);
		add(rbresume);
		
		rbsuspend = new JRadioButton("Suspend");
		rbsuspend.setForeground(Color.BLACK);
		rbsuspend.setFont(new Font("Arial", Font.BOLD, 22));
		rbsuspend.setBackground(Color.WHITE);
		rbsuspend.setBounds(400, 460, 128, 40);
		add(rbsuspend);
		
		
		bg3=new ButtonGroup();
		bg3.add(rbresume);
		bg3.add(rbsuspend);
		
		bg1=new ButtonGroup();
		bg1.add(rbmale);
		bg1.add(rbfemale);
		
		bg2=new ButtonGroup();
		bg2.add(rbsingle);
		bg2.add(rbdouble);
		
		if(doc.getInteger("gender")==1)
		{
			rbmale.setSelected(true);
		}
		else if(doc.getInteger("gender")==2)
		{
			rbfemale.setSelected(true);
		}
		if(doc.getInteger("shift")==1)
		{
			rbsingle.setSelected(true);
		}
		else if(doc.getInteger("shift")==2)
		{
			rbdouble.setSelected(true);
		}
		if(doc.getBoolean("status")==true)
		{
			rbresume.setSelected(true);
		}
		else if(doc.getBoolean("status")==false)
		{
			rbsuspend.setSelected(true);
		}
		
		lnotice3 = new JLabel("Invalide Contact number");
		lnotice3.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice3.setForeground(new Color(153, 0, 0));
		lnotice3.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice3.setOpaque(true);
		lnotice3.setBackground(Color.WHITE);
		lnotice3.setBounds(225, 508, 438, 24);
		add(lnotice3);
		lnotice3.setVisible(false);
		
		dateChooser = new JDateChooser(doc.getDate("date"));
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 20));
		dateChooser.setDateFormatString("dd/mm/yyyy");
		dateChooser.setBounds(583, 265, 167, 35);
		add(dateChooser);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 22));
		lblStatus.setBounds(97, 460, 128, 40);
		add(lblStatus);
		
		
		bback = new JButton("Back");
		bback.addActionListener(this);
		bback.setForeground(Color.WHITE);
		bback.setFont(new Font("Arial", Font.BOLD, 22));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(510, 544, 120, 50);
		add(bback);

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
		else if(bg1.getSelection()==null||bg2.getSelection()==null||bg3.getSelection()==null)
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
		try
		{   System.out.println("Adding to Database "+emp.id+"  "+emp.salary);
			UpdateDB.updateEmpData(emp);
		}
		catch(Exception e)
		{
			lnotice2.setVisible(true);
		}
	}
	private void clear()
	{
		tfirstn.setText("");
		tmiddle.setText("");
		tsurname.setText("");
		teid.setText("");
		taddress.setText("");
		tsalary.setText("");
		tcontactno.setText("");
		bg1.clearSelection(); 
		bg2.clearSelection();
		bg3.clearSelection();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton b =(JButton)arg0.getSource();
		lnotice2.setVisible(false);
		if(b==bsave)
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
				if(rbresume.isSelected())
					emp.status=true;
				else if(rbsuspend.isSelected())
					emp.status=false;
					
				emp.fname=tfirstn.getText();
				emp.middle=tmiddle.getText();
				emp.surname=tsurname.getText();
				emp.address=taddress.getText();
				emp.mobileno=tcontactno.getText();
				emp.id=teid.getText();
				emp.salary=salary;
				emp.date=dateChooser.getDate();
				
				addToDatabase();
				System.out.println("Added to Database");
			}	
		}
		if(b==bback)
		{
			stk.pop();
			cl.show(parent,"2.2");
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


