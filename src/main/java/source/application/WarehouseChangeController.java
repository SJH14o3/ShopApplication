package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import source.database.Warehouse;
import source.database.WarehousesDataBase;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class WarehouseChangeController implements Initializable {
    @FXML
    private TextField name, manager;
    @FXML
    private TextArea address;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!WarehouseChange.ADD) {
            Warehouse warehouse = WarehousesDataBase.get(WarehouseChange.ID);
            name.setText(warehouse.name);
            manager.setText(warehouse.manager);
            address.setText(warehouse.address);
        }
    }
    @FXML
    private void submit() {
        String nameStr = name.getText();
        if (nameStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No input for name");
            alert.setContentText("Please provide a name");
            alert.showAndWait();
            return;
        }
        if (nameStr.length() > 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character limit for name. name length: " + nameStr.length());
            alert.setContentText("Make sure name has less than 50 characters");
            alert.showAndWait();
            return;
        }
        String managerStr = manager.getText();
        if (managerStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No input for manager");
            alert.setContentText("Please provide a manager");
            alert.showAndWait();
            return;
        }
        if (managerStr.length() > 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character limit for manager. manager length: " + nameStr.length());
            alert.setContentText("Make sure manager has less than 200 characters");
            alert.showAndWait();
            return;
        }
        String addressStr = address.getText();
        if (addressStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No input for address");
            alert.setContentText("Please provide a address");
            alert.showAndWait();
            return;
        }
        if (addressStr.length() > 200) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character limit for address. address length: " + nameStr.length());
            alert.setContentText("Make sure address has less than 200 characters");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        if (WarehouseChange.ADD) {
            alert.setHeaderText("Successfully added the new warehouse!");
            WarehousesDataBase.add(nameStr, addressStr, managerStr);
            alert.showAndWait();
            WarehousesController.page = WarehousesController.count;
        }
        else {
            alert.setHeaderText("Successfully edited the warehouse!");
            WarehousesDataBase.modify(nameStr, addressStr, managerStr, WarehouseChange.ID);
        }
        new Warehouses();

    }
    @FXML
    private void cancel() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Discard changes?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yes) {
            new Warehouses();
        }
    }
}
