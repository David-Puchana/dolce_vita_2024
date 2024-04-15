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
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {                                                          
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
                            session.setAttribute("nombre", nombre);                    
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
                    case "load": 
                        documento = request.getParameter("id");
                        
                        
                        
                        break;    
                    case "actualizar": 
                        break;
                    case "eliminar": 
                        break;    
                    default:break;   
                 }                         
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
