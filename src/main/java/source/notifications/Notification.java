package source.notifications;

public abstract class Notification {
    private final String text;
    private final int type;
    public final int id;
    public int getType() {
        return type;
    }
    public String getText() {
        return text;
    }
    public Notification(String text, int type, int id) {
        this.id = id;
        switch (type) {
            case 1 -> this.text = "No bid was inserted to your \"" + text + "\" auction and the auction's deadline has been passed";
            case 2 -> this.text = "You have won the \"" + text + "\" auction!";
            default -> this.text = "ERROR";
        }
        this.type = type;
    }
}
