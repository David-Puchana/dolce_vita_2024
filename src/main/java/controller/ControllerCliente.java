/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import modelDAO.UsuarioDAO;

import modelDAO.ClienteDAO;
import model.Cliente;




/**
 *
 * @author david
 */
public class ControllerCliente extends HttpServlet {
    Cliente client = new Cliente();
    ClienteDAO client_dao = new ClienteDAO();
    Usuario user = new Usuario();
    UsuarioDAO user_dao = new UsuarioDAO();
    HttpSession session;
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                List <Cliente> lista = client_dao.listar(); 
                StringBuilder sb = new StringBuilder(); 
                
                sb.append("[");
                
                for (Cliente client_0 : lista) {
                    sb.append("{");                    
                    sb.append("\"documento\":\"").append(client_0.getDocumento()).append("\",");
                    sb.append("\"nombre\":\"").append(client_0.getNombres()).append("\",");
                    sb.append("\"apellido\":\"").append(client_0.getApellidos()).append("\",");
                    sb.append("\"direccion\":\"").append(client_0.getDireccion()).append("\",");
                    sb.append("\"telefono\":\"").append(client_0.getTelefono()).append("\",");
                    sb.append("\"email\":\"").append(client_0.getEmail()).append("\",");
                    sb.append("\"whatsapp\":\"").append(client_0.getWhatsapp()).append("\",");              
                    sb.append("\"password\":\"").append(client_0.getPass()).append("\",");
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
                case "login": 
                   String username = request.getParameter("username");
                   String password = request.getParameter("password");
                   String role = request.getParameter("selectrole");

                   user = user_dao.login(username, password, role);                        
                   if(client.getEmail() != null)
                   {   
                       HttpSession session = request.getSession();
                       String nombre = client.getNombres() + " " + client.getApellidos();
                       String documento = client.getDocumento();
                       session.setAttribute("nombre", nombre);                    
                       session.setAttribute("documento", documento);                    
                       response.getWriter().write("login:" + nombre);
                   }
                   else
                   {
                       String msgError = "Credenciales Incorrectas";
                       response.getWriter().write("error:" + msgError);
                   }                                                 
                   break;
               case "registrar":                    
                   String documento = request.getParameter("documento");
                   String nombre = request.getParameter("nombres");
                   String apellido = request.getParameter("apellidos");
                   String direccion = request.getParameter("direccion");
                   String telefono = request.getParameter("telefono");
                   String email = request.getParameter("email");
                   String wpp = request.getParameter("wpp");
                   String pass = request.getParameter("password");                                              
                   
                   client.setDocumento(documento);
                   client.setNombres(nombre);
                   client.setApellidos(apellido);
                   client.setDireccion(direccion);
                   client.setTelefono(telefono);
                   client.setEmail(email);
                   client.setWhatsapp(wpp);                   
                   client.setPass(pass);

                   boolean result = client_dao.addCliente(client);

                   if(result){
                       response.getWriter().write("add");
                   }else{
                       response.getWriter().write("falla");
                   }                        
                   break;   
               case "actualizar": 
                  
                   String documento_1 = request.getParameter("documento");
                   String nombre_1 = request.getParameter("nombres");
                   String apellido_1 = request.getParameter("apellidos");
                   String direccion_1 = request.getParameter("direccion");
                   String telefono_1 = request.getParameter("telefono");
                   String email_1 = request.getParameter("email");
                   String wpp_1 = request.getParameter("wpp");                  
                   String pass_1 = request.getParameter("password");
                   String documento_id = request.getParameter("documento_1");                                             

                   
                   client.setDocumento(documento_1);
                   client.setNombres(nombre_1);
                   client.setApellidos(apellido_1);
                   client.setDireccion(direccion_1);
                   client.setTelefono(telefono_1);
                   client.setEmail(email_1);
                   client.setWhatsapp(wpp_1);                   
                   client.setPass(pass_1);                                              

                   boolean result_1 = client_dao.updateClient(client,documento_id);

                   if(result_1){
                       response.getWriter().write("update");
                   }else{
                       response.getWriter().write("falla");
                   }     
                   break;
               case "eliminar": 
                    String documento_2 = request.getParameter("documento");
                    boolean result_2 = client_dao.delete(documento_2);
                    if(result_2){
                        response.getWriter().write("eliminado");
                    }else{
                        response.getWriter().write("falla");
                    }                        
                   break;    
               case "configuracion" :
                    String cc = request.getParameter("documento");
                    StringBuilder sb = new StringBuilder();                 
                    sb.append("[");

                    client = client_dao.configcliente(cc);

                    sb.append("{");                   
                    sb.append("\"documento\":\"").append(client.getDocumento()).append("\",");
                    sb.append("\"nombre\":\"").append(client.getNombres()).append("\",");
                    sb.append("\"apellido\":\"").append(client.getApellidos()).append("\",");
                    sb.append("\"direccion\":\"").append(client.getDireccion()).append("\",");
                    sb.append("\"telefono\":\"").append(client.getTelefono()).append("\",");
                    sb.append("\"email\":\"").append(client.getEmail()).append("\",");
                    sb.append("\"whatsapp\":\"").append(client.getWhatsapp()).append("\",");
                    sb.append("\"password\":\"").append(client.getPass()).append("\"}]");
                    String json = sb.toString(); 
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(json);                       
                   break;  
               default:break;   
            }                         
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
