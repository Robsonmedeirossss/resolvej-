package com.dev.resolveja.dto.mapper;

import com.dev.resolveja.domain.Rating;
import com.dev.resolveja.domain.ServiceSolicitation;
import com.dev.resolveja.domain.enums.RatingStatus;
import com.dev.resolveja.dto.request.RatingRequest;
import com.dev.resolveja.dto.response.RatingResponse;

public class RatingMapper {
    public static Rating toEntity(RatingRequest request ,ServiceSolicitation serviceSolicitation) {
        return Rating.builder()
            .note(request.note())
            .comment(request.comment())
            .status(RatingStatus.PEDNING)
            .serviceSolicitation(serviceSolicitation)
        .build();
    }

    public static RatingResponse toResponse(Rating rating) {
        return RatingResponse.builder()
            .id(rating.getId())
            .note(rating.getNote())
            .comment(rating.getComment())
            .status(rating.getStatus())
            .serviceSolicitationId(rating.getServiceSolicitation().getId())
            .createdAt(rating.getCreatedAt())
        .build();
    }
}
