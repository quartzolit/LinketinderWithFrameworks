package linketinder

class Company {
    String name;
    String cnpj;
    String email;
    String state;
    String cep;
    String country;
    String description;
    String password;

    static hasMany = [vacancies:Vacancy, skills:Skill]

    static constraints = {

    }

    static mapping = {
        table name:"companies2"
    }
}
