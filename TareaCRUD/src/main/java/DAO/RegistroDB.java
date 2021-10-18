/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import REGISTROS.Items;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author angel
 */
public class RegistroDB {
     private ArrayList _RegItems;
    
    public RegistroDB(){

        this._RegItems = new ArrayList<Items>();
    }
    
   
    public ArrayList<Items> getItems(){
        return this.getinvItems(false);
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS REGISTROS"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " NOMBRE TEXT NOT NULL,"
                        + " FACULTAD TEXT NOT NULL,"
                        + " EDAD INT NOT NULL,"
                        + " TELEFONO INT NOT NULL"
                        + ")";
       
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Items> getItems(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =  Conexion.getConexion().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from REGISTROS;");
                this._RegItems.clear();
                while (misRegistro.next()) {
                    Items registro = new Items();
                    registro.setId(misRegistro.getInt("ID"));
                    registro.setNombre(misRegistro.getString("NOMBRE"));
                    registro.setFacultad(misRegistro.getString("FACULTAD"));
                    registro.setTelefono(misRegistro.getInt("EDAD"));
                    registro.setEdad(misRegistro.getInt("TELEFONO"));
                    this._RegItems.add(registro);
                }
                comando.close();
           }
           return this._RegItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._RegItems;
        }   
    }
    
    public Items getRegItemById(int id){
        try {
            String SQLGetByID = "SELECT * FROM REGISTROS WHERE ID = ?;";
            PreparedStatement comando =  Conexion.getConexion().prepareStatement(SQLGetByID);
            comando.setInt(1, id);
            ResultSet misRegistro = comando.executeQuery();
            Items registro = new Items();
            while (misRegistro.next()) {
                registro.setId(misRegistro.getInt("ID"));
                registro.setNombre(misRegistro.getString("NOMBRE"));
                registro.setFacultad(misRegistro.getString("FACULTAD"));
                registro.setTelefono(misRegistro.getInt("EDAD"));
                registro.setTelefono(misRegistro.getInt("TELEFONO"));
                break;
            }
            comando.close();

            return registro;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }
    
    public int updateProdItem(Items ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE REGISTROS set NOMBRE=?, FACULTAD=?, EDAD=?, TELEFONO=? where ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);
            
            comando.setString(1, ItemToUpdate.getNombre());
            comando.setString(2, ItemToUpdate.getFacultad());
            comando.setInt(3, ItemToUpdate.getTelefono());
            comando.setInt(4, ItemToUpdate.getEdad());
            comando.setInt(5, ItemToUpdate.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertAplicacionItem(Items ItemToInsert) {
        try {
            String SQLInsert = "INSERT INTO REGISTROS (NOMBRE, FACULTAD, EDAD, TELEFONO) values (?, ?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);
            
            comando.setString(1, ItemToInsert.getNombre());
            comando.setString(2, ItemToInsert.getFacultad());
            comando.setInt(3, ItemToInsert.getTelefono());
            comando.setInt(4, ItemToInsert.getEdad());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     
    public int deleteProdItem(Items ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM REGISTROS WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);
            
            comando.setInt(1, ItemToDelete.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    private ArrayList<Items> getinvItems(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
