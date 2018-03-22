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
  req: Request=new Request();

  // Min-max date for entered Date validation
  minDate = new Date();
  maxDate = new Date();

  receivedReq: Request; // recieved request
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


    this.httpService.postData(req)
      .subscribe(
        (data: Request) => {
          this.receivedReq = data;
          this.done = true;
          console.log("Server Response");
          console.log(this.receivedReq);
        },
        error => console.log(error)
      );
  }


  public DateSet() {
    this.minDate.setFullYear(this.minDate.getFullYear()-120)
    this.maxDate.setFullYear(this.maxDate.getFullYear()-18)
  }

  public save() {
    console.log(this.authForm);
  }
}
