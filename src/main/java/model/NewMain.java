/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import model.Usuario;
import java.util.List;
import java.sql.Connection;
import javax.swing.JOptionPane;
import modelDAO.UsuarioDAO;

/**
 *
 * @author davidpuchana
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        UsuarioDAO uDao = new UsuarioDAO();
        Usuario  user = uDao.login("felipe@Email", "asv", "Admin");
         JOptionPane.showMessageDialog(null, user.getDireccion());
        
        
    
    
        
        
        
        
        
        
        
        
        
    }
    
}
