/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoproducciones;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillermo Veintemilla
 * Clase Productor para crear objetos de productores que extiende de Thread
 */
public class Productor extends Thread {

    Almacen almacen;
    int numProd;
    Semaphore semaforo;
    Semaphore semaforo2;
    Semaphore semaforoBin;
    Interfaz2 i2;

    /**
     * Constructor de Productor
     * @param numeroProductor id del productor
     * @param semaforo semaforo para trabajar sobre el almacen
     * @param almacen almacen sobre el que actua el productor
     * @param semaforo2 semaforo para trabajar sobre el almacen
     * @param semaforoBin semaforo binario para trabajar sobre el almacen
     * @param i interfaz en la que trabajamos
     */
    public Productor(int numeroProductor, Semaphore semaforo, Almacen almacen,Semaphore semaforo2,Semaphore semaforoBin,Interfaz2 i) {
        this.numProd = numeroProductor;
        this.semaforo = semaforo;
        this.almacen = almacen;
        this.semaforo2=semaforo2;
        this.semaforoBin=semaforoBin;
        this.i2=i;
    }

    /**
     * Metodo run para ejecutar el Thread donde producimos
     */
    public void run() {
        while(true){
            try {
                sleep(1000);
                semaforo2.acquire();
                semaforoBin.acquire();
                producir();
                semaforoBin.release();
                semaforo.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metodo en el que producimos y por lo tanto ocupamos un espacio del almacen
     * @throws InterruptedException 
     */
    private void producir() throws InterruptedException {
        
        almacen.meter(numProd);
        String log = i2.getjTextArea1().getText();
        log = "El productor " + numProd + " ha producido.\n" + log;
        i2.getjTextArea1().setText(log);
        i2.getLabelHuecos().setText(String.valueOf(almacen.resto()));
    }

}
