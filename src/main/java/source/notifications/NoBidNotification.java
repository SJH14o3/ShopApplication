package source.notifications;

public class NoBidNotification extends Notification{
    public final int auction_id;
    public NoBidNotification(String auctionName, int auction_id, int id) {
        super(auctionName, 1, id);
        this.auction_id = auction_id;
    }
}
