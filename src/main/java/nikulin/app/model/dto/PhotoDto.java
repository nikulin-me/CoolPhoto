package nikulin.app.model.dto;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.model.util.PhotoUtil;

public class PhotoDto {
    private Long id;
    private User author;
    private String message;
    private String filename;
    private String tag;
    private Long likes;
    private Boolean isLiked;

    public PhotoDto(Photo photo, Long likes, Boolean isLiked) {
        this.id = photo.getId();
        this.author = photo.getAuthor();
        this.message = photo.getMessage();
        this.filename = photo.getFilename();
        this.tag = photo.getTag();
        this.likes = likes;
        this.isLiked = isLiked;
    }


    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthorName(){
        return PhotoUtil.getAuthorName(author);
    }

    @Override
    public String toString() {
        return "PhotoDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", isLiked=" + isLiked +
                '}';
    }
}
