package br.com.boaentrega.betransportbudget.model;

import br.com.boaentrega.betransportbudget.client.route.ServiceRouteResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "budget_deliveries")
@Getter
@Setter
@Builder
public class BudgetDelivery {
    @Id
    private String id;
    private Integer client;
    private String timeToDelivery;
    private String price;
    private List<ServiceRouteResponse> routes;
}
