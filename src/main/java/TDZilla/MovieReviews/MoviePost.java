package TDZilla.MovieReviews;

import org.bson.types.Binary;
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
    private long like;
    private long dislike;
    private String editorName;
    private String postContent;
    private long movieYear;
    private Date postDate;
    private Date lastEdited;

    private ArrayList<String> binaryPics = new ArrayList<>();
    private ArrayList<String> picIdsList = new ArrayList<>();
    private ArrayList<String> movieTags = new ArrayList<>();
    private ArrayList<UserComment> userComments = new ArrayList<>();

    public MoviePost() {
    }

    public MoviePost(String postName, String movieName, String editorName, String postContent,
                     long movieYear, Date postDate, Date lastEdited,ArrayList<String> binaryPics,
                     ArrayList<String> picIdsList, ArrayList<String> movieTags,
                     ArrayList<UserComment> userComments) {
        this.postName = postName;
        this.movieName = movieName;
        this.editorName = editorName;
        this.postContent = postContent;
        this.movieYear = movieYear;
        this.postDate = postDate;
        this.lastEdited = lastEdited;
        this.binaryPics = binaryPics;
        this.picIdsList = picIdsList;
        this.movieTags = movieTags;
        this.userComments = userComments;
    }

    public void addToPicIdList(String id){
        this.picIdsList.add(id);
    }
    public void addToBinaryPics(String pic){
        this.binaryPics.add(pic);
    }
    public void addToMovieTags(String tag){
        this.movieTags.add(tag);
    }
    public void addToUserComments(UserComment comment){
        this.userComments.add(comment);
    }

    public ArrayList<String> getBinaryPics() {
        return binaryPics;
    }

    public void setBinaryPics(ArrayList<String> binaryPics) {
        this.binaryPics = binaryPics;
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

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public long getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(long movieYear) {
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

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public long getDislike() {
        return dislike;
    }

    public void setDislike(long dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "MoviePost{" +
                "id='" + id + '\'' +
                ", postName='" + postName + '\'' +
                ", movieName='" + movieName + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                ", editorName='" + editorName + '\'' +

                ", movieYear=" + movieYear +
                ", postDate=" + postDate +
                ", lastEdited=" + lastEdited +
                ", binaryPics=" + binaryPics +
                ", picIdsList=" + picIdsList +
                ", movieTags=" + movieTags +
                ", userComments=" + userComments +
                '}';
    }
}
