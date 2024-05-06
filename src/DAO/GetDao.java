/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.sun.xml.fastinfoset.alphabet.BuiltInRestrictedAlphabets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;

public class GetDao
{
    Connection conn = null;
    //DbConnectionManager connect;
    public void insertCategory(String cat)
    {
        try
        {
            String sql = "insert into category_info values('"+cat+"')";
            conn = DbConnectionManager.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
           
            JOptionPane.showMessageDialog(null, "Category Added Successfully");
            //conn.close();
        }
        catch(PSQLException e)
        {
                JOptionPane.showMessageDialog(null, cat+" already Exists");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void display(javax.swing.table.DefaultTableModel dtm)
    {
          try
        {
            conn = DbConnectionManager.getConnection();
            String sql = "select * from category_info";
            int sno = 0;
            
            Statement st = conn.createStatement();
            //st.executeUpdate(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                String content = rs.getString("category");
//                Object o[] ={++sno,content};
//                dtm.addRow(o);
                    
                Vector row = new Vector();
                row.add(++sno);
                row.add(content);
                dtm.addRow(row);
   
             }
        
         }   
         catch (SQLException ex) 
         {
            Logger.getLogger(GetDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void delete(String cat)
    {
          try
        {
            conn = DbConnectionManager.getConnection();
            String sql = "delete from category_info where category = '"+cat+"'";
           
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Category Deleted Successfully");
         }   
         catch (SQLException ex) 
         {
            Logger.getLogger(GetDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void showAll(JComboBox combo)
    {
          try
        {
            conn = DbConnectionManager.getConnection();
            String sql = "select * from category_info";
            
            
            Statement st = conn.createStatement();
            st.executeQuery(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                combo.addItem(rs.getString("category"));
            }
        
         }   
         catch (SQLException ex) 
         {
            Logger.getLogger(GetDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
  
}
