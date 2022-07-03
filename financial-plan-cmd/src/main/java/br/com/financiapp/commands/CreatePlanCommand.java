package br.com.financiapp.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanCommand {

    @TargetAggregateIdentifier
    private UUID id;

    //@NotEmpty
    private UUID userId;

    private String name;

    //@NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

}
