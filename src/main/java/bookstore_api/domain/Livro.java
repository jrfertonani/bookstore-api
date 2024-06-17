package bookstore_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
