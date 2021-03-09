import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { Material } from '../models/material';
import { Skill } from '../models/skill';

@Injectable({
  providedIn: 'root'
})
export class AddCourseService {

  constructor(private http: HttpClient) { }

  ADD_COURSE = '/course-cu/create';
  ADD_SKILL = 'skill/create';
  ADD_MATERIAL = 'material/add';

  addCourse(course: Course): any {
    return this.http.post(this.ADD_COURSE, course);
  }

  addSkill(skill: Skill): any {
    return this.http.post(this.ADD_SKILL, skill);
  }

  addMaterial(courseId: number, fileName: string, fileType: string, createdOn: Date, file: File): any {
    const formData: FormData = new FormData();
    formData.append('courseId', courseId as unknown as string);
    formData.append('fileName', fileName);
    formData.append('fileType', fileType);
    formData.append('createdOn', (createdOn.toString()));
    formData.append('file', file);

    return this.http.post(this.ADD_MATERIAL, formData, {responseType: 'text'});

  }
}
