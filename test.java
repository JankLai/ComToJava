package com.comtojava;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.SafeArray;
import com.jacob.com.Variant;

import java.util.Scanner;

public class test {

    /**    
     * @param args    
     */    
    public static void main(String[] args) {    
        // TODO Auto-generated method stub
// 构建一个com组件，该组件存储了10000个12位的验证码；输入一个验证码，组件返回该验证码是否正确（是否是10000个其中之一）；
//输入一个字符串，返回一个对应的映射的验证码（映射方法自己定义）；
        try{    
            ActiveXComponent dotnetCom = null;    
            dotnetCom = new ActiveXComponent("ComToJava.Application");   //需要调用的C#代码中的命名空间名和类名

            System.out.println("****************欢迎来到验证码系统******************");
            Scanner scanner=new Scanner(System.in);
            int chosen;
            while(true){
                System.out.println("请选择功能：\n1.查看10000个验证码\n2.输入验证码，并由组件返回该验证码是否正确" +
                        "\n3.输入一个字符串，返回一个对应的映射的验证码");
                chosen=scanner.nextInt();
                switch (chosen){
                    case 1:
                        getChecks(dotnetCom);
                        break;
                    case 2:
                        verify(dotnetCom,scanner);
                        break;
                    case 3:
                        pass(dotnetCom,scanner);
                        break;
                    default:

                }
            }

            } catch (Exception ex) {    
                ex.printStackTrace();    
            }    
    }
    //1.获得10000个验证码
    public static void getChecks(ActiveXComponent dotnetCom){
        Variant checks=Dispatch.call(dotnetCom,"getChecks");
        SafeArray safeArray=checks.toSafeArray();
        String[] ss=safeArray.toStringArray();
        for(String s:ss){
            System.out.println(s);
        }
    }
    //2.验证输入的验证码是否正确
    public static void verify(ActiveXComponent dotnetCom, Scanner scanner){
        System.out.println("请输入验证码：");
        scanner.nextLine();
        String checkingCode=scanner.nextLine();
        Variant var = Dispatch.call(dotnetCom, "isCheckTrue",checkingCode);  //需要调用的方法名和参数值
        String isTrue=var.toString();
        if(isTrue=="true"){
            System.out.println("验证码正确！");
        }
        else
            System.out.println("验证码错误！");

    }
    //3.输入一个字符串，返回一个对应的映射的验证码
    public static void pass(ActiveXComponent dotnetCom,Scanner scanner){
        System.out.println("请输入一个字符串：");
        scanner.nextLine();
        String str=scanner.nextLine();
        Variant var = Dispatch.call(dotnetCom, "encode",str);  //需要调用的方法名和参数值
        String code=var.toString();
        System.out.println("对应的验证码为："+code);
    }


}
