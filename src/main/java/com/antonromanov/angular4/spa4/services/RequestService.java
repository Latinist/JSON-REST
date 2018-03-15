package com.antonromanov.angular4.spa4.services;

import com.antonromanov.angular4.spa4.model.Request;
import com.antonromanov.angular4.spa4.model.RequestList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RequestService {


    // private RequestList requestList;
    ObjectMapper mapper = new ObjectMapper();
    private List<Request> requests;

   /* @Autowired
    private RequestsRepository requestsRepository; */

    public List<Request> findByUserNameOrEmail(String username) {


        requests.stream().forEach(elem -> System.out.println("findByUserNameOrEmail " + elem.getFirstName()));
        System.out.println("А Сюда дошли или нет? ");

      /*  requiredCars = cars.stream()
                .filter(c -> c.getName() != null)
                .filter(c -> c.getName().startsWith("M")); */

        List<Request> result = requests.stream()
                .filter(x -> x.getFirstName() != null)
                .filter(x -> x.getFirstName().equalsIgnoreCase(username)).collect(Collectors.toList());
        System.out.println("Сюда дошли или нет? ");

        return result;

    }

    public List<Request> findAll() {

        //  List<Request> result = requests.stream().filter(x -> x.getFirstName().equalsIgnoreCase(username)).collect(Collectors.toList());

        return requests;

    }


    public void saveToFile() {

        try {


               mapper.writeValue(new File("d:\\user.json"), requests);




        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void loadFromFile() {

        //  List<Request> myRequestList = new ArrayList<Request>();

        requests = new ArrayList<Request>();

        try {

             if (fileCheck()) {

                 System.out.println("FILE EXISTS !!!");

                 requests = mapper.reader()
                         .forType(new TypeReference<List<Request>>() {
                         })
                         .readValue(new FileInputStream("d:\\user.json"));

            } else {

                 System.out.println("FILE NOT EXISTS !!!");

            }

            System.out.println(requests);
            requests.stream().forEach(elem -> System.out.println("element " + elem.getFirstName()));


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //   return requests;

    }


    public List<Request> addUser(String username) {

        List<Request> result = requests.stream().filter(x -> x.getFirstName().equalsIgnoreCase(username)).collect(Collectors.toList());

        return result;

    }

    public void addArticle(String username) {
     /*   List<Req> list = requestsRepository.findByTitleAndCategory(article.getTitle(), article.getCategory());
        if (list.size() > 0) {
            return false;
        } else { */

        //  Req req1 = new Req(1, "Anton", "Borisovich", "Romanov", this.convertStringToDate(), "ar@list.ru");

        //requestsRepository.save(req1);

        // requests.add(req1);

        requests.stream().forEach(elem -> System.out.println("in addArticle method " + elem.getFirstName()));
        System.out.println("We are in addArticel ---------> ");

        Request request4 = new Request(3, username, "Borisovich", "Romanov", this.convertStringToDate(), "ar@list.ru");

        requests.add(request4);


        //  return true;
    }


    @PostConstruct
    private void iniDataForTesting() {

        requests = new ArrayList<Request>();

    /*    Request request1 = new Request(1, "Anton", "Borisovich", "Romanov", this.convertStringToDate(), "ar@list.ru");
        Request request2 = new Request(2, "Anton", "Borisovich", "Romanov", this.convertStringToDate(), "ar@list.ru");
        Request request3 = new Request(3, "Anton", "Borisovich", "Romanov", this.convertStringToDate(), "ar@list.ru");

        requests.add(request1);
        requests.add(request2);
        requests.add(request3); */

        //  requests.addAll(this.loadFromFile());
        this.loadFromFile();
        requests.stream().forEach(elem -> System.out.println("Post Construct " + elem.getFirstName()));

    }

    private boolean fileCheck() {

        Boolean check;
        File file = new File("d:\\user.json");
        // File notExist = new File("xyz.txt");

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

    private Date convertStringToDate() {
        Date currentDate = new Date();
        Locale local = new Locale("ru", "RU");

        try {

            String datestring = "15.03.2018";
            //DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy", local);

            DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);
            currentDate = new Date();
            System.out.println("currentDate = " + df.format(currentDate));
            //currentDate = df.format(currentDate);
            currentDate = sdf.parse(datestring);

            /*Date date = format.parse(string);
            System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010 */

        } catch (Exception e) {
            e.printStackTrace();
        }


        return currentDate;
    }


}
