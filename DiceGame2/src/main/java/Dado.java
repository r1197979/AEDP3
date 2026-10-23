public class Dado {

    private int valor;

    public Dado(){
        valor= lanzar();
    }

    public int lanzar(){
        valor= (int) (Math.random()*6 + 1);
        return valor;
    }

    public int getValor() {
        return valor;
    }
}
