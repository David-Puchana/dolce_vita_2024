/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author david
 */
public class Cliente {
    
    private int idUsuario;    
    private String documento;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private String whatsapp;
    private String pass;
    private Character state = '1';

    public Cliente() {
    }

    public Cliente(int idUsuario, String documento, String nombres, String apellidos, String direccion, String telefono, String email, String whatsapp, String pass) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.whatsapp = whatsapp;
        this.pass = pass;
    }
        
    
    public int getIdCliente() {
        return idUsuario;
    }
    
    public void setIdCliente(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }     
    
    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }         
     
}
