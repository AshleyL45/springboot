package entities;

public class Category {
    private Long categoryId;
    private String name;
    private Long userId;


    public Category() {}


    public Category(Long categoryId, String name, Long userId) {
        this.categoryId = categoryId;
        this.name = name;
        this.userId = userId;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
