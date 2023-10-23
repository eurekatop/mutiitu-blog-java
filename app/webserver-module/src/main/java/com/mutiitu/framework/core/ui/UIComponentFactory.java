package com.mutiitu.framework.core.ui;

import org.slf4j.LoggerFactory;

import com.google.inject.Injector;

public class UIComponentFactory<T extends UIComponent> implements UiComopnentFactoryInterfaca {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    private static Injector injector;
    private final Class<T> clazz;

    public UIComponentFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static void configure(Injector injector) {
        UIComponentFactory.injector = injector;
    }

    public UIComponent  inst() {
        return UIComponentFactory.injector.getInstance(this.clazz);
    }
}
