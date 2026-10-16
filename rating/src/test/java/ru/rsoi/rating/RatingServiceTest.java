package ru.rsoi.rating;

import org.junit.jupiter.api.Test;
import ru.rsoi.rating.entity.Rating;
import ru.rsoi.rating.repo.RatingRepository;
import ru.rsoi.rating.service.RatingService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    @Test
    void clampsTo100() {
        RatingRepository repo = mock(RatingRepository.class);
        Rating rating = new Rating();
        rating.setUsername("u");
        rating.setStars(95);
        when(repo.findByUsername("u")).thenReturn(Optional.of(rating));
        when(repo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        assertEquals(100, new RatingService(repo).change("u", 50).getStars());
    }

    @Test
    void clampsTo1() {
        RatingRepository repo = mock(RatingRepository.class);
        Rating rating = new Rating();
        rating.setUsername("u");
        rating.setStars(5);
        when(repo.findByUsername("u")).thenReturn(Optional.of(rating));
        when(repo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        assertEquals(1, new RatingService(repo).change("u", -50).getStars());
    }
}
