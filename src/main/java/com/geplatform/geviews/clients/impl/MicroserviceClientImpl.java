package com.geplatform.geviews.clients.impl;

import com.geplatform.geviews.clients.MicroserviceClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MicroserviceClientImpl implements MicroserviceClient {

    private final RestTemplate restTemplate;


    public MicroserviceClientImpl() {
        this.restTemplate = new RestTemplate();
    }


    @Override
    public <T> List<T> postDataToAnotherService(String url, Object request, Class<T> responseType) {
        try {
            ResponseEntity<List<T>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(request),
                    new ParameterizedTypeReference<List<T>>() {}
            );
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            // Handle 4xx errors
            e.printStackTrace();
            return null;
        } catch (ResourceAccessException e) {
            // Handle resource access errors
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public <T> T getDataFromAnotherService(String url, Class<T> responseType) {
        try {
            return restTemplate.getForObject(url, responseType);
        } catch (HttpClientErrorException e) {
            // Gestione degli errori 4xx
            e.printStackTrace();
            return null;
        } catch (ResourceAccessException e) {
            // Gestione degli errori di accesso alle risorse
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteDataFromAnotherService(String url) {
        try {
             restTemplate.delete(url);
             return true;
        } catch (HttpClientErrorException e) {
            // Gestione degli errori 4xx
            e.printStackTrace();
            return null;
        } catch (ResourceAccessException e) {
            // Gestione degli errori di accesso alle risorse
            e.printStackTrace();
            return null;
        }
    }
}



