import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { GoogleApiModule } from 'ng-gapi';
import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';

import { ViewCourseComponent } from './view-course/view-course.component';
import { FeedbackDialogComponent } from './feedback-dialog/feedback-dialog.component';
import { AddCourseComponent } from './add-course/add-course.component';
import { EditCourseComponent } from './edit-course/edit-course.component';
import { MaterialDialogComponent } from './material-dialog/material-dialog.component';
import { TrendingComponent } from './trending/trending.component';
import { ChartsModule } from 'ng2-charts';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    ViewCourseComponent,
    FeedbackDialogComponent,
    AddCourseComponent,
    EditCourseComponent,
    MaterialDialogComponent,
    TrendingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    GoogleApiModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    ChartsModule
  ],
  entryComponents: [
    FeedbackDialogComponent,
    MaterialDialogComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
