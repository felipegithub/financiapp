package br.com.financiapp.handlers;

import br.com.financiapp.events.FinancialPlanCreatedEvent;
import jakarta.inject.Singleton;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;

@Singleton
@ProcessingGroup("financial-group")
public class FinancialPlanEventHandler {

    @EventHandler
    public void on(FinancialPlanCreatedEvent event){
        System.out.println("Event Handler");
        System.out.println(event);
    }

}
