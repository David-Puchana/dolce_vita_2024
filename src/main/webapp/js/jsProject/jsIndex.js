/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function limpiarFormulario() {
    const forms = document.querySelectorAll(".form");
    forms.forEach(form =>{
        form.reset();
    });                
}

$(document).ready(function() {
  $('#formservant').submit(function(event) {
    event.preventDefault();
    var formData = {
      email: $('#user').val(),
      password: $('#password').val(),
      rol: $('#selectrole').val(),     
      option: 'login'
    };      
    $.ajax({
      type: 'POST',
      url: 'ControllerUser',
      data: formData,
      success: function(response) {  
        console.log("Respuesta del servidor:", response);    
        var parts = response.split(":");
        var validation = parts[0];
        var msg = parts[1];
       
        if(validation === "login"){
            Swal.fire({
              width: "40%",
              position: "center",
              icon: "success",
              title: "Bienvenido " + msg,
              showConfirmButton: false,
              timer: 1500
            }).then((result) => {
                if (result.dismiss === Swal.DismissReason.timer) {
                    window.location.href = "view/admin/viewAdmin.jsp";                                        
                }
               });                                 
        } else {
            Swal.fire({
              icon: "error",
              title: "Oops...",
              text: msg,
              showConfirmButton: false,
              timer: 1500
            });            
        }
        
      },
      error: function(xhr, status, error) {        
        console.error('Error al enviar datos al servlet:', error);
      }
    });
  });
});
