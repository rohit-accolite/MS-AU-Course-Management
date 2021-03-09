import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EditCourseService {

  constructor(private http: HttpClient) { }

  UPDATE_COURSE_BY_ID = 'course-cu/update/';
  UPDATE_SKILL_BY_ID = 'skill/update/';

  updateCourseById(updatedCourse : any): any {
    return this.http.put(this.UPDATE_COURSE_BY_ID + updatedCourse.id, updatedCourse);
  }

  updateSkillById(updatedSkill : any): any {
    return this.http.put(this.UPDATE_SKILL_BY_ID + updatedSkill.id, updatedSkill);
  }
}
