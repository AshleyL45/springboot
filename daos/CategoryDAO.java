package daos;

import entities.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO {

    private final JdbcTemplate jdbcTemplate;

    public CategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Category> categoryRowMapper = (rs, rowNum) -> new Category(
            rs.getLong("category_id"),
            rs.getString("name"),
            rs.getLong("user_id")
    );

    public List<Category> findAll() {
        String sql = "SELECT * FROM categories";
        return jdbcTemplate.query(sql, categoryRowMapper);
    }

    public Category findById(Long id) {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        return jdbcTemplate.query(sql, categoryRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category with ID : " + id + " doesn't exist"));
    }

    public Category save(Category category) {
        String sql = "INSERT INTO categories (name, user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                category.getName(),
                category.getUserId()
        );

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        Long id = jdbcTemplate.queryForObject(sqlGetId, Long.class);

        category.setCategoryId(id);
        return category;
    }

    public Category update(Long id, Category category) {
        if (!categoryExists(id)) {
            throw new RuntimeException("Category with ID : " + id + " doesn't exist");
        }

        String sql = "UPDATE categories SET name = ?, user_id = ? WHERE category_id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                category.getName(),
                category.getUserId(),
                id
        );

        if (rowsAffected <= 0) {
            throw new RuntimeException("Failure to update category with ID : " + id);
        }

        return this.findById(id);
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    private boolean categoryExists(Long id) {
        String checkSql = "SELECT COUNT(*) FROM categories WHERE category_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
}
