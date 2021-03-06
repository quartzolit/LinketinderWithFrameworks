import { Skill } from "./Skill.model";
import { Vacancy } from "./Vacancy.model";

export class Person{
    type: string;
    id?:number;
    email: string;
    password?: string;
    name?: string;
    surName?: string;
    //age?: number;
    birthdate?: Date;
    cpf?: string;
    companyName?: string;
    cnpj?: string;
    country: string;
    cep: string;
    state: string;
    description: string;
    skills?: Skill[];
    vacancy?: Vacancy[];
    approval?: Person[];
    disapproval?: Person[];
}
