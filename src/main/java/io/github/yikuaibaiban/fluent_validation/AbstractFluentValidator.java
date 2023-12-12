package io.github.yikuaibaiban.fluent_validation;

import br.com.fluentvalidator.AbstractValidator;

/**
 * 抽象流式验证器
 *
 * @author ykbb
 * @since 2023/11/20
 */
@SuppressWarnings("unused")
public abstract class AbstractFluentValidator<T> extends AbstractValidator<T> {
    /**
     * 获取上下文键
     *
     * @return {@link String}
     */
    protected String getContextKey() {
        return this.getClass().getSimpleName();
    }
}
