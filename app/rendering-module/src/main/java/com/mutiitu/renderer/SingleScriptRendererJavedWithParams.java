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
 

    Dictionary<String, SlotRecord> slots;
    public SingleScriptRendererJavedWithParams() {
        this.slots = new Hashtable<String, SlotRecord>();

        double randomValue = Math.random();
        String formattedValue = String.format("%.20f", randomValue);
        this.slots.put("0",new SlotRecord(0, "slot 0" + formattedValue));
        this.slots.put("1",new SlotRecord(0, "slot 0" + formattedValue));
        this.slots.put("2",new SlotRecord(0, "slot 0" + formattedValue));
        this.slots.put("3",new SlotRecord(0, "slot 0" + formattedValue));
    }
    public SlotRecord getSlot (String key) {
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

            for (int i = 0; i < 10.0f; i++) {
                String slotKey = String.valueOf(i);
                String jsCode = """
                    if ( globalThis.mu == undefined ) {
                        globalThis.mu = new mu.SingleScriptRendererJavedWithParams(); 
                        var muKey;
                    }
                    muKey = ({slotKey} % 4) + "";
                    
                    globalThis.mu.getSlot(muKey);
                
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



