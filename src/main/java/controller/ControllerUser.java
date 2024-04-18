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
                String json = user_dao.listar(); 
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
                           
            switch (opcion){                    
                case "login": 
                   user = user_dao.login(email, pass, rol);   
                   
                   if(user.getEmail() != null)
                   {   
                       HttpSession session = request.getSession();
                       String name = user.getNombres() + " " + user.getApellidos();
                       String document = user.getDocumento();
                       session.setAttribute("nombre", name);                    
                       session.setAttribute("documento", document);                    
                       response.getWriter().write("login:" + name);
                   }
                   else
                   {
                       String msgError = "Credenciales Incorrectas";
                       response.getWriter().write("error:" + msgError);
                   }                                                 
                   break;
               case "registrar": 
                                           
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
                   String documento_id = request.getParameter("documento_1");                                             

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

                   boolean result_1 = user_dao.updateUser(user,documento_id);

                   if(result_1){
                       response.getWriter().write("update");
                   }else{
                       response.getWriter().write("falla");
                   }     
                   break;
               case "eliminar":                     
                    boolean result_2 = user_dao.delete(documento);
                    if(result_2){
                        response.getWriter().write("eliminado");
                    }else{
                        response.getWriter().write("falla");
                    }                        
                   break;    
               case "configuracion" :                    
                    String json = user_dao.configuser(documento);
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
