public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        
        AuditLog audit = new AuditLog();
        ConsolePreview preview = new ConsolePreview();
        SenderConfig config = new SenderConfig(); 

        EmailSender email = new EmailSender(audit, preview, config);
        SmsSender sms = new SmsSender(audit, preview);
        WhatsAppSender wa = new WhatsAppSender(audit, preview);

        Notification.Email emailMsg = new Notification.Email("Welcome", "Hello and welcome to SST!", "riya@sst.edu");
        Notification.Sms smsMsg = new Notification.Sms("Hello and welcome to SST!", "9876543210");
        
        email.send(emailMsg);
        sms.send(smsMsg);
        
        try {
            Notification.WhatsApp waMsg = new Notification.WhatsApp("Hello and welcome to SST!", "9876543210");
            wa.send(waMsg);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}