package br.com.boaentrega.bewarehousemanager.service.delegate;

import br.com.boaentrega.bewarehousemanager.controllers.InternalApiDelegate;
import br.com.boaentrega.bewarehousemanager.model.WarehouseResponse;
import br.com.boaentrega.bewarehousemanager.service.workflow.InternalAvaiableGetWorkflow;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InternalApiDelegateImpl implements InternalApiDelegate {

    private final InternalAvaiableGetWorkflow internalAvaiableGetWorkflow;

    @Override
    public ResponseEntity<List<WarehouseResponse>> internalAvaiableGet(Long postalcodeOrigin, Long postalcodeDestination) {
        var response = this.internalAvaiableGetWorkflow.execute(postalcodeOrigin, postalcodeDestination);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
