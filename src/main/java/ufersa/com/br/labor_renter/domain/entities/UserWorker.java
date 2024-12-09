package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.Entity;

@Entity
public class UserWorker extends UserAbstract {
    private String documento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
