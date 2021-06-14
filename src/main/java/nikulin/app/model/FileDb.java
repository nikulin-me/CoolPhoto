package nikulin.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Files")
@Data
@NoArgsConstructor
public class FileDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private String type;

    @Lob
    private byte[] data;


    public FileDb(UUID id, String name, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
