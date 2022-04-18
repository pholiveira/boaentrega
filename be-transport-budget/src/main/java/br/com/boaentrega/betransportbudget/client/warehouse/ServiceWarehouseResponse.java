package br.com.boaentrega.betransportbudget.client.warehouse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceWarehouseResponse {
    private Long id;
    private String latitude;
    private String longitude;
    private String schedule;
}
