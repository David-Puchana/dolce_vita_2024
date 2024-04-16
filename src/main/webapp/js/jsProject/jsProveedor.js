/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function enviarSolicitudProveedor(controller, url) {   
    var servletUrl = path + '/' + controller;
    $.ajax({
    type: 'GET',
    url: servletUrl,
    success: function(response) {        
            var jsonData = JSON.parse(response);  
            console.log(response);
            
            var dataTableData = [];            
            for (var i = 0; i < jsonData.length; i++) {
                var row = [];                
                row.push(jsonData[i].nit);
                row.push(jsonData[i].razonsocial);  
                row.push(jsonData[i].direccion);
                row.push(jsonData[i].telefono);  
                row.push(jsonData[i].email);
                row.push(jsonData[i].whatsapp);                                                                                                                           
                dataTableData.push(row);
            }
            
            cargarContenidoProveedor(url,dataTableData);            
    },
    error: function(xhr, status, error) {        
    }
    });
    
}

function cargarContenidoProveedor(url,data) {
    
        var xhr = new XMLHttpRequest();
        xhr.open("GET",url, true);
                     
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {                
                document.getElementById("view-data").innerHTML = xhr.responseText;
                $(document).ready( function () {
                    $('#tablaProveedores').DataTable({                        
                        language: {
                            search: "Buscar",
                            lengthMenu: 'Mostrar _MENU_  registros por página',
                            info: 'Página _PAGE_ de _PAGES_'
                        },
                        data: data,  
                        columnDefs: [
                            { responsivePriority: 1, targets: 0},
                            { responsivePriority: 2, targets: 1},
                            { responsivePriority: 3, targets: 2},
                            { responsivePriority: 4, targets: 3},
                            { responsivePriority: 5, targets: 4},
                            { responsivePriority: 7, targets: -1},                            
                            {
                                targets: -1, 
                                data: null,
                                render: function(data, type, row) {    
                                    var idProveedor = row[0];                                      
                                    var btnActualizar = '<button class="btn btn-secondary btn-actualizar mx-1" onclick="loadataProveedor(this)" data-bs-toggle="modal" data-bs-target="#modaladd_proveedores" id="' + idProveedor + '">Actualizar</button>';
                                    var btnEliminar = '<button class="btn btn-danger btn-eliminar mx-1" onclick="eraseProveedor(this.id)" id="' + idProveedor + '">Eliminar</button>';                                   
                                    return btnActualizar + btnEliminar;
                                }
                            }
                        ],                                                                                                                      
                        responsive: true                    
                    });                                    
                });                                    
            }
        };
        xhr.send();        
} 

function toggleButtonsProveedor() {                    
    var divadd = document.getElementById('divadd');
    var divupdate = document.getElementById('divupdate');
    var title = document.getElementById('titleModal');    
    title.textContent = 'Registrar Proveedor';

    divadd.style.display = 'block';   

    if(divupdate.style.display !== 'none'){
        divupdate.style.display = 'none';   
    }            
} 

function addProveedor(){    
    event.preventDefault();        
    var servletUrl = path + '/ControllerProveedor?option=registrar';     
    
    var form_add = document.getElementById('formproveedor');
    if (form_add.checkValidity()) {
      var formData = $('#formproveedor').serialize();     
          $.ajax({
            type: 'POST',
            url: servletUrl,
            data: formData,
            success: function(response) {                
                if(response==="add"){
                    $('#modaladd_proveedores').modal('hide');
                    Swal.fire({
                        width: "40%",
                        position: "center",
                        icon: "success",
                        title: "Registro exitoso",
                        showConfirmButton: false,
                        timer: 1500
                    }).then((result) => {
                        var controller = "ControllerProveedor?option=listar";
                        var url = "proveedor.jsp";
                        enviarSolicitudProveedor(controller, url);
                    });
                }
            },
            error: function(xhr, status, error) {
            }
        }); 
    } else {
        Swal.fire({
           icon: "error",
           title: "Oops...",
           text: 'Algunos de los campos estan mal diligenciados o imcompletos, revise y vuelva a intentarlo',
           showConfirmButton: false,
           timer: 2500
         }); 
    }
}

let idBtnProveedor = "";

function loadataProveedor(btn){  
    var divupdate = document.getElementById('divupdate');
    var divadd = document.getElementById('divadd');
    var titulo = document.getElementById('titleModal');    
    idBtnProveedor = btn.id;            
    
    titulo.textContent = 'Actualizar Proveedor';

    if(divadd.style.display === 'block'){        
        divadd.style.display = 'none';   
    }    
    divupdate.style.display = 'block';   

    var table = $('#tablaClientes').DataTable();
    var row = $(btn).closest('tr');
    var rowData = table.row(row).data();    
        
    $("#documento").val(rowData[0]);
    $("#nombres").val(rowData[1]);
    $("#apellidos").val(rowData[2]);
    $("#email").val(rowData[3]);
    $("#wpp").val(rowData[4]);
    $("#password").val(rowData[5]);        
    $("#direccion").val(rowData[6]);
    $("#telefono").val(rowData[7]);            
}  

function updateClient(){
    
    event.preventDefault();  
    
    var servletUrl = path + '/ControllerCliente?option=actualizar';        
    var formData = $('#formclient').serialize();        
    formData+="&documento_1="+idBtnCliente;
       
     $.ajax({
            type: 'POST',
            url: servletUrl,
            data: formData,
            success: function(response) {                
                if(response==="update"){
                    $('#modaladd_clientes').modal('hide');
                    Swal.fire({
                        width: "40%",
                        position: "center",
                        icon: "success",
                        title: "Actualizado Correctamente",
                        showConfirmButton: false,
                        timer: 1500
                    }).then((result) => {
                        var controller = "ControllerCliente?option=listar";
                        var url = "client.jsp";
                        enviarSolicitudCliente(controller, url);
                    });
                }
            },
            
            error: function(xhr, status, error) {           
            }
    }); 
}

function eraseProveedor(cc){
    event.preventDefault();        
    var servletUrl = path + '/ControllerCliente?option=eliminar';        
    
    var dataerase = "documento="+cc;

    $.ajax({
        type: 'POST',
        url: servletUrl,
        data: dataerase,
        success: function(response) {                
            if(response==="eliminado"){
                $('#modaladd_clientes').modal('hide');
                Swal.fire({
                    width: "40%",
                    position: "center",
                    icon: "success",
                    title: "Eliminación exitosa",
                    showConfirmButton: false,
                    timer: 1500
                }).then((result) => {
                    var controller = "ControllerCliente?option=listar";
                    var url = "client.jsp";
                    enviarSolicitudCliente(controller, url);
                });
            }
        },

        error: function(xhr, status, error) {

        }
    }); 
    
    };