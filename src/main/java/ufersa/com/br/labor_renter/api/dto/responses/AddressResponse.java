package ufersa.com.br.labor_renter.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    long id;
    String cep;
    String street;
    int houseNumber;
    String complement;
    long contractor_id;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.cep = address.getCep();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.complement = address.getComplement();
        this.contractor_id = address.getContractor().getId();
    }
}
