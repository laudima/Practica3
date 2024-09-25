package kass.concurrente.modelos;

import kass.concurrente.constantes.Constante;

/**
 * Clase que fungira como habitacion
 * Se sabe que existe un interruptor que nos dice
 * si el foco esta prendido o no
 * Se desconoce el estado inicial del foco (Usar un random, para que sea
 * pseudoaleatorio el estado inicial)
 * @author Laura Itzel Rodriguez Dimayuga 
 * @version 1.0
 */
public class Habitacion {
    private volatile boolean prendido;//Variable compartida
    private Vocero vocero;
    /**
     * Metodo Constructor
     * Aqui se define el como estara el foco inicialmente
     */
    public Habitacion(){
        // El boton de la luz empieza prendido o no de manera aleatoria
        prendido = Math.random() < 0.5;
        vocero = new Vocero(0, true, 0);
    }

    /**
     * Metodo que permite al prisionero entrar a la habitacion
     * Recordemos que solo uno pasa a la vez, esta es la SECCION CRITICA
     * En este caso se controla desde fuera
     * Es similar al algoritmo que progonan y similar al de su tarea
     * El prisionero espera una cantidad finita de tiempo
     * @param prisionero El prisionero que viene entrando
     * @return false si ya pasaron todos, true en otro caso
     * @throws InterruptedException Si falla algun hilo
     */
    public Boolean entraHabitacion(Prisionero prisionero) throws InterruptedException{
        if(!prendido && prisionero.getMarcado() < 2 && !prisionero.getEsVocero()){
            prendido = true;
            prisionero.setMarcado(prisionero.getMarcado() + 1);
            System.out.println(Constante.VERDE +"El prisionero " + prisionero.getId() + " ha prendido el interruptor" + Constante.RESET);
        }
        if(prisionero.getEsVocero()){
            prisionero.setMarcado(prisionero.getMarcado() + 1);
            vocero.setMarcado(vocero.getMarcado() + 1);
            return entraHabitacion(vocero);
        }
        System.out.println(prisionero);
        prisionero.setPaso(false);
        return false; 
    }

    public Boolean entraHabitacion(Vocero vocero){
        Boolean yaPasaron = false;
        if(prendido){
            prendido = false;
            vocero.incrementaContador();
        }
        if(vocero.getContador() >= 2*Constante.PRISIONEROS - 2){
            yaPasaron = true;
        }
        System.out.println(vocero);
        return yaPasaron;
    }

    public void setPrendido(boolean prendido){
        this.prendido = prendido;
    }

    public boolean getPrendido(){
        return prendido;
    }

    public Vocero getVocero(){
        return vocero;
    }
}
