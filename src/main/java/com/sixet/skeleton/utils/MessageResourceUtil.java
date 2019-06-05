package com.sixet.skeleton.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageResourceUtil {

    @Autowired
    private MessageSource messageResource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        this.accessor = new MessageSourceAccessor(this.messageResource, Locale.getDefault());
    }

    /**
     * Method to get messages.properties file.
     *
     * @param code Message code
     * @return Message text
     */
    public String get(String code) {
        return this.accessor.getMessage(code);

    }
}
