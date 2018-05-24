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

public class ReceiptView extends JPanel implements ActionListener{
	JLabel trecieptno;
	JLabel temployeeid;

	/**
	 * Create the panel.
	 */
	CardLayout cl;
	public JPanel parent;
	public Stack<String> stk;
	private JButton bback;
	public ReceiptView(String id,JPanel parent,Stack<String>stk,CardLayout cl) {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		
		ConnectionDB.getConnected1();
		Document  doc = SelectDB.getPayData(id);
		
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
		
		trecieptno = new JLabel(id);
		trecieptno.setBorder(null);
		trecieptno.setOpaque(true);
		trecieptno.setFont(new Font("Arial", Font.PLAIN, 20));
		trecieptno.setBackground(Color.WHITE);
		trecieptno.setBounds(300, 155, 120, 35);
		add(trecieptno);
		
		temployeeid = new JLabel(doc.getString("eid"));
		temployeeid.setFont(new Font("Arial", Font.PLAIN, 22));
		temployeeid.setBounds(300, 205, 120, 35);
		add(temployeeid);
		
		JLabel lblNewLabel = new JLabel("SALARY");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(102, 102, 102));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setBounds(270, 50, 360, 50);
		add(lblNewLabel);
		
		JLabel name = new JLabel(doc.getString("fname"));
		name.setFont(new Font("Arial", Font.PLAIN, 22));
		name.setBounds(300, 255, 200, 35);
		add(name);
		
		JLabel amount = new JLabel(""+doc.get("amount"));
		amount.setFont(new Font("Arial", Font.PLAIN, 22));
		amount.setBounds(300, 305, 120, 35);
		add(amount);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		JLabel date = new JLabel(df.format(doc.getDate("date")));
		date.setFont(new Font("Arial", Font.PLAIN, 22));
		date.setBounds(300, 355, 120, 35);
		add(date);
		
		bback = new JButton("Back");
		bback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bback.setForeground(Color.WHITE);
		bback.addActionListener(this);
		bback.setFont(new Font("Arial", Font.BOLD, 22));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(400, 449, 120, 50);
		add(bback);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton)arg0.getSource();
		if(b==bback)
		{
			stk.pop();
			cl.show(parent,"2.4");
			parent.remove(this);
		}
			
				
		
	}
}
