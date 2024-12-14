package ufersa.com.br.labor_renter.api.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractorRequest {

    @CPF(message = "Cpf inválido")
    String cpf;

    @NotBlank
    String name;

    @NotBlank
    @Email(message = "Email Inválido")
    String email;

    @NotBlank
    String password;
}
