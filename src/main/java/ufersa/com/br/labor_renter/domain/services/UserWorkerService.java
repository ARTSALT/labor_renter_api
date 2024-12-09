package ufersa.com.br.labor_renter.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.domain.entities.UserWorker;
import ufersa.com.br.labor_renter.domain.repositories.UserWorkerRepository;

import java.util.List;

@Service
public class UserWorkerService {

    @Autowired
    private UserWorkerRepository userWorkerRepository;

    public List<UserWorker> findAll() {
        return userWorkerRepository.findAll();
    }

    // depois fazer exceptions personalizadas
    public UserWorker findById(Long id) throws Exception {
        try {
            return userWorkerRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID inexistente"));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public UserWorker create(UserWorker u) {
        return userWorkerRepository.save(u);
    }

    public void delete(Long id) {
        userWorkerRepository.deleteById(id);
    }

    // depois fazer exceptions personalizadas
    public UserWorker update(Long id, UserWorker u) throws Exception {
        UserWorker userWorkerExist = userWorkerRepository.findById(id)
                .orElseThrow(() -> new Exception("ID inexistente"));

        userWorkerExist.setNome(u.getNome());
        userWorkerExist.setCpf(u.getCpf());
        userWorkerExist.setDocumento(u.getDocumento());
        userWorkerExist.setEmail(u.getEmail());
        userWorkerExist.setSenha(u.getSenha());

        return userWorkerRepository.save(userWorkerExist);
    }

}
