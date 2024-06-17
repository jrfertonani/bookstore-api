package bookstore_api.services;

import bookstore_api.domain.Categoria;
import bookstore_api.domain.Livro;
import bookstore_api.dtos.LivroDTO;
import bookstore_api.repositories.LivroRepository;
import bookstore_api.services.exeptions.DataIntegrityViolationExcepition;
import bookstore_api.services.exeptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private CategoriaService categoriaService;

    @Transactional()
    public Livro findById(Integer id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Livro.class.getName()));
    }

    @Transactional()
    public List<Livro> findAll(Integer id_cat){
        categoriaService.findById(id_cat);
        return repository.findAllByCategoria(id_cat);
        }

    @Transactional()
    public Livro create(Integer id_cat, Livro obj){
        obj.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        obj.setCategoria(cat);
        return repository.save(obj);
    }

    @Transactional()
    public Livro update(Integer id, Livro obj) {
        Livro newObj = findById(id);
        newObj.setTitulo(obj.getTitulo());
        newObj.setNome_autor(obj.getNome_autor());
        newObj.setTexto(obj.getTexto());
        return repository.save(newObj);
    }

    @Transactional()
    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationExcepition e){
            throw new DataIntegrityViolationExcepition("Livro não pode ser deletado! Possui livros associados");
        }
    }


    public List<Livro> findAllCat(Integer id_cat) {
        categoriaService.findById(id_cat);
        return repository.findAllByCategoria(id_cat);
    }
}