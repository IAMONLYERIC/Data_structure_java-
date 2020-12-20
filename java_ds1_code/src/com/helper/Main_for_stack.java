package com.helper;

import com.bjtu.Stack.Stack;

public class Main_for_stack {
    public static void main_for_stack(String []args){

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < 10; i++){
            stack.push(i);
        }
        System.out.println("栈内元素有： " + stack.size());

        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
        System.out.println("");
        System.out.println("栈内元素有： " + stack.size());

        
    }
    
}
