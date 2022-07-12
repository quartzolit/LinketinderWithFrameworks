export class Vacancy{
    title:string;
    id: number;
    skills:string[]

    constructor(title?, skills?){
        this.title = title;
        this.skills = skills
    }
}