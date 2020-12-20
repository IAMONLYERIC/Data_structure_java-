package com.helper;

import java.util.Stack;

import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    public static void main_soulution(String[] args) {

        String s = "(){}}{";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        HashMap<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i< s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;
                if(c != map.get(stack.pop()) ) return false;
            }

        }
        return stack.isEmpty();

    }

}