package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {
       
       String url="jdbc:mysql://localhost:3306/sumit",use="root",pass="root",q;
       Connection con;
       PreparedStatement  ps;
       
       public BookDao() throws SQLException, ClassNotFoundException {
    	   Class.forName("com.mysql.cj.jdbc.Driver");
    	   con=DriverManager.getConnection(url, use, pass);
       }
       
       public boolean saveBook(Book bobj) throws SQLException {
    	   q="insert into book values(?,?,?,?) ";
    	   ps=con.prepareStatement(q);
    	   
    	   ps.setInt(1,bobj.getId());
    	   ps.setString(2, bobj.getName());
    	   ps.setString(3, bobj.getAuthor());
    	   ps.setDouble(4,bobj.getPrice());
    	   
    	   
    	   int r=ps.executeUpdate();
    	   if(r > 0) {
    		   return true;
    	   }else {
    		   return false;
    	   }
       }
       
       public boolean updateBook(Book bobj) throws SQLException {
    	   q="update book set price=? where id=?";
    	   ps=con.prepareStatement(q);
    	   
    	   ps.setDouble(1, bobj.getPrice());
    	   ps.setInt(2, bobj.getId());
    	   
    	   int r = ps.executeUpdate();
    	   if(r > 0) {
    		   return true;
    	   }else {
    		   return false;
    	   }
       }
	
       public boolean deleteBook(int i) throws SQLException {
    	   q="delete from book where id=?";
    	   ps=con.prepareStatement(q);
    	   
    	   ps.setInt(1, i);
    	   int r=ps.executeUpdate();
    	   if(r > 0) {
    		   return true;
    	   }else {
    		   return false;
    	   }
       }
       
       public ResultSet searchBook(int i) throws SQLException {
    	   q="select* from book where id=?";
    	   ps=con.prepareStatement(q);
    	   
    	   ps.setInt(1, i);
    	   ResultSet r =ps.executeQuery();
    	   return r;
       }
	

}
