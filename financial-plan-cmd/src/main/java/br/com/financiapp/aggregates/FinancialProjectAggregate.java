package br.com.financiapp.aggregates;

import br.com.financiapp.commands.CreatePlanCommand;
import br.com.financiapp.commands.UpdatePlanCommand;
import br.com.financiapp.events.FinancialPlanCreatedEvent;
import br.com.financiapp.events.FinancialPlanUpdatedEvent;
import br.com.financiapp.models.CurrencyEnum;
import br.com.financiapp.models.Plan;
import br.com.financiapp.models.PlanDescription;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class FinancialProjectAggregate {

    @AggregateIdentifier
    private UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private List<Operation> operations;

    @CommandHandler
    public FinancialProjectAggregate(CreatePlanCommand createPlanCommand){
        System.out.println("FinancialProjectAggregate > new");
        FinancialPlanCreatedEvent event = FinancialPlanCreatedEvent.builder()
                .id(createPlanCommand.getId())
                .userId(createPlanCommand.getUserId())
                .name(createPlanCommand.getName())
                .description(PlanDescription.builder().content(createPlanCommand.getDescription()).build())
                .startDate(createPlanCommand.getStartDate())
                .endDate(createPlanCommand.getEndDate())
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdatePlanCommand command) {
        var updatedPlan = Plan.builder().build();
        updatedPlan.setId(command.getId());
        int somado = Integer.parseInt(name) + 1;
        String somadoStr = String.valueOf(somado);
        updatedPlan.setName(somadoStr);
        var event = FinancialPlanUpdatedEvent.builder()
                .id(command.getId())
                .name(updatedPlan.getName())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void onCreated(FinancialPlanCreatedEvent createdEvent){
        System.out.println("FinancialProjectAggregate > onCreated");
        this.id = createdEvent.getId();
        this.name = createdEvent.getName();
        this.description = createdEvent.getDescription().getContent();
        this.startDate = createdEvent.getStartDate();
        this.endDate = createdEvent.getEndDate();
        this.active = true;
        this.operations = new ArrayList<>();
    }

    @EventSourcingHandler
    public void onUpdate(FinancialPlanUpdatedEvent event) {
        this.name = event.getName();
    }

    static class Operation {
        String name;
        BigDecimal value;
        CurrencyEnum currency;
        UUID typeId;
        boolean active;
    }

}
