package com.oucenter.core.finder.impl;

import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.stereotype.Component;
@Component
public class FinderIntroductionAdvisor extends DefaultIntroductionAdvisor
{
    public FinderIntroductionAdvisor()
    {
        super(new FinderIntroductionInterceptor());
    }
}
