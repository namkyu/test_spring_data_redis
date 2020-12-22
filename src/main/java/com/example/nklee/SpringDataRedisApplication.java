package com.example.nklee;

import com.example.nklee.entity.Account;
import com.example.nklee.entity.Customer;
import com.example.nklee.entity.RedisTimeToLive;
import com.example.nklee.repository.CustomerRepository;
import com.example.nklee.repository.RedisTimeToLiveRepository;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Slf4j
@EnableAsync
@SpringBootApplication
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class SpringDataRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisApplication.class, args);
    }

    @Bean
    ApplicationListener<RedisKeyExpiredEvent<?>> eventListener() {
        return event -> {
            System.out.println(String.format("Received expire event for key=%s with value %s.", new String(event.getSource()), event.getValue()));
        };
    }

    @Autowired
    RedisTimeToLiveRepository redisTimeToLiveRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            redisTimeToLiveRepository.save(new RedisTimeToLive(1L, 3L));

            Customer customer = new Customer(1L, "80010121098", "John Smith");
            customer.addAccount(new Account(1L, "1234567890", 2000, 10L));
            customer.addAccount(new Account(2L, "1234567891", 4000, 10L));
            customer.addAccount(new Account(3L, "1234567892", 6000, 10L));
            customerRepository.save(customer);
            Thread.sleep(3000L);
        };
    }


    @Component
    public class RedisKeyExpiredEventListener {

        @EventListener
        public void anything(RedisKeyExpiredEvent<?> expiredEvent) {
            log.info("Current Thread : {}", Thread.currentThread().getName());
            Object value = expiredEvent.getValue();

            System.out.println(value.getClass());
            System.out.println(RedisTimeToLive.class);

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
