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
// ����һ��com�����������洢��10000��12λ����֤�룻����һ����֤�룬������ظ���֤���Ƿ���ȷ���Ƿ���10000������֮һ����
//����һ���ַ���������һ����Ӧ��ӳ�����֤�루ӳ�䷽���Լ����壩��
        try{    
            ActiveXComponent dotnetCom = null;    
            dotnetCom = new ActiveXComponent("ComToJava.Application");   //��Ҫ���õ�C#�����е������ռ���������

            System.out.println("****************��ӭ������֤��ϵͳ******************");
            Scanner scanner=new Scanner(System.in);
            int chosen;
            while(true){
                System.out.println("��ѡ���ܣ�\n1.�鿴10000����֤��\n2.������֤�룬����������ظ���֤���Ƿ���ȷ" +
                        "\n3.����һ���ַ���������һ����Ӧ��ӳ�����֤��");
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
    //1.���10000����֤��
    public static void getChecks(ActiveXComponent dotnetCom){
        Variant checks=Dispatch.call(dotnetCom,"getChecks");
        SafeArray safeArray=checks.toSafeArray();
        String[] ss=safeArray.toStringArray();
        for(String s:ss){
            System.out.println(s);
        }
    }
    //2.��֤�������֤���Ƿ���ȷ
    public static void verify(ActiveXComponent dotnetCom, Scanner scanner){
        System.out.println("��������֤�룺");
        scanner.nextLine();
        String checkingCode=scanner.nextLine();
        Variant var = Dispatch.call(dotnetCom, "isCheckTrue",checkingCode);  //��Ҫ���õķ������Ͳ���ֵ
        String isTrue=var.toString();
        if(isTrue=="true"){
            System.out.println("��֤����ȷ��");
        }
        else
            System.out.println("��֤�����");

    }
    //3.����һ���ַ���������һ����Ӧ��ӳ�����֤��
    public static void pass(ActiveXComponent dotnetCom,Scanner scanner){
        System.out.println("������һ���ַ�����");
        scanner.nextLine();
        String str=scanner.nextLine();
        Variant var = Dispatch.call(dotnetCom, "encode",str);  //��Ҫ���õķ������Ͳ���ֵ
        String code=var.toString();
        System.out.println("��Ӧ����֤��Ϊ��"+code);
    }


}
