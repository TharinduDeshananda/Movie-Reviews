package TDZilla.MovieReviews;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoTemplateService {

    @Bean("cmongoTemplate")
    public MongoTemplate getTemplate(){

        try{
            MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
            factoryBean.setHost("localhost");
            factoryBean.setPort(27017);
            factoryBean.afterPropertiesSet();
            MongoClient client = factoryBean.getObject();
            SimpleMongoClientDatabaseFactory databaseFactory= new SimpleMongoClientDatabaseFactory(client,"movie_reviews");

            MongoTemplate template = new MongoTemplate(databaseFactory);
            return template;
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


}
