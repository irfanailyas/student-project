
package student;


import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

   
public class MarksSheet {
 Connection con = MyConnection.getConnection();
    PreparedStatement ps;
   
     public boolean isIdExist(int sid) {
        try {
            ps=con.prepareStatement("select * from score where student_id=?");
            ps.setInt(1,sid);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                return true;    
            } 
        } catch(SQLException ex) {
            Logger.getLogger(MarksSheet.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }
    
     
     //
     public void getScoreValue(JTable table,int sid) {
         String sql="select * from course where student_id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,sid);
            ResultSet rs=ps.executeQuery();
            DefaultTableModel  model =(DefaultTableModel) table.getModel();
            Object[] row;
            while(rs.next()) {
                row=new Object[10];
                row[0]=rs.getInt(1);
                row[1]=rs.getInt(2);
                row[2]=rs.getInt(3);
                row[3]=rs.getString(4);
                row[4]=rs.getDouble(5);
                row[5]=rs.getString(6);
                row[6]=rs.getDouble(7);
                row[7]=rs.getString(8);
                row[8]=rs.getDouble(9);
                row[9]=rs.getDouble(10);
                 
                model.addRow(row);    
                 
                
            }
        } catch (SQLException ex) {
          
        Logger.getLogger(MarksSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public double getCGPA(int sid) {
         double cgpa=0.0;
         Statement st;
     try {
         
         st=con.createStatement();
         ResultSet rs=st.executeQuery("select avg(average) from score where student_id="+sid+"");
         if(rs.next()) {
             cgpa=rs.getDouble(1);
             
         }
     } catch (SQLException ex) {
         Logger.getLogger(MarksSheet.class.getName()).log(Level.SEVERE, null, ex);
     }
        return cgpa;
         
     }
     
}

