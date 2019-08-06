package com.competition.response;

import lombok.Getter;

@Getter
public enum PermissionEnum {

    WIKI("user:wiki"),
    COMMENT("user:comment"),
    ATTENTION("user:attention"),
    PRAISE("user:praise");

    private String description;
    PermissionEnum(String description){this.description = description;}
}
