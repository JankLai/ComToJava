using System;
using System.Text;
using System.Runtime.InteropServices;
using System.Security.Cryptography;
using System.IO;

//构建一个com组件，该组件存储了10000个12位的验证码；输入一个验证码，组件返回该验证码是否正确（是否是10000个其中之一）；
//输入一个字符串，返回一个对应的映射的验证码（映射方法自己定义）；

namespace ComToJava     //命名空间名称，根据自己需要自定义。这里我们取名ComToJava
{
    [Guid("31457153-6C40-4FAB-AFA9-7CE6D084E836")]     //GUID值
    public interface IEncrypt    //定义接口名
    {
        /*获得10000个12位的验证码*/
        string[] initChecks();
        //验证码验证
        bool isCheckTrue(string checkingCode);
        //获得所有验证码
        string[] getChecks();
        string GetEncrypt(string str, string str2);    //返回的字符串值，与需要调用的类的方法名一致。

        string encode(string str);
        string decode(string str);
    }
    [Guid("A2295E74-FDB9-4E1E-9E9E-4F9BD76A08A3"), ClassInterface(ClassInterfaceType.None)]     //GUID值
    [ProgId("ComToJava.Application")]
    public class Encrypt : IEncrypt    //类名，接口名 ，必须定义接口
    {
        String[] checkCodes;


        public Encrypt()
        {
            checkCodes = initChecks();
        }

        public string GetEncrypt(string str, string str2)     //定义具有两个参数的方法，返回字符串。根据各自需要定义。
        {

            return "测试： " + str + " | " + str2;
        }

        public string[] initChecks()
        {

            string[] checks = new string[10001];


            System.Random random = new Random();

            for (int m = 0; m < 10000; m++)
            {
                int number;
                char code;
                string checkCode = String.Empty;
                for (int i = 0; i < 12; i++)
                {
                    number = random.Next();

                    if (number % 2 == 0)
                        code = (char)('0' + (char)(number % 10));
                    else
                        code = (char)('0' + (char)(number % 10));
                    //code = (char)('A' + (char)(number % 26));

                    checkCode += code.ToString();
                }
                checks[m] = checkCode;

            }
            return checks;
        }
        public string[] getChecks()
        {
            return checkCodes;
        }
        public bool isCheckTrue(string checkingCode)
        {
            bool isTrue = false;
            for (int i = 0; i < 10000; i++)
            {
                if (checkingCode == checkCodes[i])
                {
                    isTrue = true;
                    break;
                }
            }
            return isTrue;
        }

        //字符串加密        
        public string encode(string str)
        {
            DESCryptoServiceProvider descsp = new DESCryptoServiceProvider();
            byte[] key = Encoding.Unicode.GetBytes("1234");
            byte[] data = Encoding.Unicode.GetBytes(str);
            MemoryStream MStream = new MemoryStream();
            CryptoStream CStream = new CryptoStream(MStream, descsp.CreateEncryptor(key, key), CryptoStreamMode.Write);
            CStream.Write(data, 0, data.Length);
            CStream.FlushFinalBlock();
            return Convert.ToBase64String(MStream.ToArray());
        }
        //字符串解密        
        public string decode(string str)
        {
            DESCryptoServiceProvider descsp = new DESCryptoServiceProvider();
            byte[] key = Encoding.Unicode.GetBytes("1234");
            byte[] data = Convert.FromBase64String(str);
            MemoryStream MStream = new MemoryStream();
            CryptoStream CStram = new CryptoStream(MStream, descsp.CreateDecryptor(key, key), CryptoStreamMode.Write);
            CStram.Write(data, 0, data.Length);
            CStram.FlushFinalBlock();
            return Encoding.Unicode.GetString(MStream.ToArray());
        }
    }




}