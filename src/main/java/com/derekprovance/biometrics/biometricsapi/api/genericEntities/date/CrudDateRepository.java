package com.derekprovance.biometrics.biometricsapi.api.genericEntities.date;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.DateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoRepositoryBean
public interface CrudDateRepository<T extends DateEntity, U extends Serializable> extends CrudRepository<T, U> {
    Object findByDate(LocalDate date);
    List<?> findByDateBetween(LocalDate start, LocalDate end);
    List<?> findAllByDate(LocalDate date);
}
