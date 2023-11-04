package com.mutiitu.framework.core.ui;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;

public class UIComponent {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private PebbleEngine engine;
    private PebbleTemplate compiledTemplate;

    private Map<String, Object> context = new HashMap<>();

    protected UIComponent() {
        this.engine = new PebbleEngine.Builder().build();
        // TODO:remove /java
        // get file template
        var fileTemplate = this.getClass().getCanonicalName();
        fileTemplate = "" + fileTemplate.replace('.', '/') + ".html"; // TOdo: file separator
        logger.info("fileTemplate ##############3 " + fileTemplate);

        // this.compiledTemplate = engine.getTemplate("templates/home2.html");
        this.compiledTemplate = engine.getTemplate(fileTemplate);
    }

    protected String UUID() {
        String uuidWithHyphens = java.util.UUID.randomUUID().toString();
        String uuidWithUnderscores = uuidWithHyphens.replace("-", "_");
        return uuidWithUnderscores;
    }

    protected void addVar(String name, Object value) {
        this.context.put(name, value);
    }

    protected String render() throws IOException {
        Writer writer = new StringWriter();

        // add UUID to template 
        this.context.put("UUID", this.UUID());
        
        // populate template with public variables
        var c = this.getClass();
        logger.info("--------------- render template -------------");
        logger.info(c.getCanonicalName());
        logger.info(c.getPackageName());
        var fields = c.getDeclaredFields();
        for (Field field : fields) {
            logger.info(field.getName());
            logger.info(field.getModifiers() + "");
            var className = field.getType().getSimpleName();

            if (className != "UIComponentFactory" && field.getModifiers() == 1 || field.getModifiers() == 17) /*
                                                                                                               * public
                                                                                                               * ,
                                                                                                               * public
                                                                                                               * final
                                                                                                               */ {
                try {
                    var fieldName = field.getName();
                    var fieldValue = field.get(this);
                    logger.info( fieldName + " / " + fieldValue);
                    this.context.put(fieldName, fieldValue);
                } catch (Exception e) {
                    //
                    logger.error("null", e);
                }

            }

            if (className.equals("UIComponentFactory")) {
                try {
                    logger.info("########### UIComponentFactory");
                    var fieldValue = field.get(this);
                    logger.info(fieldValue.toString());
                    UiComopnentFactoryInterfaca aa = (UiComopnentFactoryInterfaca) field.get(this);
                    logger.info(aa.toString());
                    var uiComponent = aa.inst();

                    var fieldName = field.getName();
                    logger.info(fieldName);
                    logger.info(uiComponent.getClass().getCanonicalName());
                    // var inst = (UiComopnentFactoryInterfaca) field.get(this);
                    // var uiComponent = inst.inst();

                    // logger.info (uiComponent.toString() );

                    this.context.put(fieldName, uiComponent);
                } catch (Exception e) {
                    //
                    logger.error("null", e);
                }

            }

        }

        try {
            this.compiledTemplate.evaluate(writer, this.context);
            return writer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public String toString() {
        try {
            return this.render();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
