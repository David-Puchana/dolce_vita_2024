/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
(() => {
  const forms = document.querySelectorAll('.needs-validation');

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      form.classList.add('was-validated');
    }, false);
  });
})();

var enlaces = document.querySelectorAll(".accion");

enlaces.forEach(function(enlace) {
    enlace.addEventListener("click", function(event) {
        event.preventDefault();

        var controller = this.getAttribute("href");   
        var url = this.getAttribute("data-url");     
        
        if(controller==="#"){
            var xhr = new XMLHttpRequest();
            xhr.open("GET", url, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {                    
                    document.getElementById("view-data").innerHTML = xhr.responseText;
                }
            };
            xhr.send();            
        }else{
            var rutaController = controller.split("?")[0];
            switch (rutaController){                    
                case "ControllerUser": 
                    enviarSolicitudAjax(controller, url);
                    break;
                case "ControllerCliente": 
                    console.log("aqui");
                    enviarSolicitudCliente(controller, url);
                    break;
                case "ControllerProveedor":
                    enviarSolicitudProveedor(controller, url);
                    break;
                default:
                    break;                        
            }    
        }        
    });
});

function enviarSolicitudAjax(controller, url) {   
    var servletUrl = path + '/' + controller;
    $.ajax({
    type: 'GET',
    url: servletUrl,
    success: function(response) {        
            var jsonData = JSON.parse(response);            
            var dataTableData = [];            
            for (var i = 0; i < jsonData.length; i++) {
                var row = [];                
                row.push(jsonData[i].documento);
                row.push(jsonData[i].nombre);
                row.push(jsonData[i].apellido);                
                row.push(jsonData[i].email);
                row.push(jsonData[i].whatsapp);                
                row.push(jsonData[i].rol);
                row.push(jsonData[i].tipoDocumento);
                row.push(jsonData[i].password);                                  
                row.push(jsonData[i].direccion);
                row.push(jsonData[i].telefono);                                                              
                dataTableData.push(row);
            }
            
            cargarContenido(url,dataTableData);            
    },
    error: function(xhr, status, error) {        
    }
    });
    
}

function cargarContenido(url,data) {
    
        var xhr = new XMLHttpRequest();
        xhr.open("GET",url, true);
                     
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {                
                document.getElementById("view-data").innerHTML = xhr.responseText;
                $(document).ready( function () {
                    $('#tablaUsuarios').DataTable({                        
                        language: {
                            search: "Buscar",
                            lengthMenu: 'Mostrar _MENU_  registros por página',
                            info: 'Página _PAGE_ de _PAGES_'
                        },
                        data: data,  
                        rowId: 'documento',
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
                                    var idUsuario = row[0];                                      
                                    var btnActualizar = '<button class="btn btn-secondary btn-actualizar mx-1" onclick="loadata(this)" data-bs-toggle="modal" data-bs-target="#modaladd" id="' + idUsuario + '">Actualizar</button>';
                                    var btnEliminar = '<button class="btn btn-danger btn-eliminar mx-1" onclick="erase(this.id)" id="' + idUsuario + '">Eliminar</button>';                                   
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

function limpiarFormulario() {
    const forms = document.querySelectorAll(".form");
    forms.forEach(form =>{
        form.reset();
    });                
}

function add(){    
    event.preventDefault();        
    var servletUrl = path + '/ControllerUser?option=registrar';     
    
    var form_add = document.getElementById('formuser');
    if (form_add.checkValidity()) {
      var formData = $('#formuser').serialize();
          $.ajax({
            type: 'POST',
            url: servletUrl,
            data: formData,
            success: function(response) {                
                if(response==="add"){
                    $('#modaladd').modal('hide');
                    Swal.fire({
                        width: "40%",
                        position: "center",
                        icon: "success",
                        title: "Registro exitoso",
                        showConfirmButton: false,
                        timer: 1500
                    }).then((result) => {
                        var controller = "ControllerUser?option=listar";
                        var url = "user.jsp";
                        enviarSolicitudAjax(controller, url);
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

let idBtn = "";

function loadata(btn){  
    var divupdate = document.getElementById('divupdate');
    var divadd = document.getElementById('divadd');
    var titulo = document.getElementById('titleModal');    
    idBtn = btn.id;            
    
    titulo.textContent = 'Actualizar Usuario';

    if(divadd.style.display === 'block'){        
        divadd.style.display = 'none';   
    }    
    divupdate.style.display = 'block';   

    var table = $('#tablaUsuarios').DataTable();
    var row = $(btn).closest('tr');
    var rowData = table.row(row).data();    
    
    $("#tipoDocumento").val(rowData[6]);
    $("#documento").val(rowData[0]);
    $("#nombres").val(rowData[1]);
    $("#apellidos").val(rowData[2]);
    $("#email").val(rowData[3]);
    $("#direccion").val(rowData[8]);
    $("#telefono").val(rowData[9]);
    $("#wpp").val(rowData[4]);
    $("#rol").val(rowData[5]);
    $("#password").val(rowData[7]);
        
}  

 function toggleButtons() {                    
    var divadd = document.getElementById('divadd');
    var divupdate = document.getElementById('divupdate');
    var title = document.getElementById('titleModal');    
    title.textContent = 'Registrar Usuario';

    divadd.style.display = 'block';   

    if(divupdate.style.display !== 'none'){
        divupdate.style.display = 'none';   
    }            
} 

function update(){
    
    event.preventDefault();  
    
    var servletUrl = path + '/ControllerUser?option=actualizar';        
    var formData = $('#formuser').serialize();        
    formData+="&documento_1="+idBtn;
       
     $.ajax({
            type: 'POST',
            url: servletUrl,
            data: formData,
            success: function(response) {                
                if(response==="update"){
                    $('#modaladd').modal('hide');
                    Swal.fire({
                        width: "40%",
                        position: "center",
                        icon: "success",
                        title: "Actualizado Correctamente",
                        showConfirmButton: false,
                        timer: 1500
                    }).then((result) => {
                        var controller = "ControllerUser?option=listar";
                        var url = "user.jsp";
                        enviarSolicitudAjax(controller, url);
                    });
                }
            },
            
            error: function(xhr, status, error) {           
            }
    }); 
}

function erase(cc){
    event.preventDefault();        
    var servletUrl = path + '/ControllerUser?option=eliminar';        
    
    var dataerase = "documento="+cc;

    $.ajax({
        type: 'POST',
        url: servletUrl,
        data: dataerase,
        success: function(response) {                
            if(response==="eliminado"){
                $('#modaladd').modal('hide');
                Swal.fire({
                    width: "40%",
                    position: "center",
                    icon: "success",
                    title: "Eliminación exitosa",
                    showConfirmButton: false,
                    timer: 1500
                }).then((result) => {
                    var controller = "ControllerUser?option=listar";
                    var url = "user.jsp";
                    enviarSolicitudAjax(controller, url);
                });
            }
        },

        error: function(xhr, status, error) {

        }
    }); 
    
    };

function  dataConfig(cc){
    event.preventDefault();        
    var servletUrl = path + '/ControllerUser?option=configuracion';        
    var datos = {documento:cc};
         
    $.ajax({
    type: 'POST', 
    url: servletUrl,
    data: datos,    
    success: function(response) {         
        var jsonData = JSON.parse(response);                  
        $("#tipoDocumento-admin").val(jsonData[0].tipoDocumento);
        $("#documento-admin").val(jsonData[0].documento);
        $("#nombres-admin").val(jsonData[0].nombre);
        $("#apellidos-admin").val(jsonData[0].apellido);
        $("#email-admin").val(jsonData[0].email);
        $("#direccion-admin").val(jsonData[0].direccion);
        $("#telefono-admin").val(jsonData[0].telefono);
        $("#wpp-admin").val(jsonData[0].whatsapp);
        $("#rol-admin").val(jsonData[0].rol);
        $("#password-admin").val(jsonData[0].password);
        
    },
    error: function(xhr, status, error) { 
        console.error('Error en la solicitud:', error);
    }
    });
}

$(document).ready(function() {

    $('#update_admin').click(function() {
        event.preventDefault();  
        var servletUrl = path + '/ControllerUser?option=actualizar'; 
        var documento = $(this).val();
        var formData = $('#formuser_admin').serialize();    
        formData+="&documento_1="+documento;
        
        var data = formData.replace(/-admin/g, '');
                          
         $.ajax({
            type: 'POST',
            url: servletUrl,
            data: data,
            success: function(response) {
                $('#modaladd_admin').modal('hide');                              
                if(response==="update"){                    
                    Swal.fire({
                        width: "40%",
                        position: "center",
                        icon: "success",
                        title: "Actualizado Correctamente",
                        showConfirmButton: false,
                        timer: 1500
                    }).then((result) => {
                        var controller = "ControllerUser?option=listar";
                        var url = "user.jsp";
                        enviarSolicitudAjax(controller, url);
                    });
                }
            },
            
            error: function(xhr, status, error) {           
            }
         }); 

    });
});  



