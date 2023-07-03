package source.threads;

import source.Global;
import source.products.AuctionDataBase;

public class placeAuctionThread extends Thread{
    private final int auctionID;
    private final double value;
    @Override
    public void run() {
        AuctionDataBase.placeBid(auctionID, value, Global.getUser_id());
    }
    public placeAuctionThread(int auctionID, double value) {
        this.auctionID = auctionID;
        this.value = value;
        //System.out.println("Value in Thread: " + this.value);
    }
}
