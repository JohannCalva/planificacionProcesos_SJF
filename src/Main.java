import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        String[] nombre = new String[]{"A", "B", "C", "D", "E"};
        int[] rafagaCPU = new int[]{3, 1, 3, 4, 2};
        int[] tiempoLlegada = new int[]{2, 4, 0, 1, 3};
        int tamanioArreglos = nombre.length;
        int[] tiempoDeInicio = new int[tamanioArreglos];
        int[] tiempoDeRetorno = new int[tamanioArreglos];
        int[] tiempoDeEspera = new int[tamanioArreglos];
        int tiempoTotal = 0;

        ordenarArreglosPorRafagaYTiempoLlegada(nombre, rafagaCPU, tiempoLlegada);

        for(int i = 0; i < tamanioArreglos; i++){

            if(tiempoLlegada[i] > tiempoTotal){
                intercambiarPosicion(nombre, i, i+1);
                intercambiarPosicion(rafagaCPU, i, i+1);
                intercambiarPosicion(tiempoLlegada, i, i+1);
            }

            if(i == 0){
                tiempoDeInicio[i] = tiempoLlegada[i];
            }else{
                tiempoDeInicio[i] = Math.max(tiempoTotal, tiempoLlegada[i]);
            }
            tiempoDeRetorno[i] = tiempoDeInicio[i] + rafagaCPU[i];
            tiempoDeEspera[i] = tiempoDeInicio[i] - tiempoLlegada[i];
            tiempoTotal += rafagaCPU[i];
        }
        
        System.out.print("Orden de los Trabajos segun SJF: ");
        System.out.print("\nNombre: ");
        imprimirArreglo(nombre);
        System.out.print("\nRafaga de CPU:");
        imprimirArreglo(rafagaCPU);
        System.out.print("\n Tiempo de llegada");
        imprimirArreglo(tiempoLlegada);

        System.out.print("\nTiempos de Espera:");
        imprimirArreglo(tiempoDeEspera);
        System.out.print("\nTiempos de Retorno:");
        imprimirArreglo(tiempoDeRetorno);

        System.out.println("\nTiempo medio de espera: " + calcularPromedioArreglo(tiempoDeEspera));
        System.out.println("Tiempo de retorno medio: " + calcularPromedioArreglo(tiempoDeRetorno));

    }

    public static void ordenarArreglosPorRafagaYTiempoLlegada(String[] nombre, int[] rafagaCPU, int[] tiempoLlegada){
        posicionarPrimerProceso(nombre, rafagaCPU, tiempoLlegada);
        for(int i = 1; i < rafagaCPU.length; i++){
            for(int j = 1; j < rafagaCPU.length-1; j++){
                if(rafagaCPU[j]>rafagaCPU[j+1] || (rafagaCPU[j] == rafagaCPU[j+1] && tiempoLlegada[j] > tiempoLlegada[j+1])){
                    intercambiarPosicion(tiempoLlegada, j, j+1);
                    intercambiarPosicion(rafagaCPU, j, j+1);
                    intercambiarPosicion(nombre, j, j+1);
                }
            }
        }
    }

    public static int devuelvePosiconPrimerProceso(int[] tiempoLlegada){
        int posicion = 0;
        for(int i = 0; i < tiempoLlegada.length; i++){
            if(tiempoLlegada[i] < tiempoLlegada[posicion]){
                posicion = i;
            }
        }
        return posicion;
    }

    public static void posicionarPrimerProceso(String[] nombre, int[] rafagaCPU, int[] tiempoLlegada){
        int pos = devuelvePosiconPrimerProceso(tiempoLlegada);
        intercambiarPosicion(nombre, 0, pos);
        intercambiarPosicion(rafagaCPU, 0, pos);
        intercambiarPosicion(tiempoLlegada, 0, pos);
    }

    public static void intercambiarPosicion(int[] arreglo, int i, int j){
        int aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }

    public static void intercambiarPosicion(String[] arreglo, int i, int j){
        String aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }

    public static void imprimirArreglo(int[] arreglo){
        System.out.print("\n[ ");
        for(int i = 0; i < arreglo.length; i++){
            System.out.print(arreglo[i] + " ");
        }
        System.out.print("]");
    }

    public static void imprimirArreglo(String[] arreglo){
        System.out.print("\n[ ");
        for(int i = 0; i < arreglo.length; i++){
            System.out.print(arreglo[i] + " ");
        }
        System.out.print("]");
    }

    public static double calcularPromedioArreglo(int[] arreglo){
        int suma = 0;
        for(int valor : arreglo){
            suma += valor;
        }
        return (double) suma /arreglo.length;
    }

}