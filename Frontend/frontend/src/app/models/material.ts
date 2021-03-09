export class Material{
    courseId!: Number;
    fileName!: string;
    fileType!: string;
    createdOn!: Date;
    file!: any;

    constructor(courseId: number, fileName: string, fileType: string, createdOn: Date, file: any) {
        this.courseId = courseId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.createdOn = createdOn;
        this.file = file;
    }

}