package com.mycompany.renderer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.callback.JavetBuiltInModuleResolver;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.utils.JavetOSUtils;
import com.caoccao.javet.values.reference.V8ValueObject;
import com.caoccao.javet.values.reference.V8ValuePromise;
import com.caoccao.javet.node.modules.NodeModuleAny;
import com.caoccao.javet.node.modules.NodeModuleModule;
import com.caoccao.javet.node.modules.NodeModuleProcess;

public class SingleScriptRendererJaveteExample {

     //protected static final Path ROOT_PATH =
     //       Path.of(System.getProperty("user.dir"), "src-react", "dist", "build-ssr", "assets").normalize();

     protected static final Path ROOT_PATH =
     Path.of("/home/rfranr/source/01-rfranr/resume/2026/resume").normalize();



    static public String renderByEsm() {
        // Create a Node.js runtime.
        try (NodeRuntime nodeRuntime =
                     V8Host.getNodeI18nInstance().createV8Runtime()) {
            // Set the SSR module resolver.
            nodeRuntime.setV8ModuleResolver(new SsrModuleResolver(ROOT_PATH));
            // Handle the event unhandledRejection, otherwise the Node.js process will exit immediately.
            nodeRuntime.getExecutor(
                    "process.on('unhandledRejection', (reason, promise) => {\n" +
                            "    console.error('Unhandled Rejection at:', promise, 'reason:', reason);\n" +
                            "});").executeVoid();
            // Prepare the code to call the render function.
            String codeString =
                    "//import * as render from './test.js';\n" +
                            "//globalThis.html = 'render()';";
            // Execute the code as a module.
            try (V8ValuePromise v8ValuePromise = nodeRuntime.getExecutor(codeString)
                    .setResourceName(ROOT_PATH.resolve("output.js").toString())
                    .setModule(true)
                    .execute()) {
                // Pump the Node.js event loop.
                nodeRuntime.await();
                // Return the result as a String.
                return nodeRuntime.getGlobalObject().getString("html");
            }
        } catch (Exception e) {
            //LOGGER.error("Failed to render by ESM.", e);
            return "Error rendering component: " + e.getMessage();
        }
    }

    static public String renderByCjs() {
        // Create a Node.js runtime.
        try (NodeRuntime nodeRuntime =
                     V8Host.getNodeI18nInstance().createV8Runtime()) {
            // Tell Node.js where the root path of require() is.
            nodeRuntime.getNodeModule(NodeModuleModule.class)
                    .setRequireRootDirectory(ROOT_PATH.toFile());
            // Set the working directory for Node.js.
            nodeRuntime.getNodeModule(NodeModuleProcess.class)
                    .setWorkingDirectory(ROOT_PATH.toString());
            // Call require('./render.js') to load the SSR render module.
            NodeModuleAny module =
                    nodeRuntime.getNodeModule("./output.js", NodeModuleAny.class);
            
            return  nodeRuntime.getExecutor(ROOT_PATH.toString()+"/output.js").executeString();

            //try (V8ValueObject v8ValueObject = module.getModuleObject()) {
            //    System.out.println(module.getName());
            //    // Call render('App') and return the result as a String.
            //    return v8ValueObject.invokeString("render", "App");
            //}
        } catch (Exception e) {
            return "Error rendering component m: " + e.getMessage();
        }
    }    

    public static void main(String[] args) throws Exception {
        var res = SingleScriptRendererJaveteExample.renderByCjs();
        System.out.println(res);
        // try (NodeRuntime nodeRuntime = V8Host.getNodeInstance().createV8Runtime()) {

        //     File workingDirectory = new File(JavetOSUtils.WORKING_DIRECTORY,
        //     "/tmp/example/");
        //     File workingDirectory = new File("/tmp/example/");
        //     nodeRuntime
        //             .getNodeModule(NodeModuleModule.class)
        //             .setRequireRootDirectory(workingDirectory);
        //     nodeRuntime
        //             .getNodeModule(NodeModuleProcess.class)
        //             .setWorkingDirectory(workingDirectory);

        //     v8Runtime.getExecutor("process.cwd = () => '/tmp/example'").executeVoid();
        //     var nodeModuleProcess = nodeRuntime.getNodeModule(NodeModuleProcess.class);

        //     var node_modules_path = Paths.get("/tmp/example/node_modules");
        //     nodeRuntime.setV8ModuleResolver(new SsrModuleResolver(node_modules_path));

        //     var _workingDirectory = nodeModuleProcess.getWorkingDirectory().toPath();
        //     var scriptFile = nodeModuleProcess
        //             .getWorkingDirectory()
        //             .toPath()
        //             .resolve("output.js")
        //             .toFile();

        //     System.out.println(_workingDirectory);
        //     System.out.println("getNodeModuleCount " + nodeRuntime.getNodeModuleCount());
        //     System.out.println("getV8ModuleCount " + nodeRuntime.getV8ModuleCount());
        //     System.out.println("script Path: " + scriptFile.toPath());

        //     nodeRuntime.getExecutor("process.cwd = () => '/tmp/example'").executeVoid();
        //     var html = nodeRuntime
        //             .getExecutor(scriptFile)
        //             .setModule(false)
        //             .executeString();

        //     System.out.println(html);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
