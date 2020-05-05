package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.Voice;
import ru.serg_nik.foodvoice.test_data.VoiceTestData;

class VoiceRepositoryTest extends BaseEntityJpaRepositoryTest<Voice> {

    @Autowired
    VoiceRepositoryTest(VoiceRepository repository) {
        super(repository, new VoiceTestData());
    }

}
