/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;

/**
 *
 * @author USER
 */
public class SpendingDao 
{
    Connection conn = null;
    int total = 0;
    //DbConnectionManager connect;
    
    public void insert(String amount, java.util.Date dt, String cat)
    {
         try
        {
            int amt = Integer.parseInt(amount);
            
            java.sql.Date date = new java.sql.Date(dt.getTime());
            System.out.println(date);
            String sql = "insert into spendingDetail (scategory, dateof, amount) values('"+cat+"','"+date+"',"+amt+")";
            conn = DbConnectionManager.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
           
            JOptionPane.showMessageDialog(null, "Spending inserted Successfully");
            //conn.close();
        }
//        catch(PSQLException e)
//        {
//                JOptionPane.showMessageDialog(null, cat+" already Exists");
//        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getAll(javax.swing.table.DefaultTableModel dtm)
    {
          try
        {

            java.time.LocalDate ld = java.time.LocalDate.now();
            java.time.LocalDate bld = ld.minusDays(20);
            System.out.println("Current Date : " + ld);
            System.out.println("20 days late Date : " + bld);

            //String sql = "select * from spendingDetail where dateof<='"+ld+"' and dateof='"+bld+"'";
            String sql = "SELECT * FROM spendingDetail WHERE dateof BETWEEN '"+bld+"' AND '"+ld+"'";
            
            conn = DbConnectionManager.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                total += rs.getInt("amount");
                Object o[] ={rs.getInt("sid"),rs.getDate("dateof"),rs.getString("scategory"),rs.getInt("amount")};
                dtm.addRow(o);
//                Vector row = new Vector();
//                row.add(rs.getInt("sid"));
//                row.add(rs.getDate("dateof"));
//                row.add(rs.getString("scategory"));
//                row.add(rs.getString("scategory"));
//                dtm.addRow(row);

            }
           
//          
        }
//        catch(PSQLException e)
//        {
//                JOptionPane.showMessageDialog(null, cat+" already Exists");
//        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void deleteData(int id)
    {
          try
        {
//String sql = "select * from spendingDetail where dateof<='"+ld+"' and dateof='"+bld+"'";
            String sql = "delete from spendingDetail where sid = "+id+"";
            
            conn = DbConnectionManager.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Deleted data successfully");

//          
        }

        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getDate(java.sql.Date dt1,java.sql.Date dt2,javax.swing.table.DefaultTableModel dtm )
    {
          try
        {

            String sql = "SELECT * FROM spendingDetail WHERE dateof BETWEEN '"+dt1+"' AND '"+dt2+"' order by dateof asc";
            
            conn = DbConnectionManager.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(sql);
           
              while(rs.next())
            {
                total += rs.getInt("amount");
                Object o[] ={rs.getDate("dateof"),rs.getString("scategory"),rs.getInt("amount")};
                dtm.addRow(o);
//               
            }
            

//          
        }

        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getCat(java.sql.Date dt1,java.sql.Date dt2,javax.swing.table.DefaultTableModel dtm , String c)
    {
          try
        {

            String sql = "SELECT * FROM spendingDetail WHERE dateof BETWEEN '"+dt1+"' AND '"+dt2+"'and scategory='"+c+"' order by dateof asc";
            
            conn = DbConnectionManager.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(sql);
           
              while(rs.next())
            {
                total += rs.getInt("amount");
                Object o[] ={rs.getDate("dateof"),rs.getString("scategory"),rs.getInt("amount")};
                dtm.addRow(o);
//               
            }

        }

        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public int getTotal()
    {
        return total;
    }
}
