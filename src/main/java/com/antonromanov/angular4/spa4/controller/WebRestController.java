package com.antonromanov.angular4.spa4.controller;


import com.antonromanov.angular4.spa4.model.Request;
import com.antonromanov.angular4.spa4.model.RequestList;
import com.antonromanov.angular4.spa4.model.RequestValidation;
import com.antonromanov.angular4.spa4.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class WebRestController {


    RequestService requestService;

    @Autowired
    public void setUserService(RequestService requestService) {
        this.requestService = requestService;
    }



    // THE STRING FOR BROWSER IS - http://localhost:8080/rest/user/hello?name=User

    String json = "{\"id\":2488,\"content\":\"Hello!\"}";

    @RequestMapping("/hello")
            public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        /*    return new Greeting(counter.incrementAndGet(),
            String.format(template, name));*/

        return name+" - TEST";

            }
    @PostMapping("/save")
    public ResponseEntity<?> newRequest(@RequestBody Request request) {

        RequestList result = new RequestList();

        requestService.loadFromFile();

        //requestService.addArticle(request.getFirstName());
        System.out.println("Write a firstname = " + request.getFirstName());
        System.out.println("Write a middlename = " + request.getMiddleName());
        System.out.println("Write a lastname = " + request.getLastName());
        System.out.println("Write a BirthDay = " + request.getBirthday());
        System.out.println("Write a Email = " + request.getEmail());
        //System.out.println("Write an ID = " + request.getId());



        List<Request> requests =  requestService.findReqs(request);
       // List<Request> requests = requestService.findByUserNameOrEmail(request.getFirstName());


        //return ResponseEntity.ok(result);

        if (requests.isEmpty()) {

            result.setMsg("No user found!");
            requestService.addArticle(request);
            requests = requestService.findAll();
            result.setResult(requests);
            requestService.saveToFile();
        } else {
            result.setMsg("This request is exists");
            result.setResult(requests);
        }

        return ResponseEntity.ok(result);



    }


/*
    @PostMapping("/api/search")
      public ResponseEntity<?> newRequest(@Valid @RequestBody RequestValidation request, Errors errors) {

        System.out.println("Write a username = " + request.getUsername());

        requestService.loadFromFile();


        RequestList result = new RequestList();

        List<Request> requests = requestService.findByUserNameOrEmail(request.getUsername());


        System.out.println("Last Name = " + request.getLastName());

        if (requests.isEmpty()) {

            result.setMsg("no user found!");
            requestService.addArticle(request.getUsername());
            requests = requestService.findAll();
            result.setResult(requests);
            requestService.saveToFile();
        } else {
            result.setMsg("success");
            result.setResult(requests);
        }

        return ResponseEntity.ok(result);

}

*/
}

