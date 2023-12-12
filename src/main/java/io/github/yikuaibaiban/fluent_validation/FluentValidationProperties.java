package io.github.yikuaibaiban.fluent_validation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Fluent Validator 配置属性
 *
 * @author ykbb
 * @since 2023/12/08
 */
@ConfigurationProperties(prefix = "fluent-validation")
@Data
public class FluentValidationProperties {
    /**
     * 验证方式(Verification method)
     */
    private ValidationMethod validationMethod = ValidationMethod.SPRING_FIRST;

    /**
     * 验证器后缀(Authenticator suffix)
     */
    private String suffix = "Validator";
}
