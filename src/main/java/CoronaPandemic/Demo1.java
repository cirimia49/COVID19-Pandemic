package CoronaPandemic;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.util.List;

public class Demo1 extends Application {

    static List<Country> countries;

    static {
        try {
            countries = ReadData.readData();
        } catch (ParseException | UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Corona Pandemic");
        Scene scene = new Scene(new Group(), 850, 600);

        final ComboBox<String> dropDownMenu = new ComboBox<>();

        dropDownMenu.getItems().addAll("Pie_Chart", "XY_Chart");
        dropDownMenu.setValue("Select a visualization method");

        dropDownMenu.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(observable.getValue().equals("XY_Chart")){
                getXYChart(stage, scene);
            } else if(observable.getValue().equals("Pie_Chart")){
                getPieChart(stage, scene);
            }else {
                System.out.println(observable.getValue());
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(15);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Note 1: Select XY_Chart for CFR Statistics"), 0, 0, 4,1);
        grid.add(new Label("Note 2: Select Pie_Chart for Case Statistics"), 0, 1, 4,1);
        grid.add(new Label("Select your option:"), 0, 2);
        grid.add(dropDownMenu, 1, 2, 2,1);

        List<Country> countriesSorted = new DataManipulation().sortByTotalCases(countries);

        Label country = new Label("Country");
        country.setTextFill(Color.GRAY);
        country.setOnMouseClicked(event -> {
            DisplayData.UpdateGrid(grid, new DataManipulation().sortByCountry(countries));
        });

        Label healthEx = new Label("Health Exp (in USD trillion)");
        healthEx.setTextFill(Color.GRAY);
        healthEx.setOnMouseClicked(event -> {
            DisplayData.UpdateGrid(grid, new DataManipulation().sortByHeatlhEx(countries));
        });

        Label totalCases = new Label("Total Cases");
        totalCases.setTextFill(Color.GRAY);
        totalCases.setOnMouseClicked(event -> {
            DisplayData.UpdateGrid(grid, new DataManipulation().sortByTotalCases(countries));
        });

        Label totalDeaths = new Label("Total Deaths");
        totalDeaths.setTextFill(Color.GRAY);
        totalDeaths.setOnMouseClicked(event -> {
            DisplayData.UpdateGrid(grid, new DataManipulation().sortByTotalDeaths(countries));
        });

        Label totalRecovered = new Label("Total Recovered");
        totalRecovered.setTextFill(Color.GRAY);
        totalRecovered.setOnMouseClicked(event -> {
            DisplayData.UpdateGrid(grid, new DataManipulation().sortByTotalRecovered(countries));
        });

        Label cfr = new Label("CFR");
        cfr.setTextFill(Color.GRAY);
        cfr.setOnMouseClicked(event -> {
            DisplayData.UpdateGrid(grid, new DataManipulation().sortByCFR(countries));
        });

        int startRowForData = grid.getRowCount();
        grid.add(country,0, startRowForData);
        grid.add(healthEx,1,startRowForData);
        grid.add(totalCases,2,startRowForData);
        grid.add(totalDeaths,3,startRowForData);
        grid.add(totalRecovered,4,startRowForData);
        grid.add(cfr,5,startRowForData);
        startRowForData++;


        for(int i = 0; i < countriesSorted.size(); i++){
            grid.add(new Label(countriesSorted.get(i).getName()), 0, startRowForData+i);
            grid.add(new Label(String.format("%.2f",countriesSorted.get(i).getHealthExpenditure())), 1,startRowForData+i);
            grid.add(new Label(String.format("%7d",countriesSorted.get(i).getTotal_cases())), 2,startRowForData+i);
            grid.add(new Label(String.format("%7d",countriesSorted.get(i).getDeaths())), 3,startRowForData+i);
            grid.add(new Label(String.format("%7d",countriesSorted.get(i).getRecovered())), 4,startRowForData+i);
            grid.add(new Label(String.format("%.2f",countriesSorted.get(i).getCfr())), 5,startRowForData+i);
        }

        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();
    }

    private void getXYChart(Stage stage, Scene sceneInherited) {
        final Axis<Number> xAxis = new NumberAxis();
        final Axis<Number> yAxis = new NumberAxis();
        xAxis.setLabel("Health Expenditure (in USD trillion)");
        yAxis.setLabel("CFR");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Case Fatality Rate(CFR) vs. Health Expenditure of GDP");

        for(Country c : countries){
            XYChart.Series series = new XYChart.Series();
            series.setName(c.getName());
            series.getData().add(new XYChart.Data(c.getHealthExpenditure(), c.getCfr()));
            lineChart.getData().add(series);
        }

        Button button = new Button(" Main Page ");
        button.setOnMouseClicked(event -> {
            stage.setScene(sceneInherited);
        });

        GridPane g = new GridPane();
        g.setVgap(15);
        g.add(button, 1, 0);
        g.add(lineChart, 1, 1);

        lineChart.setMinHeight(500);
        lineChart.setMinWidth(750);

        Scene scene  = new Scene(g, 850, 600);
        stage.setScene(scene);
    }

    private void getPieChart(Stage stage, Scene sceneInherited) {
        final ComboBox<String> dropDownMenu = new ComboBox<String>();
        for(Country c : countries){
            dropDownMenu.getItems().add(c.getName());
        }
        dropDownMenu.setValue("Select a country");

        Button button = new Button(" Main Page ");
        button.setOnMouseClicked(event -> {
            stage.setScene(sceneInherited);
        });

        GridPane g = new GridPane();

        g.setVgap(15);
        g.add(button, 1, 0);
        g.add(dropDownMenu, 1, 1);

        Scene scene  = new Scene(g, 850, 600);
        dropDownMenu.valueProperty().addListener((observable, oldValue, newValue) ->  {

            Country tempCountry = null;

            for (Country c : countries){
                if(c.getName().equals(observable.getValue())){
                    tempCountry = c;
                }
            }

            if(tempCountry != null){
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList(
                                new PieChart.Data("Active Cases ("+tempCountry.getActiveCases()+")", tempCountry.getActiveCases()),
                                new PieChart.Data("Recovered Cases ("+tempCountry.getRecovered()+")", tempCountry.getRecovered()),
                                new PieChart.Data("Deaths ("+tempCountry.getDeaths()+")", tempCountry.getDeaths()));
                final PieChart pieChart = new PieChart(pieChartData);
                pieChart.setTitle(tempCountry.getName() + " Total Cases: " + tempCountry.getTotal_cases());

                if(g.getChildren().size() != 2)
                    g.getChildren().remove(g.getChildren().size() - 1);

                g.add(pieChart, 1, 2);
            }
            stage.setScene(scene);
        });

        stage.setScene(scene);
    }
}
