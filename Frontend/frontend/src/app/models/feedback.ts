export class Feedback {
    courseId!: number;
    participantName!: string;
    feedbackText!: string;
    createdOn!: Date;

    constructor(courseId: number, participantName: string, feedbackText: string, createdOn: Date) {
        this.courseId = courseId;
        this.participantName = participantName;
        this.feedbackText = feedbackText;
        this.createdOn = createdOn;
    }
}