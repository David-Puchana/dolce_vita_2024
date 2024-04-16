/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import model.Usuario;
import modelDAO.UsuarioDAO;


public class ControllerUser extends HttpServlet {
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
                List <Usuario> lista = user_dao.listar(); 
                StringBuilder sb = new StringBuilder(); 
                
                sb.append("[");
                
                for (Usuario usuario : lista) {
                    sb.append("{");
                    sb.append("\"tipoDocumento\":\"").append(usuario.getTipoDocumento()).append("\",");
                    sb.append("\"documento\":\"").append(usuario.getDocumento()).append("\",");
                    sb.append("\"nombre\":\"").append(usuario.getNombres()).append("\",");
                    sb.append("\"apellido\":\"").append(usuario.getApellidos()).append("\",");
                    sb.append("\"direccion\":\"").append(usuario.getDireccion()).append("\",");
                    sb.append("\"telefono\":\"").append(usuario.getTelefono()).append("\",");
                    sb.append("\"email\":\"").append(usuario.getEmail()).append("\",");
                    sb.append("\"whatsapp\":\"").append(usuario.getWhatsapp()).append("\",");
                    sb.append("\"rol\":\"").append(usuario.getRol()).append("\",");
                    sb.append("\"password\":\"").append(usuario.getPass()).append("\",");
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
                   if(user.getEmail() != null)
                   {   
                       HttpSession session = request.getSession();
                       String nombre = user.getNombres() + " " + user.getApellidos();
                       String documento = user.getDocumento();
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
                   String tipodocumento = request.getParameter("tipoDocumento");
                   String documento = request.getParameter("documento");
                   String nombre = request.getParameter("nombres");
                   String apellido = request.getParameter("apellidos");
                   String direccion = request.getParameter("direccion");
                   String telefono = request.getParameter("telefono");
                   String email = request.getParameter("email");
                   String wpp = request.getParameter("wpp");
                   String rol = request.getParameter("rol");
                   String pass = request.getParameter("password");                                              

                   user.setTipoDocumento(tipodocumento);
                   user.setDocumento(documento);
                   user.setNombres(nombre);
                   user.setApellidos(apellido);
                   user.setDireccion(direccion);
                   user.setTelefono(telefono);
                   user.setEmail(email);
                   user.setWhatsapp(wpp);
                   user.setRol(rol);
                   user.setPass(pass);

                   boolean result = user_dao.addUsuario(user);

                   if(result){
                       response.getWriter().write("add");
                   }else{
                       response.getWriter().write("falla");
                   }                        
                   break;   
               case "actualizar": 
                   String tipodocumento_1 = request.getParameter("tipoDocumento");
                   String documento_1 = request.getParameter("documento");
                   String nombre_1 = request.getParameter("nombres");
                   String apellido_1 = request.getParameter("apellidos");
                   String direccion_1 = request.getParameter("direccion");
                   String telefono_1 = request.getParameter("telefono");
                   String email_1 = request.getParameter("email");
                   String wpp_1 = request.getParameter("wpp");
                   String rol_1 = request.getParameter("rol");
                   String pass_1 = request.getParameter("password");
                   String documento_id = request.getParameter("documento_1");                                             

                   user.setTipoDocumento(tipodocumento_1);
                   user.setDocumento(documento_1);
                   user.setNombres(nombre_1);
                   user.setApellidos(apellido_1);
                   user.setDireccion(direccion_1);
                   user.setTelefono(telefono_1);
                   user.setEmail(email_1);
                   user.setWhatsapp(wpp_1);
                   user.setRol(rol_1);
                   user.setPass(pass_1);                                              

                   boolean result_1 = user_dao.updateUser(user,documento_id);

                   if(result_1){
                       response.getWriter().write("update");
                   }else{
                       response.getWriter().write("falla");
                   }     
                   break;
               case "eliminar": 
                    String documento_2 = request.getParameter("documento");
                    boolean result_2 = user_dao.delete(documento_2);
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


                    user = user_dao.configuser(cc);

                    sb.append("{");
                    sb.append("\"tipoDocumento\":\"").append(user.getTipoDocumento()).append("\",");
                    sb.append("\"documento\":\"").append(user.getDocumento()).append("\",");
                    sb.append("\"nombre\":\"").append(user.getNombres()).append("\",");
                    sb.append("\"apellido\":\"").append(user.getApellidos()).append("\",");
                    sb.append("\"direccion\":\"").append(user.getDireccion()).append("\",");
                    sb.append("\"telefono\":\"").append(user.getTelefono()).append("\",");
                    sb.append("\"email\":\"").append(user.getEmail()).append("\",");
                    sb.append("\"whatsapp\":\"").append(user.getWhatsapp()).append("\",");
                    sb.append("\"rol\":\"").append(user.getRol()).append("\",");
                    sb.append("\"password\":\"").append(user.getPass()).append("\"}]");
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
    }

}
