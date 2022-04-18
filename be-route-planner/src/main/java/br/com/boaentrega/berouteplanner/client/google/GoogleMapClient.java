package br.com.boaentrega.berouteplanner.client.google;

import br.com.boaentrega.berouteplanner.model.RouteResponse;
import br.com.boaentrega.berouteplanner.model.RouterLeg;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapClient {

    public RouteResponse request(RouterLeg routerLeg) {
        var response = RouteResponse.builder()
                .distanceUnit("Kilometer")
                .durationUnit("Minutes")
                .startTime(routerLeg.getOriginStartDate())
                .build();
        if (routerLeg.getOriginId().equals("4") && routerLeg.getDestinationId().equals("1")) {
            response.setTravelDistance(499);
            response.setTravelDuration(477);
            response.setToolZone(0);
        } else if (routerLeg.getOriginId().equals("1") && routerLeg.getDestinationId().equals("6")) {
            response.setTravelDistance(573);
            response.setTravelDuration(448);
            response.setToolZone(1);
        } else if (routerLeg.getOriginId().equals("6") && routerLeg.getDestinationId().equals("5")) {
            response.setTravelDistance(8);
            response.setTravelDuration(16);
            response.setToolZone(0);
        }
        response.setRouterLeg(routerLeg);
        response.setEndTime(response.getStartTime().plusMinutes(response.getTravelDuration()));
        return response;
    }

}
