package io.github.yikuaibaiban.fluent_validation;

import java.lang.annotation.*;

/**
 * Fluent 验证器(FluentValidator)
 *
 * @author ykbb
 * @since 2023/12/11
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FluentValidator {
    /**
     * 关联验证器(Associated validators)
     *
     * @return {@link String}
     */
    String value();
}
