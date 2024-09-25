package kass.concurrente.modelos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HabitacionTest {
    Habitacion h;
    Prisionero p;
    Vocero v;

    @BeforeEach
    void setUp(){
        h = new Habitacion();
        p = new Prisionero(0,false,0);
        v = new Vocero(1,true,0);

        h.setPrendido(false);
    }

    @Test
    void switchTest1(){
        try {
            h.setPrendido(false);
            h.entraHabitacion(p);
        } catch (InterruptedException e) {
            System.err.println("Error en el hilo" + e.getMessage());
        }
        assertTrue(h.getPrendido());
    }

    @Test
    void switchTest2(){
        h.setPrendido(true);
        h.entraHabitacion(v);
        assertFalse(h.getPrendido());
    }

    @Test
    void marcado(){
        h.setPrendido(false);
        h.entraHabitacion(v);
        assertEquals(1,v.getMarcado());
    }

    @Test
    void simSimple(){
        try {
            h.setPrendido(false);
            h.entraHabitacion(p);
        } catch (InterruptedException e) {
            System.err.println("Error en el hilo" + e.getMessage());
        }
        
        h.entraHabitacion(v);

        assertEquals(1,v.getContador());
    }

    @Test
    void testMultiplePrisioneros() {
        Prisionero p0 = new Prisionero(0,true,0);
        Prisionero p1 = new Prisionero(1,false,0);
        Prisionero p2 = new Prisionero(2,false,0);

        h.setPrendido(false);
    
        try {
            h.entraHabitacion(p1); 
            h.entraHabitacion(p0); // Contador de vocero = 1
            h.entraHabitacion(p2);
            h.entraHabitacion(p0); // Contador de vocero = 2
            h.entraHabitacion(p1);
            h.entraHabitacion(p0); // Contador de vocero = 3
            h.entraHabitacion(p2);
            h.entraHabitacion(p0); // Contador de vocero = 4
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            assertFalse(h.entraHabitacion(p1));
            assertTrue(h.entraHabitacion(p0));
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
