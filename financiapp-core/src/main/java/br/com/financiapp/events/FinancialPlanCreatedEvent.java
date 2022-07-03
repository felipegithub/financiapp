package br.com.financiapp.events;

import br.com.financiapp.models.PlanDescription;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class FinancialPlanCreatedEvent {

    private UUID id;
    private UUID userId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private PlanDescription description;

}
