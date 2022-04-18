package br.com.boaentrega.betransportbudget.client.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouterLegs {
    private String originId;
    private String originLatitude;
    private String originLongitude;
    private LocalDateTime originStartDate;
    private String destinationId;
    private String destinationLatitude;
    private String destinationLongitude;
}
