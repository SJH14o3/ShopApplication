package source.notifications;

public class AuctionWinnerNotification extends Notification{
    public AuctionWinnerNotification(String auctionName, int id) {
        super(auctionName, 2, id);
    }
}
