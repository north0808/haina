using System;
using System.Collections.Generic;
using System.Data.SQLiteClient;
using System.Text;

namespace BelugaMobile.Db
{
    class PhoneDistrict : IDisposable
    {
        private SQLiteDataReader _reader;

        public PhoneDistrict(SQLiteDataReader reader)
        {
            _reader = reader;
        }

        public void Dispose()
        {
            _reader.Close();
        }

        public bool Next()
        {
            return _reader.Read();
        }

        public Object this[String name]
        {
            get
            {
                return _reader[_reader.GetOrdinal(name)];
            }
        }

        public Object this[int i]
        {
            get
            {
                return _reader[i];
            }
        }
    }
}
