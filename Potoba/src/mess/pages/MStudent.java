package mess.pages;

import mess.interface_db.ExtractDB;
import mess.interface_db.FeeLoader;
import mess.interface_db.UpdateDB;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.toedter.calendar.JDateChooser;

import mess.object.Student;

import javax.swing.JComboBox;

import org.bson.Document;

public class MStudent extends JPanel implements FocusListener,ActionListener {
	
	private JLabel lFirstName,lMiddleName,lLastName,lContactNo,lYear,lGender,lblDateOfJoining,lCollege,lMid,lMeals,lblStudentRegistration,lName,tMid;
	private JTextField tContact,tFirstName,tMiddleName,tLastName;
	private JComboBox tYear,tCollege;
	private JSeparator separator,separator_1,separator_2,separator_3,separator_6,separator_9,separator_10, separator_11;
	private JRadioButton rMale,rFemale;
	private ButtonGroup gender,meals;
	private JRadioButton rTwo,rOne;
	private JButton bmod;
	private JDateChooser dChooser;
	private Student sobj;
	private Calendar cal;
	public String mid;
	private JPanel parent;
	private CardLayout card;
	private Stack MessStack;
	
	public MStudent(String id,JPanel par,CardLayout cl,Stack stk) 
	{

		MessStack=stk;
		parent=par;
		card=cl;
		mid=id;
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		ExtractDB.db=mongoClient.getDatabase("Mess");
		UpdateDB.db=mongoClient.getDatabase("Mess");
		
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		loadObject();
		
		lName = new JLabel("Name");
		lName.setHorizontalAlignment(SwingConstants.TRAILING);
		lName.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lName.setBounds(150, 150, 120, 40);
		add(lName);
		
		lFirstName = new JLabel("First ");
		lFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lFirstName.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lFirstName.setBounds(340, 129, 40, 20);
		add(lFirstName);
		
		lMiddleName = new JLabel("Middle");
		lMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
		lMiddleName.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lMiddleName.setBounds(480, 129, 60, 20);
		add(lMiddleName);
		
		lLastName = new JLabel("Last");
		lLastName.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lLastName.setBounds(640, 129, 40, 20);
		add(lLastName);
		
		lContactNo = new JLabel("Contact No");
		lContactNo.setHorizontalAlignment(SwingConstants.TRAILING);
		lContactNo.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lContactNo.setBounds(450, 390, 120, 40);
		add(lContactNo);
		
		lGender = new JLabel("Gender");
		lGender.setHorizontalAlignment(SwingConstants.TRAILING);
		lGender.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lGender.setBounds(150, 210, 120, 40);
		add(lGender);
		
		lblDateOfJoining = new JLabel("Date of Joining");
		lblDateOfJoining.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDateOfJoining.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblDateOfJoining.setBounds(450, 270, 150, 40);
		add(lblDateOfJoining);
		
		lCollege = new JLabel("College");
		lCollege.setHorizontalAlignment(SwingConstants.TRAILING);
		lCollege.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lCollege.setBounds(150, 330, 120, 40);
		add(lCollege);
		
		lYear = new JLabel("Year");
		lYear.setHorizontalAlignment(SwingConstants.TRAILING);
		lYear.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lYear.setBounds(150, 390, 120, 40);
		add(lYear);
		
		lMid = new JLabel("Member ID");
		lMid.setHorizontalAlignment(SwingConstants.TRAILING);
		lMid.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lMid.setBounds(150, 270, 120, 40);
		add(lMid);
		
		lMeals = new JLabel("Meals");
		lMeals.setHorizontalAlignment(SwingConstants.TRAILING);
		lMeals.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lMeals.setBounds(150, 450, 120, 40);
		add(lMeals);
		
		lblStudentRegistration = new JLabel("Student Registration");
		lblStudentRegistration.setForeground(Color.WHITE);
		lblStudentRegistration.setOpaque(true);
		lblStudentRegistration.setBackground(new Color(102, 102, 102));
		lblStudentRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentRegistration.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblStudentRegistration.setBounds(320, 60, 300, 40);
		add(lblStudentRegistration);
		
		separator = new JSeparator();
		separator.setBounds(419, 188, 1, 2);
		add(separator);
			
		separator_1 = new JSeparator();
		separator_1.setBounds(300, 190, 120, 1);
		add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(450, 190, 120, 1);
		add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(599, 190, 121, 1);
		add(separator_3);
		
		separator_6 = new JSeparator();
		separator_6.setBounds(300, 248, -432, 232);
		add(separator_6);
		
		separator_11 = new JSeparator();
		separator_11.setBounds(600, 430, 170, 1);
		add(separator_11);
		
		tFirstName = new JTextField(""+sobj.fname);
		tFirstName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tFirstName.setBounds(300, 150, 120, 40);
		add(tFirstName);
		tFirstName.setColumns(10);
		tFirstName.setBorder(null);
		tFirstName.addFocusListener(this);
		
		tMiddleName = new JTextField(""+sobj.pname);
		tMiddleName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tMiddleName.setBounds(450, 150, 120, 40);
		add(tMiddleName);
		tMiddleName.setColumns(10);
		tMiddleName.setBorder(null);
		tMiddleName.addFocusListener(this);
		
		tLastName = new JTextField(""+sobj.lname);
		tLastName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tLastName.setBounds(600, 150, 120, 40);
		add(tLastName);
		tLastName.setColumns(10);
		tLastName.setBorder(null);
		tLastName.addFocusListener(this);
		
		tContact = new JTextField(""+sobj.contact);
		tContact.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tContact.setBounds(600, 390, 170, 40);
		add(tContact);
		tContact.setColumns(10);
		tContact.setBorder(null);
		tContact.addFocusListener(this);
		
		dChooser = new JDateChooser(sobj.doj);
		dChooser.setDate(new Date());
		dChooser.getCalendarButton().setFont(new Font("Century Gothic", Font.PLAIN, 12));
		dChooser.setBounds(630, 275, 100, 30);
		add(dChooser);
		
		DefaultComboBoxModel<String> clg=new DefaultComboBoxModel<String>();
		clg.addElement("Select College");
		clg.addElement("1.Walchand College Of Engineering");
		clg.addElement("2. Bharti Vidhyapeeth");
		clg.addElement("3. Other");
		
		tCollege = new JComboBox<String>(clg);
		tCollege.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tCollege.setBounds(300, 330, 450, 40);
		tCollege.setSelectedIndex(sobj.college);
		add(tCollege);
		
		DefaultComboBoxModel<String> yr=new DefaultComboBoxModel<String>();
		yr.addElement("Select Year");
		yr.addElement("I");
		yr.addElement("II");
		yr.addElement("III");
		yr.addElement("IV");
		yr.addElement("V");
		
		tYear = new JComboBox<String>(yr);
		tYear.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tYear.setBounds(300, 390, 140, 40);
		tYear.setSelectedIndex(sobj.year);
		add(tYear);
		
		cal=Calendar.getInstance();
		cal.setTime(new Date());
		
		tMid = new JLabel(""+sobj.mid);
		tMid.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tMid.setForeground(Color.BLACK);
		tMid.setBackground(Color.WHITE);
		tMid.setBounds(300, 270, 120, 40);
		add(tMid);
		
		bmod = new JButton("Modify");
		bmod.setBackground(new Color(102, 102, 102));
		bmod.setForeground(Color.WHITE);
		bmod.setFont(new Font("Century Gothic", Font.BOLD, 20));
		bmod.setBounds(400, 510, 120, 40);
		add(bmod);
		bmod.addActionListener(this);
		
		rMale = new JRadioButton("Male");
		rMale.setBackground(Color.WHITE);
		rMale.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		rMale.setBounds(300, 210, 120, 40);
		add(rMale);
		
		rFemale = new JRadioButton("Female");
		rFemale.setBackground(Color.WHITE);
		rFemale.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		rFemale.setBounds(450, 210, 120, 40);
		add(rFemale);
		
		gender = new ButtonGroup();
		gender.add(rMale);
		gender.add(rFemale);
		
		if(sobj.gender==1)
			rMale.setSelected(true);
		else
			rFemale.setSelected(true);
		
		
		rOne = new JRadioButton("One Time");
		rOne.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		rOne.setBackground(Color.WHITE);
		rOne.setBounds(300, 450, 120, 40);
		add(rOne);
		
		rTwo = new JRadioButton("Two Time");
		rTwo.setBackground(Color.WHITE);
		rTwo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		rTwo.setBounds(450, 450, 120, 40);
		add(rTwo);
		
		meals=new ButtonGroup();
		meals.add(rOne);
		meals.add(rTwo);
		
		if(sobj.meals==1)
			rOne.setSelected(true);
		else
			rTwo.setSelected(true);
	}
	
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		JTextField tf = (JTextField)e.getSource();
		
		if(tf==tFirstName && !tf.getText().trim().isEmpty() && lFirstName.getForeground().equals(Color.RED)){
			lFirstName.setForeground(Color.BLACK);
		}
		
		if(tf==tMiddleName && !tf.getText().trim().isEmpty() && lMiddleName.getForeground().equals(Color.RED)){
			lMiddleName.setForeground(Color.BLACK);
		}
		
		if(tf==tLastName && !tf.getText().trim().isEmpty() && lLastName.getForeground().equals(Color.RED)){
			lLastName.setForeground(Color.BLACK);
		}
		if(tf == tContact){
			String contactPatternString = "^[7-9][0-9]{9}$";
			Pattern contactPattern = Pattern.compile(contactPatternString);
			Matcher matcher = contactPattern.matcher(tf.getText());
			
			if(!matcher.matches()){
				tf.setForeground(Color.RED);
				lContactNo.setForeground(Color.RED);
			}
			else{
				lContactNo.setForeground(Color.BLACK);
				tf.setForeground(Color.BLACK);
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn=(JButton)e.getSource();
		if(btn==bmod)
		{
			if(isEmpty()==true)
				return;
			if(add2db()==false)
				return;
		}
		MessStack.pop();
		card.show(parent,"1.4");
		parent.remove(this);
		
	}
	boolean isEmpty()
	{
		int flag=1;
		lFirstName.setForeground(Color.BLACK);
		lMiddleName.setForeground(Color.BLACK);
		lLastName.setForeground(Color.BLACK);
		lGender.setForeground(Color.BLACK);
		lCollege.setForeground(Color.BLACK);
		lYear.setForeground(Color.BLACK);
		lContactNo.setForeground(Color.BLACK);
		lMeals.setForeground(Color.BLACK);
		
		if(tFirstName.getText().trim().isEmpty()==true){
			lFirstName.setForeground(new Color(255,0,0));
			tFirstName.requestFocus();
			flag=0;
		}
		else if(tMiddleName.getText().trim().isEmpty() == true){
			lMiddleName.setForeground(new Color(255,0,0));
			tMiddleName.requestFocus();
			flag=0;
		}
		else if(tLastName.getText().trim().isEmpty() == true){
			lLastName.setForeground(new Color(255,0,0));
			tLastName.requestFocus();
			flag=0;
		}
		else if(gender.getSelection()==null){
			rMale.requestFocus();
			lGender.setForeground(Color.RED);
			flag=0;
		}
		else if(tCollege.getSelectedIndex()==0){
			lCollege.setForeground(new Color(255,0,0));
			flag=0;
		}
		else if(tYear.getSelectedIndex()==0){
			lYear.setForeground(new Color(255,0,0));
			
			flag=0;
		}
		else if(tContact.getText().trim().isEmpty() == true || tContact.getForeground()== Color.RED){
			lContactNo.setForeground(new Color(255,0,0));
			tContact.requestFocus();
			flag=0;
		}
		else if(meals.getSelection()==null){
			lMeals.requestFocus();
			lMeals.setForeground(Color.RED);
			flag=0;
		}
		if(flag==0)
			return true;
		return false;
	}
	
	boolean add2db()
	{
		reloadObject();
		try{
			UpdateDB.uMember(sobj);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	void loadObject()
	{
		sobj=new Student();
		MongoCursor<Document> cursor=ExtractDB.getIDMembers(mid);
		Document doc;
		try{
			doc=cursor.next();
			sobj.fname=doc.getString("first-name");
			sobj.pname=doc.getString("parent-name");
			sobj.lname=doc.getString("last-name");
			sobj.mid=mid;
			sobj.contact=doc.getString("contact");
			sobj.doj=doc.getDate("Date-of-joining");
			sobj.gender=doc.getInteger("gender");
			sobj.meals=doc.getInteger("meals");												
			sobj.status=doc.getInteger("status");
			sobj.college=((Document)doc.get("misc")).getInteger("attribute1");
			sobj.year=((Document)doc.get("misc")).getInteger("attribute2");
			sobj.absentee=doc.getInteger("absentee");
			sobj.ramt=doc.getInteger("remaining-amt");
			cursor.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	void reloadObject()
	{
		sobj.fname=tFirstName.getText().trim();
		sobj.pname=tMiddleName.getText().trim();
		sobj.lname=tLastName.getText().trim();
		sobj.mid=tMid.getText().trim();
		sobj.contact=tContact.getText().trim();
		sobj.doj=dChooser.getDate();
		if(rMale.isSelected())
			sobj.gender=1;
		else if(rFemale.isSelected())
			sobj.gender=2;
		if(rOne.isSelected()){
			sobj.meals=1;
		}
		else if(rTwo.isSelected()){
			sobj.meals=2;
		}																		
		sobj.status=1;
		sobj.college=tCollege.getSelectedIndex();
		sobj.year=tYear.getSelectedIndex();
	}
}
