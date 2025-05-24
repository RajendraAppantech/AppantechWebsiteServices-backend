package com.web;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class HtmlTemplateProcessor {

    public void generateHtmlFromTemplate(String templatePath, Map<String, String> data, String outputPath) throws IOException {
        // Read HTML template
        String html = Files.readString(Paths.get(templatePath), StandardCharsets.UTF_8);

        // Replace all placeholders
        for (Map.Entry<String, String> entry : data.entrySet()) {
            html = html.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }

        // Write updated HTML to output path
        Files.write(Paths.get(outputPath), html.getBytes(StandardCharsets.UTF_8));
    }
}
