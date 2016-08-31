package lib;

import java.util.Comparator;
import java.util.Map;

/**
 * Clase que implemnta un comparador, el cual sera el que se usara para 
 * lograr un ordenamiento descendente de los elementos de un Map ordenado.
 * @author larar
 */
public final class NumberProbabilityComparator implements Comparator<Object>{

    private final Map<Integer, Integer> origen;
    
    public NumberProbabilityComparator(Map<Integer, Integer> origen) {
        this.origen = origen;
    }
    @Override
    public int compare(Object primerNumero, Object segundoNumero) {
        /**
         * Aqui solo cambiamos el valor devuelto de la comparacion al ser igual
         * de cero a uno, con esto logramos el objetivo, y no tenemos que 
         * reimplementar el metodo compareTo.
        */
        return (origen.get(segundoNumero).compareTo(origen.get(primerNumero)) == 0)
                ? 1
                : origen.get(segundoNumero).compareTo(origen.get(primerNumero));
    }

}