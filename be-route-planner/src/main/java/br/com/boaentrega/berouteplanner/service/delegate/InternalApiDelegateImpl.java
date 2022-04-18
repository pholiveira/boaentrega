package br.com.boaentrega.berouteplanner.service.delegate;

import br.com.boaentrega.berouteplanner.controller.InternalApiDelegate;
import br.com.boaentrega.berouteplanner.model.RouteRequest;
import br.com.boaentrega.berouteplanner.model.RouteResponse;
import br.com.boaentrega.berouteplanner.service.workflow.PlannerRouteGetWorkflow;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InternalApiDelegateImpl implements InternalApiDelegate {

    private final PlannerRouteGetWorkflow plannerRouteGetWorkflow;

    @Override
    public ResponseEntity<List<RouteResponse>> internalRoutePost(List<RouteRequest> routeRequest) {
        var response = plannerRouteGetWorkflow.execute(routeRequest);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
