package source.products;

import source.application.ControllerMenu;

public class ProductPreLoadThread extends Thread{
    @Override
    public void run() {
        ControllerMenu.preLoad = ProductDataBase.getSelectedProductsMainInfo("ORDER BY product_id DESC");
    }
}
