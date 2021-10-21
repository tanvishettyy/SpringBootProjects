package org.impelsys.apigateway;

import java.time.Duration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerResilience4JFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.DispatcherHandler;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;



@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder)
	{
		log.info("In customRouteLocator");
		return builder.routes()
				//.route("path_route",r ->r.path("/employee/")
				//.and().method("POST","PUT","DELETE")
				//.and().host("localhost")
				.route(r->r.path("/employee/**")
				.filters(f->f.circuitBreaker(c->c.setName("CircuitBreaker").setFallbackUri("forward:/empServiceFallback")))
				.uri("lb://EMPLOYEE-SERVICE"))
				
				.route(r->r.path("/department/**")
				.filters(f->f.circuitBreaker(c->c.setName("CircuitBreaker").setFallbackUri("forward:/deptServiceFallBack")))
				.uri("lb://DEPARTMENT-SERVICE"))
				//.route("path_route", r->r.path("/employee/**").and().method("GET").uri("http://localhost:9003"))
				.build();
	}
	
	
	
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer()
	{
		log.info("In default constructor");
		//CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.ofDefaults();  
		//.ofDeafults -> uses default we can use custom also. custom is better
		
		//USE CASE: 1
		//count based
		//circuit breaker should be up if 50% of last 10 calls failed:
		//count based circuit breaker
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
						.slidingWindowType(SlidingWindowType.TIME_BASED)
						.slidingWindowSize(10)
						.slowCallRateThreshold(50.0f)
						.slowCallDurationThreshold(Duration.ofSeconds(5))
						.minimumNumberOfCalls(10)
						.failureRateThreshold(50.0f)  //50%
						.build();
		
	/*	//USE CASE: 2
		//count based
		//circuit breaker should be up if 50% of last 10 calls failed:
		//count based circuit breaker
		CircuitBreakerConfig circuitBreakerConfig2 = CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(10)
				.failureRateThreshold(50.0f)  //50%
				.build();
		
		//USE CASE: 3
		//time based.
		//the circuit breaker should be up if 50% requests fail in last 20 seconds:
		//50% of 12 = 6. if 6 request fail open circuit breaker. 
		CircuitBreakerConfig  circuitBreakerConfig3 = CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.TIME_BASED)
				.slidingWindowSize(20)
				.failureRateThreshold(50.0f)  //50%
				.minimumNumberOfCalls(12)
				.build();
		
		//USE CASE: 4
		//open circuit breaker if 70 % calls in last 15 seconds took more than 5 or more seconds to complete
		//70% of 10 = 7. if 7 request fail open circuit breaker
		CircuitBreakerConfig  circuitBreakerConfig4 = CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.TIME_BASED)
				.slidingWindowSize(15)
				.failureRateThreshold(70.0f)  //70%
				.minimumNumberOfCalls(10)  //10 request
				.build();
		*/
	    TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(8)).build();
//		ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory("CircuitBreaker").build();
//		Resilience4JConfigBuilder resilienceConfigBuilder = new Resilience4JConfigBuilder("CircuitBreaker");
//		resilienceConfigBuilder.circuitBreakerConfig(circuitBreakerConfig);
		
		return factory->factory.configureDefault(id->new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(circuitBreakerConfig).timeLimiterConfig(timeLimiterConfig).build());
	}
}


//@Bean	
//@ConditionalOnBean(ReactiveCircuitBreakerFactory.class)
//public ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory()
//{
//	ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory();
//	return factory;
//}
//
//@Bean("springCloudCircuitBreakerFilterFactory")
//public SpringCloudCircuitBreakerFilterFactory resilience4jCircuitBreakerFactory
//(ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory,
//	ObjectProvider<DispatcherHandler> dispatcherHandler)
//	{
//		return new SpringCloudCircuitBreakerResilience4JFilterFactory(reactiveCircuitBreakerFactory, dispatcherHandler);
//	}
