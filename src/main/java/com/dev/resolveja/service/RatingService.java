package com.dev.resolveja.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dev.resolveja.domain.Rating;
import com.dev.resolveja.domain.ServiceSolicitation;
import com.dev.resolveja.domain.User;
import com.dev.resolveja.dto.mapper.RatingMapper;
import com.dev.resolveja.dto.request.RatingRequest;
import com.dev.resolveja.dto.response.RatingResponse;
import com.dev.resolveja.repository.RatingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RatingService {
    
    private final RatingRepository ratingRepository;
    private final ServiceSolicitationService serviceSolicitationService;
    private final UserService userService;

    public List<RatingResponse> findAll(int page, int size, String orderBy, String direction) {
        Sort sort = Sort.by(
            Direction.fromString(direction),
            orderBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return this.ratingRepository.findAll(pageable)
            .stream()
            .map(RatingMapper::toResponse)
            .toList();
    }

    public RatingResponse findById(Long id) {
        return RatingMapper.toResponse(
            this.findEntityById(id)
        );
    }

    @Transactional 
    public RatingResponse create(Long serviceSolicitationId, RatingRequest request) {
        ServiceSolicitation solicitation = this.serviceSolicitationService.findEntityById(serviceSolicitationId);

        if(!solicitation.getStatus().isRealized()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Não é possível avaliar uma solitação não concluída");
        }

        Rating rating = RatingMapper.toEntity(request, solicitation);

        return RatingMapper.toResponse(
            this.ratingRepository.save(rating)
        );
    }

    public void deleteById(Long id) {
        this.findEntityById(id);
        this.ratingRepository.deleteById(id);
    }

    public List<RatingResponse> findAllRatingsByProviderId(Long providerId) {
        User provider = this.userService.findEntityById(providerId, "provider");

        if(provider.getServiceSolicitations().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Nenhum serviço solicitado para o prestador de id %d", providerId)
            );
        }

        return provider.getServiceSolicitations()
            .stream()
            .map(solicitation -> RatingMapper.toResponse(solicitation.getRatings()))
            .toList();
    }

    private Rating findEntityById(Long id) {
        return this.ratingRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Nenhuma avaliação encontrada para o id %d", id)
            ));
    }

}
