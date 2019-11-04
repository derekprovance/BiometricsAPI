package com.derekprovance.biometrics.biometricsapi.api.singleEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface CrudSingleRepository<T extends BaseSingleEntity, U extends Serializable> extends CrudRepository<T, U> {
    Object findByDate(LocalDate date);
    List<?> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
