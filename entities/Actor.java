package entities;


import java.time.LocalDate;

public class Actor {
    private Long actorId;
    private String name;
    private LocalDate birthdate;
    private String birthplace;
    private Double popularity;
    private String gender;
    private String biography;
    private String imageUrl;


    public Actor() {}


    public Actor(Long actorId, String name, LocalDate birthdate, String birthplace,
                 Double popularity, String gender, String biography, String imageUrl) {
        this.actorId = actorId;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.popularity = popularity;
        this.gender = gender;
        this.biography = biography;
        this.imageUrl = imageUrl;
    }


    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
