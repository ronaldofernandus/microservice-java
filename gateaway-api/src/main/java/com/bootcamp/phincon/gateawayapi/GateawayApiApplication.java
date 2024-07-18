package com.bootcamp.phincon.gateawayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GateawayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateawayApiApplication.class, args);
	}

//    	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(r -> r.path("/users/**")
//						.filters(f -> f
//
//							.addResponseHeader("X-Powered-By","DanSON Gateway Service")
//						)
//						.uri("http://localhost:8282")
//				)
//
//				.build();
//	}

}
