export class Trending {
    courseName!: string;
    feedbackCount!: number;
    averageRating!: number;

    constructor(courseName: string, feedbackCount: number, averageRating: number) {
        this.courseName = courseName;
        this.feedbackCount = feedbackCount;
        this.averageRating = averageRating;
    }
}