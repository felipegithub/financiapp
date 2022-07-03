package br.com.financiapp.events;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FinancialPlanUpdatedEvent {

    private UUID id;
    private String name;

}
