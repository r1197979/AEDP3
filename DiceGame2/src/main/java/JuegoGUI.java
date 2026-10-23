import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JuegoGUI extends Application {

    private JuegoLogica juego;
    private MenuVista menuVista;
    private VistaTablero vistaTablero;

    @Override
    public void start(Stage primaryStage) {
        juego = new JuegoLogica();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        // fondo de pantalla
        root.setStyle(
                "-fx-background-image: url('file:fondo.png'); " + "-fx-background-size: cover; " +
                        "-fx-background-position: center center; " + "-fx-background-repeat: no-repeat;");

        // instanciar componentes
        menuVista = new MenuVista(this);
        root.setLeft(menuVista);
        vistaTablero = new VistaTablero();
        root.setCenter(vistaTablero);
        actualizarPantalla();

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Dice Game 2");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void ejecutarTirar() {
        if (menuVista.isFin()) {
            return;
        }
        juego.tirarDados();
        actualizarPantalla();
    }

    public void ejecutarMover() {
        if (menuVista.isFin()) {
            return;
        }
        juego.moverPersonas();
        actualizarPantalla();
    }

    public void ejecutarMoverDado(int estacionO, int estacionD) {
        if (estacionO >= 0 && estacionD >= 0) {
            juego.moverDados(estacionO, estacionD);
            actualizarPantalla();
        }
    }

    public void actualizarPantalla() {
        menuVista.actualizarRonda(juego.getRondaActual());
        vistaTablero.actualizar(juego.getEstaciones());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public JuegoLogica getJuego(){return juego;}
}