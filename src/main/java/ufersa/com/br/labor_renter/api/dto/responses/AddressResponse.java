package ufersa.com.br.labor_renter.api.dto.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import ufersa.com.br.labor_renter.domain.entities.Address;

public class AddressResponse {
    @NotBlank
    String cep;

    @NotBlank(message = "O campo rua é obrigatório")
    String rua;

    @NotBlank(message = "O campo número é obrigatório")
    int houseNumber;

    String complement;

    @NotEmpty(message = "O id do contratante é obrigatório")
    long contractor_id;

    public AddressResponse(Address address) {
        this.cep = address.getCep();
        this.rua = address.getRua();
        this.houseNumber = address.getHouseNumber();
        this.complement = address.getComplement();
        this.contractor_id = address.getContractor().getId();
    }
}
