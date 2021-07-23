import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';

import { Credentials } from '../models/credentials.model';
import { Users } from '../models/users.model';
import { Patient } from '../models/patient';
import { Appointment } from '../models/appointment';
import { tap } from 'rxjs/operators';
import { catchError, retry } from 'rxjs/operators';

@Injectable()
export class ApiService {

  API_URL: String;
  AUTH_API_URL = '/auth/server/';

  constructor(private http: HttpClient) {
    this.API_URL = 'api';
  }

  public checkLogin(username: string, password: string): Observable<Credentials> {
    // should return response from server
  return this.http
    .post<Credentials>(this.API_URL + this.AUTH_API_URL, {
      username,
      password,
    })
    .pipe(catchError(this.handleError));
    // handle error 

  }

  public getUserDetails(userId: number): Observable<Users> {
    // should return user details retireved from server
    return this.http
      .get<Users>(this.API_URL + '/users/' + userId)
      .pipe(catchError(this.handleError));
    // handle error 

  }

  public updateDetails(userDetails: Users): Observable<Users> {
    // should return user details if successfully updated the details
     return this.http
       .put<Users>(this.API_URL + '/users/' + userDetails.userId, userDetails)
       .pipe(catchError(this.handleError));
    // handle error 

  }

  public registerPatient(patientDetails: any): Observable<any> {

    // should return response from server if patientDetails added successfully
    return this.http
      .post<Patient>(this.API_URL + '/allpatients', patientDetails)
      .pipe(catchError(this.handleError));

    // handle error 

  }

  public getAllPatientsList(): Observable<any> {

    // should return all patients from server
    return this.http
      .get<any>(this.API_URL + '/allpatients')
      .pipe(catchError(this.handleError));
    // handle error 

  }

  public getParticularPatient(id): Observable<any> {

    // should return particular patient details from server
    return this.http
      .get<any>(this.API_URL + '/allpatients/' + id)
      .pipe(catchError(this.handleError));
    // handle error 

  }

  public getDiseasesList(): Observable<any> {

    // should return diseases from server
    return this.http
      .get<any[]>(this.API_URL + '/diseases')
      .pipe(catchError(this.handleError));;
    // handle error 

  }

  public bookAppointment(appointmentDetails: any): Observable<any> {

    // should return response from server if appointment booked successfully
    return this.http
      .post<Appointment>(this.API_URL + '/reqappointments', appointmentDetails)
      .pipe(catchError(this.handleError));
    // handle error 

  }

  public requestedAppointments(): Observable<any> {

    // should return all requested appointments from server
    return this.http
      .get<Appointment[]>(this.API_URL + '/reqappointments')
      .pipe(catchError(this.handleError));
    // handle error 

  }

  public getAppointments(userId): Observable<any> {

    // should return appointments of particular patient from server
     return this.http
       .get<Appointment[]>(
         this.API_URL + '/reqappointments?patientId=' + userId
       )
       .pipe(catchError(this.handleError));
    // handle error 

  }

  public deleteAppointment(appointmentId): Observable<any> {

    // should delete the appointment
    return this.http
      .delete<void>(this.API_URL + '/reqappointments/' + appointmentId)
      .pipe(catchError(this.handleError));
    // handle error

  }

  private handleError(error: HttpErrorResponse) {
    // handle error
      console.error(
        `Backend returned code ${error.status}, body was: `,
        error.error
      );
    return throwError(error);
  }
  
}
