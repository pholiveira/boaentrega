package br.com.boaentrega.berouteplanner.client.bing;

import br.com.boaentrega.berouteplanner.model.RouteResponse;
import br.com.boaentrega.berouteplanner.model.RouterLeg;
import org.springframework.stereotype.Service;

@Service
public class BingMapClient {

    public RouteResponse request(RouterLeg routerLeg) {
        var response = RouteResponse.builder()
                .distanceUnit("Kilometer")
                .durationUnit("Minutes")
                .startTime(routerLeg.getOriginStartDate())
                .build();
        if (routerLeg.getOriginId().equals("4") && routerLeg.getDestinationId().equals("1")) {
            response.setTravelDistance(408);
            response.setTravelDuration(341);
            response.setToolZone(1);
        } else if (routerLeg.getOriginId().equals("1") && routerLeg.getDestinationId().equals("6")) {
            response.setTravelDistance(704);
            response.setTravelDuration(560);
            response.setToolZone(0);
        } else if (routerLeg.getOriginId().equals("6") && routerLeg.getDestinationId().equals("5")) {
            response.setTravelDistance(11);
            response.setTravelDuration(17);
            response.setToolZone(0);
        }
        response.setRouterLeg(routerLeg);
        response.setEndTime(response.getStartTime().plusMinutes(response.getTravelDuration()));
        return response;
    }

}
