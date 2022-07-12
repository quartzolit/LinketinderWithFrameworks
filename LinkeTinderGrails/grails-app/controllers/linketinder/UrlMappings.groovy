package linketinder

class UrlMappings {

    static mappings = {

        post "/company(.$format)?"(controller: "company",action:"save")
        get "/company(.$format)?"(controller: "company",action:"index")
        get "/company/$idCompany(.$format)?"(controller: "company",action:"show")

        post "/vacancy/$idCompany(.$format)?"(controller: "vacancy",action:"save")
        get "/vacancy(.$format)?"(controller: "vacancy",action: "index")
        get "/vacancy/skills/$idVacancy(.$format)" (controller: "vacancy",action: "show")

        delete "/vacancy/$idVacancy(.$format)" (controller: "vacancy",action: "delete")

        put "/vacancy/$title(.$format)" (controller: "vacancy",action: "update")

        post "/skill/$idVacancy(.$format)" (controller: "vacancy",action: "save")



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
