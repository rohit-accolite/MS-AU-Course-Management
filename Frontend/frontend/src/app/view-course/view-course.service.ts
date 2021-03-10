import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Feedback } from '../models/feedback';

@Injectable({
  providedIn: 'root'
})
export class ViewCourseService {
  
  constructor(private httpClient: HttpClient) { }
  
  GET_USER_BY_ID = 'user/get-by-id/';
  
  GET_FEEDBACK_BY_COURSE_ID = 'feedback/get-by-course-id/';
  CREATE_FEEDBACK = 'feedback/create';
  
  GET_SKILL_BY_COURSE_ID = 'skill/get-by-course-id/';
  UPDATE_SKILL = 'skill/update/';
  
  GET_MATERIALS_BY_COURSE_ID = 'material/get-by-course-id/';
  ADD_MATERIAL = 'material/add';
  DELETE_MATERIAL = 'material/delete/';

  getUserById(id: number): any {
    return this.httpClient.get(this.GET_USER_BY_ID + id);
  }

  getFeedBacksByCourseId(id: number): any {
    return this.httpClient.get(this.GET_FEEDBACK_BY_COURSE_ID + id);
  }

  createFeedback(feedback: Feedback): any {
    return this.httpClient.post(this.CREATE_FEEDBACK, feedback);
  }

  getSkillByCourseId(id: number): any {
    return this.httpClient.get(this.GET_SKILL_BY_COURSE_ID + id);
  }

  updateSkill(skill: any): any {
    return this.httpClient.put(this.UPDATE_SKILL + skill.id, skill);
  }

  getMaterialsByCourseId(id: number): any {
    return this.httpClient.get(this.GET_MATERIALS_BY_COURSE_ID + id);
  }

  deleteMaterial(id: number): any {
    return this.httpClient.delete(this.DELETE_MATERIAL + id);
  }

  addMaterial(courseId: number, fileName: string, fileType: string, createdOn: Date, file: File): any {
    const formData: FormData = new FormData();
    formData.append('courseId', courseId as unknown as string);
    formData.append('fileName', fileName);
    formData.append('fileType', fileType);
    formData.append('createdOn', (createdOn.toString()));
    formData.append('file', file);

    return this.httpClient.post(this.ADD_MATERIAL, formData, {responseType: 'text'});
  }
}
