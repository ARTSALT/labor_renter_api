package ufersa.com.br.labor_renter.api.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufersa.com.br.labor_renter.domain.entities.UserWorker;
import ufersa.com.br.labor_renter.domain.services.UserWorkerService;
import java.util.List;

@RestController
@RequestMapping("api/v1/UserWorker")
public class UserWorkerController {
    @Autowired
    private UserWorkerService userWorkerService;

    @GetMapping
    public ResponseEntity<List<UserWorker>> listALl() {
        return new ResponseEntity<>(userWorkerService.findAll(),HttpStatus.FOUND );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWorker> findById(@PathVariable Long id) {
        UserWorker userWorker = userWorkerService.findById(id).getBody();

        if (userWorker != null) {
            return new ResponseEntity<>(userWorker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserWorker> create(@RequestBody UserWorker userWorker) {
        UserWorker createdUserWorker = userWorkerService.create(userWorker);
        return new ResponseEntity<>(createdUserWorker, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserWorker> update(@PathVariable Long id, @RequestBody UserWorker userWorker) throws Exception {
        UserWorker updatedUserWorker = userWorkerService.update(id, userWorker);
        return updatedUserWorker != null
                ? new ResponseEntity<>(updatedUserWorker, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            userWorkerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
