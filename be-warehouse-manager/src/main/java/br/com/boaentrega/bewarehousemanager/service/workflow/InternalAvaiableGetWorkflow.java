package br.com.boaentrega.bewarehousemanager.service.workflow;

import br.com.boaentrega.bewarehousemanager.model.WarehouseResponse;
import br.com.boaentrega.bewarehousemanager.service.GetWarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InternalAvaiableGetWorkflow {

    private final GetWarehouseService getWarehouseService;

    public List<WarehouseResponse> execute(Long postcodeOrigin, Long postcodeDestination) {
        return this.getWarehouseService.execute(postcodeOrigin, postcodeDestination);
    }
}
