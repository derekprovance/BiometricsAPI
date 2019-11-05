package com.derekprovance.biometrics.biometricsapi.api.utility.connectedApiAccess;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ConnectedApiAccess {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ConnectedApi api;

    @NotNull
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccessType getType() {
        return type;
    }

    public void setType(AccessType type) {
        this.type = type;
    }

    public ConnectedApi getApi() {
        return api;
    }

    public void setApi(ConnectedApi api) {
        this.api = api;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
