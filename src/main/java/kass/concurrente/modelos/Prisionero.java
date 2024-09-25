package kass.concurrente.modelos;

import kass.concurrente.constantes.Constante;

/**
 * Clase que modela un prisioner
 * @version 1.0
 * @author Laura Itzel Rodriguez Dimayuga 
 */
public class Prisionero {
    protected Integer id;
    protected Boolean esVocero;
    protected int marcado; // Cuantas veces ha pasado
    protected Boolean paso; // Si ya paso

    /**
     * Metodo constructor para generar un prisionero
     * @param id El identificador del prisionero
     * @param esVocero true si es Vocero false en otro caso
     * @param marcado true si ya paso
     */
    public Prisionero(Integer id, Boolean esVocero, int marcado){
        this.id = id; 
        this.esVocero = esVocero;
        this.marcado = marcado; 
        paso = false;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Boolean getEsVocero(){
        return esVocero;
    }

    public void setEsVocero(Boolean esVocero){
        this.esVocero = esVocero;
    }

    public int getMarcado(){
        return marcado;
    }

    public void setMarcado(int marcado){
        this.marcado = marcado;
    }

    public Boolean getPaso(){
        return paso;
    }

    public void setPaso(Boolean paso){
        if(marcado == 2){
            this.paso = true;
        }else{
            this.paso = paso;
        }
    }

    public String toString(){
        if(Boolean.FALSE.equals(esVocero)){
            if(marcado == 2 && !paso){
                return Constante.AZUL + "El prisionero " + id  + " ya apago 2 veces" + Constante.RESET;
            }
            return Constante.AZUL + "El prisionero " + id + " entro en la habitacion" + Constante.RESET;
    
        }else{
            return Constante.ROJO + "El vocero ha pasado " + marcado + " veces" + Constante.RESET;
        }
    }
}
