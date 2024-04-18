<%-- 
    Document   : proveedor
    Created on : 16/04/2024, 11:19:07 a. m.
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    
    <div class="row d-flex justify-content-center align-middle" id="divtitleusersection">        
        <div class="row my-1 d-flex justify-content-center align-middle">
            <div class="col-6"><h1>Gestion de Proveedores</h1></div>
            <div class="col-3 align-self-center">
                <div class="d-grid gap-2">
                    <button type="button" class="btn btn-success btnAdd"   onclick="toggleButtonsProveedor()" data-bs-toggle="modal" data-bs-target="#modaladd_proveedores">Agregar</button>
                </div>                    
            </div>
        </div>        
        <hr>
        <div class="modal fade modal-lg" id="modaladd_proveedores" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row w-100">
                            <div class="col-3">                        
                              <img src="${pageContext.servletContext.contextPath}/img/dolveVitaLogoAdminSmall.png" id="imgLogoSmall">                        
                            </div>    
                            <div class="col-6 mt-3">
                                <h4 class="modal-title w-100 text-center" id="titleModal">Rigistrar Proveedor</h4>              
                            </div>
                            <div class="col-3">
                                <button type="button" class="btn-close ms-auto" style="float: right;" data-bs-dismiss="modal" aria-label="Close" onclick="limpiarFormulario()"></button>
                             </div>                                                
                        </div>
                    </div>              
                    <div class="modal-body mx-1">           
        
                        <form class="row g-4 needs-validation" id="formproveedor" novalidate>

                            <div class="col-md-3">
                              <label for="nit" class="form-label">Nit</label>
                              <input type="text" class="form-control" id="nit" name="nit" pattern=".{11,}" title="El campo debe contener al menos 11 caracteres." required>
                            </div>

                            <div class="col-md-3">
                              <label for="razonsocial" class="form-label" >Razon Social</label>
                              <input type="text" class="form-control" id="razonsocial" name="razonsocial" pattern="[a-zA-Z]{3,}" title="Los razonsocial debe contener al menos tres letras y no puede contener números ni caracteres especiales." required>
                            </div>
                            
                            <div class="col-sm-3">
                                <label for="wpp" class="form-label">Whatsapp</label>
                                <input type="text" class="form-control" id="wpp" name="wpp" required>
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
                                <input type="text" class="form-control" id="direccion" name="direccion"  pattern=".{4,}" title="La dirección debe tener al menos 4 caracteres." required>                    
                            </div>

                            <div class="col-sm-3">
                                <label for="telefono" class="form-label">Teléfono</label>
                                <input type="text" class="form-control" id="telefono" name="telefono">
                            </div>
      
                            <div class="col-12" id="divadd" style="display: none;">
                              <button class="btn btn-success btnAdd" value="registrar" onclick="addProveedor()">Registrar</button>
                            </div>
                            
                            <div class="col-12" id="divupdate" style="display: none;">
                                <button class="btn btn-secondary" value="actualizar" onclick="updateProveedor()">Actualizar</button>
                            </div>
                            
                        </form>                                                                 
                    </div>                    
                </div>
            </div>
        </div>                        
     
        <div class="col-12 d-flex justify-content-center">
            <table id="tablaProveedores" class="table table-striped nowrap" style="width: 100%">
                <thead>    
                    <tr>                        
                        <td>Nit</td>
                        <td>Razón Social</td>                        
                        <td>Dirección</td>
                        <td>Teléfono</td>
                        <td>Email</td>       
                        <td>whatsapp</td>                                                                            
                        <td>Acciones</td>
                    </tr>
                </thead>
                <tbody>                
                </tbody>
            </table> 
        </div>    
    </div>            
</div>
