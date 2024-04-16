/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author david
 */
public class Proveedor {
    private int idProveedor;    
    private String razonSocial;
    private String nit;
    private String direccion;
    private String telefono;
    private String email;
    private String whatsapp;
    private Character state = '1';
    
    public Proveedor(){};

    public Proveedor(int idProveedor, String razonSocial, String nit, String direccion, String telefono, String email, String whatsapp) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.whatsapp = whatsapp;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }
    
       
    
    
}
