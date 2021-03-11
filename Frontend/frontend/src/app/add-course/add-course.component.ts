import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Course } from '../models/course';
import { Material } from '../models/material';
import { Skill } from '../models/skill';
import { AddCourseService } from './add-course.service';


@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {
  
  constructor(private router: Router, private addCourseService: AddCourseService, private toastr: ToastrService) { }
  
  currentUser: any;
  
  courseName!: string;
  courseDescription!: string;
  preRequisite!: string;
  skillName!: string;

  file: any;
  fileName = 'Upload File';
  blob!: Blob;

  ngOnInit(): void {
    this.currentUser = localStorage.getItem('userData');
    this.currentUser = JSON.parse(this.currentUser);
  }

  addCourse(): any {
    this.addCourseService.addCourse(new Course(this.courseName, this.courseDescription, this.preRequisite, new Date(), new Date(), this.currentUser.id )).subscribe((respCourse: any) => {

      this.addCourseService.addSkill(new Skill(respCourse.id, this.skillName)).subscribe((respSkill: any) => {
        if(this.file) {
          this.addCourseService.addMaterial(respCourse.id, this.file.name, this.file.type, new Date(), this.file).subscribe((respMaterial: any) => {

          });
        }
        this.toastr.success('Course Created', 'Success');
      });
    });
  }

  onFileChanged(event: any): any {
    this.file = event.target.files[0];
    if(this.file) {
      this.fileName = this.file.name;
    }
    else {
      this.fileName = 'Upload File';
    }
  }

  goToHome(): void {
    this.router.navigate(['home']);
  }

  goToTrending(): void {
    this.router.navigate(['trend']);
  }

  signOut(): void {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then( () => {
      localStorage.clear();
      console.log('User signed out.');
    });
    this.router.navigate(['']);
  }
}
