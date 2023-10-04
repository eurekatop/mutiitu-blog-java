package com.mutiitu.framework.core.ui;

import java.util.HashMap;

import com.google.inject.Injector;

public class UIComponentFactory<T extends UIComponent> implements UiComopnentFactoryInterfaca {

    private static HashMap<String,UIComponent> mapa= new HashMap<>();
    private static Injector injector;

    public UIComponentFactory() {
    }

    public static void configure(Injector injector) {
        UIComponentFactory.injector = injector;
    }


    public static void AddComponent(String componentName, UIComponent component) {
        UIComponentFactory.mapa.put(componentName, component);
    }



    public UIComponent  inst() {
        var clazz = UIComponentFactory.mapa.get("Header3");
        return UIComponentFactory.injector.getInstance(clazz.getClass());
    }


}
