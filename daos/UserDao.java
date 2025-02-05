package daos;

import entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, _) -> new User(
            rs.getLong("user_id"),
            rs.getString("email"),
            rs.getString("password")
    );

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User with ID : " + id + " doesn't exist"));
    }

    public User save(User user) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        Long id = jdbcTemplate.queryForObject(sqlGetId, Long.class);

        user.setUserId(id);
        return user;
    }

    public User update(Long id, User user) {
        if (!userExists(id)) {
            throw new RuntimeException("User with ID : " + id + " doesn't exist");
        }

        String sql = "UPDATE users SET email = ?, password = ? WHERE user_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Failed to update user with ID : " + id);
        }

        return this.findById(id);
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    private boolean userExists(Long id) {
        String checkSql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
}
