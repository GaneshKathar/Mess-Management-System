package employee.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Stack;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.toedter.calendar.JDateChooser;

import employee.controller.ConnectionDB;
import employee.controller.InsertDB;
import employee.controller.SelectDB;
import employee.controller.UpdateDB;
import employee.model.Payment;


public class EPayment extends JPanel implements FocusListener,ActionListener,ItemListener{
	JTextField tamount;
	JButton bsave;
	JLabel trecieptno;
	JLabel temployeeid;
	JComboBox <String>cbname ;
	JLabel lnotice;
	
	int reciept_no;
	int employee_id;
	String name;
	int amount;
	JLabel lnotice1;
	ArrayList<String> arr;
	JDateChooser dateChooser;
	Payment p;
	CardLayout cl;
	private JLabel lnotice2;
	public JPanel parent;
	public Stack<String> stk;
	private JButton bback;
	public EPayment(JPanel parent,Stack<String>stk,CardLayout cl) {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		
		p=new Payment();
		ConnectionDB.getConnected1();
		Calendar c = Calendar.getInstance();
		String m= c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		
		String rid=m.toUpperCase().substring(0, 3)+SelectDB.getPCount();
		
		JLabel lrecieptno = new JLabel("Reciept No");
		lrecieptno.setBackground(Color.WHITE);
		lrecieptno.setOpaque(true);
		lrecieptno.setHorizontalAlignment(SwingConstants.TRAILING);
		lrecieptno.setFont(new Font("Arial", Font.BOLD, 22));
		lrecieptno.setBounds(150, 150, 120, 40);
		add(lrecieptno);
		
		JLabel lemployeeid = new JLabel("Employee ID");
		lemployeeid.setOpaque(true);
		lemployeeid.setHorizontalAlignment(SwingConstants.TRAILING);
		lemployeeid.setFont(new Font("Arial", Font.BOLD, 22));
		lemployeeid.setBackground(Color.WHITE);
		lemployeeid.setBounds(131, 200, 138, 40);
		add(lemployeeid);
		
		JLabel lname = new JLabel("Name");
		lname.setOpaque(true);
		lname.setHorizontalAlignment(SwingConstants.TRAILING);
		lname.setFont(new Font("Arial", Font.BOLD, 22));
		lname.setBackground(Color.WHITE);
		lname.setBounds(150, 250, 120, 40);
		add(lname);
		
		JLabel lamount = new JLabel("Amount");
		lamount.setOpaque(true);
		lamount.setHorizontalAlignment(SwingConstants.TRAILING);
		lamount.setFont(new Font("Arial", Font.BOLD, 22));
		lamount.setBackground(Color.WHITE);
		lamount.setBounds(150, 300, 120, 40);
		add(lamount);
		
		JLabel ldate = new JLabel("Date");
		ldate.setOpaque(true);
		ldate.setHorizontalAlignment(SwingConstants.TRAILING);
		ldate.setFont(new Font("Arial", Font.BOLD, 22));
		ldate.setBackground(Color.WHITE);
		ldate.setBounds(150, 350, 120, 40);
		add(ldate);
		
		trecieptno = new JLabel(rid);
		trecieptno.setBorder(null);
		trecieptno.setOpaque(true);
		trecieptno.setFont(new Font("Arial", Font.PLAIN, 20));
		trecieptno.setBackground(Color.WHITE);
		trecieptno.setBounds(300, 155, 120, 35);
		add(trecieptno);
		
		tamount = new JTextField();
		tamount.setBorder(null);
		tamount.setColumns(10);
		tamount.setFont(new Font("Arial", Font.PLAIN ,20));
		tamount.setBounds(300, 305, 120, 35);
		tamount.addFocusListener(this);
		add(tamount);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(300, 340, 120, 2);
		add(separator_3);
		arr = new ArrayList<String>();
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add("-Select one-");
		arr.add("<invalide>");
		
		
		MongoCursor<Document> mc =SelectDB.getElist();
		while(mc.hasNext())
		{
			Document doc = mc.next();
			Document doc1= (Document) doc.get("_id");
			String id = (String) doc1.get("id");
			String name = doc.getString("fname")+" "+doc.getString("surname");
			arr.add(id);
			arr1.add(name);
		}
		
		cbname = new JComboBox<String>(new DefaultComboBoxModel(arr1.toArray()));
		cbname.setForeground(Color.BLACK);
		cbname.setBackground(Color.WHITE);
		cbname.setFont(new Font("Arial", Font.PLAIN, 22));
		cbname.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cbname.setBounds(300, 255, 264, 35);
		cbname.addItemListener(this);
		add(cbname);
		
		bsave = new JButton("Save");
		bsave.setForeground(new Color(255, 255, 255));
		bsave.setBackground(new Color(102, 102, 102));
		bsave.setFont(new Font("Arial", Font.BOLD, 22));
		bsave.setBounds(270, 450, 120, 50);
		add(bsave);
		bsave.addActionListener(this);
		
		temployeeid = new JLabel("");
		temployeeid.setFont(new Font("Arial", Font.PLAIN, 22));
		temployeeid.setBounds(300, 205, 120, 35);
		add(temployeeid);
		
		lnotice = new JLabel("Amount field is Empty");
		lnotice.setVisible(false);
		lnotice.setForeground(Color.RED);
		lnotice.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice.setFont(new Font("Arial", Font.PLAIN, 16));
		lnotice.setBounds(255, 403, 345, 19);
		add(lnotice);
		
		lnotice1 = new JLabel("Select Employee");
		lnotice1.setVisible(false);
		lnotice1.setForeground(Color.RED);
		lnotice1.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice1.setFont(new Font("Arial", Font.PLAIN, 16));
		lnotice1.setBounds(255, 403, 345, 19);
		add(lnotice1);
		
		lnotice2 = new JLabel("Connection Problem Please try again");
		lnotice2.setVisible(false);
		lnotice2.setForeground(Color.RED);
		lnotice2.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice2.setFont(new Font("Arial", Font.PLAIN, 16));
		lnotice2.setBounds(255, 403, 345, 19);
		add(lnotice2);
		
		JLabel lblNewLabel = new JLabel("SALARY");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(102, 102, 102));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setBounds(270, 50, 360, 50);
		add(lblNewLabel);
		
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 20));
		dateChooser.setDateFormatString("dd/MM/ yyyy");
		dateChooser.setBounds(300, 350, 158, 35);
		add(dateChooser);
		
		bback = new JButton("Back");
		bback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bback.setForeground(Color.WHITE);
		bback.addActionListener(this);
		bback.setFont(new Font("Arial", Font.BOLD, 22));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(510, 450, 120, 50);
		add(bback);
		
		
		
		

	}
	
	public int isEmpty()
	{
		if(tamount.getText().isEmpty())
		{
			return 1;
		}
		else if(cbname.getSelectedIndex()==0)
		{
			return 2;
		}
		else
			return 0;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton)arg0.getSource();
		if(b==bsave)
		{
			lnotice2.setVisible(false);
			if(isEmpty()==1)
			{
				lnotice.setVisible(true);
			}
			else if(isEmpty()==2)
			{
				lnotice1.setVisible(true);
			}
			else
			{
				p.date=dateChooser.getDate();
				p.amountpaid=amount;
				p.eid=temployeeid.getText();
				p.name=(String) cbname.getSelectedItem();
				p.pid=trecieptno.getText();
				if(!addTODatabase(p))
				{
					lnotice2.setVisible(true);
				}
				else
				{
					clear();
				}
				
			}
		}
		if(b==bback)
		{
			stk.pop();
			cl.show(parent,"2");
			parent.remove(this);

		}
		
		
	}
	private void clear(){
		UpdateDB.updatePCount();
		Calendar c = Calendar.getInstance();
		String m= c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		
		String rid=m.toUpperCase().substring(0, 3)+SelectDB.getPCount();
		dateChooser.setDate(new Date());
		cbname.setSelectedIndex(0);
		tamount.setText("");
		temployeeid.setText("");
		trecieptno.setText(rid);
	}
	
	private boolean addTODatabase(Payment p) 
	{
		try
		{
			 InsertDB.insertReceipt(p);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	@Override
	
	public void focusGained(FocusEvent arg0) {
		
		lnotice.setVisible(false);
		lnotice1.setVisible(false);
		
	}
	@Override
	
	public void focusLost(FocusEvent arg0) {
		
		JTextField text= (JTextField)arg0.getSource();
		if(text==tamount)
		{
			if(!(text.getText().isEmpty()))
			{
				try
				{
					amount = Integer.parseInt(text.getText());
				}
				catch(Exception e)
				{
					text.setText("");
					text.requestFocus();
				}
			}
				
		}
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		JComboBox<String>j=(JComboBox<String>)arg0.getSource();
		int id = j.getSelectedIndex();
		temployeeid.setText(arr.get(id));
		
	}
	
	

	
	
}
