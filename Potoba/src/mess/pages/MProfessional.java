package mess.pages;

import mess.interface_db.ExtractDB;
import mess.interface_db.UpdateDB;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import org.bson.Document;

import mess.object.Professional;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.toedter.calendar.JDateChooser;

public class MProfessional extends JPanel implements FocusListener,ActionListener{
	private JTextField tFirstName,tMiddleName,tLastName,tContact;
	private JLabel lFirstName,lMiddleName,lLastName,lName,lContactNo,lGender,lblDateOfJoining,lAddress,lMid,lMeals,tMid,lblStudentRegistration,lProfession;
	private JTextField tAddress,tProfession;
	private JSeparator separator,sepFirst,sepMiddle,sepLast,separator_6,sepAddress,sepProfession,sepContact;
	private JRadioButton rMale,rFemale,rTwo,rOne;
	private ButtonGroup meals,gender;
	private JButton bmod;
	private JDateChooser dChooser;
	private Professional pobj;
	private Calendar cal;
	public String mid;
	private JPanel parent;
	private CardLayout card;
	private Stack MessStack;
	
	public MProfessional(String id,JPanel par,CardLayout cl,Stack stk) {

		MessStack=stk;
		parent=par;
		card=cl;
		mid=id;
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		ExtractDB.db=mongoClient.getDatabase("Mess");
		UpdateDB.db=mongoClient.getDatabase("Mess");
		
		loadObject();
		setBackground(Color.WHITE);
		setLayout(null);
		
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
		
		lAddress = new JLabel("Address");
		lAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		lAddress.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lAddress.setBounds(150, 330, 120, 40);
		add(lAddress);
		
		lProfession = new JLabel("Profession");
		lProfession.setHorizontalAlignment(SwingConstants.TRAILING);
		lProfession.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lProfession.setBounds(150, 390, 120, 40);
		add(lProfession);
		
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
		
		sepFirst = new JSeparator();
		sepFirst.setBounds(300, 190, 120, 1);
		add(sepFirst);
		
		sepMiddle = new JSeparator();
		sepMiddle.setBounds(450, 190, 120, 1);
		add(sepMiddle);
		
		sepLast = new JSeparator();
		sepLast.setBounds(599, 190, 121, 1);
		add(sepLast);
		
		sepAddress = new JSeparator();
		sepAddress.setBounds(300, 370, 450, 1);
		add(sepAddress);
		
		sepProfession = new JSeparator();
		sepProfession.setBounds(300, 430, 120, 1);
		add(sepProfession);
		
		sepContact = new JSeparator();
		sepContact.setBounds(600, 430, 170, 1);
		add(sepContact);
		
		lblStudentRegistration = new JLabel(" Professional Registration");
		lblStudentRegistration.setForeground(Color.WHITE);
		lblStudentRegistration.setOpaque(true);
		lblStudentRegistration.setBackground(new Color(102, 102, 102));
		lblStudentRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentRegistration.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblStudentRegistration.setBounds(300, 60, 340, 40);
		add(lblStudentRegistration);
		
		separator = new JSeparator();
		separator.setBounds(419, 188, 1, 2);
		add(separator);
		
		tFirstName = new JTextField(pobj.fname);
		tFirstName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tFirstName.setBounds(300, 150, 120, 40);
		add(tFirstName);
		tFirstName.setColumns(10);
		tFirstName.setBorder(null);
		tFirstName.addFocusListener(this);
		
		tMiddleName = new JTextField(pobj.pname);
		tMiddleName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tMiddleName.setBounds(450, 150, 120, 40);
		add(tMiddleName);
		tMiddleName.setColumns(10);
		tMiddleName.setBorder(null);
		tMiddleName.addFocusListener(this);
		
		tLastName = new JTextField(pobj.lname);
		tLastName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tLastName.setBounds(600, 150, 120, 40);
		add(tLastName);
		tLastName.setColumns(10);
		tLastName.setBorder(null);
		tLastName.addFocusListener(this);
		
		tContact = new JTextField(pobj.contact);
		tContact.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tContact.setBounds(600, 390, 170, 40);
		add(tContact);
		tContact.setColumns(10);
		tContact.setBorder(null);
		tContact.addFocusListener(this);
		
		tAddress = new JTextField(pobj.address);
		tAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tAddress.setBounds(300, 330, 450, 40);
		add(tAddress);
		tAddress.setColumns(10);
		tAddress.setBorder(null);
		tAddress.addFocusListener(this);
		
		tProfession = new JTextField(pobj.occupation);
		tProfession.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tProfession.setBounds(300, 390, 120, 40);
		add(tProfession);
		tProfession.setColumns(10);
		tProfession.setBorder(null);
		tProfession.addFocusListener(this);
	
		cal=Calendar.getInstance();
		cal.setTime(new Date());
		
		tMid = new JLabel(mid);
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

		dChooser = new JDateChooser();
		dChooser.setDate(new Date());
		dChooser.getCalendarButton().setFont(new Font("Century Gothic", Font.PLAIN, 12));
		dChooser.setBounds(630, 275, 100, 30);
		add(dChooser);
		
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
		

		if(pobj.gender==1)
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

		if(pobj.meals==1)
			rOne.setSelected(true);
		else
			rTwo.setSelected(true);	

	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		JTextField tf = (JTextField)arg0.getSource();
		
		if(tf==tFirstName && !tf.getText().trim().isEmpty() && lFirstName.getForeground().equals(Color.RED)){
			lFirstName.setForeground(Color.BLACK);
		}
		
		if(tf==tMiddleName && !tf.getText().trim().isEmpty() && lMiddleName.getForeground().equals(Color.RED)){
			lMiddleName.setForeground(Color.BLACK);
		}
		
		if(tf==tLastName && !tf.getText().trim().isEmpty() && lLastName.getForeground().equals(Color.RED)){
			lLastName.setForeground(Color.BLACK);
		}
		
		if(tf==tAddress && !tf.getText().trim().isEmpty() && lAddress.getForeground().equals(Color.RED)){
			lAddress.setForeground(Color.BLACK);
		}
		
		if(tf==tProfession && !tf.getText().trim().isEmpty() && lProfession.getForeground().equals(Color.RED)){
			lProfession.setForeground(Color.BLACK);
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
		lAddress.setForeground(Color.BLACK);
		lProfession.setForeground(Color.BLACK);
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
		else if(tAddress.getText().trim().isEmpty() == true){
			lAddress.setForeground(new Color(255,0,0));
			tAddress.requestFocus();
			flag=0;
		}
		else if(tProfession.getText().trim().isEmpty() == true){
			lProfession.setForeground(new Color(255,0,0));
			tProfession.requestFocus();
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
			UpdateDB.uMember(pobj);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	void loadObject()
	{
		pobj=new Professional();
		MongoCursor<Document> cursor=ExtractDB.getIDMembers(mid);
		Document doc;
		try{
			doc=cursor.next();
			pobj.fname=doc.getString("first-name");
			pobj.pname=doc.getString("parent-name");
			pobj.lname=doc.getString("last-name");
			pobj.mid=mid;
			pobj.contact=doc.getString("contact");
			pobj.doj=doc.getDate("Date-of-joining");
			pobj.gender=doc.getInteger("gender");
			pobj.meals=doc.getInteger("meals");												
			pobj.status=doc.getInteger("status");
			pobj.occupation=((Document)doc.get("misc")).getString("attribute1");
			pobj.address=((Document)doc.get("misc")).getString("attribute2");
			pobj.absentee=doc.getInteger("absentee");
			pobj.ramt=doc.getInteger("remaining-amt");
			cursor.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void reloadObject()
	{
		pobj=new Professional();
		pobj.fname=tFirstName.getText().trim();
		pobj.pname=tMiddleName.getText().trim();
		pobj.lname=tLastName.getText().trim();
		pobj.mid=tMid.getText().trim();
		pobj.contact=tContact.getText().trim();
		pobj.doj=dChooser.getDate();
		if(rMale.isSelected())
			pobj.gender=1;
		else if(rFemale.isSelected())
			pobj.gender=2;
		if(rOne.isSelected()){
			pobj.meals=1;
		}
		else if(rTwo.isSelected()){
			pobj.meals=2;
		}																		
		pobj.status=1;
		pobj.occupation=tProfession.getText().trim();
		pobj.address=tAddress.getText().trim();
				
	}
}
