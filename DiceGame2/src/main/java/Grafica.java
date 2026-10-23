import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Grafica {

    /*
     * GRAFICA 1 ACTIVIDAD
     * mostrar personas que ingresaron en cada ronda para cada estacion indivdual
     */
    public static void mostrarActividad(ArrayList<Estacion> estaciones) {
        Stage stage = new Stage();
        stage.setTitle("Actividad por Estación");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Ronda");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Personas Ingresadas");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLegendVisible(false);
        Label lblPromedio = new Label("Selecciona estación para ver el promedio de ingresados");
        lblPromedio.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #0369a1;");

        // button para cada estacion
        HBox boxBotones = new HBox(6);
        boxBotones.setAlignment(Pos.CENTER);
        boxBotones.setPadding(new Insets(10, 0, 0, 0));
        for(int i = 0; i < estaciones.size(); i++){
            int indexEstacion = i;
            Button btnEstacion = new Button(String.valueOf(i + 1));
            btnEstacion.setStyle("-fx-background-color: linear-gradient(to bottom, #00aeef, #008cd2); " +
                    "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; " +
                    "-fx-cursor: hand;");
            btnEstacion.setPrefWidth(42);

            btnEstacion.setOnAction(e ->{
                barChart.getData().clear();
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("Estación " + (indexEstacion + 1));

                List<Integer> entradas = estaciones.get(indexEstacion).getHistorialEntradas();
                int sumaTotal = 0;
                for (int r = 0; r < entradas.size(); r++) {
                    int personas = entradas.get(r);
                    series.getData().add(new XYChart.Data<>(String.valueOf(r + 1), personas));
                    sumaTotal += personas;
                }
                barChart.getData().add(series);

                // calcular promedio de entrada de personas total a una estacion
                double promedio = 0.0;
                if(entradas.size() > 0){
                    promedio = (double) sumaTotal / entradas.size();
                }
                lblPromedio.setText(String.format("Estación %d: Promedio de %.2f personas por ronda", (indexEstacion + 1), promedio));
            });
            boxBotones.getChildren().add(btnEstacion);
        }

        // empezar mostrando estacion 1
        if(!boxBotones.getChildren().isEmpty()){
            ((Button) boxBotones.getChildren().get(0)).fire();
        }
        VBox layout = new VBox(10, barChart, lblPromedio, boxBotones);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));
        Scene scene = new Scene(layout, 780, 500);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * GRAFICA 2 SALIDA
     * mostrar cuantas personas van acumuladas en la salida en cada ronda
     */
    public static void mostrarSalida(List<Integer> historialSalida) {
        Stage stage = new Stage();
        stage.setTitle("Salida del Sistema");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Ronda");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Personas procesadas completamente");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLegendVisible(false);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Salida Acumulada");

        int acumulado = 0;
        for (int r = 0; r < historialSalida.size(); r++) {
            acumulado += historialSalida.get(r);
            series.getData().add(new XYChart.Data<>(String.valueOf(r + 1), acumulado));
        }
        barChart.getData().add(series);

        // mostrar total acumulado
        Label lblInfo = new Label("Total de personas procesadas: " + acumulado);
        lblInfo.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #0369a1;");

        VBox layout = new VBox(10, barChart, lblInfo);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 780, 480);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * GRAFICA 3 PERSONAS EN SISTEMA
     * suma la cantidad de personas en cada estacion para obtener total de personas en sistema para cada ronda
     */
    public static void mostrarPersonasEnSistema(List<Integer> historialPersonas) {
        Stage stage = new Stage();
        stage.setTitle("Numero de personas en el sistema");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Ronda");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Personas totales dentro del sistema");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Personas en Sistema");

        for (int r = 0; r < historialPersonas.size(); r++) {
            int personas = historialPersonas.get(r);
            series.getData().add(new XYChart.Data<>(String.valueOf(r + 1), personas));
        }
        barChart.getData().add(series);

        // obtener el valor mas reciente de personas en el sistema
        int actual = 0;
        if (!historialPersonas.isEmpty()) {
            actual = historialPersonas.get(historialPersonas.size() - 1);
        }

        // label para indicar numero de personas totales
        Label lblInfo = new Label("Personas en sistema: " + actual);
        lblInfo.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #0369a1;");

        VBox layout = new VBox(10, barChart, lblInfo);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 780, 480);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * GRAFICA 4 TIEMPO EN SISTEMA
     * mostrar tiempo promedio de una persona en el sistema en cada ronda
     */
    public static void mostrarTiempoEnSistema(List<Double> historialTiempo) {
        Stage stage = new Stage();
        stage.setTitle("Tiempo promedio en el sistema");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Ronda");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Tiempo promedio (rondas)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Tiempo en Sistema");

        for (int r = 0; r < historialTiempo.size(); r++) {
            double tiempo = historialTiempo.get(r);
            series.getData().add(new XYChart.Data<>(String.valueOf(r + 1), tiempo));
        }
        barChart.getData().add(series);

        // obtener el valor mas reciente de tiempo en el sistema
        double actual = 0.0;
        if (!historialTiempo.isEmpty()) {
            actual = historialTiempo.get(historialTiempo.size() - 1);
        }

        Label lblInfo = new Label(String.format("Tiempo en sistema: %.2f rondas", actual));
        lblInfo.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #0369a1;");

        VBox layout = new VBox(10, barChart, lblInfo);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 780, 480);
        stage.setScene(scene);
        stage.show();
    }
}