// Base class (Template)
abstract class NotificationSender {

    // Template Method (fixed flow)
    public final void send(String to, String message) {
        checkRateLimit(to);
        validate(to);
        log(to, message);

        String formatted = format(message);
        String composed = compose(formatted);

        sendMessage(to, composed);

        log(to, composed);
        analytics(); // hook
    }

    // Common methods (same for all subclasses)
    private void checkRateLimit(String to) {
        System.out.println("Check rate limit - " + to);
    }

    private void validate(String to) {
        System.out.println("Validate - " + to);
    }

    private void log(String to, String message) {
        System.out.println("Log to - " + to + ", message - " + message);
    }

    protected String format(String message) {
        return message.trim();
    }

    // Abstract methods (must be implemented by subclasses)
    protected abstract String compose(String message);
    protected abstract void sendMessage(String to, String message);

    // Hook (optional override)
    protected void analytics() {
        System.out.println("Default Analytics");
    }
}

// Email implementation
class EmailNotif extends NotificationSender {

    @Override
    protected String compose(String message) {
        return "<html><body><p>" + message + "</p></body></html>";
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Send EMAIL to - " + to + ", message - " + message);
    }

    @Override
    protected void analytics() {
        System.out.println("Custom EMAIL Analytics!");
    }
}

// SMS implementation
class SMSNotif extends NotificationSender {

    @Override
    protected String compose(String message) {
        return "[SMS] " + message;
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Send SMS to - " + to + ", message - " + message);
    }
}

// Client code
public class AfterTP {
    public static void main(String[] args) {

        NotificationSender email = new EmailNotif();
        email.send("user@example.com", "Welcome to Template Pattern!");

        System.out.println();

        NotificationSender sms = new SMSNotif();
        sms.send("1234567890", "Your OTP is 1234");
    }
}