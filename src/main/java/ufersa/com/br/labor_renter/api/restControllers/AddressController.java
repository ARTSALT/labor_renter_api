package ufersa.com.br.labor_renter.api.restControllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufersa.com.br.labor_renter.api.dto.requests.AddressRequest;
import ufersa.com.br.labor_renter.api.dto.responses.AddressResponse;
import ufersa.com.br.labor_renter.domain.services.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService service) {
        this.addressService = service;
    }

    @GetMapping("")
    public ResponseEntity<List<AddressResponse>> getAll() {
        return new ResponseEntity<>(addressService.getAll(), HttpStatus.FOUND);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<AddressResponse>> getAll(@PathVariable long user_id) {
        return new ResponseEntity<>(addressService.getAllOfUser(user_id), HttpStatus.FOUND);
    }

    @GetMapping("/{address_id}")
    public ResponseEntity<AddressResponse> get(@PathVariable long address_id) {
        return new ResponseEntity<>(addressService.get(address_id), HttpStatus.FOUND);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<AddressResponse> create(@PathVariable long user_id, @Valid @RequestBody AddressRequest request) {
        return new ResponseEntity<>(addressService.create(user_id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("{address_id}")
    public ResponseEntity<Void> delete(@PathVariable long address_id) {
        addressService.delete(address_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{address_id}")
    public ResponseEntity<AddressResponse> patch(@PathVariable long address_id, @Valid @RequestBody AddressRequest request) {
        return new ResponseEntity<>(addressService.update(address_id, request), HttpStatus.OK);
    }
}
