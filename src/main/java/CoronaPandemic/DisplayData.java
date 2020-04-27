package CoronaPandemic;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.List;

public class DisplayData {
    static void UpdateGrid(GridPane grid, List<Country> countriesSorted){
        grid.getChildren().remove(10, grid.getChildren().size());
        int startRowForData = grid.getRowCount();
        for(int i = 0; i < countriesSorted.size(); i++){
            grid.add(new Label(countriesSorted.get(i).getName()), 0, startRowForData+i);
            grid.add(new Label(String.format("%.2f",countriesSorted.get(i).getHealthExpenditure())), 1,startRowForData+i);
            grid.add(new Label(String.format("%7d",countriesSorted.get(i).getTotal_cases())), 2,startRowForData+i);
            grid.add(new Label(String.format("%7d",countriesSorted.get(i).getDeaths())), 3,startRowForData+i);
            grid.add(new Label(String.format("%7d",countriesSorted.get(i).getRecovered())), 4,startRowForData+i);
            grid.add(new Label(String.format("%.2f",countriesSorted.get(i).getCfr())), 5,startRowForData+i);
        }

    }
}
