package com.mutiitu.renderer;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.converters.JavetProxyConverter;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interception.jvm.JavetJVMInterceptor;
import com.caoccao.javet.interop.NodeRuntime;


public class SingleScriptRendererJavedWithParams {

    Dictionary<String, String> slots;
    public SingleScriptRendererJavedWithParams() {
        this.slots = new Hashtable<String, String>();
        double randomValue = Math.random();
        
        String formattedValue = String.format("%.20f", randomValue);
        this.slots.put("0", "slot 0" + formattedValue);
        this.slots.put("1", "slot 1" + formattedValue);
        this.slots.put("2", "slot 2" + formattedValue );
        this.slots.put("3", "slot 3" + formattedValue );
    }
    public String getSlot (String key) {
        return this.slots.get(key);
    }


    public static void main(String[] args) throws Exception {
        try (NodeRuntime nodeRuntime = V8Host.getNodeInstance().createV8Runtime()) {

            File workingDirectory = new File("/home/rfranr/source/01-rfranr/resume/2026/resume/dist/test.resolver/assets");

            JavetProxyConverter javetProxyConverter = new JavetProxyConverter();

            //javetProxyConverter.getConfig().setReflectionObjectFactory(JavetBuddy);)

            nodeRuntime.setConverter(javetProxyConverter);

            JavetJVMInterceptor javetJVMInterceptor = new JavetJVMInterceptor(nodeRuntime);
            javetJVMInterceptor.register(nodeRuntime.getGlobalObject());

            // Step 6: Create package 'java'.
            //nodeRuntime.getExecutor("let java = javet.package.java").executeVoid();
            nodeRuntime.getExecutor("let mu = javet.package.com.mutiitu.renderer").executeVoid();

            for (int i = 0; i < 1000000.0f; i++) {
                String slotKey = String.valueOf(i);
                String jsCode = """
                    if ( globalThis.mu == undefined ) {
                        globalThis.mu = new mu.SingleScriptRendererJavedWithParams(); 
                    }
                    console.log ( {slotKey} % 4 )
                    globalThis.mu.getSlot(({slotKey} % 4) + "");
                
                    // new mu.SingleScriptRendererJavedWithParams().getSlot( '{slotKey}');
                """;
                jsCode = jsCode.replace("{slotKey}", slotKey);
                System.out.println(nodeRuntime.getExecutor(jsCode).executeString());
            }
            //String slotKey = "1";
            //String jsCode = """
            //    let sb = new mu.SingleScriptRendererJavedWithParams();
            //    sb.getSlot( '{slotKey}' );
            //""";
            //jsCode = jsCode.replace("{slotKey}", slotKey);
            //System.out.println(nodeRuntime.getExecutor(jsCode).executeString());



        } 
        catch (JavetException e) {
            e.fillInStackTrace();
            e.printStackTrace();
        }
    }
}



