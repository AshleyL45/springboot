package daos;

import entities.Film;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmDAO {

    private final JdbcTemplate jdbcTemplate;

    public FilmDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Film> filmRowMapper = (rs, rowNum) -> new Film(
            rs.getLong("film_id"),
            rs.getString("title"),
            rs.getString("synopsis"),
            rs.getDate("release_date").toLocalDate(),
            rs.getString("production"),
            rs.getString("image_url")
    );

    public List<Film> findAll() {
        String sql = "SELECT * FROM films";
        return jdbcTemplate.query(sql, filmRowMapper);
    }

    public Film findById(Long id) {
        String sql = "SELECT * FROM films WHERE film_id = ?";
        return jdbcTemplate.query(sql, filmRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Film with ID : " + id + " doesn't exist"));
    }

    public Film save(Film film) {
        String sql = "INSERT INTO films (title, synopsis, release_date, production, image_url) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                film.getTitle(),
                film.getSynopsis(),
                film.getReleaseDate(),
                film.getProduction(),
                film.getImageUrl()
        );

        return film;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM films WHERE film_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
