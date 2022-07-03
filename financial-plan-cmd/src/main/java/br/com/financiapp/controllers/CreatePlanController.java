package br.com.financiapp.controllers;

import br.com.financiapp.commands.CreatePlanCommand;
import br.com.financiapp.controllers.dtos.FinancialProjectDTO;
import br.com.financiapp.services.FinancialProjectService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Inject;

import java.util.UUID;

@Controller("/api/v1/createFinancialPlan")
public class CreatePlanController {

    @Inject
    private FinancialProjectService financialProjectService;

    @Post
    @Secured({"FINANCIAPP-MASTER", "FINANCIAPP-USER"})
    public HttpResponse<FinancialProjectDTO> createFinancialPlan(@Body CreatePlanCommand createPlanCommand, Authentication loggedUser) {

        UUID planId = financialProjectService.createPlan(createPlanCommand);

        FinancialProjectDTO body = FinancialProjectDTO.builder().id(planId.toString()).build();

        return HttpResponse.created(body);
    }

}
