/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoproducciones;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Guillermo Veintemilla 
 * Clase Almacen donde se implementan metodos para trabajar sobre el almacen.
 */
public class Almacen {

    private int[] tamanio;
    private int tamanioMaximo;
    Interfaz2 i2;

    /**
     * Constructor del Almacen
     *
     * @param tamanioMaximo tamaño del almacen
     * @param i interfaz sobre la que trabajamos
     */
    public Almacen(int tamanioMaximo, Interfaz2 i) {
        tamanio = new int[tamanioMaximo];
        this.tamanioMaximo = tamanioMaximo;
        this.i2 = i;
    }

    /**
     * Metodo para ocupar espacio en el almacen
     *
     * @param numProd productor que ocupa un espacio
     */
    public void meter(int numProd) {
        for (int i = 0; i < tamanio.length; i++) {
            if (tamanio[i] == 0) {
                tamanio[i] = (int) (Math.random() * 9 + 1) + numProd * 1000;
                ImageIcon icono = new ImageIcon("rojo.png");
                i2.elegirLabel(i).setIcon(icono);
                break;
                //System.out.println("numero: "+tamanio[i]);
            }
        }
    }

    /**
     * Metodo para vaciar espacio en el almacen
     */
    public void sacar() {
        for (int i = 0; i < tamanio.length; i++) {
            if (tamanio[i] != 0) {
                //System.out.println("numero: "+tamanio[i]+" =0");
                tamanio[i] = 0;
                ImageIcon icono = new ImageIcon("verde.png");
                i2.elegirLabel(i).setIcon(icono);
                break;
            }
        }
    }

    /**
     * Metodo que nos devuelve el tamaño del almacen
     *
     * @return tamanio
     */
    public int tamanio() {
        return tamanio.length;
    }

    /**
     * Metodo que calcula los huecos vacios en el Almacen
     *
     * @return huecos vacios
     */
    public int resto() {
        int contador = 0;
        for (int i = 0; i < tamanio.length; i++) {
            if (tamanio[i] == 0) {
                contador++;
            }
        }

        return contador;
    }
}
