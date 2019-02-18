package club.gclmit.payment.qr.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.utils
 * @author: gclm
 * @date: 2019-02-15 10:06
 * @description: 空值判断工具类
 */
@Slf4j
@Component
public class ObjectOperationUtils {

    /**
     * 判断一个对象的 所有属性是否为空
     * @details 孤城落寞 2019-02-15 10:50
     * @param object
     * @return boolean
     */
    public boolean objectFieldIsNull(Object object) throws IllegalAccessException {

        for (Field field : object.getClass().getDeclaredFields()){

            /**
             * 配置获取私有属性的值
             */
            field.setAccessible(true);

            /**
             * 这里忽略static final 类型的属性，如若不需要可以去掉
             */
            if (Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers())){
                continue;
            }

            /**
             * field.get(Object obj) 获取属性的值
             */
            if (!isEmpty(field.get(object))){
                return false;
            }

            /**
             * 关闭获取私有属性的值
             */
            field.setAccessible(false);
        }
        return true;
    }

    /**
     * 判断一个对象某个属性是否为空
     * @details 孤城落寞 2019-02-15 14:51
     * @param object
     * @param fieldName
     * @return boolean
     */
    public boolean objectSingleFieldIsNull(Object object,String fieldName) throws NoSuchFieldException, IllegalAccessException {

        log.info("对象："+object+"属性名："+fieldName);

        Field field = object.getClass().getDeclaredField(fieldName);

        field.setAccessible(true);

        if (!isEmpty(field.get(object))){
            log.info("field值:"+field.get(object));
            return  false;
        }

        field.setAccessible(false);
        return true;
    }


    public Object setObjectField(Object bean,String fieldName,String fieldValue) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> beanClass = bean.getClass();

        Field field = beanClass.getDeclaredField(fieldName);

        field.setAccessible(true);

        /**
         * 首字母转换成大写
         */
        char[] cs = fieldName.toCharArray();
        cs[0]  -= 32;

        String fieldMethodName = new StringBuilder("set").append(String.valueOf(cs)).toString();

        Method method = beanClass.getDeclaredMethod(fieldMethodName, field.getType());

        method.invoke(bean,fieldValue);

        field.setAccessible(true);

        return bean;
    }


    /**
     * Object 对象非空判断。
     *   如果对象为空则返回 true,非空则返回 false
     * 目前只支持 String、Number、File、Collection、List、Map、Object[] 类型
     * @details 孤城落寞 2019-02-15 10:40
     * @param object
     * @return boolean
     */
    public boolean isEmpty(Object object){

        if(object == null){
            return  true;
        } else if (object instanceof String && "".equals(object.toString().trim())){
            return  true;
        } else if (object instanceof Number && ((Number) object).doubleValue() < 0){
            return true;
        } else if (object instanceof File && (((File) object).isDirectory() || !((File)object).exists())){
            return true;
        } else if (object instanceof Collection && ((Collection) object).isEmpty()){
            return  true;
        } else if (object instanceof List && (((List) object).isEmpty() || ((List) object).size() == 0)){
            return  true;
        } else if (object instanceof Map && (((Map) object).isEmpty() || ((Map) object).size() == 0)){
            return true;
        } else if (object instanceof Object[] && ((Object[]) object).length == 0){
            return  true;
        }
        return false;
    }



}
