package ru.nsu.lopatkin.dis.models.manager.request;

import lombok.Getter;

@Getter
public class HashCrackRequest {
    private String hash;
    private Integer maxLength;
}
