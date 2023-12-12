# Fluent Validation
<p>
    <a target="_blank" href='https://github.com/yikuaibaiban/fluent-validation'>
        <img src="https://img.shields.io/github/stars/yikuaibaiban/fluent-validation?logo=github&label=fluent-validation" alt="GitHub Repo stars"/></a>
    <a target="_blank" href='https://gitee.com/yikuaibaiban/fluent-validation'>
        <img src="https://gitee.com/yikuaibaiban/fluent-validation/badge/star.svg" alt="Gitee Star"/></a>
</p>

### Fluent Validation 一个优雅的且强大的验证框架

## 特性

1. 轻量

   > 整个框架只依赖了[neoremind/fluent-validator](https://github.com/neoremind/fluent-validator)

2. 便捷

   > 不需要复杂的配置，只需要简单几步即可使用

3. 增强

   > 提供了比Spring原生更强大的验证功能

4. 解耦

   > 分离了模型与验证之间的强依赖关系，实现代码解耦

## 快速开始

1. 安装依赖

    ```xml
    <dependency>
        <groupId>io.github.yikuaibaiban</groupId>
        <artifactId>fluent-validation-spring-boot-starter</artifactId>
        <version>{{version}}</version>
    </dependency>
    ```

2. 启用Fluent Validation

    ```java
    import io.github.yikuaibaiban.fluent_validation.EnableFluentValidator;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    
    @SpringBootApplication
    // 使用下方注解启用验证
    @EnableFluentValidator
    public class DemoApplication {
        public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
        }
    }
    ```

## 开发

1. 基于类名称的验证器创建

   > 更多验证器的语法请参考：[neoremind/fluent-validator](https://github.com/neoremind/fluent-validator)

   ```java
   import io.github.yikuaibaiban.fluent_validation.AbstractFluentValidator;
   import org.springframework.stereotype.Service;
   import org.springframework.util.StringUtils;
   
   @Service
   public class AuthorizeQueryValidator extends AbstractFluentValidator<AuthorizeQuery> {
       @Override
       public void rules() {
           setPropertyOnContext(getContextKey());
   
           ruleFor(AuthorizeQuery::getAccount)
                   .must(StringUtils::hasText)
                   .withMessage("账号不能为空")
                   .critical();
   
           ruleFor(AuthorizeQuery::getPassword)
                   .must(StringUtils::hasText)
                   .withMessage("密码不能为空")
                   .critical();
       }
   }
   ```

2. 基于注解的验证器创建

   ```java
   import io.github.yikuaibaiban.fluent_validation.FluentValidator;
   import lombok.Data;
   import lombok.ToString;
   
   @Data
   @ToString
   // 通过下方注解绑定验证器
   @FluentValidator("authorizeQueryValidator")
   public class AuthorizeQuery {
       private String account;       
       private String password;
   }
   ```

3. 配置项(application.yml)

   ```xml
   fluent-validation:
     suffix: "Validator" // 验证器后缀 例如：模型名称为AuthorizeQuery，在未指定验证器的情况下系统将会自动寻找AuthorizeQueryValidator作为其验证器
     validation-method: fluent // 验证模式 DISABLE(禁用),SPRING(只使用默认jakarta.validation),FLUENT(只使用FluentValidation),SPRING_FIRST(优先使用jakarta.validation)
   ```

## 更多文档

- [neoremind/fluent-validator](https://github.com/neoremind/fluent-validator)
