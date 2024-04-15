/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author david
 */
public class Credentials {
    private String  db_ip;
    private String  dp_port;
    private String  db_user;
    private String  db_pssword;  

    public Credentials() {
        this.db_ip = "localhost";
        this.dp_port = "3306";
        this.db_user = "root";
        this.db_pssword = "";  
    }
         
    public String getDb_ip() {
        return db_ip;
    }

    public String getDp_port() {
        return dp_port;
    }

    public String getDb_user() {
        return db_user;
    }

    public String getDb_pssword() {
        return db_pssword;
    }

    
      
}
