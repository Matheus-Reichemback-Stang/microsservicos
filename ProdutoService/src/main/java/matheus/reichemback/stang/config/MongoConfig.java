package matheus.reichemback.stang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "matheus.reichemback.stang.repository")
public class MongoConfig {

}
