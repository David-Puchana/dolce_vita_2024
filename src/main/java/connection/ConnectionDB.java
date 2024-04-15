/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import connection.Credentials;

public class ConnectionDB{
    Credentials credential = new Credentials();
    Connection conn;
    String host     =  credential.getDb_ip();
    String port     =  credential.getDp_port();
    String username =  credential.getDb_user();
    String password =  credential.getDb_pssword();    
    String dbURL = "jdbc:mysql://"+host+":"+port+"/dolce_vita" ;                  
    public Connection getConnectionDB() {            
            try {                                           
                Class.forName("com.mysql.jdbc.Driver"); 
                conn = DriverManager.getConnection(dbURL, username, password);                
//                if( conn != null ) 
//                    JOptionPane.showMessageDialog(null, "Conectado");
//                else
//                    JOptionPane.showMessageDialog(null, "No Conectado");
            } 
            catch(Exception ex ) {
                ex.printStackTrace();            
            }
        
        return conn;
    }
       
}
