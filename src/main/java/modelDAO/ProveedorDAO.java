/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import  connection.ConnectionDB;
import model.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author david
 */
public class ProveedorDAO {
    
    PreparedStatement pst;
    ResultSet result;
    ConnectionDB connDB = new ConnectionDB(); 
    
    public boolean addProveedor(Proveedor proveedor){
       
        String sql = "INSERT INTO Proveedor (razonSocial,nit/documento,"+
                     "direccion,telefono,email,whatsapp, state)"+
                     "VALUES (?,?,?,?,?,?,?,?,?)";
        boolean respuesta = false;    
        try{
            Connection conn = connDB.getConnectionDB();            

            pst = conn.prepareStatement(sql);
            pst.setString(1, proveedor.getRazonSocial());
            pst.setString(2, proveedor.getNit());
            pst.setString(3, proveedor.getDireccion());
            pst.setString(4, proveedor.getTelefono());
            pst.setString(5, proveedor.getEmail());
            pst.setString(6, proveedor.getWhatsapp());                        
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
        String sql = "SELECT * FROM Proveedor WHERE state=1";        
        List<Proveedor> list = new ArrayList<>();
        
        try{            
            pst = conn.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(result.getInt(1));                 
                proveedor.setRazonSocial(result.getString(2));
                proveedor.setNit(result.getString(3));
                proveedor.setDireccion(result.getString(4));
                proveedor.setTelefono(result.getString(5));
                proveedor.setEmail(result.getString(6));
                proveedor.setWhatsapp(result.getString(7));               
                list.add(proveedor);
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);            
        }        
        return list;        
    }   

    public boolean updateProveedor(Proveedor proveedor, String idDocument){
        Connection conn = connDB.getConnectionDB();     
        boolean respuesta = false;   
        String sql = "UPDATE Proveedor SET razonSocial=?, nit/documento=?,"+
                     " direccion=?, telefono=?, email=?, whatsapp=? WHERE documento=?";
        
        try{
            pst = conn.prepareStatement(sql);           
            pst.setString(1, proveedor.getRazonSocial());
            pst.setString(2, proveedor.getNit());
            pst.setString(3, proveedor.getDireccion());
            pst.setString(4, proveedor.getTelefono());
            pst.setString(5, proveedor.getEmail());
            pst.setString(6,proveedor.getWhatsapp());            
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
        String sql =  "UPDATE Proveedor SET state='0' WHERE documento=?";
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
}
