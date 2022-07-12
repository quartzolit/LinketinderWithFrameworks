package linketinder

class Skill {
    String skillName

    static belongsTo = [Company,Vacancy]
    static hasMany = [companies:Company, vacancies:Vacancy]

    static constraints = {

    }

    static mapping = {
        table name: "skills2"
    }
}
