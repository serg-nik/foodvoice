package ru.serg_nik.foodvoice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.dto.VoiceDto;
import ru.serg_nik.foodvoice.model.Voice;
import ru.serg_nik.foodvoice.test_data.VoiceTestData;

import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ru.serg_nik.foodvoice.service.VoiceService.VOICE_CHANGE_STOP_TIME;
import static ru.serg_nik.foodvoice.test_data.VoiceTestData.VOICE_ADMIN;
import static ru.serg_nik.foodvoice.test_data.VoiceTestData.VOICE_USER;

class VoiceServiceTest extends BaseEntityServiceTest<Voice, VoiceService, VoiceTestData> {

    @Autowired
    public VoiceServiceTest(VoiceService service) {
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
        VoiceDto actual = assertDoesNotThrow(() ->
                service.vote(testData.getNew().getUser().getId(), testData.getNew().getMenu().getId())
        );
        assertDoesNotThrow(() -> service.get(actual.getId()));
        VoiceDto expected = new VoiceDto(testData.getNew());
        expected.setId(actual.getId());
        expected.setDate(actual.getDate());
        assertEquals(expected, actual);
    }

    static Stream<Arguments> initChangeVoteArguments() {
        return Stream.of(
                arguments(VOICE_ADMIN, VOICE_ADMIN.getMenu().getId()),
                arguments(VOICE_ADMIN, VOICE_USER.getMenu().getId())
        );
    }

    @ParameterizedTest
    @MethodSource("initChangeVoteArguments")
    void changeVote(Voice entity, UUID menuId) throws TimeoutException {
        if (LocalTime.now().isAfter(VOICE_CHANGE_STOP_TIME)) {
            assertThrows(TimeoutException.class, () -> service.changeVote(entity.getId(), menuId));
        } else {
            VoiceDto actual = service.changeVote(entity.getId(), menuId);
            assertEquals(new VoiceDto(entity), actual);
        }
    }

    @ParameterizedTest
    @MethodSource("initChangeVoteArguments")
    void wrongChangeVote(Voice entity, UUID menuId) {
        if (LocalTime.now().isBefore(VOICE_CHANGE_STOP_TIME)) {
            assertDoesNotThrow(() -> service.changeVote(entity.getId(), menuId));
        } else {
            assertThrows(TimeoutException.class, () -> service.changeVote(entity.getId(), menuId));
        }
    }

}
