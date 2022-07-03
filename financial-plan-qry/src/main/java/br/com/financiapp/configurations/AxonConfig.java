package br.com.financiapp.configurations;

import br.com.financiapp.handlers.FinancialPlanEventHandler;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;

@Factory
@Slf4j
public class AxonConfig {

    @Inject
    private FinancialPlanEventHandler eventHandler;

    @Singleton
    public Configurer axonConfigurer(){
        log.info("Produzindo o configurador do servico de query");
        return DefaultConfigurer.defaultConfiguration().registerEventHandler(c -> eventHandler);
    }

}
