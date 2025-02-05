package entities;

import java.time.LocalDate;

public class WatchedFilm {
    private Long userId;
    private Long filmId;
    private Double rating;
    private LocalDate watchDate;

    public WatchedFilm() {}

    public WatchedFilm(Long userId, Long filmId, Double rating, LocalDate watchDate) {
        this.userId = userId;
        this.filmId = filmId;
        this.rating = rating;
        this.watchDate = watchDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }
}
