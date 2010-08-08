using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.IO;

namespace BeefWrap.Common.Net
{
    /// <summary>
    /// ����������
    /// </summary>
    public class NetWriter
    {
        /// <summary>
        /// д��ָ���������ֽڵ���������
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="bytesSrc"></param>
        /// <param name="offset"></param>
        /// <param name="count"></param>
        public static void Write(ref Stream streamDest, byte[] bytesSrc, int offset, int count)
        {
            if (null == bytesSrc || bytesSrc.Length == 0)
            {
                return;
            }
            byte[] bytes = bytesSrc;
            if (BitConverter.IsLittleEndian)
            {
                for (int i = 0; i < bytes.Length / 2; i++)
                {
                    byte b = bytes[i];
                    bytes[i] = bytes[bytes.Length - 1 - i];
                    bytes[bytes.Length - 1 - i] = b;
                }
            }
            streamDest.Write(bytes, offset, count);
        }

        /// <summary>
        /// д��һ��bool����
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="data"></param>
        /// <returns></returns>
        public static bool WriteBool(ref Stream streamDest, bool data)
        {
            byte[] bytes = BitConverter.GetBytes(data);
            Write(ref streamDest, bytes, 0, bytes.Length);
            return data;
        }

        /// <summary>
        /// д��һ��float����
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="data"></param>
        /// <returns></returns>
        public static float WriteFloat(ref Stream streamDest, float data)
        {
            byte[] bytes = BitConverter.GetBytes(data);
            Write(ref streamDest, bytes, 0, bytes.Length);
            return data;
        }

        /// <summary>
        /// д��һ��double����
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="data"></param>
        /// <returns></returns>
        public static double WriteDouble(ref Stream streamDest, double data)
        {
            byte[] bytes = BitConverter.GetBytes(data);
            Write(ref streamDest, bytes, 0, bytes.Length);
            return data;
        }

        /// <summary>
        /// д��һ��int����
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="data"></param>
        /// <returns></returns>
        public static int WriteInt(ref Stream streamDest, int data)
        {
            byte[] bytes = BitConverter.GetBytes(data);
            Write(ref streamDest, bytes, 0, bytes.Length);
            return data;
        }

        /// <summary>
        /// д��һ��long����
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="data"></param>
        /// <returns></returns>
        public static long WriteLong(ref Stream streamDest, long data)
        {
            byte[] bytes = BitConverter.GetBytes(data);
            Write(ref streamDest, bytes, 0, bytes.Length);
            return data;
        }

        /// <summary>
        /// ����utf8����д��һ���ַ���
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="data"></param>
        /// <returns></returns>
        public static string WriteUTF(ref Stream streamDest, string data)
        {
            return WriteUTF(ref streamDest, data, Encoding.UTF8);
        }

        /// <summary>
        /// д��һ���ַ���
        /// </summary>
        /// <param name="streamDest"></param>
        /// <param name="data"></param>
        /// <param name="encoding">����</param>
        /// <returns></returns>
        public static string WriteUTF(ref Stream streamDest, string data, Encoding encoding)
        {
            byte[] bytSend = null;
            if (string.IsNullOrEmpty(data))
            {
                bytSend = new byte[0];
            }
            else
            {
                bytSend = encoding.GetBytes(data);
                if (bytSend.Length > 65535)
                {
                    throw new Exception("string���͵����ݳ���(" + bytSend.Length + ")���� 65535 ���ֽ�");
                }
            }

            int utflen = bytSend.Length;
            byte a = (byte)((utflen >> 8) & 0xFF);
            byte b = (byte)((utflen >> 0) & 0xFF);
            streamDest.Write(new byte[] { a, b }, 0, 2);

            int iCount = 0;
            while (iCount < bytSend.Length)
            {
                if (iCount + 1024 > bytSend.Length)
                {
                    streamDest.Write(bytSend, iCount, bytSend.Length - iCount);
                    iCount = bytSend.Length;
                }
                else
                {
                    streamDest.Write(bytSend, iCount, 1024);
                    iCount += 1024;
                }
            }
            return data;
        }
    }
}
