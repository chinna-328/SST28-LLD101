public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        
        Exporter[] exporters = {
            new PdfExporter(),
            new CsvExporter(),
            new JsonExporter(),
            new XmlExporter()
        };

        for (Exporter e : exporters) {
            String name = e.getClass().getSimpleName().replace("Exporter", "");
            System.out.println(name + ": " + safe(e, req));
        }
    }

    private static String safe(Exporter e, ExportRequest r) {
        try {
            ExportResult out = e.export(r);
            return "OK bytes=" + out.bytes.length;
        } catch (IllegalArgumentException ex) {
            return "ERROR: " + ex.getMessage();
        }
    }
}