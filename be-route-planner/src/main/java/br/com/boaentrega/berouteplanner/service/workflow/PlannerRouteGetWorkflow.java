package br.com.boaentrega.berouteplanner.service.workflow;

import br.com.boaentrega.berouteplanner.model.RouteRequest;
import br.com.boaentrega.berouteplanner.model.RouteResponse;
import br.com.boaentrega.berouteplanner.service.GetRouteByClientsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PlannerRouteGetWorkflow {

    private final GetRouteByClientsService getRouteByClientsService;

    public List<RouteResponse> execute(List<RouteRequest> routeRequest) {
        try {
            return this.getRouteByClientsService.execute(routeRequest);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
