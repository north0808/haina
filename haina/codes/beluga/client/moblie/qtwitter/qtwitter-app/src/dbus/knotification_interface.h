/*
 * This file was generated by qdbusxml2cpp version 0.7
 * Command line was: qdbusxml2cpp -c KNotificationInterface -p knotification_interface.h:knotification_interface.cpp knotify.xml
 *
 * qdbusxml2cpp is Copyright (C) 2009 Nokia Corporation and/or its subsidiary(-ies).
 *
 * This is an auto-generated file.
 * Do not edit! All changes made to it will be lost.
 */

#ifndef KNOTIFICATION_INTERFACE_H_1254082962
#define KNOTIFICATION_INTERFACE_H_1254082962

#include <QtCore/QObject>
#include <QtCore/QByteArray>
#include <QtCore/QList>
#include <QtCore/QMap>
#include <QtCore/QString>
#include <QtCore/QStringList>
#include <QtCore/QVariant>
#include <QtDBus/QtDBus>

/*
 * Proxy class for interface org.kde.VisualNotifications
 */
class KNotificationInterface: public QDBusAbstractInterface
{
    Q_OBJECT
public:
    static inline const char *staticInterfaceName()
    { return "org.kde.VisualNotifications"; }

public:
    KNotificationInterface(const QString &service, const QString &path, const QDBusConnection &connection, QObject *parent = 0);

    ~KNotificationInterface();

public Q_SLOTS: // METHODS
    inline QDBusPendingReply<> CloseNotification(uint id)
    {
        QList<QVariant> argumentList;
        argumentList << qVariantFromValue(id);
        return asyncCallWithArgumentList(QLatin1String("CloseNotification"), argumentList);
    }

    inline QDBusPendingReply<uint> Notify(const QString &app_name, uint replaces_id, const QString &event_id, const QString &app_icon, const QString &summary, const QString &body, const QStringList &actions, const QVariantMap &hints, int timeout)
    {
        QList<QVariant> argumentList;
        argumentList << qVariantFromValue(app_name) << qVariantFromValue(replaces_id) << qVariantFromValue(event_id) << qVariantFromValue(app_icon) << qVariantFromValue(summary) << qVariantFromValue(body) << qVariantFromValue(actions) << qVariantFromValue(hints) << qVariantFromValue(timeout);
        return asyncCallWithArgumentList(QLatin1String("Notify"), argumentList);
    }

Q_SIGNALS: // SIGNALS
    void ActionInvoked(uint id, const QString &action_key);
    void NotificationClosed(uint id, uint reason);
};

namespace org {
  namespace kde {
    typedef ::KNotificationInterface VisualNotifications;
  }
}
#endif