package com.example.nklee;

import com.example.nklee.entity.Customer;
import com.example.nklee.entity.RedisTimeToLive;
import com.example.nklee.repository.CustomerRepository;
import com.example.nklee.repository.RedisTimeToLiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class SpringDataRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisApplication.class, args);
    }

    @Bean
    ApplicationListener<RedisKeyExpiredEvent<?>> eventListener() {
        return event -> System.out.println(String.format("Received expire event for key=%s with value %s.",
                new String(event.getSource()), event.getValue()));
    }

    @Autowired
    RedisTimeToLiveRepository redisTimeToLiveRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            redisTimeToLiveRepository.save(new RedisTimeToLive(1L, 20L));
            customerRepository.save(new Customer(1L, "80010121098", "John Smith"));
            Thread.sleep(5000L);
        };
    }


    @Component
    public class RedisKeyExpiredEventListener {

        @EventListener
        public void anything(RedisKeyExpiredEvent<?> expiredEvent) {
            Object value = expiredEvent.getValue();

            // ttl entity
            if (value instanceof RedisTimeToLive) {
                RedisTimeToLive ttl = (RedisTimeToLive) value;
                System.out.println(ttl.getId());
                System.out.println(ttl.getTtl());
            }
            // customer entity
            else if (value instanceof Customer) {
                Customer customer = (Customer) expiredEvent.getValue();
                System.out.println(customer.getId());
                System.out.println(customer.getName());
            }
        }
    }

}
