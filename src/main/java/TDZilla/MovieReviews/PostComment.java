package TDZilla.MovieReviews;

import java.util.Date;

public class PostComment {

    private String userName;
    private String comment;
    private Date date;
    private String pid;

    public PostComment() {
    }

    public PostComment(String userName, String comment, Date date, String pid) {
        this.userName = userName;
        this.comment = comment;
        this.date = date;
        this.pid = pid;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", pid='" + pid + '\'' +
                '}';
    }
}
