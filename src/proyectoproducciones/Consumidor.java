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
 * Clase Consumidor para crear objetos de consumidores que extiende de Thread
 */
public class Consumidor extends Thread{
    
    int numCom;
    Semaphore semaforo;
    Almacen almacen;
    Semaphore semaforo2;
    Semaphore semaforoBin;
    Interfaz2 i2;
    
    /**
     * Constructor de Consumidor
     * @param numeroConsumidor id del consumidor
     * @param semaforo semaforo para trabajar sobre el almacen
     * @param almacen almacen sobre el que actua el consumidor
     * @param semaforo2 semaforo para trabajar sobre el almacen
     * @param semaforoBin semaforo binario para trabajar sobre el almacen
     * @param i interfaz en la que trabajamos
     */
    public Consumidor(int numeroConsumidor,Semaphore semaforo,Almacen almacen,Semaphore semaforo2,Semaphore semaforoBin,Interfaz2 i){
        this.numCom=numeroConsumidor;
        this.semaforo=semaforo;
        this.almacen = almacen;
        this.semaforo2=semaforo2;
        this.semaforoBin=semaforoBin;
        this.i2=i;
    }
    
    /**
     * Metodo run para ejecutar el Thread donde consumimos
     */
    public void run(){
        while(true){
            try {
                sleep(1000);
                semaforo.acquire();
                semaforoBin.acquire();
                consumir();
                semaforoBin.release();
                semaforo2.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metodo en el que consumimos y por lo tanto vaciamos un espacio del almacen
     * @throws InterruptedException 
     */
    private void consumir() throws InterruptedException {
        almacen.sacar();
        String log = i2.getjTextArea2().getText();
        log = "El consumidor " + numCom+ " ha consumido.\n" + log;
        i2.getjTextArea2().setText(log);
        i2.getLabelHuecos().setText(String.valueOf(almacen.resto()));
    }
}
