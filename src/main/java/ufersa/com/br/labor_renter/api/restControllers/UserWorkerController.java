package ufersa.com.br.labor_renter.api.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufersa.com.br.labor_renter.domain.entities.UserWorker;
import ufersa.com.br.labor_renter.domain.services.UserWorkerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/userworkers")
public class UserWorkerController {

    @Autowired
    private UserWorkerService userWorkerService;

    // Listar todos os UserWorkers
    @GetMapping
    public ResponseEntity<List<UserWorker>> listAll() {
        List<UserWorker> userWorkers = userWorkerService.findAll();
        return new ResponseEntity<>(userWorkers, HttpStatus.OK);
    }

    // Buscar UserWorker por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserWorker> findById(@PathVariable Long id) {
        try {
            UserWorker userWorker = userWorkerService.findById(id);
            return new ResponseEntity<>(userWorker, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Criar novo UserWorker
    @PostMapping
    public ResponseEntity<UserWorker> create(@RequestBody UserWorker userWorker) {
        try {
            UserWorker createdUserWorker = userWorkerService.create(userWorker);
            return new ResponseEntity<>(createdUserWorker, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Atualizar UserWorker existente
    @PutMapping("/{id}")
    public ResponseEntity<UserWorker> update(@PathVariable Long id, @RequestBody UserWorker userWorker) {
        try {
            UserWorker updatedUserWorker = userWorkerService.update(id, userWorker);
            return new ResponseEntity<>(updatedUserWorker, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Excluir UserWorker por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            userWorkerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // No Content para indicar que foi deletado
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
