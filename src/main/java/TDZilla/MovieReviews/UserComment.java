package TDZilla.MovieReviews;

import java.util.Date;

public class UserComment {
    private String userName;
    private String comment;
    private Date commentDate;

    public UserComment() {
    }

    public UserComment(String userName, String comment, Date commentDate) {
        this.userName = userName;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}
