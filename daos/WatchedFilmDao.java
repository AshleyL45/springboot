package daos;

import entities.WatchedFilm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class WatchedFilmDao {

    private final JdbcTemplate jdbcTemplate;

    public WatchedFilmDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<WatchedFilm> watchedFilmRowMapper = (rs, _) -> new WatchedFilm(
            rs.getLong("user_id"),
            rs.getLong("film_id"),
            rs.getDouble("rating"),
            rs.getDate("watch_date").toLocalDate()
    );

    public List<WatchedFilm> findAll() {
        String sql = "SELECT * FROM watched_films";
        return jdbcTemplate.query(sql, watchedFilmRowMapper);
    }

    public WatchedFilm findById(Long userId, Long filmId) {
        String sql = "SELECT * FROM watched_films WHERE user_id = ? AND film_id = ?";
        return jdbcTemplate.query(sql, watchedFilmRowMapper, userId, filmId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("WatchedFilm with User ID : " + userId + " and Film ID : " + filmId + " doesn't exist"));
    }

    public WatchedFilm save(WatchedFilm watchedFilm) {
        String sql = "INSERT INTO watched_films (user_id, film_id, rating, watch_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                watchedFilm.getUserId(),
                watchedFilm.getFilmId(),
                watchedFilm.getRating(),
                watchedFilm.getWatchDate()
        );

        return watchedFilm;
    }

    public WatchedFilm update(Long userId, Long filmId, WatchedFilm watchedFilm) {
        if (!watchedFilmExists(userId, filmId)) {
            throw new RuntimeException("WatchedFilm with User ID : " + userId + " and Film ID : " + filmId + " does not exist");
        }

        String sql = "UPDATE watched_films SET rating = ?, watch_date = ? WHERE user_id = ? AND film_id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                watchedFilm.getRating(),
                watchedFilm.getWatchDate(),
                userId,
                filmId
        );

        if (rowsAffected <= 0) {
            throw new RuntimeException("WatchedFilm update with User ID fails : " + userId + " and Film ID : " + filmId);
        }

        return this.findById(userId, filmId);
    }

    public boolean delete(Long userId, Long filmId) {
        String sql = "DELETE FROM watched_films WHERE user_id = ? AND film_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId, filmId);
        return rowsAffected > 0;
    }

    private boolean watchedFilmExists(Long userId, Long filmId) {
        String checkSql = "SELECT COUNT(*) FROM watched_films WHERE user_id = ? AND film_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId, filmId);
        return count > 0;
    }
}
