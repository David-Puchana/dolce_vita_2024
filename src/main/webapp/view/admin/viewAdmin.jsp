<%-- 
    Document   : viewAdmin
    Created on : 9/04/2024, 11:07:51 a. m.
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cssBootstrap/bootstrap.min.css">
        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cssProject/stiyleAdmin.css">        
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>       
                
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.0.3/css/dataTables.dataTables.css">        
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css">        
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/3.0.1/css/responsive.bootstrap5.css">
        <title>DolceVita</title>        
    </head>
    <body>

        <div class="container-fluid">
            <div class="row" id="row-1">
                <div class="col-md-12" id="col-1">                                       
                    <nav class="navbar navbar-expand-lg my-2 mx-3" id="navBar">           
                        <img class="me-5" src="${pageContext.servletContext.contextPath}/img/dolveVitaLogoAdminSmall.png" id="imgLogo">             
                        <div class="container-fluid">                  
                            <button class="navbar-toggler mt-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                              <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav me-auto mt-1 mb-lg-0">
                                    <li class="nav-item singleOption">
                                      <a class="nav-link active accion" aria-current="page" href="ControllerUser?option=listar" data-url="user.jsp">Usuario</a>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            Inventario
                                        </a>
                                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" id="dropdownUsuario">
                                            <li><a class="dropdown-item text-dark accion" href="ControllerInput?option=listar" >Insumos</a></li>
                                            <li><a class="dropdown-item text-dark accion" href="#">Productos</a></li>                          
                                            <li><a class="dropdown-item text-dark accion" href="#">Recetas</a></li>                          
                                            <li><a class="dropdown-item text-dark accion" href="#">Reporte</a></li>                          
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
                                                <a id="loginCliente" class="dropdown-item" href="#">                                                                                           
                                                    Configuración
                                                </a>
                                            </li>      
                                            <li class="d-flex justify-content-center align-middle">                                                
                                                <div class="ms-2 pt-1">
                                                    <box-icon type='solid' name='x-square'></box-icon>                                                    
                                                </div>
                                                <a id="loginAsesor" class="dropdown-item" href="#">                                                                                                    
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
            </div>
    
            <div class="row" id="view-data">                            
                
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
                      
        <script src="${pageContext.servletContext.contextPath}/js/jsProject/jsAdmin.js"></script>
    </body>
</html>
