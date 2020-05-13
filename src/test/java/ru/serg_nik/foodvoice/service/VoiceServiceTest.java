package ru.serg_nik.foodvoice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.dto.VoiceDto;
import ru.serg_nik.foodvoice.model.Voice;
import ru.serg_nik.foodvoice.test_data.VoiceTestData;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ru.serg_nik.foodvoice.test_data.VoiceTestData.VOICE_ADMIN;
import static ru.serg_nik.foodvoice.test_data.VoiceTestData.VOICE_USER;

class VoiceServiceTest extends BaseEntityServiceTest<Voice, VoiceService, VoiceTestData> {

    static Stream<Arguments> initChangeVoteArguments() {
        return Stream.of(
                arguments(VOICE_USER, VOICE_USER.getUser().getId(), VOICE_ADMIN.getMenu().getId()),
                arguments(VOICE_ADMIN, VOICE_ADMIN.getUser().getId(), VOICE_USER.getMenu().getId())
        );
    }

    static Stream<Arguments> initChangeNotFoundVoteArguments() {
        return Stream.of(
                arguments(VOICE_USER, VOICE_ADMIN.getUser().getId(), VOICE_ADMIN.getMenu().getId()),
                arguments(VOICE_ADMIN, VOICE_USER.getUser().getId(), VOICE_USER.getMenu().getId())
        );
    }

    @Autowired
    VoiceServiceTest(VoiceService service) {
        super(service, new VoiceTestData());
    }

    @Override
    protected void updateNewExpectedEntity(Voice expected, Voice actual) {
        super.updateNewExpectedEntity(expected, actual);
        assertNotNull(actual.getDate());
        expected.setDate(actual.getDate());
    }

    @Test
    void vote() {
        Voice voice = assertDoesNotThrow(() ->
                service.vote(testData.getNew().getUser().getId(), testData.getNew().getMenu().getId())
        );
        assertDoesNotThrow(() -> service.get(voice.getId()));
        VoiceDto actual = new VoiceDto(voice);
        VoiceDto expected = new VoiceDto(testData.getNew());
        expected.setId(actual.getId());
        expected.setDate(actual.getDate());
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("initChangeVoteArguments")
    void changeVote(Voice expected, UUID userId, UUID menuId) throws TimeoutException {
        if (LocalTime.now().isAfter(service.getVoiceChangeStopLocalTime())) {
            assertThrows(TimeoutException.class, () -> service.changeVote(expected.getId(), userId, menuId));
        } else {
            Voice actual = service.changeVote(expected.getId(), userId, menuId);
            assertEquals(new VoiceDto(expected), new VoiceDto(actual));
        }
    }

    @ParameterizedTest
    @MethodSource("initChangeVoteArguments")
    void wrongChangeVote(Voice entity, UUID userId, UUID menuId) {
        if (LocalTime.now().isBefore(service.getVoiceChangeStopLocalTime())) {
            assertDoesNotThrow(() -> service.changeVote(entity.getId(), userId, menuId));
        } else {
            assertThrows(TimeoutException.class, () -> service.changeVote(entity.getId(), userId, menuId));
        }
    }

    @ParameterizedTest
    @MethodSource("initChangeNotFoundVoteArguments")
    void changeVoteNotFound(Voice expected, UUID userId, UUID menuId) {
        assertThrows(EntityNotFoundException.class, () -> service.changeVote(expected.getId(), userId, menuId));
    }

}
