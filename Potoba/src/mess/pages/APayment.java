package mess.pages;

import mess.interface_db.ExtractDB;
import mess.interface_db.IDLoader;
import mess.interface_db.InsertDB;
import mess.interface_db.UpdateDB;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import mess.object.Payment;

import com.mongodb.MongoClient;
import com.toedter.calendar.JDateChooser;


public class APayment extends JPanel implements ActionListener,FocusListener {
	private JTextField tmid,tamt;
	private JLabel lpid,lpid2,lmid,lamt,lname,ldate,lname2,lerror,lerror2;
	private JButton bpay,bback;
	private int flag1,flag2;
	private JLabel lblNewLabel;
	private JDateChooser dChooser;
	private Payment pobj;
	private Calendar cal;
	private CardLayout card;
	private JPanel parent;
	private Stack<String> MessStack;
	
	 public APayment(JPanel par,CardLayout cl,Stack stk) {
		 MessStack=stk;
			parent=par;
			card=cl;
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		lpid = new JLabel("Payment ID");
		lpid.setHorizontalAlignment(SwingConstants.TRAILING);
		lpid.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lpid.setOpaque(true);
		lpid.setBackground(new Color(255, 255, 255));
		lpid.setBounds(150, 150, 120, 40);
		add(lpid);
		
		ldate = new JLabel("Date");
		ldate.setOpaque(true);
		ldate.setHorizontalAlignment(SwingConstants.TRAILING);
		ldate.setFont(new Font("Century Gothic", Font.BOLD, 20));
		ldate.setBackground(Color.WHITE);
		ldate.setBounds(150, 350, 120, 40);
		add(ldate);
		
		lmid = new JLabel("Member ID");
		lmid.setOpaque(true);
		lmid.addFocusListener(this);
		lmid.setHorizontalAlignment(SwingConstants.TRAILING);
		lmid.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lmid.setBackground(new Color(255, 255, 255));
		lmid.setBounds(150, 200, 120, 40);
		add(lmid);
		
		lname = new JLabel("Name");
		lname.setOpaque(true);
		lname.setHorizontalAlignment(SwingConstants.TRAILING);
		lname.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lname.setBackground(new Color(255, 255, 255));
		lname.setBounds(150, 250, 120, 40);
		add(lname);
		
		lamt = new JLabel("Amount");
		lamt.setOpaque(true);
		lamt.setHorizontalAlignment(SwingConstants.TRAILING);
		lamt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lamt.setBackground(new Color(255, 255, 255));
		lamt.setBounds(150, 300, 120, 40);
		add(lamt);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(300, 240, 70, 10);
		add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(300, 340, 110, 10);
		add(separator_3);
		
		cal=Calendar.getInstance();
		cal.setTime(new Date());
		
		lpid2 = new JLabel(""+(cal.get(Calendar.YEAR)-2000)+(cal.get(Calendar.MONTH)+1)+IDLoader.loadPID());
		lpid2.setHorizontalAlignment(SwingConstants.LEFT);
		lpid2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lpid2.setOpaque(true);
		lpid2.setBackground(new Color(255, 255, 255));
		lpid2.setBounds(300, 155, 70, 35);
		add(lpid2);
		
		lname2 = new JLabel("");
		lname2.setOpaque(true);
		lname2.setHorizontalAlignment(SwingConstants.LEFT);
		lname2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lname2.setBackground(Color.WHITE);
		lname2.setBounds(300, 255, 199, 35);
		add(lname2);
		
		tmid = new JTextField();
		tmid.addFocusListener(this);
		tmid.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tmid.setBorder(null);
		tmid.setBounds(300, 205, 70, 35);
		add(tmid);
		tmid.setColumns(10);
		
		tamt = new JTextField();
		tamt.addFocusListener(this);
		tamt.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tamt.setBorder(null);
		tamt.setColumns(10);
		tamt.setBounds(300, 305, 110, 35);
		add(tamt);
		
		bpay = new JButton("Pay");
		bpay.addActionListener(this);
		bpay.setForeground(new Color(255, 255, 255));
		bpay.setBackground(new Color(102, 102, 102));
		bpay.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bpay.setBounds(270, 450, 120, 50);
		add(bpay);
		
		bback = new JButton("Back");
		bback.addActionListener(this);
		bback.setForeground(new Color(255, 255, 255));
		bback.setBackground(new Color(102, 102, 102));
		bback.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bback.setBounds(510, 450, 120, 50);
		add(bback);
		
		lerror = new JLabel("Do Not leave the Fields Empty");
		lerror.setForeground(new Color(153, 0, 0));
		lerror.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lerror.setHorizontalAlignment(SwingConstants.CENTER);
		lerror.setBounds(310, 400, 280, 40);
		lerror.setVisible(false);
		add(lerror);
		
		lerror2 = new JLabel("Problem in Payment Please Try Again");
		lerror2.setForeground(new Color(153, 0, 0));
		lerror2.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lerror2.setHorizontalAlignment(SwingConstants.CENTER);
		lerror2.setBounds(280, 400, 340, 40);
		lerror2.setVisible(false);
		add(lerror2);
		
		lblNewLabel = new JLabel("PAYMENT");
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(102, 102, 102));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(270, 50, 360, 50);
		add(lblNewLabel);
		
		dChooser = new JDateChooser();
		dChooser.setDate(new Date());
		dChooser.getCalendarButton().setFont(new Font("Century Gothic", Font.PLAIN, 12));
		dChooser.setBounds(310, 355, 100, 30);
		add(dChooser);

	}
	
	@Override
	public void focusGained(FocusEvent e) {
	
	}
	@Override
	public void focusLost(FocusEvent e) {
		JTextField txt=(JTextField)e.getSource();
		if(txt==tamt)
		{	try{
				Integer.parseInt(txt.getText());
			}
			catch(Exception e1){
				tamt.setText("");
			}
		}
		if(txt==tmid&&tmid.getText().trim().isEmpty()==false)
		{
			String nm=ExtractDB.getName(tmid.getText().trim());
			if(nm==null){
				lname2.setText("Invalid ID");
				return;
			}
			lname2.setText(nm);
					
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn=(JButton)e.getSource();
		if(btn==bpay)
		{
			lerror.setVisible(false);
			lerror2.setVisible(false);
			if(isEmpty()==false){
				lerror.setVisible(true);
				return;
			}
			if(pay()==false)
			{
				lerror2.setVisible(true);
				return;
			}
			clear();
		}
		if(btn==bback)
		{
			MessStack.pop();
			card.show(parent,"1");
			parent.remove(this);
		}
		
	}
	
	void setName()
	{
		//lname2.setText("");
	}
	Boolean isEmpty()
	{
		int flag=1;
		if(lpid2.getText().trim().isEmpty()==true)
		{
			flag=0;
		}
		else if(tmid.getText().trim().isEmpty()==true)
		{
			flag=0;
		}
		else if(lname2.getText().trim().isEmpty()==true)
		{
			flag=0;
		}
		else if(tamt.getText().trim().isEmpty()==true)
		{
			flag=0;	
		}
		if(flag==1)
			return true;
		return false;
	}
	void clear()
	{
		lpid2.setText(""+(cal.get(Calendar.YEAR)-2000)+(cal.get(Calendar.MONTH)+1)+IDLoader.loadPID());
		lname2.setText("");
		tamt.setText("");
		tmid.setText("");
	}
	Boolean pay()
	{
		loadObject();
		try{
			UpdateDB.uPay(pobj);
			InsertDB.iPayment(pobj);
			IDLoader.incPID();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	void loadObject()
	{
		pobj=new Payment();
		pobj.pid=lpid2.getText();
		pobj.mid=tmid.getText();
		pobj.amt=Integer.parseInt(tamt.getText());
		pobj.dop=dChooser.getDate();
		
	}
}
