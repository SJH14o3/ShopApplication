package source.notifications;

public class AuthorizeVendor extends Notification{
    public final int user_id;
    public AuthorizeVendor(String text, int user_id) {
        super(text, 3, 12);
        this.user_id = user_id;
    }
}
