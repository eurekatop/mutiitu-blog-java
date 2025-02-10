package com.mycompany.renderer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.callback.JavetBuiltInModuleResolver;
import com.caoccao.javet.interop.engine.IJavetEngine;
import com.caoccao.javet.interop.engine.JavetEnginePool;
import com.caoccao.javet.enums.JSRuntimeType;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.utils.JavetOSUtils;
import com.caoccao.javet.node.modules.NodeModuleModule;
import com.caoccao.javet.node.modules.NodeModuleProcess;

public class SingleScriptRendererJavedPool {

    public static void main(String[] args) throws Exception {

        try (JavetEnginePool<NodeRuntime> javetEnginePool = new JavetEnginePool<NodeRuntime>()) {
            javetEnginePool.getConfig().setJSRuntimeType(JSRuntimeType.Node);
            try (IJavetEngine<NodeRuntime> iJavetEngine = javetEnginePool.getEngine()) {
                NodeRuntime nodeRuntime = iJavetEngine.getV8Runtime();
                File workingDirectory = new File("/tmp/example/");
                
                

                
                // Set the require root directory so that Node.js is able to locate
                // node_modules.
                nodeRuntime
                        .getNodeModule(NodeModuleModule.class)
                        .setRequireRootDirectory(workingDirectory);

                var nodeModuleProcess = nodeRuntime.getNodeModule(NodeModuleModule.class);
                var scriptFile = new File("/tmp/example/output.js");


                var str =  nodeRuntime
                        .getExecutor(scriptFile)
                        .setModule(false)
                        .executeString();
                System.out.println(str);
            }
        }
    }
}
