package br.com.boaentrega.betransportbudget.service.workflow;

import br.com.boaentrega.betransportbudget.client.route.RoutePlannerClient;
import br.com.boaentrega.betransportbudget.client.route.ServiceRouteResponse;
import br.com.boaentrega.betransportbudget.client.warehouse.WarehouseManagerClient;
import br.com.boaentrega.betransportbudget.model.BudgetDelivery;
import br.com.boaentrega.betransportbudget.model.BudgetResponse;
import br.com.boaentrega.betransportbudget.repository.BudgetDeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PublicBudgetGetWorkflow {

    private final WarehouseManagerClient warehouseManagerClient;
    private final RoutePlannerClient routePlannerClient;

    private final BudgetDeliveryRepository budgetDeliveryRepository;

    public BudgetResponse execute(Integer apiClientId, String postalcodeOrigin, String postalcodeDestination) {
        var warehouseResponses = warehouseManagerClient.request(postalcodeOrigin, postalcodeDestination);
        var serviceRouteResponses = routePlannerClient.request(warehouseResponses);
        var budget = this.generateBudgetDelivery(serviceRouteResponses, apiClientId);
        return BudgetResponse.builder()
                .id(budget.getId())
                .timeToDelivery(budget.getTimeToDelivery())
                .price(budget.getPrice())
                .build();
    }

    public BudgetDelivery generateBudgetDelivery(List<ServiceRouteResponse> serviceRouteResponses, Integer clientId) {
        var budget = BudgetDelivery.builder()
                .id(UUID.randomUUID().toString())
                .client(clientId)
                .timeToDelivery(serviceRouteResponses.get(serviceRouteResponses.size() - 1).getEndTime().toString())
                .price(this.calcDelivery(serviceRouteResponses))
                .routes(serviceRouteResponses)
                .build();
        return budgetDeliveryRepository.save(budget);
    }

    private String calcDelivery(List<ServiceRouteResponse> serviceRouteResponses) {
        var totalKm = serviceRouteResponses.stream()
                .map(s -> new BigDecimal(s.getTravelDistance()))
                .reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(60), RoundingMode.UP);
        return totalKm.multiply(new BigDecimal("1.7")).toString();
    }
}
