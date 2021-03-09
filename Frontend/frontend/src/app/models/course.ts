export class Course {
    courseName!: string;
    courseDescription!: string;
    preRequisite!: string;
    createdOn!: Date;
    lastModifiedOn!: Date;
    userId!: number;

    constructor(courseName: string, courseDescription: string, preRequisite: string, createdOn: Date, lastModifiedOn: Date, userId: number) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.preRequisite = preRequisite;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.userId = userId;
    }
}