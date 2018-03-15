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
//@RequestMapping("/rest/user")
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

    @PostMapping("/api/search")
   // public ResponseEntity<Void> create(@RequestBody Request request) {
      public ResponseEntity<?> newRequest(@Valid @RequestBody RequestValidation request, Errors errors) {

        System.out.println("Write a username = " + request.getUsername());

        requestService.loadFromFile();

   //     LOG.info("Creating request: {}", request);

    /*    if (requestService.exists(fruit)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

AjaxResponseBody result = new AjaxResponseBody();

*/
        //this.validateUser(request);

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






    //public ResponseEntity<?> newRequest(@AuthenticationPrincipal final UserDetails user) {
   // public ResponseEntity<?> newRequest(@Valid @RequestBody RequestValidation request, Errors errors) {
    /*    PersonList persons = personDaoServiceImpl.autoCreatePerson();
        return new ResponseEntity<PersonList>(persons, HttpStatus.OK); */

     //   RequestList requestList = new RequestList();

        //If error, just return a 400 bad request, along with the error message
    /*    if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

   //     List<Request> requests = reqService.findByUserNameOrEmail(search.getUsername());



    }



/*
            method = RequestMethod.GET,
            produces = "application/json"

            */
    /*)
    public String hi(@RequestParam(value = "name") String name){
        return name+" - TEST";
    }
*/

/*
    @GetMapping(value="/hello")
    public String hi(@RequestParam(value = "name") String name){
    //    return name+" - TEST";
        return json;
    }
*/

}
}

