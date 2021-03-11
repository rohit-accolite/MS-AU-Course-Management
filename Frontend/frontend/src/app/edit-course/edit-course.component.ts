import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { EditCourseService } from './edit-course.service';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.scss']
})
export class EditCourseComponent implements OnInit {
  
  constructor(private router: Router, private editCourseService: EditCourseService, private toastr: ToastrService) { }
  
  currentUser: any;
  selectedCourse: any;
  selectedSkill: any;


  ngOnInit(): void {
    this.currentUser = localStorage.getItem('userData');
    this.currentUser = JSON.parse(this.currentUser);

    this.selectedCourse = localStorage.getItem('course');
    this.selectedCourse = JSON.parse(this.selectedCourse);

    this.selectedSkill = localStorage.getItem('skills');
    this.selectedSkill = JSON.parse(this.selectedSkill);

  }

  editCourse(): any {
    // console.log(this.selectedCourse);
    // console.log(this.selectedSkill);
    this.selectedCourse.lastModifiedOn = new Date();

    const newCourse = {
      id: this.selectedCourse.id,
      courseName: this.selectedCourse.courseName,
      courseDescription: this.selectedCourse.courseDescription,
      preRequisite: this.selectedCourse.preRequisite,
      createdOn: this.selectedCourse.createdOn,
      lastModifiedOn: new Date(),
      createdBy: this.selectedCourse.createdBy
    }

    const newSkill = {
      courseId: this.selectedSkill.courseId,
      id: this.selectedSkill.id,
      skillName: this.selectedSkill.skillName
    }

    this.editCourseService.updateCourseById(newCourse).subscribe((respCourse: any) => {
      localStorage.setItem('course', JSON.stringify(respCourse));
    });
    
    this.editCourseService.updateSkillById(newSkill).subscribe((respSkill: any) => {
      localStorage.setItem('skills', JSON.stringify(respSkill));
    });

    this.toastr.success('Course Updated', 'Success');
  }

  goToHome(): void {
    this.router.navigate(['home']);
  }

  goToAddCourse(): void {
    this.router.navigate(['create']);
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
