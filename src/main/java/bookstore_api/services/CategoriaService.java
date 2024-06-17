package bookstore_api.services;

import bookstore_api.domain.Categoria;
import bookstore_api.repositories.CategoriaRepository;
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
}
