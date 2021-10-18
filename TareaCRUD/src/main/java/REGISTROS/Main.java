/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGISTROS;
import Utilities.Layout;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Main {
    public static void main (String[] args) {
       
        Layout.printHeader("Registro");
        String OpcionMenu = "";
        
        Scanner entradaTeclado = new Scanner(System.in);
        
        Aplicacion RegApp = new Aplicacion(entradaTeclado);
        
        while (!(OpcionMenu.toUpperCase().equals("S"))) {
            Layout.printMenu();
            OpcionMenu = entradaTeclado.nextLine();

            System.out.println("Su texto ingresado es igual a " + OpcionMenu);
            RegApp.activarEvento(OpcionMenu);
            
        }
    }
    
}
