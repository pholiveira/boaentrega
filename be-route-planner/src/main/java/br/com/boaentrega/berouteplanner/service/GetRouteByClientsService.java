package br.com.boaentrega.berouteplanner.service;

import br.com.boaentrega.berouteplanner.client.bing.BingMapClient;
import br.com.boaentrega.berouteplanner.client.google.GoogleMapClient;
import br.com.boaentrega.berouteplanner.client.street.OpenStreetMapClient;
import br.com.boaentrega.berouteplanner.model.RouteRequest;
import br.com.boaentrega.berouteplanner.model.RouteResponse;
import br.com.boaentrega.berouteplanner.model.RouterLeg;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class GetRouteByClientsService {

    private final BingMapClient bingMapClient;
    private final GoogleMapClient googleMapClient;
    private final OpenStreetMapClient openStreetMapClient;

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    public List<RouteResponse> execute(List<RouteRequest> routeRequest) {
        var routeResponses = new ArrayList<RouteResponse>();
        IntStream.range(0, routeRequest.size()).reduce(0, (p, s) -> {
            var index = s + 1;
            if (routeRequest.size() != index) {
                var origin = routeRequest.get(p);
                var destination = routeRequest.get(index);

                var startDate = LocalDateTime.now();

                if (routeResponses.size() > 0) {
                    startDate = routeResponses.get(routeResponses.size() - 1).getEndTime();
                }

                var arrayTime = origin.getSchedule().split(":");
                startDate = startDate.withHourOfDay(Integer.parseInt(arrayTime[0]));
                startDate = startDate.withMinuteOfHour(Integer.parseInt(arrayTime[1]));

                var clientMap = this.getRoutesByClient(RouterLeg.builder()
                        .originId(origin.getId())
                        .originLatitude(origin.getLatitude())
                        .originLongitude(origin.getLongitude())
                        .originStartDate(startDate)
                        .destinationId(destination.getId())
                        .destinationLatitude(destination.getLatitude())
                        .destinationLongitude(destination.getLongitude())
                        .build());
                if (clientMap != null) {
                    routeResponses.add(this.findOptimizeRoute(clientMap));
                }
            }
            return index;
        });

        return routeResponses;
    }

    private List<RouteResponse> getRoutesByClient(RouterLeg clientRequestVO) {
        Future<RouteResponse> futureBing = threadPool.submit(() ->
                bingMapClient.request(clientRequestVO));
        Future<RouteResponse> futureGoogle = threadPool.submit(() ->
                googleMapClient.request(clientRequestVO));
        Future<RouteResponse> futureStreet = threadPool.submit(() ->
                openStreetMapClient.request(clientRequestVO));
        try {
            return threadPool.submit(() -> {
                var clientMapResponses = new ArrayList<RouteResponse>();
                clientMapResponses.add(futureBing.get());
                clientMapResponses.add(futureGoogle.get());
                clientMapResponses.add(futureStreet.get());
                return clientMapResponses;
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private RouteResponse findOptimizeRoute(List<RouteResponse> routeResponses) {
        return routeResponses.stream()
                .filter(c -> c.getToolZone() == 0)
                .min(Comparator.comparing(RouteResponse::getTravelDuration))
                .orElse(null);
    }

}
