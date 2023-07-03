package source.threads;

import source.products.AuctionDataBase;

public class AuctionsPreLoadCheckThread extends Thread{
    @Override
    public void run() {
        AuctionDataBase.checkAuctionsDeadline();
    }
}
