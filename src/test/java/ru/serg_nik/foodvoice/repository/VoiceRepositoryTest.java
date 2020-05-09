package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.Voice;
import ru.serg_nik.foodvoice.test_data.VoiceTestData;

class VoiceRepositoryTest extends BaseEntityJpaRepositoryTest<Voice, VoiceRepository, VoiceTestData> {

    @Autowired
    VoiceRepositoryTest(VoiceRepository repository) {
        super(repository, new VoiceTestData());
    }

}
