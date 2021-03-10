import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ViewCourseService } from './view-course.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FeedbackDialogComponent } from '../feedback-dialog/feedback-dialog.component';
import { Feedback } from '../models/feedback';
import { ToastrService } from 'ngx-toastr';
import { MaterialDialogComponent } from '../material-dialog/material-dialog.component';

@Component({
  selector: 'app-view-course',
  templateUrl: './view-course.component.html',
  styleUrls: ['./view-course.component.scss']
})
export class ViewCourseComponent implements OnInit {
  
  constructor(private router: Router, private viewCouseService: ViewCourseService, public dialog: MatDialog, private toastr: ToastrService) { }
  
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
  
  materialSource: any;
  materialColumns: string[] = ['version', 'fileName', 'createdOn', 'action'];
  
  allMaterials: any;
  materialListLength: any;
  newMaterial: any;
  
  panelOpenState = false;

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
      this.toastr.error('Could not fetch skills', 'Error!');
    });

    this.getFeedBacksByCourseId(this.selectedCourse.id);

    this.viewCouseService.getUserById(this.selectedCourse.createdBy).subscribe((resp: any) =>{
      this.OPUser = resp;
    }, (error: any) => {
      this.toastr.error('Could not fetch user data', 'Error!');
    });

    this.getMaterialsByCourseId(this.selectedCourse.id);
  }

  goToHome(): void {
    this.router.navigate(['home']);
  }

  goToEditCourse(): void {
    this.router.navigate(['edit']);
  }

  signOut(): void {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then( () => {
      localStorage.clear();
      console.log('User signed out.');
    });
    this.router.navigate(['']);
  }

  getMaterialsByCourseId(courseId: number): any {
    this.viewCouseService.getMaterialsByCourseId(courseId).subscribe((resp: any) => {
        this.allMaterials = resp;
        this.materialListLength = this.allMaterials.length;
        this.materialSource = new MatTableDataSource(this.allMaterials);
    });
  }

  deleteMaterial(id: number): any {
    this.viewCouseService.deleteMaterial(id).subscribe((resp: any) => {
      if(resp) {
        this.toastr.success('Material deleted', 'Success!');
        this.getMaterialsByCourseId(this.selectedCourse.id);
      }
      else {
        this.toastr.error('Could not delete material', 'Error!');
      }
    });
  }

  base64ToArrayBuffer(base64: any): any {
    const binaryString = window.atob(base64);
    const len = binaryString.length;
    const bytes = new Uint8Array(len);

    for (let i = 0; i < len; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }
    return bytes.buffer;
  }

  downloadMaterial(data: any, fileType: any) {
    const byteArray = this.base64ToArrayBuffer(data);
    const blob = new Blob([byteArray], { type: fileType });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
  }
  
  getFeedBacksByCourseId(courseId: number): any {
    this.viewCouseService.getFeedBacksByCourseId(courseId).subscribe((resp: any) =>{
      this.courseFeedbacks = resp;
      for(let i = 0; i < this.courseFeedbacks.length; i++) {
        this.courseFeedbacks[i].createdOn = new Date(this.courseFeedbacks[i].createdOn);
      }
      this.dataSource = new MatTableDataSource(this.courseFeedbacks);
    });
  }

  openMaterialDialog(): void {
    const dialogRef = this.dialog.open(MaterialDialogComponent, {
      data: {material: this.newMaterial}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result == null) return;

      this.newMaterial = result;
      this.viewCouseService.addMaterial(this.selectedCourse.id, this.newMaterial.name, this.newMaterial.type, new Date(), this.newMaterial).subscribe((respMaterial: any) => {
        this.getMaterialsByCourseId(this.selectedCourse.id);
        this.toastr.success('Material uploaded', 'Success');
        this.newMaterial = null;
      });
    });
  }

  openFeedbackDialog(): void {
    this.feedbackText = '';
    const dialogRef = this.dialog.open(FeedbackDialogComponent, {
      data: {name: this.currentUser.firstName, feedback: this.feedbackText}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result == null) return;
      
      this.feedbackText = result;
      let newFeedback = new Feedback(this.selectedCourse.id, this.currentUser.firstName, this.feedbackText, new Date());
      
      this.viewCouseService.createFeedback(newFeedback).subscribe((resp: any) => {
        this.getFeedBacksByCourseId(this.selectedCourse.id);
        this.toastr.success('Feedback Added', 'Success!');
      });
    });
  }
}
