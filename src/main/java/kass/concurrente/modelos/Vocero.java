package kass.concurrente.modelos;

import kass.concurrente.constantes.Constante;

/**
 * Este ess quien lleva la cuenta de los prisioneros que han entrado a la habitacion
 * a parte de los atributos de Prisionero, tambien posee un contador
 * @author <Su equipo>
 * @version 1.0
 */
public class Vocero extends Prisionero{
    protected Integer contador;

    /**
     * Metodo constructor 
     * @param id
     * @param esVocero
     * @param marcado
     */
    public Vocero(Integer id, Boolean esVocero, int marcado) {
        super(id, esVocero, marcado);
        contador = 0;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public void incrementaContador(){
        contador++;
    }
    @Override
    public String toString(){
        return Constante.ROJO + "El prisionero " + id + " ha apagado el interruptor " + contador + " veces" + Constante.RESET;
    }
}

