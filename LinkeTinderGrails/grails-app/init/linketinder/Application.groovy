package linketinder

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.grails.SpringfoxGrailsIntegrationConfiguration
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.VendorExtension
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@CompileStatic
@EnableSwagger2
@Import([SpringfoxGrailsIntegrationConfiguration])
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        ApiInfo info = new ApiInfo(
                "Linketinder - MS-Signup",
                "MS used for signup new users to the platform",
                "0.0.1",
                "Terms of Service",
                new Contact("Luiz Arthur Silva Moura", "https://github.com/quartzolite", "larthurs.moura@gmail.com"),
                "Apache License Version 2.0","https://www.apache.org/license.html",
                new ArrayList<VendorExtension>())
        return info
    }
}