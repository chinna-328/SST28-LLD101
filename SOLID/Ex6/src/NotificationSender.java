public abstract class NotificationSender<T extends Notification> {
    protected final AuditLog audit;
    protected final ConsolePreview preview;

    protected NotificationSender(AuditLog audit, ConsolePreview preview) { 
        this.audit = audit; 
        this.preview = preview;
    }

    public abstract void send(T n);
}