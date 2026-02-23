public class WhatsAppSender extends NotificationSender<Notification.WhatsApp> {
    public WhatsAppSender(AuditLog audit, ConsolePreview preview) { 
        super(audit, preview); 
    }

    @Override
    public void send(Notification.WhatsApp n) {
        // Safe: No preconditions tightened here because the payload guarantees a '+'
        preview.preview("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}