/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGISTROS;
import java.util.ArrayList;

/**
 *
 * @author angel
 */
public class Items {
    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _producto
     */
    public String getNombre() {
        return _Nombre;
    }

    /**
     * @param _Nombre the _Nombre to set
     */
    public void setNombre(String _Nombre) {
        this._Nombre = _Nombre;
    }

    /**
     * @return the _Facultad
     */
    public String getFacultad() {
        return _Facultad;
    }

    /**
     * @param _Facultad the _Facultad to set
     */
    public void setFacultad(String _Facultad) {
        this._Facultad = _Facultad;
    }

    /**
     * @return the _Edad
     */
    public int getEdad() {
        return _Edad;
    }

    /**
     * @param _Edad the _Edad to set
     */
    public void setEdad(int _Edad) {
        this._Edad = _Edad;
    }

    /**
     * @return the _Telefono
     */
    public int getTelefono() {
        return _Telefono;
    }

    /**
     * @param _Telefono the _Telefono to set
     */
    public void setTelefono(int _Telefono) {
        this._Telefono = _Telefono;
    }
    private int _id;
    private String _Nombre;
    private String _Facultad;
    private int _Edad;
    private int _Telefono;
    
    public Items(){
        this._id = 0;
        this._Nombre = "";
        this._Facultad = "";
        this._Telefono = 0;
        this._Edad =  0;
    }
    
    public Items(int id, String producto, String proveedor, int cantidad, int precio) {
        this._id = id;
        this._Nombre = producto;
        this._Facultad = proveedor;
        this._Telefono = cantidad;
        this._Edad =  precio;
    }
    
    public ArrayList<String> obtenerCampos(){
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(String.valueOf(this._id));
        campos.add(this._Nombre);
        campos.add(this._Facultad);
        campos.add(String.valueOf(this._Telefono));
        campos.add(String.valueOf(this._Edad));
        
        return campos;
        
    }
    // setters y getters 
    
}
