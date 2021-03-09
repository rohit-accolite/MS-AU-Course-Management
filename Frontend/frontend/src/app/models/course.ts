export class Course {
    courseName!: string;
    courseDescription!: string;
    preRequisite!: string;
    createdOn!: Date;
    lastModifiedOn!: Date;
    createdBy!: number;

    constructor(courseName: string, courseDescription: string, preRequisite: string, createdOn: Date, lastModifiedOn: Date, createdBy: number) {
        // if(courseName)
            this.courseName = courseName;
        // if(courseDescription)
            this.courseDescription = courseDescription;
        // if(preRequisite)
            this.preRequisite = preRequisite;
        // if(createdOn)
            this.createdOn = createdOn;
        // if(lastModifiedOn)
            this.lastModifiedOn = lastModifiedOn;
        // if(createdByUser)
            this.createdBy = createdBy;
        
    }
}