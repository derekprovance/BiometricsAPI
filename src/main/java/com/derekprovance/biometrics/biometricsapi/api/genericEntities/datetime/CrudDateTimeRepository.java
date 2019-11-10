package com.derekprovance.biometrics.biometricsapi.api.genericEntities.datetime;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.DateTimeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface CrudDateTimeRepository<T extends DateTimeEntity, U extends Serializable> extends CrudRepository<T, U> {
    List<?> findByDatetimeBetween(LocalDateTime start, LocalDateTime end);
}
