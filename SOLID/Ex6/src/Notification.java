public abstract class Notification {
    public final String body;

    protected Notification(String body) {
        this.body = body;
    }

    public static class Email extends Notification {
        public final String subject;
        public final String email;
        
        public Email(String subject, String body, String email) {
            super(body);
            this.subject = subject;
            this.email = email;
        }
    }

    public static class Sms extends Notification {
        public final String phone;
        
        public Sms(String body, String phone) {
            super(body);
            this.phone = phone;
        }
    }

    public static class WhatsApp extends Notification {
        public final String phone;
        
        public WhatsApp(String body, String phone) {
            super(body);
            if (phone == null || !phone.startsWith("+")) {
                throw new IllegalArgumentException("phone must start with + and country code");
            }
            this.phone = phone;
        }
    }
}