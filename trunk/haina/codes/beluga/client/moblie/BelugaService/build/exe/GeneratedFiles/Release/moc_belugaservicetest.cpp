/****************************************************************************
** Meta object code from reading C++ file 'belugaservicetest.h'
**
** Created: Wed Oct 7 01:54:12 2009
**      by: The Qt Meta Object Compiler version 61 (Qt 4.5.2)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../belugaservicetest.h"
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'belugaservicetest.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 61
#error "This file was generated using the moc from 4.5.2. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
static const uint qt_meta_data_CBelugaServiceTest[] = {

 // content:
       2,       // revision
       0,       // classname
       0,    0, // classinfo
       1,   12, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors

 // slots: signature, parameters, type, tag, flags
      20,   19,   19,   19, 0x08,

       0        // eod
};

static const char qt_meta_stringdata_CBelugaServiceTest[] = {
    "CBelugaServiceTest\0\0pushButtonSubmitClick()\0"
};

const QMetaObject CBelugaServiceTest::staticMetaObject = {
    { &QMainWindow::staticMetaObject, qt_meta_stringdata_CBelugaServiceTest,
      qt_meta_data_CBelugaServiceTest, 0 }
};

const QMetaObject *CBelugaServiceTest::metaObject() const
{
    return &staticMetaObject;
}

void *CBelugaServiceTest::qt_metacast(const char *_clname)
{
    if (!_clname) return 0;
    if (!strcmp(_clname, qt_meta_stringdata_CBelugaServiceTest))
        return static_cast<void*>(const_cast< CBelugaServiceTest*>(this));
    return QMainWindow::qt_metacast(_clname);
}

int CBelugaServiceTest::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        switch (_id) {
        case 0: pushButtonSubmitClick(); break;
        default: ;
        }
        _id -= 1;
    }
    return _id;
}
QT_END_MOC_NAMESPACE