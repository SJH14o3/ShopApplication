package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import source.Global;
import source.database.Warehouse;
import source.database.WarehousesDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class WarehousesController implements Initializable {
    private Warehouse[] warehouses;
    public static int page = 0;
    public static int count;
    @FXML
    private Button prevButton, backButton, nextButton;
    @FXML
    private Label id, name, manager, address;
    @FXML
    private void edit() {
        WarehouseChange.ID = warehouses[page].id;
        WarehouseChange.ADD = false;
        new WarehouseChange();
    }
    @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You are about to delete this warehouse");
        alert.setContentText("Are you sure?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yes) {
            WarehousesDataBase.delete(warehouses[page].id);
            page--;
            new Warehouses();
        }
    }
    @FXML
    private void add() {
        WarehouseChange.ADD = true;
        new WarehouseChange();
    }
    @FXML
    private void next() {
        page++;
        if (prevButton.isDisable()) {
            prevButton.setDisable(false);
        }
        if (page == warehouses.length-1) {
            nextButton.setDisable(true);
        }
        update();
    }
    @FXML
    private void prev() {
        page--;
        if (nextButton.isDisable()) {
            nextButton.setDisable(false);
        }
        if (page == 0) {
            prevButton.setDisable(true);
        }
        update();
    }
    private void update() {
        name.setText("Name: " + warehouses[page].name);
        manager.setText("Manager: " + warehouses[page].manager);
        address.setText("Address: " + warehouses[page].address);
        id.setText("ID: " + warehouses[page].id);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prevButton.setGraphic(new ImageView(new Image("prevSmall.png")));
        nextButton.setGraphic(new ImageView(new Image("nextSmall.png")));
        backButton.setGraphic(new ImageView(new Image("back_arrow.png")));
        warehouses = WarehousesDataBase.getAll();
        count = warehouses.length;
        if (page == 0 && !prevButton.isDisable()) {
            prevButton.setDisable(true);
        }
        if (page == warehouses.length-1 && !nextButton.isDisable()) {
            nextButton.setDisable(true);
        }
        update();
    }
    @FXML
    private void back() {
        page = 0;
        try {
            new Menu(Global.getStage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}