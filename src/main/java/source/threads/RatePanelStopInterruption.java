package source.threads;

import source.application.ProductPageController;

public class RatePanelStopInterruption extends Thread{
    @Override
    public void run() {
        ProductPageController.ratePaneIsMoving = true;
        try {
            Thread.sleep(1550);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProductPageController.ratePaneIsMoving = false;
    }
}
