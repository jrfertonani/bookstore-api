package bookstore_api.services;

import bookstore_api.domain.Categoria;
import bookstore_api.dtos.CategoriaDTO;
import bookstore_api.repositories.CategoriaRepository;
import bookstore_api.services.exeptions.DataIntegrityViolationExcepition;
import bookstore_api.services.exeptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Locked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional()
    public Categoria findById(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    @Transactional()
    public List<Categoria> findAll(){
    return repository.findAll();
    }

    @Transactional()
    public Categoria create(Categoria obj){
        obj.setId(null);
        return repository.save(obj);
    }

    @Transactional()
    public Categoria update(Integer id, CategoriaDTO objDto) {
        Categoria obj = findById(id);
        obj.setNome(objDto.getNome());
        obj.setDescricao(objDto.getDescricao());
        return repository.save(obj);
    }

    @Transactional()
    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationExcepition e){
            throw new DataIntegrityViolationExcepition("Categoria não pode ser deletado! Possui livros associados");
        }
    }


}
