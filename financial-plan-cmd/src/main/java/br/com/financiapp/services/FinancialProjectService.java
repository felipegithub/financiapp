package br.com.financiapp.services;

import br.com.financiapp.commands.CreatePlanCommand;
import br.com.financiapp.commands.UpdatePlanCommand;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

@Singleton
public class FinancialProjectService {

    @Inject
    private CommandGateway commandGateway;

    public UUID createPlan(CreatePlanCommand command) {
        UUID id = UUID.randomUUID();

        command.setName("1");
        command.setId(id);
        commandGateway.send(command);

        return id;
    }

    public UUID updatePlan(UpdatePlanCommand command) {

        commandGateway.send(command);

        return command.getId();
    }


}
