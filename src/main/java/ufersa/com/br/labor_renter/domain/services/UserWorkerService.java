package ufersa.com.br.labor_renter.domain.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ufersa.com.br.labor_renter.domain.entities.UserWorker;
import ufersa.com.br.labor_renter.domain.repositories.UserWorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserWorkerService {

    @Autowired
    private UserWorkerRepository userWorkerRepository;

    public List<UserWorker> findAll() {
        return userWorkerRepository.findAll();
    }

    // depois fazer exceptions personalizadas
    public ResponseEntity<UserWorker> findById(@PathVariable Long id) {
        UserWorkerService userWorkerService = null;
        Optional<UserWorker> userWorkerOpt = userWorkerService.findById(id);

        // Usando map para manipular o Optional
        return userWorkerOpt.map(userWorker -> new ResponseEntity<>(userWorker, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackOn = Exception.class)
    public UserWorker create(UserWorker u) {
        return userWorkerRepository.save(u);
    }

    @Transactional(rollbackOn = Exception.class)
    public void delete(Long id) {
        userWorkerRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    // depois fazer exceptions personalizadas
    public UserWorker update(Long id, UserWorker u) throws Exception {
        UserWorker userWorkerExist = userWorkerRepository.findById(id)
                .orElseThrow(() -> new Exception("ID inexistente"));

        userWorkerExist.setName(u.getName());
        userWorkerExist.setCpf(u.getCpf());
        userWorkerExist.setDocumento(u.getDocumento());
        userWorkerExist.setEmail(u.getEmail());
        userWorkerExist.setPassword(u.getPassword());

        return userWorkerRepository.save(userWorkerExist);
    }

}
