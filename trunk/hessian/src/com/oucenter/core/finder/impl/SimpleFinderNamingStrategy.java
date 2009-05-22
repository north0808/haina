package com.oucenter.core.finder.impl;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.oucenter.core.finder.FinderNamingStrategy;


/**
 * Looks up Hibernate named queries based on the simple name of the invoced class and the method name of the invocation
 */
@Component
public class SimpleFinderNamingStrategy implements FinderNamingStrategy
{
    public String queryNameFromMethod(Class findTargetType, Method finderMethod)
    {
        return findTargetType.getSimpleName() + "." + finderMethod.getName();
    }
}
