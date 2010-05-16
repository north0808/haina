﻿namespace Resco.Controls.AdvancedList
{
    using System;
    using System.Collections;
    using System.Drawing;
    using System.Runtime.InteropServices;

    public class Utility
    {
        public static int CacheCapacity = 0x100;
        public static int CacheKeyLength = 0x40;
        internal const int CDS_TEST = 2;
        internal const int DISP_CHANGE_SUCCESSFUL = 0;
        internal const int DM_DISPLAYORIENTATION = 0x800000;
        private static Hashtable m_WrapTextCache = null;
        internal const int SO_ANGLE0 = 0;
        internal const int SO_ANGLE180 = 2;
        internal const int SO_ANGLE270 = 4;
        internal const int SO_ANGLE90 = 1;

        [DllImport("coredll.dll")]
        private static extern int ChangeDisplaySettingsEx(string lpszDeviceName, ref DEVMODE lpDevMode, IntPtr hwnd, int dwflags, IntPtr lParam);
        internal static void Dispose()
        {
            if (m_WrapTextCache != null)
            {
                m_WrapTextCache.Clear();
                m_WrapTextCache = null;
            }
        }

        internal static object GetHashKey(string text)
        {
            int length = text.Length;
            if (length < CacheKeyLength)
            {
                return text;
            }
            uint num2 = (uint) length;
            int num3 = length / 0x20;
            int num4 = 0;
            for (int i = 0; i <= 0x10; i++)
            {
                if ((num2 & 0x8000) == 0x8000)
                {
                    num2 = (num2 << 1) + 1;
                }
                else
                {
                    num2 = num2 << 1;
                }
                num2 ^= (uint) (text[num4] ^ num4);
                num4 += num3;
            }
            num4 = length - 1;
            for (int j = 0; j < 0x10; j++)
            {
                if ((num2 & 0x8000) == 0x8000)
                {
                    num2 = (num2 << 1) + 1;
                }
                else
                {
                    num2 = num2 << 1;
                }
                num2 ^= (uint) (text[num4] ^ num4);
                num4 -= num3;
            }
            return num2;
        }

        public static SizeF MeasureString(Graphics graphics, string text, Font font)
        {
            return graphics.MeasureString(text, font);
        }

        public static WrapTextData WrapText(Graphics gr, string text, Font font, int width)
        {
            int num12;
            ArrayList list2;
            if (gr == null)
            {
                return null;
            }
            object hashKey = GetHashKey(text);
            WrapTextData[] dataArray = (WrapTextData[]) WrapTextCache[hashKey];
            if (dataArray != null)
            {
                for (int j = dataArray.Length - 1; j >= 0; j--)
                {
                    if (((dataArray[j].TextLength == text.Length) && (dataArray[j].Width == width)) && dataArray[j].Font.Equals(font))
                    {
                        return dataArray[j];
                    }
                }
            }
            WrapTextData data = new WrapTextData(text.Length, font, width);
            SizeF ef = MeasureString(gr, text, font);
            float num2 = ef.Width;
            if (num2 > width)
            {
                int length = text.Length;
                int index = 0;
                int startIndex = 0;
                int num11 = length;
                Math.Ceiling((double) ef.Height);
                num12 = 0;
                list2 = new ArrayList();
                index = text.IndexOf('\n', startIndex);
                if (index != -1)
                {
                    while (index > -1)
                    {
                        num11 = index - startIndex;
                        if ((num11 > 0) && (text[(startIndex + num11) - 1] == '\r'))
                        {
                            num11--;
                        }
                        string str2 = text.Substring(startIndex, num11);
                        ef = MeasureString(gr, str2, font);
                        num12 = Math.Max(num12, (int) Math.Ceiling((double) ef.Height));
                        list2.Add(new DrawLineData(startIndex, num11, (int) Math.Ceiling((double) ef.Width)));
                        startIndex = index + 1;
                        if (startIndex >= length)
                        {
                            break;
                        }
                        if (text[startIndex] == '\r')
                        {
                            startIndex++;
                            if (startIndex >= length)
                            {
                                break;
                            }
                        }
                        index = text.IndexOf('\n', startIndex);
                    }
                    if (startIndex < length)
                    {
                        num11 = length - startIndex;
                        string str3 = text.Substring(startIndex, num11);
                        ef = MeasureString(gr, str3, font);
                        num12 = Math.Max(num12, (int) Math.Ceiling((double) ef.Height));
                        list2.Add(new DrawLineData(startIndex, num11, (int) Math.Ceiling((double) ef.Width)));
                    }
                }
                else
                {
                    num12 = (int) Math.Ceiling((double) ef.Height);
                    list2.Add(new DrawLineData(0, length, (int) Math.Ceiling((double) ef.Width)));
                }
            }
            else
            {
                if (text.IndexOf('\n') == -1)
                {
                    data.LineHeight = (int) Math.Ceiling((double) ef.Height);
                    data.Height = data.LineHeight;
                    data.Lines = new DrawLineData[] { new DrawLineData(0, text.Length, (int) Math.Ceiling((double) num2)) };
                }
                else
                {
                    int num3 = text.Length;
                    int num4 = 0;
                    int num5 = 0;
                    int num6 = 0;
                    int num7 = (int) Math.Ceiling((double) ef.Height);
                    ArrayList list = new ArrayList();
                    do
                    {
                        num4 = text.IndexOf('\n', num5);
                        num6 = (num4 == -1) ? (num3 - num5) : (num4 - num5);
                        if ((num6 > 0) && (text[(num5 + num6) - 1] == '\r'))
                        {
                            num6--;
                        }
                        string str = text.Substring(num5, num6);
                        ef = MeasureString(gr, str, font);
                        list.Add(new DrawLineData(num5, num6, (int) Math.Ceiling((double) ef.Width)));
                        num5 = num4 + 1;
                        if (num5 >= num3)
                        {
                            break;
                        }
                        if (text[num5] == '\r')
                        {
                            num5++;
                            if (num5 >= num3)
                            {
                                break;
                            }
                        }
                    }
                    while (num4 > -1);
                    data.Lines = (DrawLineData[]) list.ToArray(typeof(DrawLineData));
                    data.Height = num7;
                    data.LineHeight = num7 / data.Lines.Length;
                }
                goto Label_0614;
            }
            ArrayList list3 = new ArrayList(2 * list2.Count);
            int count = list2.Count;
            for (int i = 0; i < count; i++)
            {
                float num19;
                int num20;
                DrawLineData data2 = (DrawLineData) list2[i];
                int num15 = data2.Width;
                if (num15 <= width)
                {
                    list3.Add(data2);
                    continue;
                }
                int num16 = data2.Length;
                int num17 = (int) Math.Ceiling((double) ((num16 * width) / num15));
                if (num17 < 1)
                {
                    list3.Add(new DrawLineData(data2.Index, 1, width));
                    if (num16 > 1)
                    {
                        data2.Index++;
                        data2.Length--;
                        ef = MeasureString(gr, text.Substring(data2.Index, data2.Length), font);
                        data2.Width = (int) Math.Ceiling((double) ef.Width);
                        i--;
                    }
                    continue;
                }
                int num18 = data2.Index;
                if (MeasureString(gr, text.Substring(num18, num17), font).Width > width)
                {
                    do
                    {
                        if (num17 <= 1)
                        {
                            break;
                        }
                        num17--;
                    }
                    while (MeasureString(gr, text.Substring(num18, num17), font).Width > width);
                    goto Label_04CB;
                }
            Label_0492:
                if (num17 < (num16 - 1))
                {
                    ef = MeasureString(gr, text.Substring(num18, num17 + 1), font);
                    if (ef.Width <= width)
                    {
                        num17++;
                        num19 = ef.Width;
                        goto Label_0492;
                    }
                }
            Label_04CB:
                num20 = num18 + num17;
                if (char.IsWhiteSpace(text[num20]))
                {
                    num20++;
                }
                else
                {
                    int num21 = text.LastIndexOfAny(new char[] { ' ', '\t' }, num20 - 1, num17 - 1);
                    if (num21 > 0)
                    {
                        num20 = num21 + 1;
                    }
                }
                if (num20 < (num18 + num16))
                {
                    int num22 = num20 - num18;
                    if (num22 > num17)
                    {
                        num22 = num17;
                    }
                    num19 = MeasureString(gr, text.Substring(num18, num22), font).Width;
                    DrawLineData data3 = new DrawLineData(num18, num22, (int) Math.Ceiling((double) num19));
                    data3.CutLength = num17;
                    list3.Add(data3);
                    data2.Index = num20;
                    num16 += num18 - num20;
                    data2.Length = num16;
                    ef = MeasureString(gr, text.Substring(num20, num16), font);
                    data2.Width = (int) Math.Ceiling((double) ef.Width);
                    i--;
                }
                else
                {
                    list3.Add(data2);
                }
            }
            data.Lines = (DrawLineData[]) list3.ToArray(typeof(DrawLineData));
            data.Height = num12 * data.Lines.Length;
            data.LineHeight = num12;
        Label_0614:
            if (dataArray == null)
            {
                dataArray = new WrapTextData[] { data };
            }
            else
            {
                WrapTextData[] array = new WrapTextData[dataArray.Length + 1];
                array[0] = data;
                dataArray.CopyTo(array, 1);
                dataArray = array;
            }
            if (WrapTextCache.Count >= CacheCapacity)
            {
                WrapTextCache.Clear();
            }
            WrapTextCache[hashKey] = dataArray;
            return data;
        }

        internal static int ScreenOrientation
        {
            get
            {
                uint dmDisplayOrientation = 0;
                if (Environment.OSVersion.Platform != ((PlatformID) 0xc0))
                {
                    DEVMODE lpDevMode = new DEVMODE();
                    lpDevMode.dmSize = (ushort) Marshal.SizeOf(typeof(DEVMODE));
                    lpDevMode.dmFields = 0x800000;
                    if (ChangeDisplaySettingsEx(null, ref lpDevMode, IntPtr.Zero, 2, IntPtr.Zero) == 0)
                    {
                        dmDisplayOrientation = lpDevMode.dmDisplayOrientation;
                    }
                }
                return (int) dmDisplayOrientation;
            }
        }

        private static Hashtable WrapTextCache
        {
            get
            {
                if (m_WrapTextCache == null)
                {
                    m_WrapTextCache = new Hashtable();
                }
                return m_WrapTextCache;
            }
        }

        [StructLayout(LayoutKind.Sequential)]
        private struct DEVMODE
        {
            [MarshalAs(UnmanagedType.ByValTStr, SizeConst=0x20)]
            public string dmDeviceName;
            public ushort dmSpecVersion;
            public ushort dmDriverVersion;
            public ushort dmSize;
            public ushort dmDriverExtra;
            public uint dmFields;
            public short dmOrientation;
            public short dmPaperSize;
            public short dmPaperLength;
            public short dmPaperWidth;
            public short dmScale;
            public short dmCopies;
            public short dmDefaultSource;
            public short dmPrintQuality;
            public short dmColor;
            public short dmDuplex;
            public short dmYResolution;
            public short dmTTOption;
            public short dmCollate;
            [MarshalAs(UnmanagedType.ByValTStr, SizeConst=0x20)]
            public string dmFormName;
            public ushort dmLogPixels;
            public uint dmBitsPerPel;
            public uint dmPelsWidth;
            public uint dmPelsHeight;
            public uint dmDisplayFlags;
            public uint dmDisplayFrequency;
            public uint dmDisplayOrientation;
        }
    }
}

