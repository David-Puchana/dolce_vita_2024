<%-- 
    Document   : viewFormListUser
    Created on : 9/04/2024, 4:34:31 p. m.
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container">
    
    <div class="row d-flex justify-content-center align-middle" id="divtitleusersection">        
        <div class="row my-4 d-flex justify-content-center align-middle">
            <div class="col-6"><h1>Gestion de Usuarios</h1></div>
            <div class="col-3 align-self-center">
                <div class="d-grid gap-2">
                    <button type="button" class="btn btn-success btnAdd"   onclick="toggleButtons()" data-bs-toggle="modal" data-bs-target="#modaladd">Agregar</button>
                </div>                    
            </div>
        </div>        
        <hr>
        <div class="modal fade modal-lg" id="modaladd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row w-100">
                            <div class="col-3">                        
                              <img src="${pageContext.servletContext.contextPath}/img/dolveVitaLogoAdminSmall.png" id="imgLogoSmall">                        
                            </div>    
                            <div class="col-6 mt-3">
                                <h4 class="modal-title w-100 text-center" id="titleModal">Rigistrar Usuario</h4>              
                            </div>
                            <div class="col-3">
                                <button type="button" class="btn-close ms-auto" style="float: right;" data-bs-dismiss="modal" aria-label="Close" onclick="limpiarFormulario()"></button>
                             </div>                                                
                        </div>
                    </div>              
                    <div class="modal-body mx-1">           
        
                        <form class="row g-4 needs-validation" id="formuser">
                            <div class="col-md-3">
                                <label for="tipoDocumento" class="form-label">Tipo de documento</label>
                                <select class="form-select" id="tipoDocumento" name="tipoDocumento" required>
                                  <option selected disabled value="">Seleccione</option>
                                  <option value="CC">Cédula</option>
                                  <option value="PASAPORTE">Pasaporte</option>
                                </select>     
                            </div>

                            <div class="col-md-3">
                              <label for="documento" class="form-label">N° documento</label>
                              <input type="text" class="form-control" id="documento" name="documento" required>
                            </div>

                            <div class="col-md-3">
                              <label for="nombres" class="form-label" >Nombres</label>
                              <input type="text" class="form-control" id="nombres" name="nombres" required>
                            </div>

                            <div class="col-md-3">
                              <label for="apellidos" class="form-label">Apellidos</label>
                              <input type="text" class="form-control" id="apellidos" name="apellidos" required>
                            </div>

                            <div class="col-md-4">
                                <label for="email" class="form-label">Email</label>
                                <div class="input-group has-validation">
                                  <span class="input-group-text" id="inputGroupPrepend">@</span>
                                  <input type="email" class="form-control" id="email" name="email" aria-describedby="inputGroupPrepend" required>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <label for="direccion" class="form-label">Dirección</label>
                                <input type="text" class="form-control" id="direccion" name="direccion" required>                    
                            </div>

                            <div class="col-sm-2">
                                <label for="telefono" class="form-label">Teléfono</label>
                                <input type="text" class="form-control" id="telefono" name="telefono">
                            </div>

                            <div class="col-sm-2">
                                <label for="wpp" class="form-label">Whatsapp</label>
                                <input type="text" class="form-control" id="wpp" name="wpp" required>
                            </div>        

                            <div class="col-md-3">
                                <label for="rol" class="form-label">Rol</label>
                                <select class="form-select" id="rol" name="rol" required>
                                  <option selected disabled value="">Seleccione</option>
                                  <option>Admin</option>
                                  <option>Asesor</option>
                                </select>
                            </div>

                            <div class="col-md-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="text" class="form-control" id="password" name="password" required>
                            </div>        

                            <div class="col-12" id="divadd" style="display: none;">
                              <button class="btn btn-success btnAdd" value="registrar" onclick="add()">Registrar</button>
                            </div>
                            
                            <div class="col-12" id="divupdate" style="display: none;">
                              <button class="btn btn-secondary" value="registrar" onclick="update()">Actualizar</button>
                            </div>
                            
                        </form>                                                                 
                    </div>                    
                </div>
            </div>
        </div>                        
     
        <div class="col-12 d-flex justify-content-center">
            <table id="tablaUsuarios" class="table table-striped nowrap" style="width: 100%">
                <thead>    
                    <tr>                        
                        <td>Documento</td>
                        <td>Nombres</td>
                        <td>Apellidos</td>                        
                        <td>Email</td>
                        <td>whatsapp</td>
                        <td>Rol</td>                        
                        <td>Tipo documento</td>
                        <td>Contraseña</td> 
                        <td>Dirección</td>
                        <td>Teléfono</td>                                                
                        <td>Acciones</td>
                    </tr>
                </thead>
                <tbody>                
                </tbody>
            </table> 
        </div>    
    </div>
            
</div>