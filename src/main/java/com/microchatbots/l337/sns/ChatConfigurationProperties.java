package com.microchatbots.l337.sns;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import javax.validation.constraints.NotNull;

@ConfigurationProperties("notification")
public class ChatConfigurationProperties implements ChatConfiguration {
    @NonNull
    @NotNull
    private Integer chatId;

    @Override
    @NonNull
    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(@NonNull Integer chatId) {
        this.chatId = chatId;
    }
}
