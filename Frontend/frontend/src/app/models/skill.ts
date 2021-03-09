export class Skill{
    courseId!: Number;
    skillName!: string;

    constructor(courseId?: Number, skillName?: string) {
        if(courseId)
            this.courseId = courseId;
        if(skillName)
            this.skillName = skillName;
    }
}