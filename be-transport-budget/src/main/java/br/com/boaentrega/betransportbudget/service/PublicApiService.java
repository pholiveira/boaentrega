package br.com.boaentrega.betransportbudget.service;

import br.com.boaentrega.betransportbudget.controller.PublicApiDelegate;
import br.com.boaentrega.betransportbudget.model.BudgetResponse;
import br.com.boaentrega.betransportbudget.service.workflow.PublicBudgetGetWorkflow;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublicApiService implements PublicApiDelegate {

    private final ValidateService validateService;
    private final PublicBudgetGetWorkflow publicBudgetGetWorkflow;

    @Override
    public ResponseEntity<BudgetResponse> publicBudgetGet(Integer apiClientId, String postalcodeOrigin, String postalcodeDestination) {
        if (!validateService.isValidClient(apiClientId)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        var response = publicBudgetGetWorkflow.execute(apiClientId, postalcodeOrigin, postalcodeDestination);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
