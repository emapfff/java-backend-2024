package edu.java.service.jdbc;

import edu.java.domain.ChatRepository;
import edu.java.exceptions.AbsentChatException;
import edu.java.exceptions.IncorrectParametersException;
import edu.java.service.TgChatService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest("app.database-access-type=jdbc")
public class TgChatServiceTest {
    @Mock
    private ChatRepository chatRepository;
    @InjectMocks
    private TgChatService tgChatService;

    @Test
    void registerValidIdTest() {
        Long tgChatId = 123456L;
        when(chatRepository.existIdByTgChatId(tgChatId)).thenReturn(0);

        assertDoesNotThrow(() -> tgChatService.register(tgChatId));
        verify(chatRepository, times(1)).add(tgChatId);
    }

    @Test
    void registerInvalidIdTest() {
        Long tgChatId = -1L;
        assertThrows(IncorrectParametersException.class, () -> tgChatService.register(tgChatId));
        verify(chatRepository, never()).add(tgChatId);
    }

    @Test
    void registerExistingChatTest() {
        Long tgChatId = 123456L;
        when(chatRepository.existIdByTgChatId(tgChatId)).thenReturn(1);

        assertDoesNotThrow(() -> tgChatService.register(tgChatId));
        verify(chatRepository, never()).add(tgChatId);
    }

    @Test
    void unregisterValidIdTest() {
        Long tgChatId = 123456L;
        when(chatRepository.existIdByTgChatId(tgChatId)).thenReturn(1);

        assertDoesNotThrow(() -> tgChatService.unregister(tgChatId));
        verify(chatRepository, times(1)).remove(tgChatId);
    }

    @Test
    void unregisterInvalidIdTest() {
        Long tgChatId = -1L;
        assertThrows(IncorrectParametersException.class, () -> tgChatService.unregister(tgChatId));
        verify(chatRepository, never()).remove(tgChatId);
    }

    @Test
    void unregisterNonExistingChatTest() {
        Long tgChatId = 123456L;
        when(chatRepository.existIdByTgChatId(tgChatId)).thenReturn(0);

        assertThrows(AbsentChatException.class, () -> tgChatService.unregister(tgChatId));
        verify(chatRepository, never()).remove(tgChatId);
    }
}
