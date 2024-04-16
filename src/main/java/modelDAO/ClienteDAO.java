/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;
import  connection.ConnectionDB;

import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
    PreparedStatement pst;
    ResultSet result;
    ConnectionDB connDB = new ConnectionDB(); 
    
    public Cliente login(String client, String pass){
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM Cliente WHERE email=? AND passwored=? AND state=1";
        try{            
            Connection conn = connDB.getConnectionDB();            
            pst = conn.prepareStatement(sql);
            pst.setString(1, client);
            pst.setString(2, pass);                  
            result = pst.executeQuery();
            
            while(result.next()){
                cliente.setIdCliente(result.getInt("idCliente"));                
                cliente.setDocumento(result.getString("documento"));
                cliente.setNombres(result.getString("nombres"));
                cliente.setApellidos(result.getString("apellidos"));
                cliente.setDireccion(result.getString("direccion"));                             
                cliente.setTelefono(result.getString("telefono"));
                cliente.setEmail(result.getString("email"));
                cliente.setWhatsapp(result.getString("whatsapp"));               
                cliente.setPass(result.getString("passwored"));                                
            }                        
            
            conn.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e); 
        }    
        
        return cliente;
    }
    
    public boolean addCliente(Cliente cliente){
       
        String sql = "INSERT INTO Cliente (documento,nombres,apellidos,"+
                     "direccion,telefono,email,whatsapp,passwored,state)"+
                     "VALUES (?,?,?,?,?,?,?,?,?)";
        boolean respuesta = false;    
        try{
            Connection conn = connDB.getConnectionDB();            

            pst = conn.prepareStatement(sql);
            pst.setString(1, cliente.getDocumento());
            pst.setString(2, cliente.getNombres());
            pst.setString(3, cliente.getApellidos());
            pst.setString(4, cliente.getDireccion());
            pst.setString(5, cliente.getTelefono());
            pst.setString(6, cliente.getEmail());
            pst.setString(7, cliente.getWhatsapp());
            pst.setString(8, cliente.getPass());
            pst.setString(9, "1");
            pst.executeUpdate();
            respuesta = true;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);            
            return false;
        }           

        return respuesta;
    }   

    public List listar(){ 
        Connection conn = connDB.getConnectionDB(); 
        String sql = "SELECT * FROM Cliente WHERE state=1";        
        List<Cliente> list = new ArrayList<>();
        
        try{
            
            pst = conn.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(result.getInt(1));                 
                cliente.setNombres(result.getString(2));
                cliente.setApellidos(result.getString(3));
                cliente.setDireccion(result.getString(4));
                cliente.setTelefono(result.getString(5));
                cliente.setEmail(result.getString(6));
                cliente.setWhatsapp(result.getString(7));               
                cliente.setPass(result.getString(8));    
                cliente.setDocumento(result.getString(9));
                list.add(cliente);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);            
        }
        
        return list;        
    }   

    public boolean updateUser(Cliente cliente, String idDocument){
        Connection conn = connDB.getConnectionDB();     
        boolean respuesta = false;   
        String sql = "UPDATE Cliente SET documento=?, nombres=?, apellidos=?,"+
                     " direccion=?, telefono=?, email=?, whatsapp=?,passwored=? WHERE documento=?";
        
        try{
            pst = conn.prepareStatement(sql);           
            pst.setString(1, cliente.getDocumento());
            pst.setString(2, cliente.getNombres());
            pst.setString(3, cliente.getApellidos());
            pst.setString(4, cliente.getDireccion());
            pst.setString(5, cliente.getTelefono());
            pst.setString(6,cliente.getEmail());
            pst.setString(7, cliente.getWhatsapp());        
            pst.setString(8, cliente.getPass());
            pst.setString(9, idDocument);
            pst.executeUpdate();
            respuesta = true;
            
        }catch(Exception e){
            respuesta = false;
            JOptionPane.showMessageDialog(null, e);            
        }
        return respuesta;

    }   
    
    public boolean delete(String documento){
        Connection conn = connDB.getConnectionDB();        
        String sql =  "UPDATE Cliente SET state='0' WHERE documento=?";
        boolean respuesta = false;   
        try{            
            pst = conn.prepareStatement(sql);
            pst.setString(1, documento);
            pst.executeUpdate();
            respuesta = true;
        }catch(Exception e){
            respuesta = false;
            JOptionPane.showMessageDialog(null, e);            
        }
        return respuesta;
    }   
    
    public Cliente configcliente(String cc){       
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM Cliente WHERE documento=?";
        try{            
            Connection conn = connDB.getConnectionDB();            
            pst = conn.prepareStatement(sql);
            pst.setString(1, cc);
            result = pst.executeQuery();
             while(result.next()){                
                cliente.setDocumento(result.getString("documento"));
                cliente.setNombres(result.getString("nombres"));
                cliente.setApellidos(result.getString("apellidos"));
                cliente.setDireccion(result.getString("direccion"));                             
                cliente.setTelefono(result.getString("telefono"));
                cliente.setEmail(result.getString("email"));
                cliente.setWhatsapp(result.getString("whatsapp"));           
                cliente.setPass(result.getString("password"));                                
            }                                    
            conn.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e); 
        }            
        return cliente;        
    }      
}
