/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import model.Proveedor;
import modelDAO.ProveedorDAO;

/**
 *
 * @author david
 */
public class ControllerProveedor extends HttpServlet {
    Proveedor proveedor = new Proveedor();
    ProveedorDAO proveedor_dao = new ProveedorDAO();
    HttpSession session;    
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
                    
            String opcion = request.getParameter("option");
                       
            if(opcion.equals("listar")){
                List <Proveedor> lista = proveedor_dao.listar(); 
                StringBuilder sb = new StringBuilder(); 
                
                sb.append("[");
                
                for (Proveedor proveedor : lista) {
                    sb.append("{");                    
                    sb.append("\"nit\":\"").append(proveedor.getNit()).append("\",");
                    sb.append("\"razonsocial\":\"").append(proveedor.getRazonSocial()).append("\",");                    
                    sb.append("\"direccion\":\"").append(proveedor.getDireccion()).append("\",");
                    sb.append("\"telefono\":\"").append(proveedor.getTelefono()).append("\",");
                    sb.append("\"email\":\"").append(proveedor.getEmail()).append("\",");
                    sb.append("\"whatsapp\":\"").append(proveedor.getWhatsapp()).append("\",");                    
                    sb.deleteCharAt(sb.length() - 1);            
                    sb.append("},");                                            
                 }
                sb.deleteCharAt(sb.length() - 1);     
                sb.append("]");
                
                String json = sb.toString(); 
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(json);                                                
            }else if (opcion.equals("logout")){
            
                HttpSession session = request.getSession(false);
                if (session != null) {                    
                    session.invalidate(); 
                }    
                response.sendRedirect("index.jsp");                                                                 
            }                
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);

            String opcion = request.getParameter("option");   

            switch (opcion){                    
               case "registrar":                    
                   String nit = request.getParameter("nit");
                   String razonsocial = request.getParameter("razonsocial");                   
                   String direccion = request.getParameter("direccion");
                   String telefono = request.getParameter("telefono");
                   String email = request.getParameter("email");
                   String wpp = request.getParameter("wpp");
                   
                   
                   proveedor.setNit(nit);
                   proveedor.setRazonSocial(razonsocial);                   
                   proveedor.setDireccion(direccion);
                   proveedor.setTelefono(telefono);
                   proveedor.setEmail(email);
                   proveedor.setWhatsapp(wpp);                   
                   
                   boolean result = proveedor_dao.addProveedor(proveedor);
                                      
                   if(result){
                       response.getWriter().write("add");
                   }else{
                       response.getWriter().write("falla");
                   }                        
                   break;   
               case "actualizar": 
                   String nit_1 = request.getParameter("nit");
                   String razonsocial_1 = request.getParameter("razonsocial");                   
                   String direccion_1 = request.getParameter("direccion");
                   String telefono_1 = request.getParameter("telefono");
                   String email_1 = request.getParameter("email");
                   String wpp_1 = request.getParameter("wpp");
                   String documento_id =  request.getParameter("documento_1");
                   
                   proveedor.setNit(nit_1);
                   proveedor.setRazonSocial(razonsocial_1);                   
                   proveedor.setDireccion(direccion_1);
                   proveedor.setTelefono(telefono_1);
                   proveedor.setEmail(email_1);
                   proveedor.setWhatsapp(wpp_1);                   
                                                      
                   boolean result_1 = proveedor_dao.updateProveedor(proveedor,documento_id);

                   if(result_1){
                       response.getWriter().write("update");
                   }else{
                       response.getWriter().write("falla");
                   }     
                   break;
               case "eliminar": 
                    String documento_2 = request.getParameter("nit");
                    boolean result_2 = proveedor_dao.delete(documento_2);
                    if(result_2){
                        response.getWriter().write("eliminado");
                    }else{
                        response.getWriter().write("falla");
                    }                        
                   break;    
               default:
                   break;   
            }                         
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
