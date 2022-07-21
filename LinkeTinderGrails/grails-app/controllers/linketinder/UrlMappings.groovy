package linketinder

class UrlMappings {

    static mappings = {

        post "/api/company(.$format)?"(controller: "company",action:"save")
        get "/api/company(.$format)?"(controller: "company",action:"index")
        get "/api/company/$idCompany(.$format)?"(controller: "company",action:"show")

        post "/api/vacancy/$idCompany(.$format)?"(controller: "vacancy",action:"save")
        get "/api/vacancy(.$format)?"(controller: "vacancy",action: "index")
        get "/api/vacancy/skills/$idVacancy(.$format)" (controller: "vacancy",action: "show")
        delete "/api/vacancy/$idVacancy(.$format)" (controller: "vacancy",action: "delete")
        put "/api/vacancy/$title(.$format)" (controller: "vacancy",action: "update")

        post "/api/skill/$idVacancy(.$format)" (controller: "vacancy",action: "save")



        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
