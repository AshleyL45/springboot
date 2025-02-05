package entities;

public class FavoriteActor {
    private Long userId;
    private Long actorId;


    public FavoriteActor() {}


    public FavoriteActor(Long userId, Long actorId) {
        this.userId = userId;
        this.actorId = actorId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }
}
