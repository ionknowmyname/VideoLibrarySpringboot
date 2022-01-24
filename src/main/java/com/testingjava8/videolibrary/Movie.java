package com.testingjava8.videolibrary;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Document(collection = "movie")
public class Movie {


    @Id
    private String id;
    private String title;
    private String type; // later use list for type and genre
    private String genre;
    private LocalDate releaseYear;

    @Transient
    private Boolean isRecent;

    @Transient
    private Integer maxAge;


    public Movie(String title, String type, String genre, LocalDate releaseYear) {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title.toLowerCase();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Boolean getRecent() {

        Integer yrs = Period.between(this.releaseYear, LocalDate.now()).getYears();
        if (yrs < 3) {
            return isRecent = true;
        }
        return isRecent = false;
    }

    public void setRecent(Boolean recent) {
        isRecent = recent;
    }

    public Integer getMaxAge() {
        if(this.type == "children"){
            maxAge = 13;
        }else{
            maxAge = 99;
        }
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", isRecent=" + isRecent +
                ", maxAge=" + maxAge +
                '}';
    }

}

