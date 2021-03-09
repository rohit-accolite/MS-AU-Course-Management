import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ViewCourseService } from './view-course.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FeedbackDialogComponent } from '../feedback-dialog/feedback-dialog.component';
import { Feedback } from '../models/feedback';

@Component({
  selector: 'app-view-course',
  templateUrl: './view-course.component.html',
  styleUrls: ['./view-course.component.scss']
})
export class ViewCourseComponent implements OnInit {

  constructor(private router: Router, private viewCouseService: ViewCourseService, public dialog: MatDialog) { }

  selectedCourse: any;
  OPUser: any;
  currentUser: any;
  courseSkills: any;
  courseFeedbacks: any;
  createdOn: any;
  lastModifiedOn: any;

  feedbackText: any;

  dataSource: any;
  displayedColumns: string[] = ['position', 'participantName', 'feedbackText', 'createdOn'];

  ngOnInit(): void {
    this.selectedCourse = localStorage.getItem('course');
    this.selectedCourse = JSON.parse(this.selectedCourse);

    this.currentUser = localStorage.getItem('userData');
    this.currentUser = JSON.parse(this.currentUser);

    this.createdOn = new Date(this.selectedCourse.createdOn);
    this.lastModifiedOn = new Date(this.selectedCourse.lastModifiedOn);

    this.viewCouseService.getSkillByCourseId(this.selectedCourse.id).subscribe((resp: any) =>{
      this.courseSkills = resp;
      localStorage.setItem('skills',JSON.stringify(this.courseSkills));
    }, (error: any) => {
      // this.toastr.error('Error!', 'Can not connect to database to fetch tasks');
      console.log("Error");
    });

    this.viewCouseService.getFeedBacksByCourseId(this.selectedCourse.id).subscribe((resp: any) =>{
      this.courseFeedbacks = resp;
      for(let i = 0; i < this.courseFeedbacks.length; i++) {
        this.courseFeedbacks[i].createdOn = new Date(this.courseFeedbacks[i].createdOn);
      }
      this.dataSource = new MatTableDataSource(this.courseFeedbacks);
    }, (error: any) => {
      // this.toastr.error('Error!', 'Can not connect to database to fetch tasks');
      console.log("Error");
    });

    this.viewCouseService.getUserById(this.selectedCourse.createdBy).subscribe((resp: any) =>{
      this.OPUser = resp;
    }, (error: any) => {
      // this.toastr.error('Error!', 'Can not connect to database to fetch tasks');
      console.log("Error");
    });
  }

  goToHome(): void {
    this.router.navigate(['home']);
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
  
  openFeedbackDialog(): void {
    // console.log('open');
    const dialogRef = this.dialog.open(FeedbackDialogComponent, {
      data: {name: this.currentUser.firstName, feedback: this.feedbackText}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(typeof(result));
      if(result == null) return;
      
      this.feedbackText = result;
      let newFeedback = new Feedback(this.selectedCourse.id, this.currentUser.firstName, this.feedbackText, new Date());
      
      this.viewCouseService.createFeedback(newFeedback).subscribe((resp: any) => {
        this.viewCouseService.getFeedBacksByCourseId(this.selectedCourse.id).subscribe((resp: any) =>{
          this.courseFeedbacks = resp;
          for(let i = 0; i < this.courseFeedbacks.length; i++) {
            this.courseFeedbacks[i].createdOn = new Date(this.courseFeedbacks[i].createdOn);
          }
          this.dataSource = new MatTableDataSource(this.courseFeedbacks);
        }, (error: any) => {
          // this.toastr.error('Error!', 'Can not connect to database to fetch tasks');
          console.log("Error");
        });
      })
    });
  }
}
