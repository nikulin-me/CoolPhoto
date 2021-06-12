package nikulin.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "phto")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name")
    private FileDb file;

    private String tag;


    public Photo(Long id, User author, String message, FileDb file, String tag) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.file = file;
        this.tag = tag;
    }
}
