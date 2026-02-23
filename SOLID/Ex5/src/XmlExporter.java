import java.nio.charset.StandardCharsets;

public class XmlExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        validateRequest(req);

        String xml = "<report>\n" +
                     "  <title>" + escape(req.title) + "</title>\n" +
                     "  <body>" + escape(req.body) + "</body>\n" +
                     "</report>";
        
        return new ExportResult("application/xml", xml.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}