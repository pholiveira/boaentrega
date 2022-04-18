package br.com.boaentrega.betransportbudget.client.warehouse;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseManagerClient {

    @Setter
    @Value("${warehouse-url}")
    private String url;

    public List<ServiceWarehouseResponse> request(String postalCodeOrigin, String postalCodeDestination) {
        try {
            var restTemplate = new RestTemplate();
            var response = restTemplate.getForEntity(this.getUri(postalCodeOrigin, postalCodeDestination), ServiceWarehouseResponse[].class);

            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return List.of(response.getBody());
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private URI getUri(String postalCodeOrigin, String postalCodeDestination) throws URISyntaxException {
        return new URIBuilder(url)
                .setParameter("postalcodeOrigin", postalCodeOrigin)
                .setParameter("postalcodeDestination", postalCodeDestination)
                .build();
    }
}
