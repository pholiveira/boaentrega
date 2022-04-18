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
public class ServiceRouteResponse {
    private String distanceUnit;
    private String durationUnit;
    private String travelDistance;
    private String travelDuration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer toolZone;
    private RouterLegs routerLeg;
}
