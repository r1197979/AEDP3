public class Persona {
    private int rondaEntrada;
    private int rondaSalida;

    public Persona(int entrada){
        rondaEntrada=entrada;
        rondaSalida=0;
    }
    public int getRondaEntrada(){return rondaEntrada;}
    public int getRondaSalida(){return rondaSalida;}
    public void setRondaEntrada(int ronda){rondaEntrada=ronda;}
    public void setRondaSalida(int ronda){rondaSalida=ronda;}
}
