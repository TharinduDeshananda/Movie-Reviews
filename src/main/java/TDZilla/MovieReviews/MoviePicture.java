package TDZilla.MovieReviews;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class MoviePicture {
    @Id
    private String id;
    private String movieName;
    private Binary picContent;
    private Date picDate;

    public MoviePicture() {
    }

    public MoviePicture(String movieName, Binary picContent, Date picDate) {
        this.movieName = movieName;
        this.picContent = picContent;
        this.picDate = picDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Binary getPicContent() {
        return picContent;
    }

    public void setPicContent(Binary picContent) {
        this.picContent = picContent;
    }

    public Date getPicDate() {
        return picDate;
    }

    public void setPicDate(Date picDate) {
        this.picDate = picDate;
    }

    @Override
    public String toString() {
        return "MoviePicture{" +
                "id='" + id + '\'' +
                ", movieName='" + movieName + '\'' +
                ", picContent=" + picContent +
                ", picDate=" + picDate +
                '}';
    }
}
