package br.com.financiapp.configurations;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Singleton
public class StartupAxonConfiguration implements ApplicationEventListener<StartupEvent> {

    @Inject
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(StartupEvent event) {
        this.applicationContext.getBean(CommandGateway.class);
    }
}
