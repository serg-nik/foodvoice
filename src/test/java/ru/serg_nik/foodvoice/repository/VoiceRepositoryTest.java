package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.Voice;
import ru.serg_nik.foodvoice.test_data.VoiceTestData;

class VoiceRepositoryTest extends BaseEntityJpaRepositoryTest<Voice> {

    @Autowired
    VoiceRepositoryTest(VoiceRepository repository) {
        super(repository, new VoiceTestData(), PageRequest.of(0, 10, Sort.by(Meta.Voice.DATE).descending()));
    }

}
