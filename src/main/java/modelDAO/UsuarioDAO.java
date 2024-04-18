/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import  connection.ConnectionDB;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author davidpuchana
 */
public class UsuarioDAO {       
    PreparedStatement pst;
    ResultSet result;
    ConnectionDB connDB = new ConnectionDB(); 
    
    public Usuario login(String user, String pass, String role){
        Usuario u = new Usuario();
        String sql = "SELECT * FROM Usuario WHERE email=? AND password=? AND rol=? AND state=1";
        try{            
            Connection conn = connDB.getConnectionDB();            
            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);      
            pst.setString(3, role);      
            result = pst.executeQuery();
            
            while(result.next()){
                u.setIdUsuario(result.getInt("idUsuario"));
                u.setTipoDocumento(result.getString("tipoDocumento"));
                u.setDocumento(result.getString("documento"));
                u.setNombres(result.getString("nombres"));
                u.setApellidos(result.getString("apellidos"));
                u.setDireccion(result.getString("direccion"));                             
                u.setTelefono(result.getString("telefono"));
                u.setEmail(result.getString("email"));
                u.setWhatsapp(result.getString("whatsapp"));
                u.setRol(result.getString("rol"));
                u.setPass(result.getString("password"));                                
            }                        
            
            conn.close();
        }
        catch(Exception e)
        {
            return u; 
        }    
        
        return u;
    }
    
    public boolean addUsuario(Usuario user){
       
        String sql = "INSERT INTO Usuario (tipoDocumento,documento,nombres,apellidos,"+
                     "direccion,telefono,email,whatsapp,rol,password,state)"+
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
          
        try{
            Connection conn = connDB.getConnectionDB();            

            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getTipoDocumento());
            pst.setString(2, user.getDocumento());
            pst.setString(3, user.getNombres());
            pst.setString(4, user.getApellidos());
            pst.setString(5, user.getDireccion());
            pst.setString(6, user.getTelefono());
            pst.setString(7, user.getEmail());
            pst.setString(8, user.getWhatsapp());
            pst.setString(9, user.getRol());
            pst.setString(10, user.getPass());
            pst.setString(11, "1");
            pst.executeUpdate();
            

        }catch(Exception e){                     
            return false;
        }           

        return true;
    }   

    public String listar() throws JsonProcessingException{ 
        Connection conn = connDB.getConnectionDB(); 
        String sql = "SELECT * FROM usuario WHERE state=1";        
        List<Usuario> list = new ArrayList<>();
        
        try{
            
            pst = conn.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(result.getInt(1));
                u.setTipoDocumento(result.getString(2));
                u.setDocumento(result.getString(3));
                u.setNombres(result.getString(4));
                u.setApellidos(result.getString(5));
                u.setDireccion(result.getString(6));
                u.setTelefono(result.getString(7));
                u.setEmail(result.getString(8));
                u.setWhatsapp(result.getString(9));
                u.setRol(result.getString(10));
                u.setPass(result.getString(11));                                           
                list.add(u);
            }
            
        }catch(Exception e){
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(list);
            return json;            
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        
        return json;        
    }   

    public boolean updateUser(Usuario user, String idDocument){
        Connection conn = connDB.getConnectionDB();              
        String sql = "UPDATE Usuario SET tipoDocumento=?, documento=?, nombres=?, apellidos=?,"+
                     " direccion=?, telefono=?, email=?, whatsapp=?,rol=?,password=? WHERE documento=?";
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getTipoDocumento());
            pst.setString(2, user.getDocumento());
            pst.setString(3, user.getNombres());
            pst.setString(4, user.getApellidos());
            pst.setString(5, user.getDireccion());
            pst.setString(6, user.getTelefono());
            pst.setString(7,user.getEmail());
            pst.setString(8, user.getWhatsapp());
            pst.setString(9, user.getRol());
            pst.setString(10, user.getPass());
            pst.setString(11, idDocument);
            pst.executeUpdate();
                      
        }catch(Exception e){
            return false;            
        }
        return true;

    }   
    
    public boolean delete(String documento){
        Connection conn = connDB.getConnectionDB();        
        String sql =  "UPDATE Usuario SET state='0' WHERE documento=?";
        
        try{            
            pst = conn.prepareStatement(sql);
            pst.setString(1, documento);
            pst.executeUpdate();            
        }catch(Exception e){
            return false;            
        }
        return true;
    }   
    
    public String configuser(String cc) throws JsonProcessingException{       
        Usuario u = new Usuario();
        String sql = "SELECT * FROM Usuario WHERE documento=?";
        try{            
            Connection conn = connDB.getConnectionDB();            
            pst = conn.prepareStatement(sql);
            pst.setString(1, cc);
            result = pst.executeQuery();
             while(result.next()){
                u.setTipoDocumento(result.getString("tipoDocumento"));
                u.setDocumento(result.getString("documento"));
                u.setNombres(result.getString("nombres"));
                u.setApellidos(result.getString("apellidos"));
                u.setDireccion(result.getString("direccion"));                             
                u.setTelefono(result.getString("telefono"));
                u.setEmail(result.getString("email"));
                u.setWhatsapp(result.getString("whatsapp"));
                u.setRol(result.getString("rol"));
                u.setPass(result.getString("password"));                                
            }                        
            
            conn.close();
        }
        catch(Exception e)
        {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(u);
            return json;                
        }            
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(u);        
        return json;        
    }    
}