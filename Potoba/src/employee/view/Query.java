package employee.view;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;
import com.mongodb.client.MongoCursor;

import employee.controller.ConnectionDB;
import employee.controller.DeleteDB;
import employee.controller.SelectDB;
import employee.controller.UpdateDB;


public class Query extends JPanel implements ActionListener {
	private JTable table;
	JScrollPane scrollPane;
	JLabel leid,lnotice;
	JPanel psearch ;
	JButton bview;
	JButton bmodify ;
	JButton bsuspend;
	JButton bdelete;
	SelectDB se;
	MongoCursor<Document> cursor;
	Stack <Document> stack;
	public JPanel parent;
	public Stack<String> stk;
	CardLayout cl;
	private JButton bback;
	private JButton brefresh;
	
	/**
	 * Create the panel.
	 */
	public Query(JPanel parent,Stack<String>stk,CardLayout cl) {
		ConnectionDB.getConnected1();
		
		cursor=SelectDB.getElist();
		
		setBackground(Color.WHITE);
		setLayout(null);
		this.parent=parent;
		this.stk=stk;
		this.cl=cl;
		
		stack = new Stack<Document>();
		psearch = new JPanel();
		psearch.setBackground(new Color(255, 255, 255));
		psearch.setBounds(100, 120, 700, 348);
		add(psearch);
		psearch.setLayout(null);
		
		scrollPane =  new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setBounds(0, 0, 700, 348);
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
		Object []header = new String[]{"EID","Name","Contact","Salary","R-Salary"};
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
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getTableHeader().setBackground(new Color(102,102,102));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		table.getTableHeader().setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		while (cursor.hasNext()) 
		{
	        	Document ob = (Document) cursor.next();
	        	Document ob1 = (Document) ob.get("_id");
	        	String eid = ob1.getString("id");
	        	if(ob.getBoolean("status")==false)
	        	{
	        		stack.push(ob);
	        	}
	        	else
	        		model.addRow(new String[]{eid,ob.get("fname")+" "+ob.get("surname"),""+ob.get("contactno"),""+ob.get("salary"),""+ob.getInteger("salary_remaining")});
		}
		while(!stack.isEmpty())
		{
			Document ob = (Document)stack.pop();
        	Document ob1 = (Document) ob.get("_id");
        	String eid = ob1.getString("id");
        	model.addRow(new String[]{"$"+eid,ob.get("fname")+" "+ob.get("surname"),""+ob.get("contactno"),""+ob.get("salary"),""+ob.getInteger("salary_remaining")});
		}
		
		bview = new JButton("View");
		bview.setForeground(new Color(255, 255, 255));
		bview.setBackground(new Color(102, 102, 102));
		bview.setFont(new Font("Arial", Font.BOLD, 22));
		bview.setBounds(100, 480, 130, 35);
		bview.addActionListener(this);
		add(bview);
		
		bmodify = new JButton("Modify");
		bmodify.setForeground(new Color(255, 255, 255));
		bmodify.setBackground(new Color(102, 102, 102));
		bmodify.setFont(new Font("Arial", Font.BOLD, 22));
		bmodify.setBounds(290, 480, 130, 35);
		bmodify.addActionListener(this);
		add(bmodify);
		
		brefresh = new JButton("Refresh");
		brefresh.setForeground(new Color(255, 255, 255));
		brefresh.setBackground(new Color(102, 102, 102));
		brefresh.setFont(new Font("Arial", Font.BOLD, 22));
		brefresh.setBounds(480, 533, 130, 35);
		brefresh.addActionListener(this);
		add(brefresh);
		
		bsuspend = new JButton("Suspend");
		bsuspend.setForeground(new Color(255, 255, 255));
		bsuspend.setBackground(new Color(153, 0, 0));
		bsuspend.setFont(new Font("Arial", Font.BOLD, 22));
		bsuspend.setBounds(480, 480, 130, 35);
		bsuspend.addActionListener(this);
		add(bsuspend);
		
		bdelete = new JButton("Delete");
		bdelete.setForeground(new Color(255, 255, 255));
		bdelete.setBackground(new Color(153, 0, 0));
		bdelete.setFont(new Font("Arial", Font.BOLD, 22));
		bdelete.setBounds(670, 480, 130, 35);
		bdelete.addActionListener(this);
		add(bdelete);
		
		JLabel lselectedeid = new JLabel("Employee id");
		lselectedeid.setFont(new Font("Arial", Font.BOLD, 22));
		lselectedeid.setHorizontalAlignment(SwingConstants.TRAILING);
		lselectedeid.setBounds(100, 530, 140, 40);
		add(lselectedeid);
		
		leid = new JLabel("");
		leid.setHorizontalAlignment(SwingConstants.CENTER);
		leid.setFont(new Font("Arial", Font.BOLD, 22));
		leid.setBounds(260, 535, 140, 35);
		add(leid);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 0, 0));
		separator_2.setBounds(260, 570, 140, 2);
		add(separator_2);
		
		lnotice = new JLabel("Select employee");
		lnotice.setOpaque(true);
		lnotice.setBackground(new Color(255, 255, 255));
		lnotice.setHorizontalAlignment(SwingConstants.CENTER);
		lnotice.setForeground(new Color(153, 0, 0));
		lnotice.setFont(new Font("Arial", Font.PLAIN, 15));
		lnotice.setBounds(365, 595, 276, 24);
		
		add(lnotice);
		
		bback = new JButton("Back");
		bback.addActionListener(this);
		bback.setForeground(Color.WHITE);
		bback.setFont(new Font("Arial", Font.BOLD, 22));
		bback.setBackground(new Color(102, 102, 102));
		bback.setBounds(670, 533, 130, 35);
		add(bback);
		lnotice.setVisible(false);

	}
	public boolean isEmpty(JLabel e)
	{
		JLabel text= (JLabel)e;
		boolean flag=false;
		if((text.getText().toString()).isEmpty())
		{
			flag=true;
		}
		return flag;
	}
	private void fillTable(MongoCursor<Document> cursor)
	{
		Object []header = new String[]{"EID","Name","Contact","Salary","R-Salary"};
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
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getTableHeader().setBackground(new Color(102,102,102));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		table.getTableHeader().setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		while (cursor.hasNext()) 
		{
	        	Document ob = (Document) cursor.next();
	        	Document ob1 = (Document) ob.get("_id");
	        	String eid = ob1.getString("id");
	        	if(ob.getBoolean("status")==false)
	        	{
	        		stack.push(ob);
	        	}
	        	else
	        		model.addRow(new String[]{eid,ob.get("fname")+" "+ob.get("surname"),""+ob.get("contactno"),""+ob.get("salary"),""+ob.getInteger("salary_remaining")});
		}
		while(!stack.isEmpty())
		{
			Document ob = (Document)stack.pop();
        	Document ob1 = (Document) ob.get("_id");
        	String eid = ob1.getString("id");
        	model.addRow(new String[]{"$"+eid,ob.get("fname")+" "+ob.get("surname"),""+ob.get("contactno"),""+ob.get("salary"),""+ob.getInteger("salary_remaining")});
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton)arg0.getSource();
		if(b==bback)
		{
			stk.pop();
			cl.show(parent,"2");
			parent.remove(this);
		}
		else if(isEmpty(leid))
		{
			lnotice.setVisible(true);
		}
		else
		{
			String id;
			if(leid.getText().contains("$"))
			{	id = leid.getText().replace("$","");
				System.out.println(id);
			}
			else
				id=leid.getText();
			if(b==bview)
			{
				parent.add(new View(id,parent,stk,cl),"2.2.1");
				stk.push("2.2.1");
				cl.show(parent, "2.2.1");
			}
			if(b==bmodify)
			{
				parent.add(new Modify(id,parent,stk,cl),"2.2.2");
				stk.push("2.2.2");
				cl.show(parent, "2.2.2");
			}
			if(b==bsuspend)
			{
				UpdateDB.updateEmpStatus(id, false);
				MongoCursor<Document> cursor = SelectDB.getElist();
				fillTable(cursor);
				
			}
			if(b==bdelete)
			{
				DeleteDB.DeleteEmp(id);
				MongoCursor<Document> cursor = SelectDB.getElist();
				fillTable(cursor);
			}
			if(b==brefresh)
			{
				MongoCursor<Document> cursor = SelectDB.getElist();
				fillTable(cursor);
			}
			
			
		}
	}
}