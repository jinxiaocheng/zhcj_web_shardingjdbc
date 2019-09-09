package com.escst.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解工具类
 * Created by Administrator on 2017/2/9.
 */
public class AnnotationUtils {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationUtils.class);

    /**
     * 获取方法的所有注解
     * @param method
     * @return
     */
    public static Annotation[] getMethodAnnotations(Method method) {
        return method.getAnnotations();
    }

    /**
     * 获取方法中指定类型的注解
     * @param method 方法对象
     * @param annotationClass 注解的Class对象
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getMethodAnnotation(Method method,Class<T> annotationClass) {
        return method.getAnnotation(annotationClass);
    }

    /**
     * 获取方法中指定类型的注解
     * @param method  方法对象
     * @param annotationClass  注解的Class对象
     * @param findInSuper   是否在父类中查找，如果设置为true，如果在当前类中找不到指定类型的注解，将会在父类中查找
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getMethodAnnotation(Method method,Class<T> annotationClass,boolean findInSuper) {
        T annotation = getMethodAnnotation(method,annotationClass);
        if(annotation == null && findInSuper) {
            Class<?> clazz = method.getDeclaringClass();
            do{
                clazz = clazz.getSuperclass();
                if(clazz == null || clazz.equals(Object.class)) {
                    break;
                }
                try {
                    Method m = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    annotation = getMethodAnnotation(m,annotationClass);
                    if(annotation != null) {
                        break;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } while (Boolean.TRUE);
        }
        return annotation;
    }

    /**
     * 获取指定类中包含指定注解类型的方法
     * @param clazz Class对象
     * @param annotationClass 注解类型
     * @param findInSuper  是否从父类中查找
     * @param <T>
     * @return
     */
    public static <T extends Annotation> List<Method> getMethodWithAnnotations(Class<?> clazz, Class<T> annotationClass, boolean findInSuper) {

        Method[] methods = null;
        if(findInSuper) {
            methods = clazz.getMethods();
        } else {
            methods = clazz.getDeclaredMethods();
        }
        List<Method> ms = new ArrayList<Method>();
        for(Method method : methods) {
            T anno = getMethodAnnotation(method,annotationClass,findInSuper);
            if(anno != null){
                ms.add(method);
            }
        }
        return ms;
    }


}
