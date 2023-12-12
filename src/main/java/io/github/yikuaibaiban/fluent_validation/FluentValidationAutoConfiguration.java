package io.github.yikuaibaiban.fluent_validation;

import br.com.fluentvalidator.AbstractValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Map;

/**
 * 流式验证配置项
 *
 * @author ykbb
 * @since 2023/11/13
 */
@Configuration
@EnableConfigurationProperties(FluentValidationProperties.class)
@Slf4j
@AllArgsConstructor
public class FluentValidationAutoConfiguration implements ApplicationContextAware {
    private final FluentValidationProperties fluentValidationProperties;

    /**
     * 验证器
     *
     * @return {@link LocalValidatorFactoryBean}
     *
     * @see <a href="https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html">https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html</a>
     * @see <a href="https://mvallim.github.io/java-fluent-validator">https://mvallim.github.io/java-fluent-validator</a>
     */
    @Bean
    @AutoConfigureOrder(Integer.MIN_VALUE)
    public LocalValidatorFactoryBean validator() {
        return new FluentValidatorDataBindValidator(fluentValidationProperties);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Validator cache starts.");

        Map<String, AbstractValidator> validators = applicationContext.getBeansOfType(AbstractValidator.class);

        if(validators.isEmpty()){
            log.warn("Can't find any validator.");
        }

        FluentValidatorContext.setValidators(validators);
        FluentValidatorContext.getValidators().forEach((k, v) -> log.info("cached validator key = {}, validator value = {}", k, v));

        log.info("The validator cache ends.");
    }
}
