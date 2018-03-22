import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Request} from './request';

@Injectable()
export class HttpService{

  constructor(private http: HttpClient){ }


  postData(req: Request){

    const body = {firstName: req.firstName, middleName: req.middleName, lastName: req.lastName, birthday: req.birthday, email: req.email};
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log(body);
    return this.http.post('http://localhost:8080/api/values', body);
  }
}
