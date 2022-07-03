package br.com.financiapp.configurations;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.Configurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;

import java.util.Collections;

@Factory
@Slf4j
public class AxonConfiguration {

    @Value("${mongodb.database:financialapp}")
    private String mongodbDatabase;

    @Value("${mongodb.host:localhost}")
    private String mongodbHost;

    @Value("${mongodb.port:27017}")
    private int mongodbPort;

    /*Usando uma Event Store do Axon Server, necessita da
     *dependencia 'axon-server-connector' no projeto que produz o Configurer */
    @Singleton
    public Configuration configuration(Configurer configurer){
        log.info("Iniciando a configuracao do Axon");
        return configurer
                .configureSerializer(c -> serializer(xstream()))
                .eventProcessing(conf -> conf.registerTokenStore(config -> tokenStore()))
                .start();

    }

    @Singleton
    public TokenStore tokenStore() {
        return MongoTokenStore.builder()
                .mongoTemplate(axonMongoTemplate())
                .serializer(serializer(xstream()))
                .build();
    }

    @Singleton
    public CommandGateway commandGateway(Configuration configuration){
        return configuration.commandGateway();
    }

    @Singleton
    public QueryBus defaultQueryBus(Configuration configuration) {
        return configuration.queryBus();
    }

    @Singleton
    public QueryUpdateEmitter defaultQueryUpdateEmitter(Configuration configuration) {
        return configuration.queryUpdateEmitter();
    }

    @Singleton
    public CommandBus defaultCommandBus(Configuration configuration) {
        return configuration.commandBus();
    }

    @Singleton
    public EventBus defaultEventBus(Configuration configuration) {
        return configuration.eventBus();
    }

    private XStream xstream(){
        XStream xstream = new XStream();
        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypesByWildcard(new String[] {
                "br.com.financiapp.**",
                "org.axonframework.**",
                "java.**",
                "com.thoughtworks.xstream.**"
        });

        return xstream;
    }

    private Serializer serializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();
    }

    private MongoTemplate axonMongoTemplate(){
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongo(), mongodbDatabase)
                .build();
    }

    private MongoClient mongo() {
        MongoFactory mongoFactory = new MongoFactory();
        MongoSettingsFactory mongoSettingsFactory = new MongoSettingsFactory();

        mongoSettingsFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(this.mongodbHost, this.mongodbPort)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());

        return mongoFactory.createMongo();
    }

     /*
        Usando uma event store do mongo:

        @Singleton
        public Configuration configuration(Configurer configurer){
            System.out.println("Chamando o configurador raiz");
            return configurer
                    .configureEmbeddedEventStore(c -> storageEngine(mongo()))
                    .configureEventSerializer(c -> serializer(xstream()))
                    .eventProcessing(conf -> conf.registerTokenStore(config -> tokenStore()))
                    .configureMessageSerializer(c -> serializer(xstream()))
                    .start();

        }

        @Singleton
        public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, Configuration configuration) {
            return EmbeddedEventStore.builder()
                    .storageEngine(storageEngine)
                    .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                    .build();
        }

        @Singleton
        public EventStorageEngine storageEngine(MongoClient client) {
            return MongoEventStorageEngine.builder()
                    .mongoTemplate(DefaultMongoTemplate.builder()
                            .mongoDatabase(client)
                            .build())
                    .snapshotSerializer(serializer(xstream()))
                    .eventSerializer(serializer(xstream()))
                    .build();
        }

     */
}
