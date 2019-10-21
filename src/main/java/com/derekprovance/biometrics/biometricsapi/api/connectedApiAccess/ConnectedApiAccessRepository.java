package com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess;

import org.springframework.data.repository.CrudRepository;

public interface ConnectedApiAccessRepository extends CrudRepository<ConnectedApiAccess, Integer> {
    ConnectedApiAccess findByApiAndType(ConnectedApi connectedApi, AccessType accessType);
}
