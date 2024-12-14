package ufersa.com.br.labor_renter.api.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Contractor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractorCreateRequest {

    @NotBlank(message = "O Campo Cpf é Obrigatório")
    String cpf;

    @NotBlank(message = "O Campo Nome é Obrigatório")
    String name;

    @NotBlank(message = "O Campo Email é Obrigatório")
    @Email(message = "Email Inválido")
    String email;

    @NotBlank(message = "O Campo Senha é Obrigatório")
    String password;

    public ContractorCreateRequest(Contractor entity) {
        this.cpf = entity.getCpf();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }
}
