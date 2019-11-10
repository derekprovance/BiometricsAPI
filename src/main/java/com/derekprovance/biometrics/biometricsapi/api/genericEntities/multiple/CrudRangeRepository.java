package com.derekprovance.biometrics.biometricsapi.api.genericEntities.multiple;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface CrudRangeRepository<T extends BaseRangeEntity, U extends Serializable> extends CrudRepository<T, U> {
    List<?> findByDatetimeBetween(LocalDateTime start, LocalDateTime end);
}
