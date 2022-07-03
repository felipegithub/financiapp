package br.com.financiapp.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Controller("/api/v1/teste")
public class QueryPlanController {

    @Inject
    private CommandGateway commandGateway;

    @Get
    public HttpResponse<String> createFinancialPlan() {


        return HttpResponse.created("teste");
    }

}
