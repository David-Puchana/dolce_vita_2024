<%-- 
    Document   : viewAdmin
    Created on : 9/04/2024, 11:07:51 a. m.
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<% HttpSession sessio = request.getSession(false); %>
<% if (sessio == null || sessio.getAttribute("nombre") == null) { %>
    <% response.sendRedirect("../../index.jsp"); %>
<% } %>


<!DOCTYPE html>
<html>
    <head>       
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cssBootstrap/bootstrap.min.css">
        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cssProject/stiyleAdmin.css">        
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>       
                
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.0.3/css/dataTables.dataTables.css">        
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css">        
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/3.0.1/css/responsive.bootstrap5.css">
        <title>DolceVita</title>        
    </head>
    <body>
        <div class="row" id="row-1">
            <nav class="navbar navbar-expand-lg my-2 mx-3" id="navBar">           
                <img class="ms-3" src="${pageContext.servletContext.contextPath}/img/dolveVitaLogoAdminSmall.png" id="imgLogo">             
                <div class="container-fluid me-4">                  
                    <button class="navbar-toggler mt-3 float-end" id="btnNav" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                      <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mt-1 mb-lg-0">

                            <li class="nav-item singleOption">
                                <a class="nav-link active accion" aria-current="page" href="#" data-url="inicio.jsp">Inicio</a>
                            </li>

                            <li class="nav-item singleOption">
                              <a class="nav-link active accion" aria-current="page" href="ControllerUser?option=listar" data-url="user.jsp">Usuario</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Producción
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" id="dropdownUsuario">
                                    <li><a class="dropdown-item text-dark accion" href="ControllerInput?option=listar" >Insumos</a></li>
                                    <li><a class="dropdown-item text-dark accion" href="#">Productos</a></li>                          
                                    <li><a class="dropdown-item text-dark accion" href="#">Recetas</a></li>                          
                                    <li><a class="dropdown-item text-dark accion" href="#">Reporte</a></li>                          
                                    <li><a class="dropdown-item text-dark accion" href="#">Proveedores</a></li>                          
                                </ul>
                            </li>

                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Facturacion
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <li><a class="dropdown-item text-dark accion" href="#">Compras</a></li>
                                    <li><a class="dropdown-item text-dark accion" href="#">Ventas</a></li>                          
                                </ul>
                            </li>        
                            <li class="nav-item singleOption">
                              <a class="nav-link active accion" aria-current="page" href="#">Clientes</a>
                            </li>                                                                                                        
                        </ul>  

                        <ul class="navbar-nav mt-1 mb-lg-0">                            
                             <li class="nav-item dropdown">                                        
                                <a class="nav-link dropdown-toggle text-dark" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                   <img src="${pageContext.servletContext.contextPath}/img/login.png" id="imgUser">   
                                   ${nombre}                                         
                                </a>
                                 <ul class="dropdown-menu">
                                    <li class="d-flex justify-content-center align-middle">  
                                        <div class="ms-2 pt-1">
                                            <box-icon name='cog'></box-icon>                                                      
                                        </div>
                                        <a id="loginCliente" class="dropdown-item" href="#" onclick="dataConfig(${documento})" data-bs-toggle="modal" data-bs-target="#modaladd_admin">                                                                                           
                                            Configuración
                                        </a>
                                    </li>      
                                    <li class="d-flex justify-content-center align-middle">                                                
                                        <div class="ms-2 pt-1">
                                            <box-icon type='solid' name='x-square'></box-icon>                                                    
                                        </div>
                                        <a id="loginAsesor" class="dropdown-item" href="${pageContext.servletContext.contextPath}/ControllerUser?option=logout">                                                                                                    
                                            Cerrar
                                        </a>
                                    </li>                                                             
                                </ul>
                            </li>
                        </ul>                                                                                                                
                    </div>
                </div>            
            </nav>                                                                                                                                                                                                                                                            
        </div>
        
        <div class="container-fluid" id="contenedor-principal">
            <div class="modal fade modal-lg" id="modaladd_admin" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div class="row w-100">
                                <div class="col-3">                        
                                  <img src="${pageContext.servletContext.contextPath}/img/dolveVitaLogoAdminSmall.png" id="imgLogoSmall">                        
                                </div>    
                                <div class="col-6 mt-3">
                                    <h4 class="modal-title w-100 text-center" id="titleModal-admin">${nombre}</h4>              
                                </div>
                                <div class="col-3">
                                    <button type="button" class="btn-close ms-auto" style="float: right;" data-bs-dismiss="modal" aria-label="Close"></button>
                                 </div>                                                
                            </div>
                        </div>              
                        <div class="modal-body mx-1">           

                            <form class="row g-4 needs-validation" id="formuser_admin">
                                <div class="col-md-3">
                                    <label for="tipoDocumento" class="form-label">Tipo de documento</label>
                                    <select class="form-select" id="tipoDocumento-admin" name="tipoDocumento-admin" required>
                                      <option selected disabled value="">Seleccione</option>
                                      <option value="CC">Cédula</option>
                                      <option value="PASAPORTE">Pasaporte</option>
                                    </select>     
                                </div>

                                <div class="col-md-3">
                                  <label for="documento" class="form-label">N° documento</label>
                                  <input type="text" class="form-control" id="documento-admin" name="documento-admin" required>
                                </div>

                                <div class="col-md-3">
                                  <label for="nombres" class="form-label" >Nombres</label>
                                  <input type="text" class="form-control" id="nombres-admin" name="nombres-admin" required>
                                </div>

                                <div class="col-md-3">
                                  <label for="apellidos" class="form-label">Apellidos</label>
                                  <input type="text" class="form-control" id="apellidos-admin" name="apellidos-admin" required>
                                </div>

                                <div class="col-md-4">
                                    <label for="email" class="form-label">Email</label>
                                    <div class="input-group has-validation">
                                      <span class="input-group-text" id="inputGroupPrepend">@</span>
                                      <input type="email" class="form-control" id="email-admin" name="email-admin" aria-describedby="inputGroupPrepend" required>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <label for="direccion" class="form-label">Dirección</label>
                                    <input type="text" class="form-control" id="direccion-admin" name="direccion-admin" required>                    
                                </div>

                                <div class="col-sm-2">
                                    <label for="telefono" class="form-label">Teléfono</label>
                                    <input type="text" class="form-control" id="telefono-admin" name="telefono-admin">
                                </div>

                                <div class="col-sm-2">
                                    <label for="wpp" class="form-label">Whatsapp</label>
                                    <input type="text" class="form-control" id="wpp-admin" name="wpp-admin" required>
                                </div>        

                                <div class="col-md-3">
                                    <label for="rol" class="form-label">Rol</label>
                                    <select class="form-select" id="rol-admin" name="rol-admin" required>
                                      <option selected disabled value="">Seleccione</option>
                                      <option>Admin</option>
                                      <option>Asesor</option>
                                    </select>
                                </div>

                                <div class="col-md-3">
                                    <label for="password" class="form-label">Contraseña</label>
                                    <input type="text" class="form-control" id="password-admin" name="password-admin" required>
                                </div>        

                                <div class="col-12" id="divupdate-admin">
                                  <button class="btn btn-secondary" value="${documento}" id="update_admin">Actualizar</button>
                                </div>

                            </form>                                                                 
                        </div>                    
                    </div>
                </div>
            </div>                        
    
            <div class="row my-3 d-flex justify-content-center" id="view-data">                                            
                <div class="row">      
                    <div class="col-md-6" id="columna_1">                                        
                        <div class="contenido-texto pt-3 mx-3 mt-3">
                           <h1 class="my-3 mx-auto text-center">¡Bienvenido al software de administración Dolce Vita!</h1>
                   
                           <p>En Dolce Vita, entendemos las complejidades del negocio y te ofrecemos las herramientas necesarias
                              para simplificar tus operaciones diarias. Con nuestras
                              funciones podrás mantener un registro preciso de todos los ingredientes
                              y productos, asegurándote de tener siempre lo que necesitas al alcance de tu mano.
                              Además, nuestra plataforma te permite realizar un seguimiento detallado de las ventas y generar
                              informes personalizados para analizar el rendimiento de tu negocio.</p>                                        
                           <p>Con nuestras características de facturación intuitivas, podrás crear facturas de manera rápida y precisa,
                               garantizando una experiencia sin complicaciones tanto para ti como para tus clientes.</p>
                        </div>        
                    </div>
                    <div class="col-md-6">
                        <img src="${pageContext.servletContext.contextPath}/img/pasteleria_r.png" id="imgpasteleria_r">
                    </div>    
                </div>
               
            </div>
        </div>      
        <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jsQuery/jquery-3.7.1.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jsBootstrap/bootstrap.bundle.min.js"></script>      
        
        
        <script src="https://cdn.datatables.net/2.0.3/js/dataTables.js"></script>          
        <script src="https://cdn.datatables.net/2.0.3/js/dataTables.bootstrap5.js"></script>        
        <script src="https://cdn.datatables.net/responsive/3.0.1/js/dataTables.responsive.js"></script>
        <script src="https://cdn.datatables.net/responsive/3.0.1/js/responsive.dataTables.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        
        <script>
            window.path = "${pageContext.request.contextPath}";
        </script>
                      
        <script src="${pageContext.servletContext.contextPath}/js/jsProject/jsUser.js"></script>
    </body>
</html>
