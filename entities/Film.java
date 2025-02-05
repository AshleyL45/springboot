package entities;

import java.time.LocalDate;

public class Film {
    private Long filmId;
    private String title;
    private String synopsis;
    private LocalDate releaseDate;
    private String production;
    private String imageUrl;

    public Film() {}

    public Film(Long filmId, String title, String synopsis, LocalDate releaseDate, String production, String imageUrl) {
        this.filmId = filmId;
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.production = production;
        this.imageUrl = imageUrl;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

