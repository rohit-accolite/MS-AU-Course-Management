import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TrendingService {

  GET_ALL_COURSES = '/course-rd/get-all';
  GET_FEEDBACK_BY_COURSE_ID = 'feedback/get-by-course-id/';
  GET_AVG_RATING_BY_COURSE_ID = 'feedback/avg-rating-on-course/';

  constructor(private http: HttpClient) { }

  getAllCourses(): any {
    return this.http.get(this.GET_ALL_COURSES);
  }

  getFeedBacksByCourseId(id: number): any {
    return this.http.get(this.GET_FEEDBACK_BY_COURSE_ID + id);
  }

  getAvgRatingByCourseId(id: number): any {
    return this.http.get(this.GET_AVG_RATING_BY_COURSE_ID + id);
  }

}
