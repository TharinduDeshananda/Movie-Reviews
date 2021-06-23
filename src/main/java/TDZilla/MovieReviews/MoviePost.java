package TDZilla.MovieReviews;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document
public class MoviePost {
    @Id
    private String id;
    private String postName;
    private String movieName;
    private String editorName;
    private Date movieYear;
    private Date postDate;
    private Date lastEdited;
    private ArrayList<String> picIdsList = new ArrayList<>();
    private ArrayList<String> movieTags = new ArrayList<>();
    private ArrayList<UserComment> userComments = new ArrayList<>();

    public MoviePost() {
    }

    public MoviePost(String postName, String movieName, String editorName,
                     Date movieYear, Date postDate, Date lastEdited,
                     ArrayList<String> picIdsList, ArrayList<String> movieTags,
                     ArrayList<UserComment> userComments) {
        this.postName = postName;
        this.movieName = movieName;
        this.editorName = editorName;
        this.movieYear = movieYear;
        this.postDate = postDate;
        this.lastEdited = lastEdited;
        this.picIdsList = picIdsList;
        this.movieTags = movieTags;
        this.userComments = userComments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public Date getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Date movieYear) {
        this.movieYear = movieYear;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public ArrayList<String> getPicIdsList() {
        return picIdsList;
    }

    public void setPicIdsList(ArrayList<String> picIdsList) {
        this.picIdsList = picIdsList;
    }

    public ArrayList<String> getMovieTags() {
        return movieTags;
    }

    public void setMovieTags(ArrayList<String> movieTags) {
        this.movieTags = movieTags;
    }

    public ArrayList<UserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(ArrayList<UserComment> userComments) {
        this.userComments = userComments;
    }
}
