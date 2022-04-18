package br.com.boaentrega.betransportbudget.client.route;

import br.com.boaentrega.betransportbudget.client.warehouse.ServiceWarehouseResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoutePlannerClient {

    @Setter
    @Value("${route-planner-url}")
    private String url;

    public List<ServiceRouteResponse> request(List<ServiceWarehouseResponse> warehouses) {
        try {
            var restTemplate = new RestTemplate();
            var request = new HttpEntity<>(warehouses);
            var response = restTemplate.postForEntity(new URIBuilder(url).build(), request, ServiceRouteResponse[].class);

            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return List.of(response.getBody());
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
