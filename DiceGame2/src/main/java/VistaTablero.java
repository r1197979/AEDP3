import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import java.util.ArrayList;

public class VistaTablero extends GridPane {

    public VistaTablero() {
        super();
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);

        // tablero dividido en 5 columnas
        for (int i = 0; i < 5; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(20);
            col.setHgrow(Priority.ALWAYS);
            this.getColumnConstraints().add(col);
        }
        // 2 filas
        for (int i = 0; i < 2; i++) {
            RowConstraints fila = new RowConstraints();
            fila.setVgrow(Priority.ALWAYS);
            this.getRowConstraints().add(fila);
        }
    }

    // acomodo de las 10 estaciones
    public void actualizar(ArrayList<Estacion> estaciones) {
        this.getChildren().clear();

        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionActual = estaciones.get(i);
            VistaEstacion tarjetaEstacion = new VistaEstacion(estacionActual);
            tarjetaEstacion.setMaxWidth(Double.MAX_VALUE);
            tarjetaEstacion.setMaxHeight(Double.MAX_VALUE);
            int columna = 0;
            int fila = 0;

            switch (i) {
                case 0: columna = 0; fila = 0; break; // Estación 1  -> Arriba, Columna 0
                case 1: columna = 1; fila = 0; break; // Estación 2  -> Arriba, Columna 1
                case 2: columna = 2; fila = 0; break; // Estación 3  -> Arriba, Columna 2
                case 3: columna = 3; fila = 0; break; // Estación 4  -> Arriba, Columna 3
                case 4: columna = 4; fila = 0; break; // Estación 5  -> Arriba, Columna 4
                case 5: columna = 4; fila = 1; break; // Estación 6  -> Abajo, Columna 4 (debajo de la 5)
                case 6: columna = 3; fila = 1; break; // Estación 7  -> Abajo, Columna 3
                case 7: columna = 2; fila = 1; break; // Estación 8  -> Abajo, Columna 2
                case 8: columna = 1; fila = 1; break; // Estación 9  -> Abajo, Columna 1
                case 9: columna = 0; fila = 1; break; // Estación 10 -> Abajo, Columna 0 (debajo de la 1)
            }
            this.add(tarjetaEstacion, columna, fila);
        }
    }
}