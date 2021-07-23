import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { Credentials } from '../models/credentials.model';
import { Users } from '../models/users.model';
import { Patient } from '../models/patient';
import { Appointment } from '../models/appointment';
import { ApiService } from './api.service';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class DataService {
  isLoggedIn = false;
  isLogIn: BehaviorSubject<boolean>;
  constructor(private api: ApiService) {
    this.isLogIn = new BehaviorSubject<boolean>(false);
  }

  authenticateUser(username: string, password: string): Observable<boolean> {
    return this.api.checkLogin(username, password).pipe(
      map((data) => {
        if (data && data.userId) {
          // store 'userId' from response as key name 'userId' to the localstorage
          localStorage.setItem('userId', data.userId + '');
          // return true if user authenticated
          this.isLogIn.next(true);
          return true;
        } else {
          // return false if user not authenticated
          return false;
        }
      })
    );
  }

  getAuthStatus(): Observable<boolean> {
    // return this.isLogIn.asObservable();
    this.isLogIn.next(this.getUserId() > 0 ? true : false);
    return this.isLogIn.asObservable(); 
  }
  doLogOut() {
    // remove the key 'userId' if exists
    this.isLoggedIn = false;
    if (localStorage.getItem('userId')) {
      localStorage.removeItem('userId');
    }
    this.isLogIn.next(false);
    return this.isLoggedIn;
  }

  getUserDetails(userId: number): Observable<Users> {
    // should return user details retrieved from api service
     return this.api.getUserDetails(userId);
  }

  updateProfile(userDetails): Observable<boolean> {
    // should return the updated status according to the response from api service
    return this.api.updateDetails(userDetails).pipe(
      map((data) => (data ? true : false))
      //map boolean to Observable<boolean>
    );
  }

  registerPatient(patientDetails): Observable<any> {
    // should return response retrieved from ApiService
    return this.api.registerPatient(patientDetails);
    // handle error
  }

  getAllPatientsList(): Observable<any> {
    // should return all patients list retrieved from ApiService
    return this.api.getAllPatientsList();
    // handle error
  }

  getParticularPatient(id): Observable<any> {
    // should return particular patient details retrieved from ApiService
    return this.api.getParticularPatient(id);
    // handle error
  }

  getDiseasesList(): Observable<any> {
    // should return response retrieved from ApiService
    return this.api.getDiseasesList();
    // handle error
  }

  bookAppointment(appointmentDetails): Observable<any> {
    // should return response retrieved from ApiService
    return this.api.bookAppointment(appointmentDetails);
    // handle error
  }

  getAppointments(patientId): Observable<any> {
    // should return response retrieved from ApiService
    return this.api.getAppointments(patientId);
    // handle error
  }

  deleteAppointment(appointmentId): Observable<any> {
    // should return response retrieved from ApiService
    return this.api.deleteAppointment(appointmentId);
    // handle error
  }

  requestedAppointments(): Observable<any> {
    // should return response retrieved from ApiService

    // handle error

    return this.api.requestedAppointments();
  }

  getUserId(): number {
    // retrieve 'userId' from localstorage
    let userId = parseInt(localStorage.getItem('userId'));
    if (!this.isLogIn.value) return -1;
    return userId ? userId : -1;
  }

}


