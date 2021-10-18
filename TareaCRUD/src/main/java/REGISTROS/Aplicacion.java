/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGISTROS;
import Utilities.Layout;
import java.util.Scanner;
import java.util.ArrayList;
import DAO.RegistroDB;

/**
 *
 * @author angel
 */
public class Aplicacion {
      private Scanner _EntradaTeclado;
    private ArrayList _MisRegistros;
    private int _MiInventarioIdCounter;
    private RegistroDB _RegModel;
    public Aplicacion(Scanner EntradaTeclado) {
        this._EntradaTeclado = EntradaTeclado;
        this._MisRegistros = new ArrayList<Items>();  
        this._MiInventarioIdCounter = 0;
        this._RegModel = new RegistroDB();
        this._RegModel.tableInitialize();
        this._MisRegistros = this._RegModel.getItems(true);
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
            case "M":
                this.mostrarRegistros();
                break;
            case "N":
                this.ingresarNuevoRegistro();
                break;
            case "A":
                this.actualizarRegistro();
                break;
            case "E":   
                this.eliminarRegistro();
                break;
            case "S":
                break;
            default:
                System.out.println("Opción invalida!!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        Layout.printHeader("Nuevo Registro de Personas");
        Items nuevoItem =  new Items();
        nuevoItem.setNombre(Layout.obtenerValorParaCampo("Nombre", "nombre X", this._EntradaTeclado));
        nuevoItem.setFacultad(Layout.obtenerValorParaCampo("Facultad", "Facultad X", this._EntradaTeclado));
        nuevoItem.setTelefono (Integer.parseInt(Layout.obtenerValorParaCampo("Edad", "0", this._EntradaTeclado)));
        nuevoItem.setEdad (Integer.parseInt(Layout.obtenerValorParaCampo("Telefono", "0", this._EntradaTeclado)));
       
        this._RegModel.insertAplicacionItem(nuevoItem);
        this._MisRegistros = this._RegModel.getItems(true);
        
        Layout.printSeparator();
        System.out.println(this._MisRegistros.size());
    }
    
    private void mostrarRegistros(){
        Layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("ID");
        columnas.add("Nombre");
        columnas.add("Facultad");
        columnas.add("Edad");
        columnas.add("Telefono");
        
        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(7);
        anchos.add(20);
        anchos.add(20);
        anchos.add(14);
        anchos.add(14);
        
        
       
        try {
            //Imprimir encabezado
            Layout.imprimirEnColumna(columnas, anchos, "|");
            Layout.printSeparator();
            for(int i = 0; i< this._MisRegistros.size(); i++){
                columnas = ((Items) this._MisRegistros.get(i)).obtenerCampos();
                Layout.imprimirEnColumna(columnas, anchos, "|");
            }
            
        } catch(Exception ex) {
            System.err.println("Fallo al imprimir " + ex.getMessage());
        }
    }
    
    private void actualizarRegistro(){
        Layout.printHeader("Actualizar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese ID Registro", "0", this._EntradaTeclado));
        Items selectRegistro = null;
        selectRegistro = this._RegModel.getRegItemById(selectedId);
        if (selectRegistro == null ) {
            System.out.println("Su registro solicitado no existe!");
        } else {
            selectRegistro.setNombre(Layout.obtenerValorParaCampo("Nombre", selectRegistro.getNombre(), this._EntradaTeclado));
            selectRegistro.setFacultad(Layout.obtenerValorParaCampo("Nombre de la facultad", selectRegistro.getFacultad(), this._EntradaTeclado));
            selectRegistro.setEdad(Integer.parseInt(Layout.obtenerValorParaCampo("Edad", Integer.toString(selectRegistro.getEdad()), this._EntradaTeclado)));
            selectRegistro.setTelefono(Integer.parseInt(Layout.obtenerValorParaCampo("Telefono", Integer.toString(selectRegistro.getTelefono()), this._EntradaTeclado)));
            
            this._RegModel.updateProdItem(selectRegistro);
            this._MisRegistros = this._RegModel.getItems(true);
            Layout.printSeparator();
            System.out.println("Registro Actualizado Satisfactoriamente!!!");
        }
        
    }
    
    private void eliminarRegistro(){
        Layout.printHeader("Eliminar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Inserte ID de Registro", "0", this._EntradaTeclado));
        Items selectedMusic = this._RegModel.getRegItemById(selectedId);
        if (selectedMusic != null){
            Layout.printSeparator();
            String confirmado = Layout.obtenerValorParaCampo("¿Desea Eliminar este Registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")){
                //this._MisRegistros.remove(encontradoEnIndice);
                this._RegModel.deleteProdItem(selectedMusic);
                 this._MisRegistros = this._RegModel.getItems(true);
                Layout.printSeparator();
                System.out.println("Su registro ha sido eliminado satisfactoriamente!");
            }
        } else {
            System.out.println("Registro solicitado no existe!");
        }
    
    }
    
}
