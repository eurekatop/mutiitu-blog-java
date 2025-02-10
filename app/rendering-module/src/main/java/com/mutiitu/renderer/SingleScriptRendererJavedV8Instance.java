package com.mycompany.renderer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.callback.JavetBuiltInModuleResolver;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.utils.JavetOSUtils;
import com.caoccao.javet.node.modules.NodeModuleModule;
import com.caoccao.javet.node.modules.NodeModuleProcess;


public class SingleScriptRendererJavedV8Instance {



    public static void main(String[] args) throws Exception {
        try (NodeRuntime nodeRuntime = V8Host.getNodeI18nInstance().createV8Runtime()) {

            //File workingDirectory = new File(JavetOSUtils.WORKING_DIRECTORY, "/tmp/example/");
            File workingDirectory = new File("/home/rfranr/source/01-rfranr/resume/2026/resume/dist/test.resolver/assets");

            nodeRuntime
                .getNodeModule(NodeModuleModule.class)
                .setRequireRootDirectory(workingDirectory);
            nodeRuntime
                .getNodeModule(NodeModuleProcess.class)
                .setWorkingDirectory(workingDirectory);

            //v8Runtime.getExecutor("process.cwd = () => '/tmp/example'").executeVoid();
            var nodeModuleProcess = nodeRuntime.getNodeModule(NodeModuleProcess.class);

            //var node_modules_path = Paths.get("/tmp/example/node_modules");
            //nodeRuntime.setV8ModuleResolver(new SsrModuleResolver(node_modules_path));



            var _workingDirectory = nodeModuleProcess.getWorkingDirectory().toPath();
            var scriptFile = nodeModuleProcess
                    .getWorkingDirectory()
                    .toPath()
                    .resolve("test.js")
                    .toFile();




            System.out.println( _workingDirectory );
            System.out.println( "getNodeModuleCount " + nodeRuntime.getNodeModuleCount() );
            System.out.println( "getV8ModuleCount " + nodeRuntime.getV8ModuleCount() );
            System.out.println( "script Path: " + scriptFile.toPath() );

            
            nodeRuntime.getExecutor("globalThis.param_1 = 'not found'").executeVoid();
            
            var jsCode = """
                        const process = {
                            argv: ['node', 'output.js']
                        };
                        const myModule = require('./test.js');
                        //const {html} = require('./output.js');
                        //import {html} from  require('./output.js');

                        console.log('heloo', myModule)
                        console.log('heloo', myModule.html())
                    """;
            var html = 
            nodeRuntime
            .getExecutor(jsCode)
            .setModule(false)
            .executeString();

            //var html =  nodeRuntime
            //   .getExecutor(scriptFile)
            //   .setModule(false)
            //   .executeString();
       




            System.out.println( html );
        } 
        catch (JavetException e) {
            e.fillInStackTrace();
            e.printStackTrace();
        }
    }
}



