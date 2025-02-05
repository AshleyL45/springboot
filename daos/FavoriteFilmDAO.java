package daos;

import entities.FavoriteFilm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteFilmDAO {

    private final JdbcTemplate jdbcTemplate;

    public FavoriteFilmDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<FavoriteFilm> favoriteFilmRowMapper = (rs, rowNum) -> new FavoriteFilm(
            rs.getLong("user_id"),
            rs.getLong("film_id")
    );

    public List<FavoriteFilm> findAll() {
        String sql = "SELECT * FROM favorite_films";
        return jdbcTemplate.query(sql, favoriteFilmRowMapper);
    }

    public FavoriteFilm findById(Long userId, Long filmId) {
        String sql = "SELECT * FROM favorite_films WHERE user_id = ? AND film_id = ?";
        return jdbcTemplate.query(sql, favoriteFilmRowMapper, userId, filmId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("FavoriteFilm with User ID : " + userId + " and Film ID : " + filmId + " doesn't exist"));
    }

    public FavoriteFilm save(FavoriteFilm favoriteFilm) {
        String sql = "INSERT INTO favorite_films (user_id, film_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, favoriteFilm.getUserId(), favoriteFilm.getFilmId());

        return favoriteFilm;
    }

    public boolean delete(Long userId, Long filmId) {
        String sql = "DELETE FROM favorite_films WHERE user_id = ? AND film_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId, filmId);
        return rowsAffected > 0;
    }
}
