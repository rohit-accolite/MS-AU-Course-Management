import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  courseList: any;
  dataSource: any;


  constructor(private router: Router, private homeService: HomeService) { }

  ngOnInit(): void {
    this.homeService.getAllCourses().subscribe((resp: any) => {
      this.courseList = resp;
      this.dataSource = new MatTableDataSource(this.courseList);
    }, (error: any) => {
      // this.toastr.error('Error!', 'Can not connect to database to fetch tasks');
      console.log("Error");
    });
  }

  displayedColumns: string[] = ['courseName', 'courseDescription', 'preRequisite', 'action'];

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  viewCourse(course: any): any {
    // console.log('course: ' + course);
    localStorage.setItem('course',JSON.stringify(course));
    this.router.navigate(['view']);
  }

  signOut(): void {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then( () => {
      // localStorage.removeItem('userData');
      localStorage.clear();
      console.log('User signed out.');
    });
    this.router.navigate(['']);
  }
}
