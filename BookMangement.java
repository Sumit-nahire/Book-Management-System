import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Book;
import Model.BookDao;

public class BookMangement extends JFrame implements ActionListener{
	
	Container cp;
	JLabel lblBid,lblBname,lblAuthor,lblPrice;
	JTextField txtBid,txtBname,txtBauthor,txtBprice;
	JButton btnInsert,btnUpdate,btnDelete,btnSearch,btnClear;
	BookDao dao;
	
	public  BookMangement(String t) throws ClassNotFoundException, SQLException {
		super(t);
		cp=getContentPane();
		cp.setLayout(new GridLayout(7,2,5,5));
		dao=new BookDao(); 
		
		lblBid = new JLabel("Book Id:-");
		txtBid = new JTextField();
		cp.add(lblBid);
		cp.add(txtBid);
		
		lblBname = new JLabel("Name:-");
		txtBname = new JTextField();
		cp.add(lblBname);
		cp.add(txtBname);
		
		
		lblAuthor = new JLabel("Author:-");
		txtBauthor = new JTextField();
		cp.add(lblAuthor);
		cp.add(txtBauthor);
		
		lblPrice = new JLabel("Price:-");
		txtBprice = new JTextField();
		cp.add(lblPrice);
		cp.add(txtBprice);
		
		
		btnInsert = new JButton("Insert");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnSearch = new JButton("Search");
		btnClear  = new JButton("Clear");
		
		cp.add(btnInsert);
		cp.add(btnUpdate);
		cp.add(btnDelete);
		cp.add(btnSearch);
		cp.add(btnClear);
		
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSearch.addActionListener(this);
		btnClear.addActionListener(this);
		
		
		setSize(400,400);
		setVisible(true);
		
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		new BookMangement("Book Management");

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			try {
				if(e.getSource() == btnInsert) {
					int i=Integer.parseInt(txtBid.getText());
					String nm =txtBname.getText();
					String au = txtBauthor.getText();
					double p =Double.parseDouble(txtBprice.getText());
					
					Book obj = new Book(i, nm, au, p);
				boolean r = dao.saveBook(obj);
				if(r)
					JOptionPane.showMessageDialog(this, "Record inserted");
				}
				else
					if(e.getSource()==btnUpdate) {
						int i=Integer.parseInt(txtBid.getText());
						double p =Double.parseDouble(txtBprice.getText());
						
						Book obj = new Book(i, null, null, p);
						boolean r = dao.updateBook(obj);
						if(r)
							JOptionPane.showMessageDialog(this, "Record updated");
						else
							JOptionPane.showMessageDialog(this,"record not found");
					}
					else
						if(e.getSource()==btnDelete) {
							int i=Integer.parseInt(txtBid.getText());
							boolean r =dao.deleteBook(i);
							if(r)
								JOptionPane.showMessageDialog(this, "Record deleted");
							else
								JOptionPane.showMessageDialog(this, "Record not found");
						}
						else
							if(e.getSource()==btnSearch) {
								int i=Integer.parseInt(txtBid.getText());
								ResultSet rs=dao.searchBook(i);
								if(rs.next()) {
									JOptionPane.showMessageDialog(this, rs.getInt(1)+" " 
											+rs.getString(2)+" "
											+rs.getString(3)+" "
											+rs.getDouble(4));
								}else {
									JOptionPane.showMessageDialog(this, "Record not found");
								}
							}
							else
								if(e.getSource()==btnClear) {
									txtBid.setText("");
									txtBname.setText("");
									txtBauthor.setText("");
									txtBprice.setText("");
								}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}


