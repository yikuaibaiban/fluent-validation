package io.github.yikuaibaiban.fluent_validation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 Fluent Validator(Enable Fluent Validator)
 *
 * @author ykbb
 * @since 2023/12/08
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(FluentValidationAutoConfiguration.class)
public @interface EnableFluentValidator {
}
