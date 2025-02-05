package daos;

import entities.FilmCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmCategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public FilmCategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<FilmCategory> filmCategoryRowMapper = (rs, _) -> new FilmCategory(
            rs.getLong("film_id"),
            rs.getLong("category_id")
    );

    public List<FilmCategory> findAll() {
        String sql = "SELECT * FROM film_categories";
        return jdbcTemplate.query(sql, filmCategoryRowMapper);
    }

    public FilmCategory findById(Long filmId, Long categoryId) {
        String sql = "SELECT * FROM film_categories WHERE film_id = ? AND category_id = ?";
        return jdbcTemplate.query(sql, filmCategoryRowMapper, filmId, categoryId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("FilmCategory with Film ID : " + filmId + " and Category ID : " + categoryId + " doesn't exist"));
    }

    public FilmCategory save(FilmCategory filmCategory) {
        String sql = "INSERT INTO film_categories (film_id, category_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, filmCategory.getFilmId(), filmCategory.getCategoryId());

        return filmCategory;
    }

    public boolean delete(Long filmId, Long categoryId) {
        String sql = "DELETE FROM film_categories WHERE film_id = ? AND category_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, filmId, categoryId);
        return rowsAffected > 0;
    }
}
