/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import model.Cliente;
import java.util.List;
import java.sql.Connection;
import javax.swing.JOptionPane;
import modelDAO.ClienteDAO;

/**
 *
 * @author davidpuchana
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        ClienteDAO uDao = new ClienteDAO();
        Cliente  user = uDao.login("das@email.com", "123");
         JOptionPane.showMessageDialog(null, user.getNombres());
        
        
    
    
        
        
        
        
        
        
        
        
        
    }
    
}
