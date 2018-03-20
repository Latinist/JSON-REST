package com.antonromanov.angular4.spa4.services;

import com.antonromanov.angular4.spa4.model.Request;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PropertySource("classpath:application.properties") //read filename from application.properties
@Service
public class RequestService {

    @Value("${file.name}") // setting filename from application.properties
    private String filename;

    ObjectMapper mapper = new ObjectMapper(); //we need mapper to marshalize collection to json
    private List<Request> requests;

  // method for autoincrement id
    private Long getLastId() {

        Request request;
        if (!requests.isEmpty()) {
            request = requests.get(requests.size() - 1);
            return request.getId();
        } else {
            return Long.valueOf(0);
        }
    }

    // method for filter existing list of requests by source request (from client)
    public List<Request> findReqs(Request request) {

        List<Request> result = requests.stream()
                .filter(x -> x.getFirstName() != null)
                .filter(x -> x.equals(request)).collect(Collectors.toList());

        result.forEach(item -> {
        });
        return result;
    }

    // get all requests
    public List<Request> findAll() {
        return requests;
    }

    public void saveToFile() {
        try {
            mapper.writeValue(new File(filename), requests);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get all requests from file before main logic
    public void loadFromFile() {
        requests = new ArrayList<Request>();
        try {
            if (fileCheck()) {
                requests = mapper.reader()
                        .forType(new TypeReference<List<Request>>() {
                        })
                        .readValue(new FileInputStream(filename));
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRequest(Request request) {
        Request request4 = new Request(this.getLastId() + 1, request.getFirstName(), request.getMiddleName(), request.getLastName(), request.getBirthday(), request.getEmail());
        requests.add(request4);
    }

    @PostConstruct
    private void iniDataForTesting() {
        requests = new ArrayList<Request>();
    }

    // check that file exists before load
    private boolean fileCheck() {

        Boolean check;
        File file = new File(filename);
        check = false;

        try {
            if (file.exists()) {
                check = true;
            } else {
                check = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
