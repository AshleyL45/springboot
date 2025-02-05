package entities;

public class FilmCategory {
    private Long filmId;
    private Long categoryId;

    public FilmCategory() {}

    public FilmCategory(Long filmId, Long categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }

    // Getters et Setters
    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
