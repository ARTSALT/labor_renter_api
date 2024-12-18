package ufersa.com.br.labor_renter.domain.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.domain.entities.Contractor;
import ufersa.com.br.labor_renter.domain.repositories.ContractorRepository;

@Service
public class ContractorDetailsServiceImpl implements UserDetailsService {
    private final ContractorRepository repository;

    public ContractorDetailsServiceImpl(ContractorRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Contractor currentUser = repository.findByEmail(email).orElseThrow(() -> new DataIntegrityViolationException("Usuario necessario"));
        if (currentUser != null) {
            User user = new User(email, currentUser.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return user;
        }else throw new UsernameNotFoundException("Usuário não encontrado!");
    }
}
