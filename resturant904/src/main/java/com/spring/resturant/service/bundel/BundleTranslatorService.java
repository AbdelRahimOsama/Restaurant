package com.spring.resturant.service.bundel;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BundleTranslatorService {
    private static ResourceBundleMessageSource resourceBundleMessageSource;

    public BundleTranslatorService(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }


   /// accept_language = language(defulte : en )
   public static String getBundleMessage_en(String key) {
       return resourceBundleMessageSource.getMessage(key, null, new Locale("en"));
   }

    public static String getBundleMessage_ar(String key) {
        return resourceBundleMessageSource.getMessage(key, null, new Locale("ar"));
    }
}
