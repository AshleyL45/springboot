package daos;

import entities.FavoriteActor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteActorDAO {

    private final JdbcTemplate jdbcTemplate;

    public FavoriteActorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<FavoriteActor> favoriteActorRowMapper = (rs, rowNum) -> new FavoriteActor(
            rs.getLong("user_id"),
            rs.getLong("actor_id")
    );

    public List<FavoriteActor> findAll() {
        String sql = "SELECT * FROM favorite_actors";
        return jdbcTemplate.query(sql, favoriteActorRowMapper);
    }

    public FavoriteActor findById(Long userId, Long actorId) {
        String sql = "SELECT * FROM favorite_actors WHERE user_id = ? AND actor_id = ?";
        return jdbcTemplate.query(sql, favoriteActorRowMapper, userId, actorId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("FavoriteActor with User ID : " + userId + " and Actor ID : " + actorId + " doesn't exist"));
    }

    public FavoriteActor save(FavoriteActor favoriteActor) {
        String sql = "INSERT INTO favorite_actors (user_id, actor_id) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                favoriteActor.getUserId(),
                favoriteActor.getActorId()
        );

        return favoriteActor;
    }

    public boolean delete(Long userId, Long actorId) {
        String sql = "DELETE FROM favorite_actors WHERE user_id = ? AND actor_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId, actorId);
        return rowsAffected > 0;
    }

    private boolean favoriteActorExists(Long userId, Long actorId) {
        String checkSql = "SELECT COUNT(*) FROM favorite_actors WHERE user_id = ? AND actor_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId, actorId);
        return count > 0;
    }
}
