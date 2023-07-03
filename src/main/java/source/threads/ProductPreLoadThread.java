package source.threads;

import source.application.ControllerMenu;
import source.products.ProductDataBase;

public class ProductPreLoadThread extends Thread{
    @Override
    public void run() {
        ControllerMenu.preLoad = ProductDataBase.getSelectedProductsMainInfo("ORDER BY product_id DESC");
    }
}
