package bookstore_api.dtos;

import bookstore_api.domain.Categoria;
import bookstore_api.domain.Livro;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LivroDTO implements Serializable {
    private static final long seralVersionUID = 1L;

    @Id
    private Integer id;
    private String titulo;

    public LivroDTO(Livro obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
    }

}
