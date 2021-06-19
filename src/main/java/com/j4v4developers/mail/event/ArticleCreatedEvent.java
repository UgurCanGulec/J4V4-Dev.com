package com.j4v4developers.mail.event;

import org.springframework.context.ApplicationEvent;

public class ArticleCreatedEvent extends ApplicationEvent {

    public ArticleCreatedEvent(Object source) {
        super(source);
    }
}
