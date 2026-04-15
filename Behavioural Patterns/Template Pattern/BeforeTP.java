class EmailNotification {
  public void send(String to, String message) {
    System.out.println("Check rate limit to "+to);
    
    System.out.println("Validate - "+to);

    System.out.println("Log - "+to+" - "+message);
    String formattedMessage = message.trim();
    String composedMessage = "<html><body><p>" + formattedMessage + "</p></body></html>";

    System.out.println("Send EMAIL to "+to+" message content - "+composedMessage);
    System.out.println("Custom EMAIL Analytics");
  }
}

class SMSNotification {
  public void send(String to, String message) {
      System.out.println("Check rate limit to "+to);
      
      System.out.println("Validate - "+to);

      System.out.println("Log - "+to+" - "+message);
      String formattedMessage = message.trim();
      String composedMessage = "Hello there! "+formattedMessage;

      System.out.println("Send SMS to "+to+" message content - "+composedMessage);
      System.out.println("Custom SMS Analytics");
    }
}

public class BeforeTP {
    public static void main(String args[]) {
      EmailNotification en = new EmailNotification();
      en.send("user@example.com", "Welcome");

      SMSNotification sn = new SMSNotification();
      sn.send("12345667", "OTP is 5447");
    }
}