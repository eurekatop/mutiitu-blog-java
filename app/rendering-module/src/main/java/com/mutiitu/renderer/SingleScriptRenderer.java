package com.mycompany.renderer;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.graalvm.polyglot.*;

public class SingleScriptRenderer {
    public static void main(String[] args) throws Exception {
        // Path to the JavaScript file you want to execute
        //String jsCode = Files.readString(Paths.get("/tmp/example/test.js"));
        String jsCode = Files.readString(Paths.get("/home/rfranr/source/01-rfranr/resume/2026/resume/output.js"));

        

        // Create a GraalVM Context for JavaScript
        try (Context context = Context
                .newBuilder("js") // Create a context for JavaScript
                .allowAllAccess(true) // Allow full access to Java from JavaScript
                .option("js.commonjs-require", "true") // ✅ Enable CommonJS (require)
                .option("js.commonjs-require-cwd", "/tmp/example/node_modules") // ✅ Set module resolution path
                .option("js.ecmascript-version", "2022") // ✅ Ensure modern JS features work
                .build()
                ) {
            // Execute the JavaScript code
            Value result = context.eval("js", jsCode); // Load and evaluate the JS code

            // Assuming the JS code defines a 'render' function, execute it
            String html = result.execute("App").asString();
            System.out.println("<!DOCTYPE html><html><body>" + html + "</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



