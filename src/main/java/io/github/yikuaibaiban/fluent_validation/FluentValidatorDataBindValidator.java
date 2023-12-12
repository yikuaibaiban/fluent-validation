package io.github.yikuaibaiban.fluent_validation;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.context.ValidationResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 数据绑定验证器
 *
 * @author ykbb
 * @since 2023/10/13
 */
@Slf4j
@AllArgsConstructor
public class FluentValidatorDataBindValidator extends LocalValidatorFactoryBean {
    private final FluentValidationProperties fluentValidationProperties;

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void validate(Object target, Errors errors) {
        boolean disableValidation = fluentValidationProperties.getValidationMethod() == ValidationMethod.DISABLE;
        if (disableValidation) {
            return;
        }

        boolean needSpringValidation = fluentValidationProperties.getValidationMethod() == ValidationMethod.SPRING || fluentValidationProperties.getValidationMethod() == ValidationMethod.SPRING_FIRST;
        if (needSpringValidation) {
            super.validate(target, errors);
            if (errors.getErrorCount() > 0) {
                return;
            }
        }

        boolean needFluentValidation = fluentValidationProperties.getValidationMethod() == ValidationMethod.FLUENT || fluentValidationProperties.getValidationMethod() == ValidationMethod.SPRING_FIRST;
        if (!needFluentValidation || FluentValidatorContext.getValidators().isEmpty()) {
            return;
        }

        // 获取FluentValidator注解
        FluentValidator fluentValidator = target.getClass().getAnnotation(FluentValidator.class);

        // 构建验证器名称
        String validatorKey = fluentValidator != null && StringUtils.hasText(fluentValidator.value()) ?
                StringUtils.uncapitalize(fluentValidator.value()) :
                StringUtils.uncapitalize(target.getClass().getSimpleName() + fluentValidationProperties.getSuffix());

        // 检查验证器是否存在
        if (!FluentValidatorContext.getValidators().containsKey(validatorKey)) {
            log.warn("The validator({}) is not registered.", validatorKey);
            return;
        }

        // 依据验证器名称提取已经缓存的验证器
        AbstractValidator validator = FluentValidatorContext.getValidators().get(validatorKey);
        ValidationResult validationResult = validator.validate(target);
        if (!validationResult.isValid()) {
            validationResult.getErrors().forEach(error -> errors.reject(error.getField(), error.getMessage()));
        }
    }
}
