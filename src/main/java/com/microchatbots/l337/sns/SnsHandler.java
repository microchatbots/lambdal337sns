package com.microchatbots.l337.sns;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.microchatbots.telegrambots.api.BlockingTelegramBot;
import com.microchatbots.telegrambots.api.TelegramBot;
import com.microchatbots.telegrambots.core.send.SendMessage;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Introspected
public class SnsHandler extends MicronautRequestHandler<SNSEvent, Void> {
    private static final Logger LOG = LoggerFactory.getLogger(SnsHandler.class);

    @Inject
    public ChatConfiguration chatConfiguration;

    @Inject
    @Named("l337")
    public BlockingTelegramBot telegramBot;

    @Override
    public Void execute(SNSEvent input) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("sns: {}", input.toString());
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("chat id: {}", chatConfiguration.getChatId());
        }

        for (SNSEvent.SNSRecord snsRecord : input.getRecords()) {
            sendMessage(snsRecord.getSNS().getMessage());
        }

        return null;
    }

    private void sendMessage(String message) {
        telegramBot.sendMessage(message, chatConfiguration.getChatId());
    }
}
