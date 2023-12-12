package io.github.yikuaibaiban.fluent_validation;

import br.com.fluentvalidator.AbstractValidator;

import java.util.Map;

/**
 * 流式验证器上下文
 *
 * @author ykbb
 * @since 2023/11/13
 */
public class FluentValidatorContext {

    /**
     * 验证器集合
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, AbstractValidator> validators;

    /**
     * 获取验证器集合
     *
     * @return 验证器集合
     */
    @SuppressWarnings({"rawtypes"})
    public static Map<String, AbstractValidator> getValidators() {
        if (validators == null) {
            return new java.util.HashMap<>();
        }
        return validators;
    }

    /**
     * 设置验证器集合
     *
     * @param validators 验证器集合
     */
    @SuppressWarnings("rawtypes")
    public static void setValidators(Map<String, AbstractValidator> validators) {
        FluentValidatorContext.validators = validators;
    }
}
