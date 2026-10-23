import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MenuVista extends VBox {

    private boolean fin;
    private Label lblRonda;
    private Button btnTirar;
    private Button btnMover;

    private ComboBox<String> cbOrigen;
    private ComboBox<String> cbDestino;
    private Button btnMoverDado;

    private Button btnActividad;
    private Button btnSalida;
    private Button btnPersonasEnSistema;
    private Button btnTiempoEnSistema;

    public MenuVista(JuegoGUI principal) {
        fin=false;
        super(10); // espaciado vertical entre elementos
        this.setPadding(new Insets(15));
        this.setPrefWidth(260);
        this.setMinWidth(260);
        this.setAlignment(Pos.TOP_CENTER);

        this.setStyle("-fx-background-color: rgba(255, 255, 255, 0.55); " + "-fx-background-radius: 20; " +
                "-fx-border-color: rgba(255, 255, 255, 0.8); " + "-fx-border-radius: 20; " + "-fx-border-width: 1.5;");

        String estiloBotonAzulPrincipal = "-fx-background-color: linear-gradient(to bottom, #00aeef, #008cd2); " + "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " + "-fx-font-size: 14px; " + "-fx-background-radius: 22; " + "-fx-cursor: hand;";

        String estiloBotonGrafica = "-fx-background-color: linear-gradient(to bottom, #00aeef, #008cd2); " +
                "-fx-text-fill: white; " + "-fx-font-weight: bold; " + "-fx-font-size: 12px; " + "-fx-background-radius: 18; " + "-fx-cursor: hand;";

        lblRonda = new Label("Ronda: 1 / 20");
        lblRonda.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0369a1;");

        btnTirar = new Button("Tirar dados");
        btnMover = new Button("Mover");

        btnTirar.setStyle(estiloBotonAzulPrincipal);
        btnMover.setStyle(estiloBotonAzulPrincipal);

        btnTirar.setPrefHeight(45);
        btnMover.setPrefHeight(45);
        btnTirar.setMaxWidth(Double.MAX_VALUE);
        btnMover.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(btnTirar, Priority.ALWAYS);
        HBox.setHgrow(btnMover, Priority.ALWAYS);

        setEstadoBotones(true);

        HBox boxAcciones = new HBox(8, btnTirar, btnMover);
        boxAcciones.setAlignment(Pos.CENTER);

        btnTirar.setOnAction(e -> {
            principal.ejecutarTirar();
            setEstadoBotones(false); // Deshabilita Tirar y habilita Mover
        });

        btnMover.setOnAction(e -> {
            principal.ejecutarMover();
            setEstadoBotones(true); // Deshabilita Mover y habilita Tirar
        });

        // 3. Sección "Transferir dados"
        Label lblTransferir = new Label("Transferir dados");
        lblTransferir.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #0f172a; -fx-padding: 4 0 0 0;");

        cbOrigen = new ComboBox<>();
        cbDestino = new ComboBox<>();

        for (int i = 1; i <= 10; i++) {
            cbOrigen.getItems().add("Estación " + i);
            cbDestino.getItems().add("Estación " + i);
        }

        cbOrigen.setPromptText("De:");
        cbDestino.setPromptText("A:");
        cbOrigen.setMaxWidth(Double.MAX_VALUE);
        cbDestino.setMaxWidth(Double.MAX_VALUE);

        HBox rowDe = new HBox(8, new Label(), cbOrigen);
        rowDe.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(cbOrigen, Priority.ALWAYS);

        HBox rowA = new HBox(8, new Label(), cbDestino);
        rowA.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(cbDestino, Priority.ALWAYS);

        btnMoverDado = new Button("Mover Dado");
        btnMoverDado.setStyle(estiloBotonAzulPrincipal);
        btnMoverDado.setPrefHeight(38);
        btnMoverDado.setMaxWidth(Double.MAX_VALUE);

        btnMoverDado.setOnAction(e -> {
            int origen = cbOrigen.getSelectionModel().getSelectedIndex();
            int destino = cbDestino.getSelectionModel().getSelectedIndex();
            principal.ejecutarMoverDado(origen, destino);
        });

        VBox boxTransferir = new VBox(6, lblTransferir, rowDe, rowA, btnMoverDado);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Label lblGraficos = new Label("Gráficos");
        lblGraficos.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #0f172a;");

        btnActividad = new Button("Actividad");
        btnSalida = new Button("Salida");
        btnPersonasEnSistema = new Button("Personas en Sistema");
        btnTiempoEnSistema = new Button("Tiempo en Sistema");

        btnActividad.setStyle(estiloBotonGrafica);
        btnSalida.setStyle(estiloBotonGrafica);
        btnPersonasEnSistema.setStyle(estiloBotonGrafica);
        btnTiempoEnSistema.setStyle(estiloBotonGrafica);

        int alturaGraficas = 38;
        int anchoGraficas = 210;

        btnActividad.setPrefHeight(alturaGraficas);
        btnSalida.setPrefHeight(alturaGraficas);
        btnPersonasEnSistema.setPrefHeight(alturaGraficas);
        btnTiempoEnSistema.setPrefHeight(alturaGraficas);

        btnActividad.setMaxWidth(anchoGraficas);
        btnSalida.setMaxWidth(anchoGraficas);
        btnPersonasEnSistema.setMaxWidth(anchoGraficas);
        btnTiempoEnSistema.setMaxWidth(anchoGraficas);

        btnActividad.setOnAction(e ->
                Grafica.mostrarActividad(principal.getJuego().getEstaciones())
        );

        btnSalida.setOnAction(e ->
                Grafica.mostrarSalida(principal.getJuego().getHistorialSalida())
        );

        btnPersonasEnSistema.setOnAction(e ->
                Grafica.mostrarPersonasEnSistema(principal.getJuego().getHistorialPersonasEnSistema())
        );

        btnTiempoEnSistema.setOnAction(e ->
                Grafica.mostrarTiempoEnSistema(principal.getJuego().getHistorialTiempoEnSistema())
        );

        VBox boxGraficos = new VBox(6, lblGraficos, btnActividad, btnSalida, btnPersonasEnSistema, btnTiempoEnSistema);
        boxGraficos.setAlignment(Pos.CENTER);

        this.getChildren().addAll(
                lblRonda,
                boxAcciones,
                boxTransferir,
                spacer,
                boxGraficos
        );
    }

    // control de estado de botones
    public void setEstadoBotones(boolean puedeTirar) {
        if (fin) {
            btnTirar.setDisable(true);
            btnMover.setDisable(true);
        } else {
            btnTirar.setDisable(!puedeTirar);
            btnMover.setDisable(puedeTirar);
        }
    }

    // actualizar texto de ronda y controlar fin
    public void actualizarRonda(int ronda) {
        if (ronda > 20) {
            fin = true;
            lblRonda.setText("Fin del Juego (20/20)");
            btnTirar.setDisable(true);
            btnMover.setDisable(true);
        } else {
            lblRonda.setText("Ronda: " + ronda + " / 20");
        }
    }

    // getter para consultar desde juegogui
    public boolean isFin() {
        return fin;
    }
}