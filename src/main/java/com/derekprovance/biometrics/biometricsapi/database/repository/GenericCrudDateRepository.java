package com.derekprovance.biometrics.biometricsapi.database.repository;

import com.derekprovance.biometrics.biometricsapi.database.entity.AbstractDateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoRepositoryBean
public interface GenericCrudDateRepository<T extends AbstractDateEntity, U extends Serializable> extends CrudRepository<T, U> {
    Object findFirstByDate(LocalDate date);
    List<?> findByDateBetween(LocalDate start, LocalDate end);
    List<?> findAllByDate(LocalDate date);
}
