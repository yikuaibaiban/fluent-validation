package io.github.yikuaibaiban.fluent_validation;

/**
 * 验证方式
 *
 * @author ykbb
 * @since 2023/12/11
 */
public enum ValidationMethod {
    /**
     * Disable Validator
     */
    DISABLE,
    /**
     * Spring Validator
     */
    SPRING,
    /**
     * Fluent Validator
     */
    FLUENT,
    /**
     * Spring Validator First
     */
    SPRING_FIRST
}
