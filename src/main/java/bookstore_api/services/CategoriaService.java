package bookstore_api.services;

import bookstore_api.domain.Categoria;
import bookstore_api.repositories.CategoriaRepository;
import bookstore_api.services.exeptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Locked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
