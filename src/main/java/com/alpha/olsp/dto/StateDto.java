package com.alpha.olsp.dto;

import lombok.Builder;

@Builder
public record StateDto(
        String stateId,
        String stateName
) {
}