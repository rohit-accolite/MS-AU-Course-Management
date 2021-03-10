export class Course {
    courseName!: string;
    courseDescription!: string;
    preRequisite!: string;
    createdOn!: Date;
    lastModifiedOn!: Date;
    createdBy!: number;

    constructor(courseName: string, courseDescription: string, preRequisite: string, createdOn: Date, lastModifiedOn: Date, createdBy: number) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.preRequisite = preRequisite;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.createdBy = createdBy;
        
    }
}