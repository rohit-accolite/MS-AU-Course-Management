import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Trending } from '../models/trending';
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
    // this.chart = new Chart('barchart',{
    //   type: 'bar',
    //   data: {
    //     labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    //     datasets: [{
    //       label: "Feedback Trends",
    //       data: [12, 19, 3, 5, 2, 3],
    //       backgroundColor: [
    //         'rgba(255, 99, 132, 0.2)',
    //         'rgba(54, 162, 235, 0.2)',
    //         'rgba(255, 206, 86, 0.2)',
    //         'rgba(75, 192, 192, 0.2)',
    //         'rgba(153, 102, 255, 0.2)',
    //         'rgba(255, 159, 64, 0.2)'
    //     ]
    //     }]
    //   }
    // });

    this.trendingService.getAllCourses().subscribe((resp: any) => {
      resp.forEach((course: any) => {
        this.courseNames.push(course.courseName);
        this.color1.push('rgba(255, 206, 86, 0.5)');
        this.color2.push('rgba(75, 192, 192, 0.5)');

        // console.log('name'+course.courseName);
        // console.log('id'+course.id)

        this.trendingService.getFeedBacksByCourseId(course.id).subscribe((resp: any) => {
          this.feedbackCount.push(resp.length);
          // console.log('length'+resp.length)
        });

        this.trendingService.getAvgRatingByCourseId(course.id).subscribe((resp: number) => {
          this.avgRating.push(Math.round(resp));
          // console.log('avg'+resp);
          // console.log('round'+Math.round(resp));
        });
        
      });
    });

    console.log(this.courseNames);
    console.log(this.feedbackCount);
    console.log(this.avgRating);

    this.feedbackChart = new Chart('feedbackChart',{
      type: 'bar',
      data: {
        labels: this.courseNames,
        datasets: [{
          label: "Feedback Trends",
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
          label: "Rating Trends",
          data: this.avgRating,
          backgroundColor: this.color2
        }]
      }
    });

    window.resizeBy(50,50);
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
