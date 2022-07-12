package linketinder

class Vacancy {
    String title;


    static belongsTo = [Company]
    static  hasMany = [Skill]//List<SKill> skills = []

    static constraints = {
    }

    static mapping={
        table name: "vacancies2"
    }
}
