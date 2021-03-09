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
import { FormsModule } from '@angular/forms';
import { ViewCourseComponent } from './view-course/view-course.component';
import { FeedbackDialogComponent } from './feedback-dialog/feedback-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    ViewCourseComponent,
    FeedbackDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    GoogleApiModule,
    MaterialModule,
    FormsModule
  ],
  entryComponents: [
    FeedbackDialogComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
