import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {Request} from "./request";
import {HttpService} from "./http.service";



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpService]
})
export class AppComponent {
  title = 'app';
  req: Request=new Request(); // данные вводимого пользователя


  minDate = new Date(); // firstdate
  maxDate = new Date();



//  minDate.setYear(maxDate.getFullYear()-120);
  //maxDate.setYear(maxDate.getFullYear()-18);

  //minDate = new Date();
  //maxDate = new Date(2020, 0, 1);

  receivedReq: Request; // полученный пользователь
  done: boolean = false;


  public authForm: FormGroup;


  constructor(private httpService: HttpService) {

    this.DateSet();

    this.authForm = new FormGroup({
      firstName: new FormControl(),
      middleName: new FormControl(),
      lastName: new FormControl(),
      birthday: new FormControl(),
      email: new FormControl("", [
        Validators.required,
        Validators.pattern("[a-zA-Z_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")
      ])
    });
  }
  submit(req: Request) {
  //if(user.name !='') {
    if(this.authForm.valid) {
    this.httpService.postData(req)
      .subscribe(
        (data: Request) => {
          this.receivedReq = data;
          this.done = true;
          console.log("Server Response");
          console.log(this.receivedReq.firstName);
        },
        error => console.log(error)
      );
  } else {alert("Пиздец!")}

  }

  public openModal() {
    console.log(this.authForm);

    //minDate = new Date(); // firstdate

  //  this.minDate = new Date(); // firstdate
 //   minDate.setYear(maxDate.getFullYear()-120);

  }

  public DateSet() {

// this.minDate = new Date(); // firstdate
    //this.minDate.setYear(maxDate.getFullYear()-120);
    this.minDate.setFullYear(this.minDate.getFullYear()-120)
    this.maxDate.setFullYear(this.maxDate.getFullYear()-18)



    console.log(this.minDate);
    console.log(this.maxDate);

  }

  public save() {
    console.log(this.authForm);
  }
}