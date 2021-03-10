export class Feedback {
    courseId!: number;
    participantName!: string;
    feedbackText!: string;
    rating!: number;
    createdOn!: Date;

    constructor(courseId: number, participantName: string, feedbackText: string, rating: number, createdOn: Date) {
        this.courseId = courseId;
        this.participantName = participantName;
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.createdOn = createdOn;
    }
}