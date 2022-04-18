package br.com.boaentrega.bewarehousemanager.service;

import br.com.boaentrega.bewarehousemanager.model.LocalityPostcode;
import br.com.boaentrega.bewarehousemanager.model.Warehouse;
import br.com.boaentrega.bewarehousemanager.model.WarehouseResponse;
import br.com.boaentrega.bewarehousemanager.model.WarehouseSchedules;
import br.com.boaentrega.bewarehousemanager.repository.LocalityPostcodeRepository;
import br.com.boaentrega.bewarehousemanager.repository.WarehouseSchedulesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetWarehouseService {

    private final LocalityPostcodeRepository localityPostcodeRepository;
    private final WarehouseSchedulesRepository warehouseSchedulesRepository;

    public List<WarehouseResponse> execute(Long postcodeOrigin, Long postcodeDestination) {
        var localityPostcodeOrigin = localityPostcodeRepository.findByRangePostcode(postcodeOrigin);
        var localityPostcodeDestination = localityPostcodeRepository.findByRangePostcode(postcodeDestination);

        var warehouseResponses = this.buildListWarehouseResponse(localityPostcodeOrigin, localityPostcodeDestination);

        var schedules = warehouseSchedulesRepository.findByWarehouseIdIn(warehouseResponses.stream().map(WarehouseResponse::getId).collect(Collectors.toList()));
        this.handleSchedules(warehouseResponses, schedules);

        return warehouseResponses;
    }

    private List<WarehouseResponse> buildListWarehouseResponse(LocalityPostcode origin, LocalityPostcode destination) {
        var warehouseResponses = new ArrayList<WarehouseResponse>();

        if (origin != null && destination != null) {
            if (!origin.getRegionalWarehouse().getId().equals(destination.getRegionalWarehouse().getId())) {
                warehouseResponses.add(this.getWarehouseResponse(origin.getRegionalWarehouse()));
            }
            if (destination.getRegionalWarehouse() != null && destination.getRegionalWarehouse().getActive()) {
                warehouseResponses.add(this.getWarehouseResponse(destination.getRegionalWarehouse()));
            }
            if (destination.getStateWarehouse() != null
                    && destination.getStateWarehouse().getActive()
                    && this.existWarehouse(warehouseResponses, destination.getStateWarehouse().getId())) {
                warehouseResponses.add(this.getWarehouseResponse(destination.getStateWarehouse()));
            }
            if (destination.getLocalWarehouse() != null
                    && destination.getLocalWarehouse().getActive()
                    && this.existWarehouse(warehouseResponses, destination.getLocalWarehouse().getId())) {
                warehouseResponses.add(this.getWarehouseResponse(destination.getLocalWarehouse()));
            }
        }

        return warehouseResponses;
    }

    private WarehouseResponse getWarehouseResponse(Warehouse warehouse) {
        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .latitude(warehouse.getLatitude())
                .longitude(warehouse.getLongitude())
                .build();
    }

    private Boolean existWarehouse(List<WarehouseResponse> warehouseResponses, Long id) {
        return warehouseResponses.stream().noneMatch(wr -> wr.getId().equals(id));
    }

    private void handleSchedules(List<WarehouseResponse> warehouseResponses, List<WarehouseSchedules> warehouseSchedules) {
        warehouseResponses.forEach(wr -> {
            warehouseSchedules.stream()
                    .filter(ws -> wr.getId().equals(ws.getWarehouse().getId()))
                    .findAny().ifPresent(warehouseSchedule -> wr.setSchedule(warehouseSchedule.getSchedules()));
        });
    }
}
