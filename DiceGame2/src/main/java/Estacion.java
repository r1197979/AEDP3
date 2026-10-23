import java.util.ArrayList;
import java.util.List;

public class Estacion {
    private int numEstacion;
    private ColaSimple<Persona> cola;
    private ArrayList<Dado> dados;
    private int personasPorIngresar; // personas por ingresar segun la tirada de dados
    private List<Integer> historialEntradas = new ArrayList<>(); //guarda cuantas personas entran por turno, grafica 1

    public Estacion(int num) {
        cola = new ColaSimple<>(150);
        dados = new ArrayList<>();
        numEstacion = num;
        personasPorIngresar = 0;
    }

    /*
    metodo para ingresar personas a su cola
    */
    public void ingresarPersona(Persona p){
        cola.insertar(p);
    }

    /*
    metodo para eliminar a la primera persona de la cola y la devuelve
    */
    public Persona sacarPersona() {
        return cola.eliminar();
    }

    public int getPersonasEnCola() {
        return cola.getSize();
    }

    /*
    lanza los dados que tenga disponibles
    */
    public int lanzarDados(){
        int resultado = 0;
        for(Dado d : dados){
            resultado += d.lanzar();
        }
        this.personasPorIngresar = resultado;
        return resultado;
    }

    public void agregarDado(Dado dado) {dados.add(dado);}

    public Dado quitarDado(){
        if(dados == null){
            return null;
        }else{
            return dados.remove(dados.size()-1);
        }
    }

    /*
    metodo para registrar la cantidad de personas que ingresan en la ronda actual
    */
    public void registrarEntrada(int cantidad) {
        historialEntradas.add(cantidad);
    }

    public List<Integer> getHistorialEntradas() {
        return historialEntradas;
    }

    public int getNumDados() {
        return dados.size();
    }

    public int getNumEstacion() {
        return numEstacion;
    }

    public ColaSimple<Persona> getCola() {
        return cola;
    }

    public int getPersonasPorIngresar() {
        return personasPorIngresar;
    }
}