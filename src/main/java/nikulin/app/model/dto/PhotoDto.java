package nikulin.app.model.dto;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.model.util.PhotoUtil;

public class PhotoDto {
    private Long id;
    private String message;
    private String tag;
    private User author;
    private String filename;
    private Long likes;
    private Boolean meLiked;

    public PhotoDto(Photo photo, Long likes, Boolean meLiked) {
        this.id = photo.getId();
        this.message = photo.getMessage();
        this.tag = photo.getTag();
        this.author = photo.getAuthor();
        this.filename = photo.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return PhotoUtil.getAuthorName(author);
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getTag() {
        return tag;
    }

    public User getAuthor() {
        return author;
    }

    public String getFilename() {
        return filename;
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    @Override
    public String toString() {
        return "PhotoDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}

