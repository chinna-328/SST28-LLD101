public class SmsSender extends NotificationSender<Notification.Sms> {
    public SmsSender(AuditLog audit, ConsolePreview preview) { 
        super(audit, preview); 
    }

    @Override
    public void send(Notification.Sms n) {
        preview.preview("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}