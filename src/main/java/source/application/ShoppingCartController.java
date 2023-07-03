package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import source.Global;
import source.products.CartDataBase;
import source.products.CartItem;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShoppingCartController implements Initializable {

    @FXML
    private AnchorPane pane0, pane1, pane2, pane3, emptyCart,summaryPane;
    private final AnchorPane[] panes = new AnchorPane[4];

    @FXML
    private ImageView image0, image1, image2, image3;
    private final ImageView[] images = new ImageView[4];

    @FXML
    private Label productName0, productName1, productName2, productName3;
    private final Label[] productsNames = new Label[4];

    @FXML
    private Label productBrand0, productBrand1, productBrand2, productBrand3;
    private final Label[] productsBrands = new Label[4];

    @FXML
    private Label finalPrice0, finalPrice1, finalPrice2, finalPrice3;
    private final Label[] finalPrices = new Label[4];

    @FXML
    private Label productQuantity0, productQuantity1, productQuantity2, productQuantity3;
    private final Label[] productsQuantity = new Label[4];

    @FXML
    private Button removeProduct0, removeProduct1, removeProduct2, removeProduct3;
    private final Button[] removeProducts = new Button[4];

    @FXML
    private Button increase0, increase1, increase2, increase3;
    private final Button[] increaseButtons = new Button[4];

    @FXML
    private Button decrease0, decrease1, decrease2, decrease3;
    private final Button[] decreaseButtons = new Button[4];

    @FXML
    private Button nextButton, prevButton, checkoutButton;

    @FXML
    private Label productCount, totalShoppingCart;

    @FXML
    private Label pageCounter;

    private int first, last, page;
    private CartItem[] cartItems;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nextButton.setGraphic(new ImageView(new Image("next.png")));
        prevButton.setGraphic(new ImageView(new Image("prev.png")));

        cartItems = CartDataBase.getCartItems(Global.getUser_id());
        initiateArrays();
        first = 0;
        if(cartItems.length>0){
            emptyCart.setVisible(false);
        }
        if(cartItems.length<=4){
            nextButton.setDisable(true);
            last = cartItems.length;
        }else{
            last = 3;
        }
        page = 1;
        update();
    }

    private void initiateArrays(){

        panes[0] = pane0;
        pane0 = null;
        images[0] = image0;
        image0 = null;
        productsNames[0] = productName0;
        productName0 = null;
        productsBrands[0] = productBrand0;
        productBrand0 = null;
        finalPrices[0] = finalPrice0;
        finalPrice0 = null;
        productsQuantity[0] = productQuantity0;
        productQuantity0 = null;
        removeProducts[0] = removeProduct0;
        removeProduct0.setGraphic(new ImageView(new Image("remove.png")));
        removeProduct0 = null;
        increaseButtons[0] = increase0;
        increase0.setGraphic(new ImageView(new Image("plus.png")));
        increase0 = null;
        decreaseButtons[0] = decrease0;
        decrease0.setGraphic(new ImageView(new Image("minus.png")));
        decrease0 = null;

        panes[1] = pane1;
        pane1 = null;
        images[1] = image1;
        image1 = null;
        productsNames[1] = productName1;
        productName1 = null;
        productsBrands[1] = productBrand1;
        productBrand1 = null;
        finalPrices[1] = finalPrice1;
        finalPrice1 = null;
        productsQuantity[1] = productQuantity1;
        productQuantity1 = null;
        removeProducts[1] = removeProduct1;
        removeProduct1.setGraphic(new ImageView(new Image("remove.png")));
        removeProduct1 = null;
        increaseButtons[1] = increase1;
        increase1.setGraphic(new ImageView(new Image("plus.png")));
        increase1 = null;
        decreaseButtons[1] = decrease1;
        decrease1.setGraphic(new ImageView(new Image("minus.png")));
        decrease1 = null;

        panes[2] = pane2;
        pane2 = null;
        images[2] = image2;
        image2 = null;
        productsNames[2] = productName2;
        productName2 = null;
        productsBrands[2] = productBrand2;
        productBrand2 = null;
        finalPrices[2] = finalPrice2;
        finalPrice2 = null;
        productsQuantity[2] = productQuantity2;
        productQuantity2 = null;
        removeProducts[2] = removeProduct2;
        removeProduct2.setGraphic(new ImageView(new Image("remove.png")));
        removeProduct2 = null;
        increaseButtons[2] = increase2;
        increase2.setGraphic(new ImageView(new Image("plus.png")));
        increase2 = null;
        decreaseButtons[2] = decrease2;
        decrease2.setGraphic(new ImageView(new Image("minus.png")));
        decrease2 = null;

        panes[3] = pane3;
        pane3 = null;
        images[3] = image3;
        image3 = null;
        productsNames[3] = productName3;
        productName3 = null;
        productsBrands[3] = productBrand3;
        productBrand3 = null;
        finalPrices[3] = finalPrice3;
        finalPrice3 = null;
        productsQuantity[3] = productQuantity3;
        productQuantity3 = null;
        removeProducts[3] = removeProduct3;
        removeProduct3.setGraphic(new ImageView(new Image("remove.png")));
        removeProduct3 = null;
        increaseButtons[3] = increase3;
        increase3.setGraphic(new ImageView(new Image("plus.png")));
        increase3 = null;
        decreaseButtons[3] = decrease3;
        decrease3.setGraphic(new ImageView(new Image("minus.png")));
        decrease3 = null;

    }
    private void update(){
        pageCounter.setText(String.valueOf(page));
        first = (page - 1) * 4;
        last = first + 4;
        if(last>cartItems.length){
            last = cartItems.length;
        }

        if(cartItems.length == 0){
            emptyCart.setVisible(true);
        }
        int i;
        for(i = 0; i< last-first;i++){

            if(!panes[i].isVisible()){
                panes[i].setVisible(true);
            }
            images[i].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Products/" + cartItems[i + first].product.getImageAddress() + ".jpg"))));
            productsNames[i].setText(cartItems[i+first].product.getName());
            productsBrands[i].setText(cartItems[i+first].product.getBrand());
            finalPrices[i].setText(String.valueOf(cartItems[i+first].product.getPrice()));
            productsQuantity[i].setText(String.valueOf(cartItems[i+first].quantity));
        }
        for(;i<4;i++) {
            if (panes[i].isVisible()) {
                panes[i].setVisible(false);
            }
        }
        productCount.setText(String.valueOf(getItemCounts()));
        totalShoppingCart.setText(String.valueOf(getItemsPrices()));
    }

    @FXML
    private void next(){
        page++;
        update();
        if (prevButton.isDisable()) {
            prevButton.setDisable(false);
        }
        if (!(last < cartItems.length)) {
            nextButton.setDisable(true);
        }

    }
    @FXML
    private void previous(){
       if(page==1){
           prevButton.setDisable(true);
       }else{
           page--;
           update();
           if (nextButton.isDisable()) {
               nextButton.setDisable(false);
           }
       }
    }

    @FXML
    private void remove0(){

        CartDataBase.removeCartItem(cartItems[0+first].ID);
        cartItems = CartDataBase.getCartItems(Global.getUser_id());
        update();

    }
    @FXML
    private void remove1(){

        CartDataBase.removeCartItem(cartItems[1+first].ID);
        cartItems = CartDataBase.getCartItems(Global.getUser_id());
        update();
    }
    @FXML
    private void remove2(){

        CartDataBase.removeCartItem(cartItems[2+first].ID);
        cartItems = CartDataBase.getCartItems(Global.getUser_id());
        update();

    }
    @FXML
    private void remove3(){

        CartDataBase.removeCartItem(cartItems[3+first].ID);
        cartItems = CartDataBase.getCartItems(Global.getUser_id());
        update();

    }
    @FXML
    private void increaseItem0(){

        if(CartDataBase.increaseQuantity(cartItems[first].ID,cartItems[first].quantity,cartItems[first].product.getQuantity())) {

            cartItems[first].quantity++;
            productsQuantity[0].setText(String.valueOf(cartItems[first].quantity));

        }
    }
    @FXML
    private void increaseItem1(){

        if(CartDataBase.increaseQuantity(cartItems[first+1].ID,cartItems[first+1].quantity,cartItems[first+1].product.getQuantity())) {

            cartItems[first+1].quantity++;
            productsQuantity[0].setText(String.valueOf(cartItems[first+1].quantity));

        }
    }
    @FXML
    private void increaseItem2(){

        if(CartDataBase.increaseQuantity(cartItems[first+2].ID,cartItems[first+2].quantity,cartItems[first+2].product.getQuantity())) {

            cartItems[first+2].quantity++;
            productsQuantity[0].setText(String.valueOf(cartItems[first+2].quantity));

        }
    }
    @FXML
    private void increaseItem3(){

        if(CartDataBase.increaseQuantity(cartItems[first+3].ID,cartItems[first+3].quantity,cartItems[first+3].product.getQuantity())) {

            cartItems[first+3].quantity++;
            productsQuantity[0].setText(String.valueOf(cartItems[first+3].quantity));

        }
    }
    @FXML
    private void decreaseItem0(){

        if(CartDataBase.decreaseQuantity(cartItems[first].ID,cartItems[first].quantity)){

            cartItems[first].quantity--;
            productsQuantity[0].setText(String.valueOf(cartItems[first].quantity));

        }else{
            remove0();
        }
    }
    @FXML
    private void decreaseItem1(){

        if(CartDataBase.decreaseQuantity(cartItems[first+1].ID,cartItems[first+1].quantity)){

            cartItems[first+1].quantity--;
            productsQuantity[1].setText(String.valueOf(cartItems[first+1].quantity));
        }else{
            remove1();
        }
    }
    @FXML
    private void decreaseItem2(){

        if(CartDataBase.decreaseQuantity(cartItems[first+2].ID,cartItems[first+2].quantity)){

            cartItems[first+2].quantity--;
            productsQuantity[2].setText(String.valueOf(cartItems[first+2].quantity));

        }else{
            remove2();
        }
    }
    @FXML
    private void decreaseItem3(){

        if(CartDataBase.decreaseQuantity(cartItems[first+3].ID,cartItems[first+3].quantity)){

            cartItems[first+3].quantity--;
            productsQuantity[3].setText(String.valueOf(cartItems[first+3].quantity));

        }else{
            remove3();
        }
    }
    @FXML
    private void checkout(){

    }

    public double getItemsPrices(){
        double sum = 0;
        for (int i = 0;i<cartItems.length;i++){
            sum += (cartItems[i].product.getPrice()) * (cartItems[i].quantity);
        }
        return sum;
    }
    public int getItemCounts(){
        int sum =0;
        for (int i = 0;i<cartItems.length;i++){
            sum += cartItems[i].quantity;
        }
        return sum;
    }

}
