package ufersa.com.br.labor_renter.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotBlank
    private String cep;

    @NotBlank(message = "O campo rua é obrigatório")
    private String street;

    @NotNull(message = "O campo número da casa é obrigatório")
    private int houseNumber;

    private String complement;

    public AddressRequest(Address address) {
        this.cep = address.getCep();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.complement = address.getComplement();
    }
}
