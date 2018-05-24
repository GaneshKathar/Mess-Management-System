package mess.pages;

import mess.interface_db.DeleteDB;
import mess.interface_db.ExtractDB;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Font;
import java.awt.event.*;
import java.util.Stack;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public class SearchM extends JPanel implements ActionListener,ItemListener{
	private JTable table;
	private JScrollPane panel;
	private JTextField tmid;
	private JTextField tname;	
	private JLabel lmid1,lmid2,lerror1,lerror2,lerror3;
	private JRadioButton rbmid,rbname,rbunpaid,rbpaid;
	private JButton bview,bsearch,bdelete,bsuspend,bmodify;
	private ButtonGroup bgroup;
	private int flag1,fmod,fdel,fview,fsus;
	private JPanel parent;
	private CardLayout card;
	private JButton bback;
	private Stack MessStack;
	
	public SearchM(JPanel par,CardLayout cl,Stack stk) {

		MessStack=stk;
		parent=par;
		card=cl;
		
		flag1=fmod=fdel=fsus=fview=0;
		
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
					fmod=fdel=fsus=fview=0;
					lmid2.setText("");
					JTable tab=(JTable)e.getSource();
					if(table.getSelectedColumn()==0){
						fmod=fdel=fsus=fview=1;
						lmid2.setText(""+tab.getModel().getValueAt(table.getSelectedRow(),0));
					}
				}
					
			}
		});
		
		panel = new JScrollPane(table);
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setBounds(360, 120, 500, 300);
		add(panel);
		
		JLabel lblSearch = new JLabel("SEARCH");
		lblSearch.setOpaque(true);
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblSearch.setBackground(new Color(102, 102, 102));
		lblSearch.setBounds(20, 40, 320, 50);
		add(lblSearch);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(200, 185, 70, 10);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(200, 235, 150, 10);
		add(separator_1);
		
		bgroup=new ButtonGroup();
		
		rbmid = new JRadioButton("Member ID");
		rbmid.addItemListener(this);
		rbmid.setHorizontalAlignment(SwingConstants.LEFT);
		rbmid.setFont(new Font("Century Gothic", Font.BOLD, 18));
		rbmid.setBackground(Color.WHITE);
		rbmid.setBounds(50, 150, 130, 40);
		bgroup.add(rbmid);
		add(rbmid);
		
		rbname = new JRadioButton("First Name");
		rbname.addItemListener(this);
		rbname.setHorizontalAlignment(SwingConstants.LEFT);
		rbname.setFont(new Font("Century Gothic", Font.BOLD, 18));
		rbname.setBackground(Color.WHITE);
		rbname.setBounds(50, 200, 130, 40);
		bgroup.add(rbname);
		add(rbname);
		
		rbunpaid = new JRadioButton("Unpaid");
		rbunpaid.addItemListener(this);
		rbunpaid.setHorizontalAlignment(SwingConstants.LEFT);
		rbunpaid.setFont(new Font("Century Gothic", Font.BOLD, 18));
		rbunpaid.setBackground(Color.WHITE);
		rbunpaid.setBounds(50, 250, 130, 40);
		bgroup.add(rbunpaid);
		add(rbunpaid);
		
		rbpaid = new JRadioButton("Paid");
		rbpaid.addItemListener(this);
		rbpaid.setHorizontalAlignment(SwingConstants.LEFT);
		rbpaid.setFont(new Font("Century Gothic", Font.BOLD, 18));
		rbpaid.setBackground(Color.WHITE);
		rbpaid.setBounds(50, 300, 130, 40);
		bgroup.add(rbpaid);
		add(rbpaid);
		
		tmid = new JTextField();
		tmid.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tmid.setColumns(10);
		tmid.setBorder(null);
		tmid.setBounds(200, 155, 70, 30);
		add(tmid);
		
		tname = new JTextField();
		tname.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tname.setColumns(10);
		tname.setBorder(null);
		tname.setBounds(200, 205, 150, 30);
		add(tname);
		
		lmid1 = new JLabel("Member ID");
		lmid1.setOpaque(true);
		lmid1.setHorizontalAlignment(SwingConstants.TRAILING);
		lmid1.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lmid1.setBackground(Color.WHITE);
		lmid1.setBounds(360, 490, 120, 40);
		add(lmid1);
		
		 lmid2 = new JLabel("");
		lmid2.setOpaque(true);
		lmid2.setHorizontalAlignment(SwingConstants.LEFT);
		lmid2.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		lmid2.setBackground(Color.WHITE);
		lmid2.setBounds(502, 495, 70, 35);
		add(lmid2);
		
		bview = new JButton("View");
		bview.addActionListener(this);
		bview.setForeground(new Color(255, 255, 255));
		bview.setBackground(new Color(102, 102, 102));
		bview.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bview.setBounds(340, 440, 120, 35);
		add(bview);
		
		bmodify = new JButton("Modify");
		bmodify.addActionListener(this);
		bmodify.setForeground(new Color(255, 255, 255));
		bmodify.setBackground(new Color(102, 102, 102));
		bmodify.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bmodify.setBounds(480, 440, 120, 35);
		add(bmodify);
		
		bsuspend = new JButton("Suspend");
		bsuspend.addActionListener(this); 
		bsuspend.setForeground(new Color(255, 255, 255));
		bsuspend.setBackground(new Color(153, 0, 0));
		bsuspend.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bsuspend.setBounds(620, 440, 120, 35);
		add(bsuspend);
		
		bdelete = new JButton("Delete");
		bdelete.addActionListener(this); 
		bdelete.setForeground(new Color(255, 255, 255));
		bdelete.setBackground(new Color(153, 0, 0));
		bdelete.setFont(new Font("Century Gothic", Font.BOLD, 18));
		bdelete.setBounds(760, 440, 120, 35);
		add(bdelete);
		
		bsearch = new JButton("Search");
		bsearch.addActionListener(this); 
		bsearch.setForeground(new Color(255, 255, 255));
		bsearch.setBackground(new Color(102, 102, 102));
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
		
		unset();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
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
				fillTable(ExtractDB.getIDMembers(tmid.getText().trim()));
			}
			else if(rbname.isSelected()==true)
			{
				fillTable(ExtractDB.getNameMembers(tname.getText().trim()));
			}
			else if(rbunpaid.isSelected()==true)
			{
				fillTable(ExtractDB.getUnpaidMembers());
			}
			else if(rbpaid.isSelected()==true)
			{
				fillTable(ExtractDB.getPaidMembers());
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
			lmid2.getText().trim();
		//	parent.add(new SearchM(parent,card),"1.4");
		//	card.show(parent, "1.4");
		}
		else if(btn==bmodify)
		{
			if(fmod==0){
				lerror3.setVisible(true);
				return;
			}
			String temp=lmid2.getText().trim();
			if(temp.contains("S")||temp.contains("s"))
				parent.add(new MStudent(temp,parent,card,MessStack),"1.4.2");
			else if(temp.contains("P")||temp.contains("p"))
				parent.add(new MProfessional(temp,parent,card,MessStack),"1.4.2");
			MessStack.push("1.4.2");
			card.show(parent, "1.4.2");
		}
		else if(btn==bdelete)
		{
			if(fdel==0){
				lerror3.setVisible(true);
				return;
			}
			DeleteDB.dMemberSlips(lmid2.getText().trim());
			DeleteDB.dMember(lmid2.getText().trim());
		}
		else if(btn==bsuspend)
		{
			if(fsus==0){
				lerror3.setVisible(true);
				return;
			}
			lmid2.getText().trim();
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
		tname.setText("");
	}
	boolean isEmpty()
	{
		int flag=0;
		if(flag1==0)
			flag=1;
		else if(rbmid.isSelected()==true&&tmid.getText().trim().isEmpty()==true)
			flag=1;
		else if(rbname.isSelected()==true&&tname.getText().trim().isEmpty()==true)
			flag=1;
		if(flag==1)
			return true;
		return false;
	}
	@SuppressWarnings("serial")
	public void fillTable(MongoCursor<Document> cursor)
	{
		int i=2;
		Object []header={"Member ID","Name","Contact","Amount"};
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
		columnmodel.getColumn(1).setPreferredWidth(200);
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
				dtm.addRow(new Object[]{doc2.getString("mid"),doc.getString("first-name")+" "+doc.getString("last-name"),doc.getString("contact"),doc.getInteger("remaining-amt")});
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
}
