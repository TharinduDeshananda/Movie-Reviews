package TDZilla.MovieReviews;

public class MovieTemplate {
    private String movieName;
    private String postId;
    private long likes;
    private long dislikes;
    private long movieDate;
    private String picContent;

    public MovieTemplate() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }

    public long getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(long movieDate) {
        this.movieDate = movieDate;
    }

    public String getPicContent() {
        return picContent;
    }

    public void setPicContent(String picContent) {
        this.picContent = picContent;
    }
}
