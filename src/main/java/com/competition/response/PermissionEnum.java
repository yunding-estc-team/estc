package com.competition.response;

import lombok.Getter;

@Getter
public enum PermissionEnum {

    WIKI("user:wiki"),
    COMMENT("user:comment"),
    ATTENTION("user:attention"),
    LOGIN("user:login");

    private String description;
    PermissionEnum(String description){this.description = description;}
}
