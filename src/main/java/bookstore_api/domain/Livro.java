package bookstore_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
public class Livro implements Serializable {
    private static final long seralVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String titulo;
    private String nome_autor;
    private String texto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookstore_id")
    private Categoria categoria;
}
