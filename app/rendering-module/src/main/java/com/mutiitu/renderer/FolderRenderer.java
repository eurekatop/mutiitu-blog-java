package com.mycompany.renderer;


import org.graalvm.polyglot.*;
import java.nio.file.*;
import java.io.IOException;

public class FolderRenderer {
    public static void main(String[] args) throws IOException {
        // Create a JS execution context
        try (Context context = Context.newBuilder("js")
                .allowIO(true)  // Allow file access
                .build()) {

            // Load all JavaScript files from the "dist" directory
            Path jsDir = Paths.get("dist");
            Files.walk(jsDir)
                .filter(path -> path.toString().endsWith(".js"))
                .forEach(path -> {
                    try {
                        String jsCode = Files.readString(path);
                        context.eval("js", jsCode);
                        System.out.println("Loaded: " + path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            // Call a function from the loaded scripts
            Value renderFunction = context.eval("js", "Renderer.renderReact");
            String html = renderFunction.execute("App").asString();

            System.out.println("<!DOCTYPE html><html><body>" + html + "</body></html>");
        }
    }
}
