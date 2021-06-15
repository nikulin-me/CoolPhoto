package nikulin.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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

    private String filename;

    private String tag;

    private String type;

    @Lob
    private byte[] data;

    public Photo(User author, String message, String filename, String tag, String type, byte[] data) {
        this.author = author;
        this.message = message;
        this.filename = filename;
        this.tag = tag;
        this.type = type;
        this.data = data;
    }

    public Photo(String message, String tag, User user) {
        this.message=message;
        this.tag=tag;
        this.author=user;
    }
}
