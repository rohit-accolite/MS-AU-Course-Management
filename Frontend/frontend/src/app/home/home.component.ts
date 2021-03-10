import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  courseList: any;
  dataSource: any;
  currentUser: any;

  constructor(private router: Router, private homeService: HomeService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.homeService.getAllCourses().subscribe((resp: any) => {
      this.courseList = resp;
      this.dataSource = new MatTableDataSource(this.courseList);
    }, (error: any) => {
      this.toastr.error('Can not connect to database to fetch courses', 'Error!');
    });

    this.currentUser = localStorage.getItem('userData');
    this.currentUser = JSON.parse(this.currentUser);
  }

  displayedColumns: string[] = ['position','courseName', 'courseDescription', 'preRequisite', 'action'];

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  viewCourse(course: any): any {
    localStorage.setItem('course',JSON.stringify(course));
    this.router.navigate(['view']);
  }

  deleteCourse(courseId: number): any {

    this.homeService.deleteCourseById(courseId).subscribe((resp: any) => {
      if(resp) {
        this.homeService.getAllCourses().subscribe((resp: any) => {
          this.courseList = resp;
          this.dataSource = new MatTableDataSource(this.courseList);
        });
        this.toastr.success('Course Deleted', 'Success!');
      }
      else {
        this.toastr.error('Could not delete course', 'Error!');
      }
    }, (error: any) => {
      this.toastr.error('Can not connect to database to delete course', 'Error!');
    });
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
    });
    this.router.navigate(['']);
  }
}
