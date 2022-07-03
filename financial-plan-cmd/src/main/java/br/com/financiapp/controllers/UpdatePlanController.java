package br.com.financiapp.controllers;

import br.com.financiapp.commands.UpdatePlanCommand;
import br.com.financiapp.controllers.dtos.FinancialProjectDTO;
import br.com.financiapp.services.FinancialProjectService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;

import java.util.UUID;

@Controller("/api/v1/updateFinancialPlan")
public class UpdatePlanController {

    @Inject
    private FinancialProjectService financialProjectService;

    @Put
    @Secured({"FINANCIAPP-MASTER"})
    public HttpResponse<FinancialProjectDTO> updateFinancialPlan(@Body UpdatePlanCommand createPlanCommand) {

        UUID planId = financialProjectService.updatePlan(createPlanCommand);

        FinancialProjectDTO body = FinancialProjectDTO.builder().id(planId.toString()).build();

        return HttpResponse.created(body);
    }
}
