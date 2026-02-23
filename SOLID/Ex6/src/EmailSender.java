public class EmailSender extends NotificationSender<Notification.Email> {
    private final SenderConfig config;

    public EmailSender(AuditLog audit, ConsolePreview preview, SenderConfig config) { 
        super(audit, preview); 
        this.config = config;
    }

    @Override
    public void send(Notification.Email n) {
        String body = n.body;
        if (body.length() > config.maxLen) {
            body = body.substring(0, config.maxLen);
        }
        preview.preview("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
    }
}