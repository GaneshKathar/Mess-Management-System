package mess.pages;

import mess.interface_db.DeleteDB;
import mess.interface_db.ExtractDB;
import mess.interface_db.UpdateDB;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import mess.object.Payment;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Stack;

@SuppressWarnings("serial")
public class SearchP extends JPanel implements ActionListener,ItemListener {
	private JTextField tpid,tmid;
	private JRadioButton rbpid,rbmid,rbdate;
	private JDateChooser dChooser;
	private JLabel lpid2,lerror1,lerror2,lerror3;
	private JButton bview,bsearch,bdel;
	private JScrollPane panel;
	private JTable table;
	private int flag1,fview,fdel;
	private ButtonGroup bgroup;
	private Payment pobj;
	private JPanel parent;
	private CardLayout card;
	private JButton bback;
	private Stack MessStack;
	
	public SearchP(JPanel par,CardLayout cl,Stack stk) {

		MessStack=stk;
		parent=par;
		card=cl;
		
		flag1=fview=fdel=0;
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		table=new JTable();
		table.getTableHeader().setBackground(new Color(90, 90, 90));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 16));
		table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				
				if(e.getClickCount()==2){
					fdel=fview=0;
					lpid2.setText("");
					JTable tab=(JTable)e.getSource();
					if(table.getSelectedColumn()==0){
						fdel=fview=1;
						lpid2.setText(""+tab.getModel().getValueAt(table.getSelectedRow(),0));
					}
				}
					
			}
		});
		
		
		panel = new JScrollPane(table);
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setBounds(360, 120, 500, 300);
		add(panel);
		
		rbpid = new JRadioButton("Payment ID");
		rbpid.setFont(new Font("Century Gothic", Font.BOLD, 18));
		rbpid.addItemListener(this);
		rbpid.setBackground(new Color(255, 255, 255));
		rbpid.setBounds(50, 150, 130, 40);
		add(rbpid);
		
		rbmid = new JRadioButton("Member ID");
		rbmid.addItemListener(this);
		rbmid.setFont(new Font("Century Gothic", Font.BOLD, 18));
		rbmid.setBackground(new Color(255, 255, 255));
		rbmid.setBounds(50, 200, 130, 40);
		add(rbmid);
		
		rbdate = new JRadioButton("Date");
		rbdate.addItemListener(this);
		rbdate.setFont(new Font("Century Gothic", Font.BOLD, 18));
		rbdate.setBackground(new Color(255, 255, 255));
		rbdate.setBounds(50, 250, 130, 40);
		add(rbdate);

		bgroup=new ButtonGroup();
		bgroup.add(rbmid);
		bgroup.add(rbpid);
		bgroup.add(rbdate);
		
		tpid = new JTextField();
		tpid.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tpid.setColumns(10);
		tpid.setBorder(null);
		tpid.setBounds(200, 155, 70, 30);
		add(tpid);
		
		tmid = new JTextField();
		tmid.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tmid.setColumns(10);
		tmid.setBorder(null);
		tmid.setBounds(200, 205, 70, 30);
		add(tmid);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(200, 185, 70, 10);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(200, 235, 70, 10);
		add(separator_1);
		
		JLabel label = new JLabel("Payment ID");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Century Gothic", Font.BOLD, 18));
		label.setBackground(Color.WHITE);
		label.setBounds(360, 440, 120, 40);
		add(label);
		
		JLabel lblSearch = new JLabel("SEARCH");
		lblSearch.setOpaque(true);
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblSearch.setBackground(new Color(102, 102, 102));
		lblSearch.setBounds(20, 40, 320, 50);
		add(lblSearch);
		
		bdel = new JButton("Delete");
		bdel.addActionListener(this);
		bdel.setForeground(new Color(255, 255, 255));
		bdel.setBackground(new Color(153, 0, 0));
		bdel.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bdel.setBounds(740, 440, 120, 35);
		add(bdel);
		
		bview = new JButton("View");
		bview.addActionListener(this);
		bview.setBackground(new Color(102, 102, 102));
		bview.setForeground(new Color(255, 255, 255));
		bview.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bview.setBounds(602, 440, 120, 35);
		add(bview);
		
		dChooser = new JDateChooser();
		dChooser.getCalendarButton().setFont(new Font("Century Gothic", Font.PLAIN, 12));
		dChooser.getCalendarButton().addActionListener(this);
		dChooser.setBounds(200, 250, 100, 35);
		add(dChooser);
		
		lpid2 = new JLabel("");
		lpid2.setOpaque(true);
		lpid2.setHorizontalAlignment(SwingConstants.LEFT);
		lpid2.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		lpid2.setBackground(Color.WHITE);
		lpid2.setBounds(500, 445, 70, 35);
		add(lpid2);
		
		bsearch = new JButton("Search");
		bsearch.addActionListener(this);
		bsearch.setBackground(new Color(102, 102, 102));
		bsearch.setForeground(new Color(255, 255, 255));
		bsearch.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bsearch.setBounds(50, 380, 120, 35);
		add(bsearch);
		
		lerror1 = new JLabel("Select a parameter");
		lerror1.setForeground(new Color(153, 0, 0));
		lerror1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lerror1.setBounds(90, 340, 180, 35);
		lerror1.setVisible(false);
		add(lerror1);
		
		lerror2 = new JLabel("Do not leave the field empty");
		lerror2.setForeground(new Color(153, 0, 0));
		lerror2.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lerror2.setBounds(60, 340, 240, 35);
		lerror2.setVisible(false);
		add(lerror2);
		
		lerror3 = new JLabel("select Member ID first");
		lerror3.setForeground(new Color(153, 0, 0));
		lerror3.setOpaque(true);
		lerror3.setHorizontalAlignment(SwingConstants.CENTER);
		lerror3.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		lerror3.setBackground(Color.WHITE);
		lerror3.setBounds(80, 441, 200, 35);
		lerror3.setVisible(false);
		add(lerror3);
		
		bback = new JButton("Back");
		bback.addActionListener(this);
		bback.setForeground(Color.WHITE);
		bback.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(200, 380, 120, 35);
		add(bback);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn=(JButton)e.getSource();
		lerror1.setVisible(false);
		lerror2.setVisible(false);
		lerror3.setVisible(false);
		if(btn==bsearch)
		{
			if(bgroup.getSelection()==null)
			{
				lerror1.setVisible(true);
				return;
			}
			if(isEmpty()==true)
			{
				lerror2.setVisible(true);
				return;
			}
			
			if(rbmid.isSelected()==true)
			{
				System.out.println("MID");
				fillTable(ExtractDB.getMIDPayment(tmid.getText().trim()));
			}
			else if(rbpid.isSelected()==true)
			{
				fillTable(ExtractDB.getPIDPayment(tpid.getText().trim()));
			}
			else if(rbdate.isSelected()==true)
			{
				fillTable(ExtractDB.getDatePayment(dChooser.getDate()));
			}
		}
		else if(btn==bback)
		{
			MessStack.pop();
			card.show(parent,"1");
			parent.remove(this);
		}
		else if(btn==bview)
		{
			if(fview==0){
				lerror3.setVisible(true);
				return;
			}
			lpid2.getText().trim();
			
		//	parent.add(new MProfessional(temp,parent,card),"1.4.2");
		//	card.show(parent, "1.4.2");
		}
		else if(btn==bdel)
		{
			if(fdel==0){
				lerror3.setVisible(true);
				return;
			}
			loadObject(lpid2.getText().trim());
			UpdateDB.uUnPay(pobj);
			DeleteDB.dPayment(lpid2.getText().trim());
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JRadioButton rb=(JRadioButton)e.getSource();
		flag1=1;
		unset();
	}

	void unset()
	{
		tmid.setText("");
		tpid.setText("");
		dChooser.setDate(new Date());
	}
	boolean isEmpty()
	{
		int flag=0;
		if(flag1==0)
			flag=1;
		else if(rbmid.isSelected()==true&&tmid.getText().trim().isEmpty()==true)
			flag=1;
		else if(rbpid.isSelected()==true&&tpid.getText().trim().isEmpty()==true)
			flag=1;
		if(flag==1)
			return true;
		return false;
	}
	
	public void fillTable(MongoCursor<Document> cursor)
	{
		int i=2;
		Object []header={"Payment ID","Member ID","Name","Amount","Date"};
		DefaultTableModel dtm=new DefaultTableModel(header,0)
		{
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		};
		table.setModel(dtm);
		TableColumnModel columnmodel=table.getColumnModel();
		columnmodel.getColumn(0).setPreferredWidth(100);
		columnmodel.getColumn(1).setPreferredWidth(100);
		columnmodel.getColumn(2).setPreferredWidth(100);
		columnmodel.getColumn(2).setPreferredWidth(100);
		columnmodel.getColumn(3).setPreferredWidth(100);
		
		table.setRowHeight(30);
		table.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		
		try{
			i=2;
			while(cursor.hasNext())
			{
				Document doc=cursor.next();
				Document doc2=(Document)doc.get("_id");
				String dt=(new SimpleDateFormat("dd MMM YY")).format(doc.getDate("date-of-payment"));
				dtm.addRow(new Object[]{doc2.getString("pid"),doc.getString("mid"),ExtractDB.getName(doc.getString("mid")),doc.getInteger("amount"),dt});
				i++;
			}	
			cursor.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Code TO Colour Alternate Rows As Gray

				table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
					{
						final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
						c.setBackground(row % 2 == 1 ? new Color(230,230,230) : Color.WHITE);
						c.setForeground(Color.BLACK);
						return this;
					}
				});
		table.setPreferredScrollableViewportSize(new Dimension(500,i*50));
		table.setFillsViewportHeight(true);
		panel.validate();
	}
	void loadObject(String id)
	{
		pobj=new Payment();
		try{
			MongoCursor<Document> cursor=ExtractDB.getPIDPayment(id);
			Document doc=cursor.next();
			pobj.pid=((Document)doc.get("_id")).getString("pid");
			pobj.mid=doc.getString("mid");
			pobj.amt=doc.getInteger("amount");
			pobj.dop=doc.getDate("date-of-payment");	
			cursor.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
