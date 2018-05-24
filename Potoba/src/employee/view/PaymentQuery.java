package employee.view;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.toedter.calendar.JDateChooser;
import employee.controller.ConnectionDB;
import employee.controller.DeleteDB;
import employee.controller.SelectDB;



public class PaymentQuery extends JPanel implements FocusListener,ActionListener, ItemListener {

	private JTextField trno;
	JRadioButton rbname;
	JRadioButton rbrno;
	JPanel psearch ;
	JButton bview;
	JButton bdelete ;
	JButton bsearch ;
	JLabel leid ;
	ButtonGroup bg1;
	JLabel lnotice1;
	JLabel lnotice2;
	JLabel lnotice ;
	JDateChooser dateChooser ,dateChooser_1;
	ArrayList <String>arr;
	ArrayList<String>arr1;
	
	int eid,rno,eid2;
	String fname;
	private JScrollPane scrollPane;
	private JTable table;
	private JRadioButton rbmonth;
	private JComboBox <String>cbmonth;
	private JRadioButton rbdate;
	private JComboBox<String> cbname;
	private int flag;
	public JPanel parent;
	public Stack<String> stk;
	CardLayout cl;
	private JButton bback;
	
	public PaymentQuery(JPanel parent,Stack<String>stk,CardLayout cl) {
		setBackground(Color.WHITE);
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		setLayout(null);
		
		ConnectionDB.getConnected1();
		trno = new JTextField();
		trno.setFont(new Font("Arial", Font.PLAIN, 16));
		trno.setBorder(null);
		trno.setBounds(177, 125, 137, 35);
		add(trno);
		trno.setColumns(10);
		trno.addFocusListener(this);
		
		
		rbname = new JRadioButton("Name");
		rbname.setFont(new Font("Arial", Font.BOLD, 18));
		rbname.setBackground(Color.WHITE);
		rbname.setBounds(50, 180, 76, 40);
		add(rbname);
		
		rbrno = new JRadioButton("Reciept No.");
		rbrno.setFont(new Font("Arial", Font.BOLD, 18));
		rbrno.setBackground(Color.WHITE);
		rbrno.setBounds(50, 120, 137, 40);
		add(rbrno);
		
		psearch = new JPanel();
		psearch.setBackground(Color.WHITE);
		psearch.setBounds(360, 120, 500, 300);
		add(psearch);
		psearch.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setBounds(0, 0, 500, 300);
		psearch.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2)
				{
					 JTable target = (JTable)arg0.getSource();
				     int row = target.getSelectedRow();
				     leid.setText(target.getValueAt(row,0).toString());
				 }
			}
		});
		
		
		bview = new JButton("View");
		bview.setForeground(new Color(255, 255, 255));
		bview.setBackground(new Color(102, 102, 102));
		bview.setFont(new Font("Arial", Font.BOLD, 22));
		bview.setBounds(558, 440, 120, 35);
		add(bview);
		bview.addActionListener(this);
		
		bdelete = new JButton("Delete");
		bdelete.setForeground(new Color(255, 255, 255));
		bdelete.setBackground(new Color(153, 0, 0));
		bdelete.setFont(new Font("Arial", Font.BOLD, 22));
		bdelete.setBounds(735, 440, 125, 35);
		add(bdelete);
		bdelete.addActionListener(this);
		
		JLabel lselectedeid = new JLabel("Reciept No.");
		lselectedeid.setFont(new Font("Arial", Font.BOLD, 22));
		lselectedeid.setHorizontalAlignment(SwingConstants.TRAILING);
		lselectedeid.setBounds(360, 490, 138, 40);
		add(lselectedeid);
		
		leid = new JLabel("");
		leid.setHorizontalAlignment(SwingConstants.CENTER);
		leid.setFont(new Font("Arial", Font.PLAIN, 20));
		leid.setBounds(528, 495, 150, 35);
		add(leid);
		
		bsearch = new JButton("Search");
		bsearch.setBackground(new Color(102, 102, 102));
		bsearch.setForeground(new Color(255, 255, 255));
		bsearch.setFont(new Font("Arial", Font.BOLD, 22));
		bsearch.setBounds(50, 385, 120, 35);
		add(bsearch);
		bsearch.addActionListener(this);
		
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(528, 530, 150, 2);
		add(separator_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(177, 160, 139, 2);
		add(separator);
		
		lnotice1 = new JLabel("Selected Field is Empty");
		lnotice1.setForeground(new Color(153, 0, 0));
		lnotice1.setBackground(new Color(255, 255, 255));
		lnotice1.setOpaque(true);
		lnotice1.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice1.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice1.setBounds(63, 349, 231, 24);
		add(lnotice1);
		lnotice1.setVisible(false);
		
		lnotice2 = new JLabel("Connection probelm,Please try again");
		lnotice2.setOpaque(true);
		lnotice2.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice2.setForeground(new Color(153, 0, 0));
		lnotice2.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice2.setBackground(Color.WHITE);
		lnotice2.setBounds(443, 542, 276, 24);
		add(lnotice2);
		lnotice2.setVisible(false);
		
		lnotice = new JLabel("Select employee");
		lnotice.setOpaque(true);
		lnotice.setBackground(new Color(255, 255, 255));
		lnotice.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice.setForeground(new Color(153, 0, 0));
		lnotice.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice.setBounds(443, 542, 276, 24);
		add(lnotice);
		lnotice.setVisible(false);
		
		
		arr=new ArrayList<String>();
		arr1=new ArrayList<String>();
		arr1.add("-select one-");
		arr.add("-select-");
		MongoCursor<Document> mc =SelectDB.getElist();
		while(mc.hasNext())
		{
			Document doc1 = mc.next();
			Document doc2= (Document) doc1.get("_id");
			String pid = (String) doc2.get("id");
			String name = doc1.getString("fname")+" "+doc1.getString("surname");
			arr.add(pid);
			arr1.add(name);
		}
		cbname = new JComboBox<String>(new DefaultComboBoxModel(arr1.toArray()));
		cbname.setFont(new Font("Arial", Font.PLAIN, 14));
		cbname.setBounds(134, 185, 218, 35);
		cbname.addItemListener(this);
		add(cbname);
		
		rbdate = new JRadioButton("Date");
		rbdate.setFont(new Font("Arial", Font.BOLD, 18));
		rbdate.setBackground(Color.WHITE);
		rbdate.setBounds(50, 240, 72, 40);
		add(rbdate);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("d MMM, yy");
		dateChooser.setBounds(134, 245, 98, 35);
		add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("d MMM, yy");
		dateChooser_1.setBounds(254, 245, 98, 35);
		add(dateChooser_1);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTo.setBounds(233, 245, 19, 40);
		add(lblTo);
		
		rbmonth = new JRadioButton("Month");
		rbmonth.setFont(new Font("Arial", Font.BOLD, 18));
		rbmonth.setBackground(Color.WHITE);
		rbmonth.setBounds(50, 300, 79, 40);
		add(rbmonth);
		
		String [] month ={"-select one-","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		cbmonth = new JComboBox<String>(new DefaultComboBoxModel(month));
		cbmonth.setFont(new Font("Arial", Font.PLAIN, 16));
		cbmonth.setBounds(134, 305, 140, 35);
		cbmonth.addItemListener(this);
		add(cbmonth);
		
		bg1= new ButtonGroup();
		bg1.add(rbname);
		bg1.add(rbrno);
		bg1.add(rbmonth);
		bg1.add(rbdate);
		
		bback = new JButton("Back");
		bback.addActionListener(this);
		bback.setForeground(Color.WHITE);
		bback.setFont(new Font("Arial", Font.BOLD, 22));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(228, 385, 120, 35);
		add(bback);
		
	}
	public int isEmpty()
	{
		if(bg1.getSelection()==null)
		{
			return 1;
		}
		else if(rbdate.isSelected()==true && (dateChooser.getDate()==null || dateChooser_1.getDate()==null))
		{
			return 1;
		}
		else if(rbrno.isSelected()==true && trno.getText().isEmpty())
		{
			return 1;
		}
		else if(rbname.isSelected()==true && cbname.getSelectedIndex()==0)
		{
			return 1;
		}
		else if(rbmonth.isSelected()==true && cbmonth.getSelectedIndex()==0)
		{
			return 1;
		}
		return 0;
	
	}
	private void fillTabel(MongoCursor<Document> cursor)
	{
		Object []header = new String[]{"RNO","EID","Name","Date","Amount"};
		DefaultTableModel model = new DefaultTableModel(header,0)
		{
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
			}
			
		};
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(row % 2 == 0 ? Color.white : new Color(230, 230, 230));
				return this;
			}
		}); 
		table.setRowHeight(30);
		table.setGridColor(Color.BLACK);
		table.setSelectionBackground(new Color(102,102,102));
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(185);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getTableHeader().setBackground(new Color(102,102,102));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		table.getTableHeader().setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		System.out.println("hii   sdfsi");
		while(cursor.hasNext())
		{
			Document d= cursor.next();
			Document d1 = (Document) d.get("_id");
			String rno = d1.getString("rid");
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy");
			model.addRow(new String[]{rno,""+d.get("eid"),d.getString("fname"),""+df.format(d.get("date")),""+d.get("amount")});
		}
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton)arg0.getSource();
		if(b==bsearch)
		{
			if(isEmpty()==1)
			{
				lnotice1.setVisible(true);
			}
			else
			{
				System.out.println("hii");
				flag=1;
				if(rbname.isSelected()==true)
				{
					int index=cbname.getSelectedIndex();
					String id = arr.get(index);
					MongoCursor<Document> cursor = (MongoCursor<Document>) SelectDB.getPayByName(id);
					fillTabel(cursor);
				}
				if(rbrno.isSelected()==true)
				{
					String id = trno.getText();
					MongoCursor<Document> cursor = (MongoCursor<Document>) SelectDB.getPayByRno(id);
					fillTabel(cursor);
				}
				if(rbdate.isSelected()==true)
				{
					Date d1=dateChooser.getDate();
					Date d2=dateChooser_1.getDate();
					MongoCursor<Document> cursor=(MongoCursor<Document>)SelectDB.getPayByDate(d1, d2);
					fillTabel(cursor);
				}
				if(rbmonth.isSelected()==true)
				{
					int i = cbmonth.getSelectedIndex();
					Date d = getDateM(i-1);
					Date d1 = getDateM(i);
					System.out.println(d+"  "+d1);
					MongoCursor<Document> cursor=(MongoCursor<Document>)SelectDB.getPayByMonth(d, d1);
					fillTabel(cursor);
				}
			}
		}
		if(b==bview)
		{
			parent.add(new ReceiptView(leid.getText(),parent,stk,cl),"2.4.1");
			stk.push("2.4.1");
			cl.show(parent, "2.4.1");
		}
		if(b==bdelete)
		{	
			Document doc= SelectDB.getPayData(leid.getText());
			DeleteDB.DeletePay(leid.getText(),doc.getInteger("amount"));
			if(isEmpty()==1)
			{
				lnotice1.setVisible(true);
			}
			else
			{
				
				flag=1;
				if(rbname.isSelected()==true)
				{
					int index=cbname.getSelectedIndex();
					String id = arr.get(index);
					MongoCursor<Document> cursor = (MongoCursor<Document>) SelectDB.getPayByName(id);
					fillTabel(cursor);
				}
				if(rbrno.isSelected()==true)
				{
					String id = trno.getText();
					MongoCursor<Document> cursor = (MongoCursor<Document>) SelectDB.getPayByRno(id);
					fillTabel(cursor);
				}
				if(rbdate.isSelected()==true)
				{
					Date d1=dateChooser.getDate();
					Date d2=dateChooser_1.getDate();
					MongoCursor<Document> cursor=(MongoCursor<Document>)SelectDB.getPayByDate(d1, d2);
					fillTabel(cursor);
				}
				if(rbmonth.isSelected()==true)
				{
					int i = cbmonth.getSelectedIndex();
					Date d = getDateM(i-1);
					Date d1 = getDateM(i);
					System.out.println(d+"  "+d1);
					MongoCursor<Document> cursor=(MongoCursor<Document>)SelectDB.getPayByMonth(d, d1);
					fillTabel(cursor);
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
	
	private Date getDateM(int i) {
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH,i);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
		
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		
		
	}
	
	@Override
	public void focusLost(FocusEvent arg0) {
		
	}
		
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
