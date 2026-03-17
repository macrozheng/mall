package com.macro.mall.portal;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
    public static void main(String[] args) {
        // 1. 我们先创建一个具体的 List<String>
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello Generics");

        // 2. 将它赋值给 List<? extends Object>
        // 逻辑上：String 是 Object 的子类，所以这是合法的
        List<? extends Object> list = stringList;

        // ==========================================
        // 【测试一：能否“放” (写入/Add)】
        // ==========================================

        // ❌ 报错：你不能往里放 Object
        // 原因：虽然 list 声明为 extends Object，但它实际上可能是一个 List<Integer>
         list.add(new Object());

        // ❌ 报错：你甚至不能往里放 String
        // 原因：编译器在“捕获”通配符后，只知道它是某种 Object 的子类，
        // 但编译器无法确认这个子类到底是不是 String。
        // list.add("Test String"); 

        // ✅ 只有 null 是例外
        // 原因：null 是任何引用类型的合法值
        list.add(null);
        System.out.println("写入测试：只有 null 成功存入");

        // ==========================================
        // 【测试二：能否“拿” (读取/Get)】
        // ==========================================

        // ✅ 成功：拿出来的东西一定是 Object
        // 原因：不管 list 内部到底存的是 String 还是 Integer，它们一定都是 Object
        Object obj = list.get(0);
        System.out.println("读取测试：成功拿到数据 -> " + obj);

        // ==========================================
        // 【对比：为什么 List<Object> 可以放？】
        // ==========================================
        List<Object> normalList = new ArrayList<>();
        normalList.add(new Object()); // ✅ 成功
        normalList.add("String OK");  // ✅ 成功

        // 注意：List<Object> 不能接收 List<String> 的赋值
        // normalList = stringList; // ❌ 报错：类型不匹配
    }
}