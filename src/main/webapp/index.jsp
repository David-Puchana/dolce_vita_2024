<%-- 
    Document   : index.jsp
    Created on : 8/04/2024, 10:19:08 p. m.
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.servletContext.contextPath}/css/cssBootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>    
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cssProject/styleIndex.css"">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script src="jsQuery/jquery-3.7.1.min.js" type="text/javascript"></script>
        <title>DolceVita</title>
    </head>
    <body>
                     
        <nav class="navbar navbar-expand-lg bg-white my-2 mx-3">           
            <img class="me-5" src="img/dolveVitaLogo.png" id="imgLogo">             
            <div class="container-fluid">                  
              <button class="navbar-toggler mt-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mt-1 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Inicio</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Nosotros</a>
                  </li>                  
                </ul>  
                <ul class="navbar-nav mt-1 mb-lg-0">                            
                     <li class="nav-item dropdown">
                         <a class="nav-link dropdown-toggle text-dark" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="img/login.png">
                            Login
                         </a>
                         <ul class="dropdown-menu">
                            <li class="d-flex justify-content-center">
                                <a id="loginCliente" class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modalclient">Cliente</a>
                            </li>      
                             <li class="d-flex justify-content-center">
                                 <a id="loginAsesor" class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modalservant">Asesor</a>
                             </li>                                                             
                         </ul>
                     </li>
                 </ul>                                                                                                                
              </div>
            </div>            
        </nav>                   
        <hr>
        
        <div class="modal fade modal-sm" id="modalclient" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" style="float: right;" class="btn-close ms-auto"" data-bs-dismiss="modal" aria-label="Close" onclick="limpiarFormulario()"></button>
                        <div class="logoSamall  d-flex justify-content-center align-items-center flex-grow-1 my-4">       
                          <img src="img/dolveVitaLogoAdminSmall.png" id="imgLogoSmall"> 
                        </div>
                        <h4 class="modal-title w-100 text-center" id="exampleModalLabel">Inicio de Sesión</h4>              
                    </div>              
                    <div class="modal-body mx-1">                     
                        <form class="form" id="formcliente">
                          <div class="inputForm mt-2 mb-4" > 
                            <input class="mt-2" type="text" required>
                            <label>Usuario</label>              
                          </div>

                          <div class="inputForm my-4">              
                            <input class="mt-2" type="password" required>
                            <label>Contraseña</label>              
                          </div>  
                        </form>              
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                      <div class="d-flex justify-content-center">
                        <button class="btnInit my-2" type="submit" id="login">Iniciar</button>
                      </div>
                      <div class="register my-2">
                        Quiero hace el <a href="#"> registro</a>
                      </div>        
                    </div>            
                </div>
            </div>
        </div>
                
        <div class="modal fade modal-sm" id="modalservant" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" style="float: right;" class="btn-close ms-auto" data-bs-dismiss="modal" aria-label="Close" onclick="limpiarFormulario()"></button>
                        <div class="logoSamall  d-flex justify-content-center align-items-center flex-grow-1 mt-3 mb-2">       
                          <img src="img/dolveVitaLogoAdminSmall.png" id="imgLogoSmall"> 
                        </div>
                        <h4 class="modal-title w-100 text-center" id="exampleModalLabel">Inicio de Sesión</h4>              
                    </div>              
                    <div class="modal-body mx-1">                     
                        <form class="form" id="formservant">
                            <div class="inputForm mt-2 mb-4" > 
                              <input class="mt-2" type="text" name="user" id="user" required>
                              <label>Usuario</label>              
                            </div>
                            <div class="inputForm my-4">              
                              <input class="mt-2" type="password" name="password" id="password" required>
                              <label>Contraseña</label>              
                            </div>   
                            <div class="d-flex justify-content-center align-items-center flex-grow-1">   
                               <select class="form-select" name="selectrole" id="selectrole" required>      
                                    <option value="" disabled selected>Seleccione su rol</option>     
                                    <option>Asesor</option>     
                                    <option>Admin</option>     
                               </select>      
                            </div>
                            <div class="d-flex justify-content-center my-4">
                                <button class="btnInit" type="submit">Iniciar</button>
                            </div>
                        </form>    
                        
                        <div class="register my-2">
                          Quiero hace el <a href="#"> registro</a>
                        </div> 
                    </div>
                    
                </div>
            </div>
        </div>
                  
        <div class="container">
            <div class="row">
                <img src="img/imgPastelHome.png" class="img-center"> 
            </div>        
            <section class="row my-3 ">
                <div class="col-md-8" >
                    <article class="p-5 h-100 d-inline-block">
                        <h1>NUESTROS PASTELES SON
                            HECHOS A MANO
                            CON AMOR.</h1>
                        <p class="mt-2">En el corazón de la ciudad, escondida entre calles adoquinadas y aromas a café 
                            recién hecho, se encuentra la Pastelería Dolce Vita. Un lugar donde la pasión por la repostería se traduce en dulces obras de arte,
                            cada una elaborada con un amor que se siente en cada bocado.</p>    
                        <p>Déjate llevar por la magia de nuestros sabores y embárcate en una experiencia que despertará
                            tus emociones evocando dulces momentos de felicidad. ¡Te esperamos con los brazos abiertos 
                            para que disfrutes de momentos inolvidables llenos de sabor y felicidad en cada instante! </p>
                    </article>
                </div>
                <div class="col-md-4 bg-white">
                    <img src="img/imgTrozoPastel.png" id="imgTrozoPastel"> 
                </div>
            </section>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="cake">
                        <img src="img/cake_1.png" class="imgCake"> 
                        <button class="btnCake"> Pedir online </button>
                    </div>                     
                </div>
                <div class="col-lg-4">
                    <div class="cake">
                        <img src="img/pastelero.png" class="imgCake"> 
                        <button class="btnCake"> Conoce al equipo </button>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="cake">
                        <img src="img/cake_2.png" class="imgCake"> 
                        <button class="btnCake"> Personaliza tu pastel </button>
                    </div>
                </div>
            </div>            
        </div>
        <footer>
            <div class="container-fuid pt-3 pb-2">        
                <p class="small text-center" style="color:white;">&copy; Todos los derechos reservados | 2024</p>
            </div>
        </footer>
        
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jsQuery/jquery-3.7.1.min.js"></script>                     
        <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jsBootstrap/bootstrap.bundle.min.js"></script>     
        <script src="${pageContext.servletContext.contextPath}/js/jsProject/jsIndex.js"></script>                          
    </body>
</html>
