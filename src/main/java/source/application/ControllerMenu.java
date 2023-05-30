package source.application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {
    @FXML
    private RadioButton r1, r2, r3;

    @FXML
    private ChoiceBox<String> category;

    @FXML
    private Slider min, max;

    @FXML
    private Label minText, maxText;
    private int minNum, maxNum;

    @FXML
    private CheckBox allBrands, b1, b2, b3, b4, b5, b6; //rahe sade bara arraye kardan peyda nakardam vegarna kheyli behtar bood.

    private String[] categories = {"All", "Dairy", "Proteins", "Drinks", "Snacks"};

    public void sortBy(ActionEvent event){
        if (r1.isSelected()) {
            System.out.println("Sort by: Most Expensive");
        }
        else if (r2.isSelected()) {
            System.out.println("Sort by: Cheapest");
        }
        else if (r3.isSelected()) {
            System.out.println("Sort by: Rating");
        }
    }
    private String brandsToString() {
        StringBuffer str;
        if (allBrands.isSelected()) {
            return "all";
        }
        else {
            byte count = 0;
            if (b1.isSelected()) {
                count++;
            }
            if (b2.isSelected()) {
                count++;
            }
            if (b3.isSelected()) {
                count++;
            }
            if (b4.isSelected()) {
                count++;
            }
            if (b5.isSelected()) {
                count++;
            }
            if (b6.isSelected()) {
                count++;
            }
            str = new StringBuffer();

            if (b1.isSelected()) {
                str.append("Brand A");
                count--;
                if (count != 0) {
                    str.append(", ");
                }
            }
            if (b2.isSelected()) {
                str.append("Brand B");
                count--;
                if (count != 0) {
                    str.append(", ");
                }
            }
            if (b3.isSelected()) {
                str.append("Brand C");
                count--;
                if (count != 0) {
                    str.append(", ");
                }
            }
            if (b4.isSelected()) {
                str.append("Brand D");
                count--;
                if (count != 0) {
                    str.append(", ");
                }
            }
            if (b5.isSelected()) {
                str.append("Brand E");
                count--;
                if (count != 0) {
                    str.append(", ");
                }
            }
            if (b6.isSelected()) {
                str.append("Brand F");
            }
        }
        return str.toString();
    }

    public void applyFilter(ActionEvent event){
        System.out.println("Applied Filter:\n"
        + "Minimum price: " + minNum + "\n"
        + "Maximum price: " + maxNum + "\n"
        + "brands: " + brandsToString() + "\n"
        );
    }
    public void uncheckBrands(ActionEvent event){
        if (!allBrands.isSelected()) {
            allBrands.setSelected(false);
        }
        else {
            allBrands.setSelected(true);
            if (b1.isSelected()) {
                b1.setSelected(false);
            }
            if (b2.isSelected()) {
                b2.setSelected(false);
            }
            if (b3.isSelected()) {
                b3.setSelected(false);
            }
            if (b4.isSelected()) {
                b4.setSelected(false);
            }
            if (b5.isSelected()) {
                b5.setSelected(false);
            }
            if (b6.isSelected()) {
                b6.setSelected(false);
            }
        }
    }
    public void checkBrands(ActionEvent event){
        if (allBrands.isSelected()) {
            allBrands.setSelected(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        minNum = 0;
        maxNum = 1_000_000;
        minText.setText(Integer.toString(minNum));
        maxText.setText(Integer.toString(maxNum));

        min.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                minNum = (int) min.getValue();
                minText.setText(Integer.toString(minNum));
            }
        });
        max.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                maxNum = (int) max.getValue();
                maxText.setText(Integer.toString(maxNum));
            }
        });
        category.getItems().addAll(categories);
        category.setValue(categories[0]);
        category.setOnAction(this::setCategory);
    }

    public void setCategory(ActionEvent event) {
        System.out.println("Category: " + category.getValue());
    }
}
