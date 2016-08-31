package lib;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase de metodos utilitarios que involucran operaciones sobre los arreglos,
 * las operaciones de estos metodos pueden no tener objetivos relacionados pero
 * siempre involucran arreglos como datos de entrada a manejar.
 *
 * @author Ing. Manuel Lara (lararojas.mr@gmail.com)
 * @date 24/08/2016
 */
public class ArrayUtil {

    /**
     * Metodo que a partir de un arreglo unidimensional con una serie de valores
     * enteros con posibles repetidos, regresa un arreglo unidimensional con
     * valores no repetidos que indican de manera descendente, los valores con
     * mas probabilidades de salir si se pusiera a escojer a un usuario al azar.
     *
     * Es decir si tenemos, por ejemplo:
     *
     * [1,2,6,8,1,1,2,6] como arreglo origen.
     *
     * al pasar nuestro arreglo a la funcion, este devolveria:
     *
     * [1,2,6,8]
     *
     * Nota: Los valores con probabilidades repetidas se mantienen juntos, es
     * decir, se agrupan en orden en el que son procesados.
     *
     * @param origen vector oigen de los datos a procesar
     * @return vector de numeros en orden descendente tomando como critero la
     * probabilidad de aparecer al ser seleccionado algun item del vector
     * original.
     */
    public Integer[] getProbabilities(int[] origen) {

        //validamos que el vector cumpla con los requisios minimos
        if (origen == null) {
            //si no se cumplen se emite un mensaje de error indicando formato incorrecto
            throw new NumberFormatException();
        }
        /**
         * la logica del algoritmo comienza aqui, instanciamos un HashMap, el
         * cual es mas eficiente en general y nos permite agregar un par
         * <key, value> donde en uno se guardara el numero y en el otro la
         * cantidad de veces que se repitio.
         *
         * Mediante esto, no tendremos que hacer sino un solo recorrido por el
         * arreglo, lo cual nos favorece en tiempo de ejecucion y una
         * complejidad O(h).
         *
         * Luego creamos una lista igual, pero en vez de HashMap, creamos una
         * que garantice el ordenamiento (TreeMap), instanciamos una
         * implementacion perzonalizada de un comparador, el cual sera la regla
         * para el ordenamiento (@see NumberProbabilityComparator).
         *
         * Con esta ultima accion, al solo agregar los items del primer map los
         * ordenara, y entonces solo tendremos que recuperar los keys, que ya
         * estaran ordenados segun la probabilidad, cumpliendo con el objetivo.
         *
         */
        //Creamos el mapa donde se agregaran en primera instancia los elementos
        Map<Integer, Integer> numerosContados = new HashMap<>();

        //este for, lo que hace es crear un indice en el mapa y como valor
        //agregar la cantidad de veces que se repite en origen. 
        //Solo hace un recorrido
        for (int index = 0; index < origen.length; index++) {

            if (numerosContados.putIfAbsent(origen[index], 1) != null) {
                //en caso de existir ya, solo se actualiza el numero de ocurrencias
                numerosContados.replace(origen[index], numerosContados.get(origen[index]) + 1);
            }
        }
        //se instancia el comparador que se utilizara para el ordenamiento al insertar
        NumberProbabilityComparator comparador = new NumberProbabilityComparator(numerosContados);
        //creamos nuestra lista ordenada y le asignamos nuestro comparador.
        Map<Integer, Integer> numerosFiltrados = new TreeMap<>(comparador);
        //luego agregamos todos los elementos del HashMap. Se orden automaticamente
        numerosFiltrados.putAll(numerosContados);
        //como no nos interesa sino los elementos ordenados por la probabilidad, 
        //de aparecer, entonces solo necesitamos las claves del MapTree.
        return numerosFiltrados.keySet().toArray(new Integer[0]);
    }

    /**
     * Este metodo representa una solucion para detectar las islas segun el
     * problema 3780.How Many Islands? descrito aqui:
     * http://acm.tju.edu.cn/toj/showp3780.html.
     *
     * Porpuesta de solucion. Este problema representa un grafo, que puede ser
     * conexo o no, por lo que aplicare algunos conocimientos en lo referente a
     * matriz de adyacencia, para calcular o detectar las islas o caminos de
     * cada ejercicio, reduciendo el numero de evaluaciones al minimo.
     *
     * Se procesara cada camino evaluando las adyacencias de cada segmento
     * identificado como isla, para cada elemento visitado, cambiaremos los
     * items a -1, de esta manera evitaremos la reverificacion de segmentos de
     * isla ya explorados.
     *
     * Es un algoritmo sencillo que permite resolver el problema, en los tiempos
     * indicados en la pagina, y que permiten obtener el numero de islas en el
     * area establecida por @var origen
     *
     * @param origen area de exploracion.
     * @param land_flag indica el numero que representa segmento de isla.
     * @return int que representa el numeros de islas encontradas.
     */
    public int getNumberOfIslands(int[][] origen, int land_flag) {

        int n_islas = 0; //indicara el numero de islas por area explorada
        int[][] area = origen; //area a explorar

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (area[i][j] == land_flag) {
                    n_islas++;
                    area = this.captureIsland(area, i, j, land_flag);
                }
            }
        }

        return n_islas;
    }

    /**
     * Este metodo sobrecargado esta diseñado para evitar establecer los valores
     * especificados por el problema en el caso de lo que representa una isla y
     * lo que representa el mar. La version generica esta desarrollada arriba.
     *
     * @param origen mapa a explorar
     * @return int que representa el numeros de islas encontradas.
     */
    public int getNumberOfIslands(int[][] origen) {
        return this.getNumberOfIslands(origen, 1);
    }

    /**
     * Este metodo es el que se encarga de explorar toda la isla, esta
     * influenciado por algoritmo de busquedas de grafos pero mas simple lo he
     * desarrallado de la idea 'isla pisada isla explorada', lo cual acelera el
     * algoritmo, al no tener que explorar mar, y si una isla ya fue explorada
     * anteriormente.
     *
     * Como trabaja, bueno, al determinarse una isla (una segmento, celda), se
     * envian a este algoritmo especificando el area y las coordenadas del
     * segmento encontrado, luego, aqui se establece un perimetro de inspeccion,
     * un cuadro de distancia y se explora todo el perimetro buscando mas
     * segmentos. i se consigue uno, el algoritmo reacciona recursivamente
     * llamandose al mismo y enviando las nuevas coordenadas, de esta forma
     * explorando toda esa parte de la isla, para luego regresar al inicio y
     * seguir revisando en otras direcciones.
     *
     * Cuando termina el algoritmo regresa el area con los puntos explorados.
     *
     * @param origen area a explorar.
     * @param x coordenada en x del segmento de isla sin explorar.
     * @param y coordenada en y del segmento de isla sin explorar.
     * @param land_flag valor que indica que es un segmento de isla.
     * @return area modificada donde se actualizan las islas exploradas.
     */
    private int[][] captureIsland(int[][] origen, int x, int y, int land_flag) {

        int[][] area = origen;
        //primero es establecer el segmento incial como isla explorada.
        area[x][y] = -1;
        //establecemos el perimetro
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                /**
                 * Exploramos dentro del perimetro considerando que posiblemente
                 * el perimetro pueda estar fuera de las fronteras del area a 
                 * explorar por lo que en caso dado, omitimos estas areas.
                 */
                if (i >= 0 && j >= 0 && i < area.length && j < area[i].length && area[i][j] == land_flag) {
                    //si encontramos un segmento de la isla se explora todo.
                    area = captureIsland(area, i, j, land_flag);
                }
            }
        }

        return area;

    }

}
