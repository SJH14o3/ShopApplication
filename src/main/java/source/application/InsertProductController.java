package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import source.Global;
import source.products.Product;
import source.products.ProductDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InsertProductController extends Insert implements Initializable {
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private ChoiceBox<String> category;
    @FXML
    private TextField nameTextField, brandTextField, priceTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Button testButton;
    private final String[] categories = {"Vegetables", "Fruits", "Dried Fruits", "Proteins", "Sweets", "Pantry", "Dairy", "Beverages", "Snacks", "Breakfast"};
    @FXML
    private void parseInformation() {
        if (InsertProduct.changeStock) {
            ProductDataBase.updateQuantity(spinner.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Successfully updated the product!");
            alert.showAndWait();
            try {
                ProductPage.previousScene = 1;
                new ProductPage(Global.getStage(), ProductPage.PRODUCT_ID);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String name = nameTextField.getText();
        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please provide a name");
            alert.showAndWait();
            return;
        }
        if (name.length() > 65) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character Limit for name. name's length: " + name.length());
            alert.setContentText("Make sure: Name field has less than 65 characters");
            alert.showAndWait();
            return;
        }
        String brand = brandTextField.getText();
        if (brand.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please provide a brand");
            alert.showAndWait();
            return;
        }
        if (brand.length() > 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character Limit for brand. brand's length: " + brand.length());
            alert.setContentText("Make sure: Brand field has less than 50 characters");
            alert.showAndWait();
            return;
        }
        double price;
        try {
            if (priceTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please provide a price");
                alert.showAndWait();
                return;
            }
            price = parsePrice(priceTextField.getText());
            if (!checkPrice(price)) {
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong price input. input in price field: \"" + priceTextField.getText() + "\"");
            alert.setContentText("Make sure: Price is only a double number with maximum 2 decimal digits");
            alert.showAndWait();
            return;
        }
        String pictureAddress = imageAddress.getText();
        if (pictureAddress.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please Provide a image");
            alert.showAndWait();
            return;
        }
        if (!legitimateImageAddress(pictureAddress)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Picture with provided address found");
            alert.setContentText("Make sure:\nimage is in \"Product\" folder\nimage is .jpg\nyou have not entered \".jpg\" part");
            alert.showAndWait();
            return;
        }
        String type = category.getValue();
        if (type == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No product type has been selected");
            alert.setContentText("Make sure: You have select a product type.");
            alert.showAndWait();
            return;
        }
        String description = descriptionTextArea.getText();
        if (description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please provide a description");
            alert.showAndWait();
            return;
        }
        if (description.length() > 1000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character Limit for description. description's length: " + description.length());
            alert.setContentText("Make sure: description field has less than 1000 characters");
            alert.showAndWait();
            return;
        }
        InsertProduct.changeStock = false;
        int typeInt = Product.stringToType(type);
        Product product = new Product(name, brand, price, spinner.getValue(), pictureAddress, typeInt, description);
        ProductDataBase.insertProduct(product);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Successfully added product to the shop!");
        alert.showAndWait();
        Stage stage = Global.getStage();
        try {
            new Menu(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category.getItems().setAll(categories);
        if (InsertProduct.changeStock) {
            Product product = ProductDataBase.getProduct(ProductPage.PRODUCT_ID);
            nameTextField.setText(product.getName());
            nameTextField.setDisable(true);
            brandTextField.setText(product.getBrand());
            brandTextField.setDisable(true);
            priceTextField.setText(String.valueOf(product.getPrice()));
            priceTextField.setDisable(true);
            imageAddress.setText(product.getImageAddress());
            imageAddress.setDisable(true);
            image.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Products/" + product.getImageAddress() + ".jpg"))));
            testButton.setDisable(true);
            category.setValue(product.typeToString());
            category.setDisable(true);
            descriptionTextArea.setText(product.getDescription());
            descriptionTextArea.setDisable(true);
            SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(product.getQuantity(),1000);
            valueFactory1.setValue(product.getQuantity());
            spinner.setValueFactory(valueFactory1);
        }
        else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000);
            valueFactory.setValue(1);
            spinner.setValueFactory(valueFactory);
        }
    }
    @FXML
    private void back() {
        Stage stage = Global.getStage();
        try {
            new Menu(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
