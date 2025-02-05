package daos;

import entities.Actor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ActorDAO {

    private final JdbcTemplate jdbcTemplate;

    public ActorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Actor> actorRowMapper = (rs, rowNum) -> new Actor(
            rs.getLong("actor_id"),
            rs.getString("name"),
            rs.getDate("birthdate").toLocalDate(),
            rs.getString("birthplace"),
            rs.getDouble("popularity"),
            rs.getString("gender"),
            rs.getString("biography"),
            rs.getString("image_url")
    );


    public List<Actor> findAll() {
        String sql = "SELECT * FROM actors";
        return jdbcTemplate.query(sql, actorRowMapper);
    }

    public Actor findById(Long id) {
        String sql = "SELECT * FROM actors WHERE id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Actor with this ID : " + id + " doesn't exist"));

    }

    public Actor save(Actor actor) {
        String sql = "INSERT INTO actors (name, birthdate, birthplace, popularity, gender, biography, image_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                actor.getName(),
                actor.getBirthdate(),
                actor.getBirthplace(),
                actor.getPopularity(),
                actor.getGender(),
                actor.getBiography(),
                actor.getImageUrl()
        );

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        Long id = jdbcTemplate.queryForObject(sqlGetId, Long.class);

        actor.setActorId(id);
        return actor;
    }

    public Actor update(Long id, Actor actor) {
        if (!actorExists(id)) {
            throw new RuntimeException("Actor with ID : " + id + " doesn't exist");
        }

        String sql = "UPDATE actors SET name = ?, birthdate = ?, birthplace = ?, popularity = ?, gender = ?, biography = ?, image_url = ? WHERE actor_id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                actor.getName(),
                actor.getBirthdate(),
                actor.getBirthplace(),
                actor.getPopularity(),
                actor.getGender(),
                actor.getBiography(),
                actor.getImageUrl(),
                id
        );

        if (rowsAffected <= 0) {
            throw new RuntimeException("Failure to update actor with ID : " + id);
        }

        return this.findById(id);
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM actors WHERE actor_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    private boolean actorExists(Long id) {
        String checkSql = "SELECT COUNT(*) FROM actors WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }

}

