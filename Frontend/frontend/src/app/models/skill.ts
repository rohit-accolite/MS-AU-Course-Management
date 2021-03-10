export class Skill{
    courseId!: Number;
    skillName!: string;

    constructor(courseId: Number, skillName: string) {
        this.courseId = courseId;
        this.skillName = skillName;
    }
}