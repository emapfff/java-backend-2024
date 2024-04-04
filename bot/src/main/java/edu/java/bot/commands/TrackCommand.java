package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import dto.AddLinkRequest;
import edu.java.bot.clients.ScrapperClient;
import edu.java.bot.tools.LinkParse;
import edu.java.bot.tools.Urls;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TrackCommand implements Command {
    private final ScrapperClient scrapperClient;
    private final LinkParse linkParse;

    @Override
    public String name() {
        return "/track";
    }

    @Override
    public String description() {
        return "начать отслеживание ссылки. \n"
            + "После введении команды напишите в отдельном сообщении ссылку, которую хотите отслеживать.";
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        URI url = URI.create(update.message().text());
        if (linkParse.parse(url) == Urls.INCORRECT_URL) {
            return new SendMessage(chatId, "Указана неверная ссылка.\n"
                + "Требуются ссылки репозитория Github или вопроса из StackOverflow");
        }
        AddLinkRequest addLinkRequest = new AddLinkRequest(URI.create(update.message().text()));
        return scrapperClient.addLink(chatId, addLinkRequest)
            .map(
                linkResponse -> new SendMessage(chatId, "Ссылка успешна добавлена")
            )
            .onErrorResume(
                throwable -> Mono.just(new SendMessage(chatId, throwable.getMessage()))
            )
            .block();
    }
}
