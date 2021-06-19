package com.j4v4developers.mail.listener;

import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.mail.event.ArticleCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleListener {

    @EventListener
    public void articleCreatedEventHandler(ArticleCreatedEvent articleCreatedEvent) {
        ArticleDto articleDto = (ArticleDto) articleCreatedEvent.getSource();
        System.out.println(articleDto.getAuthorId() + " isimli kullanıcı " + articleDto.getArticleTitle() + " başlıklı yeni bir makale yazdı.");
        System.out.println(articleCreatedEvent + " Yeni bir makale yazıldı.");
        // TODO : MAIL GONDERIMI
    }
}
