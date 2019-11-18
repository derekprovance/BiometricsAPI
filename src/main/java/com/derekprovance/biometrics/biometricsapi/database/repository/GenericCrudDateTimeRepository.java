package com.derekprovance.biometrics.biometricsapi.database.repository;

import com.derekprovance.biometrics.biometricsapi.database.entity.AbstractDateTimeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface GenericCrudDateTimeRepository<T extends AbstractDateTimeEntity, U extends Serializable> extends CrudRepository<T, U> {
    List<AbstractDateTimeEntity> findByDatetimeBetween(LocalDateTime start, LocalDateTime end);
}
