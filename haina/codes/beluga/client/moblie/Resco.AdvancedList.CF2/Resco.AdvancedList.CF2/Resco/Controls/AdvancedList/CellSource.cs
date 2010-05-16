﻿namespace Resco.Controls.AdvancedList
{
    using System;

    public class CellSource
    {
        private object m_oData;
        private CellSourceType m_srcType;
        public Cell Parent;

        public CellSource()
        {
            this.m_srcType = CellSourceType.ColumnIndex;
            this.m_oData = -1;
        }

        public CellSource(string strValue)
        {
            this.m_srcType = CellSourceType.ColumnName;
            this.m_oData = strValue;
            if ((strValue == null) || (strValue == ""))
            {
                this.m_srcType = CellSourceType.Constant;
            }
            if (strValue.StartsWith("'"))
            {
                strValue = strValue.TrimStart(new char[] { '\'' });
                strValue = strValue.TrimEnd(new char[] { '\'' });
                this.m_srcType = CellSourceType.Constant;
                this.m_oData = strValue;
            }
            else if (strValue.StartsWith("\""))
            {
                strValue = strValue.TrimStart(new char[] { '"' });
                strValue = strValue.TrimEnd(new char[] { '"' });
                this.m_srcType = CellSourceType.Constant;
                this.m_oData = strValue;
            }
            else if (strValue.StartsWith("-") || char.IsDigit(strValue[0]))
            {
                try
                {
                    int num = Convert.ToInt32(strValue);
                    this.m_srcType = CellSourceType.ColumnIndex;
                    this.m_oData = num;
                }
                catch
                {
                }
            }
        }

        public CellSource(CellSourceType type, object data)
        {
            this.m_srcType = type;
            this.m_oData = data;
        }

        public CellSource Copy()
        {
            return new CellSource(this.m_srcType, this.m_oData);
        }

        protected virtual bool ShouldSerializeColumnIndex()
        {
            return (this.m_srcType == CellSourceType.ColumnIndex);
        }

        protected virtual bool ShouldSerializeColumnName()
        {
            return (this.m_srcType == CellSourceType.ColumnName);
        }

        protected virtual bool ShouldSerializeConstantData()
        {
            return (this.m_srcType == CellSourceType.Constant);
        }

        public override string ToString()
        {
            if (this.m_srcType == CellSourceType.Constant)
            {
                return ("\"" + Convert.ToString(this.m_oData) + "\"");
            }
            return Convert.ToString(this.m_oData);
        }

        public int ColumnIndex
        {
            get
            {
                if (this.m_srcType == CellSourceType.ColumnIndex)
                {
                    return (int) this.m_oData;
                }
                return -1;
            }
            set
            {
                this.m_oData = value;
                if (this.m_srcType != CellSourceType.ColumnIndex)
                {
                    this.m_srcType = CellSourceType.ColumnIndex;
                }
            }
        }

        public string ColumnName
        {
            get
            {
                if (this.m_srcType == CellSourceType.ColumnName)
                {
                    return (string) this.m_oData;
                }
                return "";
            }
            set
            {
                this.m_oData = value;
                if (this.m_srcType != CellSourceType.ColumnName)
                {
                    this.m_srcType = CellSourceType.ColumnName;
                }
            }
        }

        public string ConstantData
        {
            get
            {
                if (this.m_srcType == CellSourceType.Constant)
                {
                    return Convert.ToString(this.m_oData);
                }
                return "";
            }
            set
            {
                this.m_oData = value;
                if (this.m_srcType != CellSourceType.Constant)
                {
                    this.m_srcType = CellSourceType.Constant;
                }
            }
        }

        public CellSourceType SourceType
        {
            get
            {
                return this.m_srcType;
            }
            set
            {
                if (value != this.m_srcType)
                {
                    if (value == CellSourceType.ColumnIndex)
                    {
                        try
                        {
                            this.m_oData = Convert.ToInt32(this.m_oData);
                        }
                        catch (Exception)
                        {
                            this.m_oData = -1;
                        }
                    }
                    else if ((value == CellSourceType.ColumnName) && !(this.m_oData is string))
                    {
                        this.m_oData = "";
                    }
                    this.m_srcType = value;
                }
            }
        }
    }
}

