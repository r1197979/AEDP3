import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;

public class VistaEstacion extends VBox {

    public VistaEstacion(Estacion estacion) {
        super(8); // espacio vertical entre secciones
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(8));
        // vbox contenedor transparente
        this.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        // agrupar personas en columnas de 10
        int personasEnCola = estacion.getPersonasEnCola();
        HBox colaBox = new HBox(4); // contenedor horizontal para colocar columnas de 10 en 10
        colaBox.setAlignment(Pos.BOTTOM_CENTER);
        colaBox.setPrefHeight(65);

        VBox columnaActual = new VBox(2);
        columnaActual.setAlignment(Pos.BOTTOM_CENTER);

        for (int i = 0; i < personasEnCola; i++) {
            // cada 5 personas se crea una nueva columna hacia la derecha
            if (i > 0 && i % 5 == 0) {
                colaBox.getChildren().add(columnaActual);
                columnaActual = new VBox(2);
                columnaActual.setAlignment(Pos.BOTTOM_CENTER);
            }
            Circle persona = new Circle(5, Color.DODGERBLUE);
            persona.setStroke(Color.web("#7dd3fc"));
            persona.setStrokeWidth(1);
            columnaActual.getChildren().add(persona);
        }
        if (!columnaActual.getChildren().isEmpty()) {
            colaBox.getChildren().add(columnaActual);
        }

        // personaje y dado en mismo contenedor hbox
        HBox centroBox = new HBox(10);
        centroBox.setAlignment(Pos.CENTER);
        // personaje
        ImageView imgPersonaje = new ImageView();
        File archivoPersonaje = new File("personaje.png");
        if (archivoPersonaje.exists()) {
            imgPersonaje.setImage(new Image(archivoPersonaje.toURI().toString()));
            imgPersonaje.setFitWidth(65);
            imgPersonaje.setPreserveRatio(true);
            centroBox.getChildren().add(imgPersonaje);
        }

        // dado y resultado
        HBox dadosBox = new HBox(2);
        dadosBox.setAlignment(Pos.CENTER);
        int numDados = estacion.getNumDados();
        int puntajeTiro = estacion.getPersonasPorIngresar();
        File archivoDado = new File("dado.png");
        if (archivoDado.exists()) {
            ImageView imgDado = new ImageView(new Image(archivoDado.toURI().toString()));
            imgDado.setFitWidth(50);
            imgDado.setPreserveRatio(true);
            // numero de dados y puntaje obtenido al lanzar
            Label lblInfoDados = new Label("#" + numDados + "\nTotal: " + puntajeTiro);
            lblInfoDados.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #0f172a; -fx-text-alignment: center;");

            dadosBox.getChildren().addAll(imgDado, lblInfoDados);
        }
        centroBox.getChildren().add(dadosBox);

        // nombre estacion
        Label lblNombre = new Label("Estación " + estacion.getNumEstacion());
        lblNombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #0369a1;");
        this.getChildren().addAll(colaBox, centroBox, lblNombre);
    }
}