/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
var enlaces = document.querySelectorAll(".accion");

enlaces.forEach(function(enlace) {
    enlace.addEventListener("click", function(event) {
        event.preventDefault();

        var controller = this.getAttribute("href");   
        var url = this.getAttribute("data-url");          
        
        enviarSolicitudAjax(controller, url);
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
                                    var btnEliminar = '<button class="btn btn-danger btn-eliminar mx-1" data-id="' + idUsuario + '">Eliminar</button>';                                   
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
    $('#divadd').css('display', 'block');    
    $('#divupdate').css('display', 'none');    
    var servletUrl = path + '/ControllerUser?option=registrar';        
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
}

function loadata(btn){       
    
    var table = $('#tablaUsuarios').DataTable();
    var row = $(btn).closest('tr');
    var rowData = table.row(row).data();
    
    
    $('#divupdate').css('display', 'block');
    $('#divadd').css('display', 'none');
                      
}
  