package nikulin.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import nikulin.app.model.util.PhotoUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
/*
@Data
*/
@NoArgsConstructor
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String message;

    @NotNull(message = "Photo is empty!")
    private String filename;

    private String tag;

    @ManyToMany
    @JoinTable(
            name = "photo_likes",
            joinColumns = {@JoinColumn(name ="photo_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> likes=new HashSet<>();

    public Photo(User author, String message, String filename, String tag) {
        this.author = author;
        this.message = message;
        this.filename = filename;
        this.tag = tag;
    }

    public Photo(String message, String tag, User user) {
        this.message=message;
        this.tag=tag;
        this.author=user;
    }

    public String getAuthorName(){
        return PhotoUtil.getAuthorName(author);
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

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }
}
