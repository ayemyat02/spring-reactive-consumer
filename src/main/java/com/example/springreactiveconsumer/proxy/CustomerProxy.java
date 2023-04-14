package com.example.springreactiveconsumer.proxy;

import com.example.springreactiveconsumer.exception.CustomerRetrievedException;
import com.example.springreactiveconsumer.model.CustomerModel;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;

@Component
public class CustomerProxy {
    private  final WebClient webClient;

    public CustomerProxy(WebClient webClient) {
        this.webClient = webClient;
    }

//    error handling -> ommit

    public Flux<CustomerModel> getAll(){
        var defaultCustomer=new CustomerModel("defaultId","defaultName",
                "default","default",null);
        return webClient.get()
                .uri("/customers")
                .exchangeToFlux(res-> res.bodyToFlux(CustomerModel.class));
//                .onErrorReturn(defaultCustomer);
//                .onErrorReturn(e -> e instanceof WebClientRequestException, defaultCustomer);
//                .onErrorReturn(WebClientRequestException.class,defaultCustomer);
//                .onErrorResume(e -> Flux.just(defaultCustomer));
//                .onErrorResume(e -> e instanceof WebClientRequestException,
//                        e->Flux.just(defaultCustomer));
//                .onErrorResume(WebClientRequestException.class,
//                        e ->Flux.just(defaultCustomer));
//                .onErrorMap(e -> new CustomerRetrievedException());

    }
}
