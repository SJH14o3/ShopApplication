package source.products;

import source.Global;

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
    }
}
