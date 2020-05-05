package ru.serg_nik.foodvoice.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.serg_nik.foodvoice.model.Voice;

@Transactional(readOnly = true)
public interface VoiceRepository extends BaseEntityJpaRepository<Voice> {
}
