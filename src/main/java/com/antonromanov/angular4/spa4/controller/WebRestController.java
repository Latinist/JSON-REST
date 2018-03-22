package com.antonromanov.angular4.spa4.controller;

import com.antonromanov.angular4.spa4.email.EmailSender;
import com.antonromanov.angular4.spa4.email.EmailStatus;
import com.antonromanov.angular4.spa4.model.Request;
import com.antonromanov.angular4.spa4.model.RequestList;
import com.antonromanov.angular4.spa4.model.UserA;
import com.antonromanov.angular4.spa4.model.UserResponse;
import com.antonromanov.angular4.spa4.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
//@RequestMapping("/rest")
public class WebRestController {

    RequestService requestService;
    @Autowired
    private EmailSender emailSender;
    private EmailStatus emailStatus;

    @Autowired
    public void setUserService(RequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping(value = "/api/values", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> newRequest(@RequestBody Request request) {


        RequestList result = new RequestList();
        requestService.loadFromFile(); //pre-load previous request from file
        List<Request> requests = requestService.findReqs(request); //check recieved from HTTP POST data in list of loaded  requests



        System.out.println("Зашли");
        System.out.println("Имячко = " + request.getFirstName());
        System.out.println("Отчество = " + request.getMiddleName());
        System.out.println("Фамилия = " + request.getLastName());
        System.out.println("ДР = " + request.getBirthday());
        System.out.println("Мыло = " + request.getEmail());
        System.out.println("Поседнее ID = " + requestService.getLastId());


      //  UserResponse result = new UserResponse();


        //result.setMsg("Todo bien");
        //  result.setResult(usera); //pushing them to result response to client


// --------------- 'REQUEST EXISTS' SERVER VALIDATION ------------------------------------------

        if (requests.isEmpty()) { //if there are no entered request in list of loaded reqs
            result.setMsg("No user found! User will add as new");
            requestService.addRequest(request); //trying to add our request
            requests = requestService.findAll(); //trying to get all reqs
            result.setResult(requests); //pushing them to result response to client
            requestService.saveToFile(); //updating our file .json
            emailStatus =    emailSender.sendPlainText(request.getEmail(),"You are subscribed","Dear, " + request.getFirstName() + "You successfully registered. Your Request Number is: " + requestService.getLastId()); //sending e-mail

        } else {
            result.setMsg("This request is exists");
            result.setResult(requests); //We'v got user request (entered by user on client) in our List (from file) - push it to client
        }
        System.out.println(result);
        return ResponseEntity.ok(result);

        //return ResponseEntity.ok(result);
    }


}
