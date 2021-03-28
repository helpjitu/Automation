package com.jitu.testpack;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(com.jitu.listeners.ListenerTestNG.class)
public class StringUsage {

    public static void testStr() {
        String str1="Hello";
        String str2="World";
        System.out.println(str1=str2);
        System.out.println(str1==str2);
        System.out.println(str1);
    }
}
