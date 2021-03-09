import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from '../models/course';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  GET_ALL_COURSES = '/course-rd/get-all';

  constructor(private http: HttpClient) { }

  getAllCourses(): any {
    return this.http.get(this.GET_ALL_COURSES);
  }
}
