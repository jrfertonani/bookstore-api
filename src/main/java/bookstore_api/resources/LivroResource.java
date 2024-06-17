package bookstore_api.resources;

import bookstore_api.domain.Livro;
import bookstore_api.dtos.LivroDTO;
import bookstore_api.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroResource {

    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){
        Livro obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    /*
        @GetMapping
        public ResponseEntity<List<LivroDTO>> findAll(){
            List<Livro> list = service.findAll();
            List<LivroDTO> listDTO = list.stream().map(LivroDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok().body(listDTO);
        }
    */
    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro obj){
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivroDTO> updatePatch(@PathVariable Integer id,
                                           @RequestBody LivroDTO objDto){
        Livro newObj = service.update(id, objDto);
        return ResponseEntity.ok().body(new LivroDTO(newObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAllCat(@RequestParam(value = "categoria", defaultValue = "0")Integer id_cat){
        List<Livro> list = service.findAllCat(id_cat);
        List<LivroDTO> listDTO = list.stream().map(
                LivroDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}
