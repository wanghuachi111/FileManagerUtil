package com.utils.files.rconvenience.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author thc
 * @Title:
 * @Package com.thc.servicebase
 * @Description:
 * @date 2020/8/11 4:50 下午
 */
@Configuration
@EnableSwagger2 // swagger
public class SwaggerConfig {


    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                // 接口路径中包含admin和error时不显示，不写就都会显示
                // .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    /**
     * 文档信息
     * @return
     */
    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("文件编辑器")
                .description("批量编辑文件")
                .version("1.0")
                .contact(new Contact("wanghuachi", "http://111111", "839462980@qq.com"))
                .build();
    }
}
