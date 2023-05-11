package com.util.utils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class MyTool<T> {
    private Class Tclass;

    public Class getTclass() {
        return Tclass;
    }

    public void setTclass(Class tclass) {
        Tclass = tclass;
    }

    public MyTool(Class tclass) {
        Tclass = tclass;
    }

    // 剔除[]
    public static String getFormAttribute(String string){
        int head = string.indexOf("[");
        if (head == -1) return string;
        return string.substring(head+1,string.length()-1);
    }

    // 首字母大写
    public static String firstLetterNameUpper(String string){
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    // 首字母小写
    public static String firstLetterNameLower(String string){
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

    // 前端对象实例化
    public T getObjectFromWeb(T t,Map<String, String[]> parameterMap) {
//        System.out.println("前端对象实例化");
        // 前端返回的属性集遍历
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
//            剔除[]
            // System.out.println(entry);
            String formAttribute = MyTool.getFormAttribute(entry.getKey());
            try {
                // 获取泛型的所有方法
                Method[] methods = Tclass.getMethods();
                for (Method method : methods) {
                    // 获取当前属性集对应类的赋值方法
                    String methodName = "set" + MyTool.firstLetterNameUpper(formAttribute);
                    if (method.getName().equals(methodName)) {
                        String paramType = method.getParameters()[0].toString();
                        // 根据不同的属性类型 反射赋值
                        switch (paramType) {
                            case "java.lang.String arg0":
                                method.invoke(t, entry.getValue()[0]);
                                break;
                            case "int arg0":
                                method.invoke(t, Integer.parseInt(entry.getValue()[0]));
                                break;
                            case "boolean arg0":
                                if ("true".equals(entry.getValue()[0]) || "True".equals(entry.getValue()[0])) {
                                    method.invoke(t, true);
                                } else {
                                    method.invoke(t, false);
                                }
                                break;
                            case "double arg0":
                                method.invoke(t, Double.parseDouble(entry.getValue()[0]));
                                break;
                            default:
                                System.out.println("当前的参数类型为：" + paramType);
                                new RuntimeException("方法参数类型有误");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("反射异常");
                e.printStackTrace();
            }
        }
        return t;
    }
}
