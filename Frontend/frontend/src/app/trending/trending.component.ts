import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chart } from 'chart.js';
import { TrendingService } from './trending.service';

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.scss']
})
export class TrendingComponent implements OnInit {

  constructor(private router: Router, private trendingService: TrendingService) { }

  feedbackChart: any;
  ratingChart: any;

  courseNames: any[] = [];
  feedbackCount: any[] = [];
  avgRating: number[] = [];
  color1: any[] = [];
  color2: any[] = [];
  

  ngOnInit(): void {

    this.trendingService.getAllCourses().subscribe((respC: any) => {
      respC.forEach((course: any) => {
        this.trendingService.getFeedBacksByCourseId(course.id).subscribe((respF: any) => {
          this.trendingService.getAvgRatingByCourseId(course.id).subscribe((respR: number) => {
            this.courseNames.push(course.courseName);
            this.color1.push('rgba(255, 206, 86, 0.5)');
            this.color2.push('rgba(75, 192, 192, 0.5)');
            this.avgRating.push(Math.round(respR));
            this.feedbackCount.push(respF.length);
          });
        });
      });
    });

    // console.log(this.courseNames);
    // console.log(this.feedbackCount);
    // console.log(this.avgRating);

    this.feedbackChart = new Chart('feedbackChart',{
      type: 'bar',
      data: {
        labels: this.courseNames,
        datasets: [{
          label: "Feedback Count",
          data: this.feedbackCount,
          backgroundColor: this.color1
        }]
      }
    });

    this.feedbackChart = new Chart('ratingChart',{
      type: 'bar',
      data: {
        labels: this.courseNames,
        datasets: [{
          label: "Average Rating",
          data: this.avgRating,
          backgroundColor: this.color2
        }]
      }
    });

  }

  goToAddCourse(): void {
    this.router.navigate(['create']);
  }

  goToHome(): void {
    this.router.navigate(['home']);
  }

  signOut(): void {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then( () => {
      localStorage.clear();
    });
    this.router.navigate(['']);
  }

}
