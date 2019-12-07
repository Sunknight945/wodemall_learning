package com.macro.mall.tiny.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 已经是 spring的组建了, 所以接下来可以配置swagger的bean实例docket
 * 通过修改配置实现调用接口自带Authorization头，这样就可以访问需要登录的接口了。
 *
 * @author ovo
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
  
  /**
   * swagger的bean实例docket.
   */
  @Bean
  public Docket RestDocketApi() {
    return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(apiInfo())
               .select()
               //RequestHandlerSelectors,配置要描接口的方式
               //basePackage:指定要扫描的包
               .apis(RequestHandlerSelectors.basePackage("com.macro.mall.tiny.controller"))
               //过滤什么路径
               .paths(PathSelectors.any())
               .build()  //build 一般是工厂模式
               //添加登录认证
               .securitySchemes(securitySchemes())
               .securityContexts(securityContexts());
  }
  
  
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
               .title("SwaggerUi前后分离接口")
               .description("mall-tiny 购物的接口")
               //.termsOfServiceUrl("") //服务条款网址
               .contact("uiys/")
               .version("1.0")
               .build();
  }
  
  //通过修改配置实现调用接口自带 Authorization 头，这样就可以访问需要登录的接口了。
  //修改 默认的 .securitySchemes(安全策略) 来实现启用
  //swagger的时候可以让其自带Authorization(授权书)头这样就可以测试admin->role->permission
  //之间的获取权限的了相关内容了.
  //效果：配置完之后点击右上角的Authorize，弹出认证窗口之后输入请求token，这样之后的每次请求的请求头都会带有token认证信息
  private List<ApiKey> securitySchemes() {
    //设置请求头信息
    List<ApiKey> result = new ArrayList<>();
    ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
    result.add(apiKey);
    return result;
  }
  
  
  private List<SecurityContext> securityContexts() {
    //设置需要登录认证的路径
    List<SecurityContext> result = new ArrayList<>();
    result.add(getContextByPath("/brand/.*"));
    return result;
  }
  
  private SecurityContext getContextByPath(String pathRegex){
    return SecurityContext.builder()
               .securityReferences(defaultAuth())
               .forPaths(PathSelectors.regex(pathRegex))
               .build();
  }
  
  private List<SecurityReference> defaultAuth() {
    List<SecurityReference> result = new ArrayList<>();
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    result.add(new SecurityReference("Authorization", authorizationScopes));
    return result;
  }
}