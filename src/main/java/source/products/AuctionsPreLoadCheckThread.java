package source.products;

public class AuctionsPreLoadCheckThread extends Thread{
    @Override
    public void run() {
        AuctionDataBase.checkAuctionsDeadline();
    }
}
