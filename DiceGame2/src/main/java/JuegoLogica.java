import java.util.ArrayList;
import java.util.List;

public class JuegoLogica {

    private ArrayList<Estacion> estaciones; //representa a las 10 estaciones
    private ArrayList<Persona> personasProcesadas; //personas que ya salieron de la 10ma estacion
    private int rondaActual;

    private List<Integer> historialSalida = new ArrayList<>();
    private List<Integer> historialPersonasEnSistema = new ArrayList<>();
    private List<Double> historialTiempoEnSistema = new ArrayList<>();

    public JuegoLogica(){
        estaciones= new ArrayList<>();
        personasProcesadas= new ArrayList<>();
        rondaActual=1;
        iniciarJuego();
    }

    /*
    se encarga de inicializar los elementos del juego, cada estacion tiene 1 dado y 3 personas
     */
    private void iniciarJuego(){
        for(int i=0; i<10; i++){
            Estacion e= new Estacion(i+1);
            e.agregarDado(new Dado()); //todas las estaciones comienzan con un dado

            if(i<9){ // la ultima estacion no comienza con personas
                for(int j=0; j<4; j++){
                    e.ingresarPersona(new Persona(1));
                }
            }
            estaciones.add(e);
        }
    }

    /*
    se encarga de tirar los dados de las estaciones (boton roll)
     */
    public void tirarDados(){
        for(Estacion e: estaciones){
            e.lanzarDados(); //se define el numero de personas que cada estacion va a enviar
        }
    }

    /*
    de acuerdo al numero de personas que cada estacion tenga que enviar, se realizan los movimientos comenzando desde la estacion 10
     */
    public void moverPersonas() {
        int salidaEnEstaRonda = 0;

        // recorrido inverso de todas las estaciones
        for(int i = 9; i >= 0; i--){
            Estacion e = estaciones.get(i);
            int porProcesar = calcularPersonasAMover(e);
            // registrar entrada para la gráfica de actividad
            e.registrarEntrada(porProcesar);

            // si es la Estación 10 registrar la ronda de salida de la persona (para graficas)
            if(e.getNumEstacion() ==10){
                salidaEnEstaRonda =porProcesar;
            }

            // movimiento de las personas
            for(int j = 0; j < porProcesar; j++){
                if(e.getNumEstacion() == 1){
                    // la primera estacion crea a las personas
                    e.ingresarPersona(new Persona(rondaActual));
                }else{
                    Estacion eAnt = estaciones.get(i - 1);
                    Persona p =eAnt.sacarPersona(); // retirar persona de la estación anterior
                    if (e.getNumEstacion() == 10) {
                        p.setRondaSalida(rondaActual);
                        personasProcesadas.add(p); // conteo de personas que salen del sistema
                    }
                    // ingresar a la estación actual
                    e.ingresarPersona(p);
                }
            }
        }
        rondaActual++;

        // registrar datos para utilizar en graficas
        historialSalida.add(salidaEnEstaRonda);

        // graf 3, cuantas personas hay en sistema
        int enSistema = 0;
        for(Estacion est : estaciones){
            enSistema += est.getPersonasEnCola();
        }
        historialPersonasEnSistema.add(enSistema);

        // graf 4
        double tiempoPromedio= 0.0;
        if(salidaEnEstaRonda > 0){
            tiempoPromedio= (double) enSistema/salidaEnEstaRonda;
        }
        historialTiempoEnSistema.add(tiempoPromedio);
    }

    /*
    metodo auxiliar que determina cual es la capacidad real de la estacion de personas por mover
     */
    private int calcularPersonasAMover(Estacion e){
        int porIngresar=e.getPersonasPorIngresar(); // personas que solicita su dado ingresar

        // primera estacion caso especial, crea nuevas personas
        if(e.getNumEstacion() ==1){
            return porIngresar;
        }

        Estacion eAnterior = estaciones.get(e.getNumEstacion()-2); // e.getNumEstacion()-1 es su índice, -2 es la anterior
        int disponible = eAnterior.getPersonasEnCola();

        if(porIngresar>disponible){
            return disponible; // límite de personas que puede ceder realmente la estación anterior
        }else{
            return porIngresar; // sí hay suficientes personas en la cola anterior
        }
    }

    /*
    metodo que se encarga de mover los dados entre las estaciones, devuelve falso si no tenia dados para mover
     */
    public boolean moverDados(int estacionO, int estacionD){
        Estacion estacionOrigen= estaciones.get(estacionO);
        Estacion estacionDestino= estaciones.get(estacionD);

        if(estacionOrigen.getNumDados()>0){
            Dado d= estacionOrigen.quitarDado();
            estacionDestino.agregarDado(d);
            return true;
        }
        return false;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public ArrayList<Persona> getPersonasProcesadas() {
        return personasProcesadas;
    }

    public ArrayList<Estacion> getEstaciones() {
        return estaciones;
    }

    public List<Integer> getHistorialSalida() {
        return this.historialSalida;
    }

    public List<Integer> getHistorialPersonasEnSistema() {
        return this.historialPersonasEnSistema;
    }

    public List<Double> getHistorialTiempoEnSistema() {
        return this.historialTiempoEnSistema;
    }



}
