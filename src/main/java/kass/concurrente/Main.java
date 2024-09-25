package kass.concurrente;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import java.util.Random;

import kass.concurrente.constantes.Constante;
import kass.concurrente.modelos.Habitacion;
import kass.concurrente.modelos.Prisionero;
import kass.concurrente.modelos.Vocero;

import static kass.concurrente.constantes.Constante.LOGS;

/**
 * Clase principal, se ejecuta todo la simulacion
 * Como en el cuento.
 * @author Laura Itzel Rodriguez Dimayuga 
 * @version 1.0
 */
public class Main implements Runnable {

    Lock lock;
    static Habitacion hab = new Habitacion();
    Prisionero[] p = new Prisionero[Constante.PRISIONEROS];
    static Boolean yaPasaron;
    
    public Main(){
        lock = new ReentrantLock(true); // true para que sea justo
        yaPasaron = false;
        //Agregar lo que haga falta para que funcione
    }

    /*
     * INSTRUCCIONES:
     * 1.- Ya genere el lock, es un reentrantLock, investiguen que hace
     * 2.- Tenenemos que tener un lugar el donde se albergaran los prisioneros
     * 3.- Tenemos que tener un lugar donde se albergan los Hilos
     * 4.- Se nececita un objeto de tipo Habitacion para que sea visitada
     * 5.- Aqui controlaremos el acceso a la habitacion, aunque por defecto tenia exclusion mutua
     * aqui hay que especificar el como se controlara
     * 6.- Hay que estar ITERANDO constantemente para que todos los prisiones puedan ir ingresando
     */
    @Override
    public void run() {
        int id = Integer.parseInt(Thread.currentThread().getName()); 
        lock.lock();
        try {
            yaPasaron = hab.entraHabitacion(p[id]);
        } catch (InterruptedException e) {
            System.err.println("Error en el hilo" + e.getMessage());
        }
        lock.unlock();

    }


    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        final Logger LOG = Logger.getLogger("paquete.NombreClase"); // EJEMPLO LOGGER
        Random random = new Random();
        System.out.println("El boton de la habitacion esta" + (hab.getPrendido() ? " prendido" : " apagado"));

        for (int i = 0; i < Constante.PRISIONEROS; i++) {
            // El primer prisionero es el vocero
            m.p[i] = new Prisionero(i, i == 0, 0);
        }

        // Mientras el vocero no diga que ya pasaron todos escogemos a un prisionero de 
        // manera aleatoria y lo mandamos a la habitacion
        while (!yaPasaron) {
            int prisionero = random.nextInt(Constante.PRISIONEROS);
            Thread t = new Thread(m, Integer.toString(prisionero));
            t.start();
            t.join();
        }
        
        System.out.println("Tenemos un total de " + Constante.PRISIONEROS + " prisioneros");
        System.out.println("Se espera que el contador sea " + (2*Constante.PRISIONEROS - 2));
        System.out.println("El vocero ha pasado " + hab.getVocero().getContador() + " veces");
        for (int i = 1; i < Constante.PRISIONEROS; i++) {
            System.out.println(m.p[i]);
        }
        System.out.println(Constante.VERDE + "Todos los prisioneros han pasado" + Constante.RESET);

        if(LOGS) LOG.info("HOLA SOY UN MENSAJE");
    }

}