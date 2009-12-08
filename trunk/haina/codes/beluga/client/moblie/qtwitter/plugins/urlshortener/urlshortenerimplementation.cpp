/***************************************************************************
 *   Copyright (C) 2009 by Dominik Kapusta            <d@ayoy.net>         *
 *   Copyright (C) 2009 by Mariusz Pietrzyk       <wijet@wijet.pl>         *
 *                                                                         *
 *   This library is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU Lesser General Public License as        *
 *   published by the Free Software Foundation; either version 2.1 of      *
 *   the License, or (at your option) any later version.                   *
 *                                                                         *
 *   This library is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU     *
 *   Lesser General Public License for more details.                       *
 *                                                                         *
 *   You should have received a copy of the GNU Lesser General Public      *
 *   License along with this library; if not, write to                     *
 *   the Free Software Foundation, Inc.,                                   *
 *   51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA          *
 ***************************************************************************/


#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QRegExp>
#include <QBuffer>
#include <QFile>
#include <QDomDocument>
#include <QDomElement>
#include <QDebug>

#include "urlshortenerimplementation.h"

UrlShortenerImplementation::UrlShortenerImplementation( QObject *parent ) : QObject( parent )
{
    connection = new QNetworkAccessManager( this );
    connect( connection, SIGNAL(finished(QNetworkReply*)), this, SLOT(replyFinished(QNetworkReply*)) );
}

UrlShortenerImplementation::~UrlShortenerImplementation() {}

int UrlShortenerImplementation::replyStatus( QNetworkReply *reply ) const
{
    return reply->attribute( QNetworkRequest::HttpStatusCodeAttribute ).toInt();
}


IsgdShortener::IsgdShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void IsgdShortener::shorten( const QString &url )
{
    if( QRegExp( "http://is.gd/" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://is.gd/api.php?longurl=" + url ) );
        request.setAttribute( QNetworkRequest::User, url );
        connection->get( request );
    }
}

void IsgdShortener::replyFinished( QNetworkReply * reply )
{
    QString response = reply->readLine();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    switch( replyStatus( reply ) ) {
    case 200:
        emit shortened( oldUrl, response );
        break;
        // TODO: change
    case 401:
        emit errorMessage( tr( "The url shortening service couldn't authorize you. Please check your username and password." ) );
        // \TODO
    case 500: {
            QString message = response.replace("Error: ", "");
            if( message == "The URL entered was not valid." ) {
                emit errorMessage( tr( "The URL entered was not valid.") );
            } else if ( message == "The URL entered was too long." ) {
                emit errorMessage( tr( "The URL entered was too long.") );
            } else if ( message ==  "The address making this request has been blacklisted by Spamhaus (SBL/XBL) or Spamcop." )  {
                emit errorMessage( tr( "The address making this request has been blacklisted by Spamhaus (SBL/XBL) or Spamcop.") );
            } else if ( message == "The URL entered is a potential spam site and is listed on either the SURBL or URIBL blacklist.") {
                emit errorMessage( tr( "The URL entered is a potential spam site and is listed on either the SURBL or URIBL blacklist." ) );
            } else if ( message == "The URL you entered is on the is.gd's blacklist (links to URL shortening sites or is.gd itself are disabled to prevent misuse)." ) {
                emit errorMessage( tr( "The URL you entered is on the is.gd's blacklist (links to URL shortening sites or is.gd itself are disabled to prevent misuse)." ) );
            } else if ( message == "The address making this request has been blocked by is.gd (normally the result of a violation of its terms of use)." ) {
                emit errorMessage( tr( "The address making this request has been blocked by is.gd (normally the result of a violation of its terms of use)." ) );
            }
        }
        break;
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}


TrimShortener::TrimShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void TrimShortener::shorten( const QString &url )
{
    QString newUrl = url.indexOf( "http://" ) > -1 ? url : "http://" + url;

    if( QRegExp( "http://tr.im/" ).indexIn( newUrl ) == -1 ) {
        QNetworkRequest request( QUrl( "http://api.tr.im/api/trim_simple?url=" + newUrl ) );
        request.setAttribute( QNetworkRequest::User, newUrl );
        connection->get( request );
    }
}

void TrimShortener::replyFinished( QNetworkReply *reply )
{
    QString response = reply->readLine();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    switch( replyStatus( reply ) ) {
    case 200:
        if( QRegExp( "\\s*" ).exactMatch( response ) ) {
            emit errorMessage( tr( "The URL has been rejected by the tr.im" ) );
        } else {
            emit shortened( oldUrl, response.trimmed() );
        }
        break;
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}


MetamarkShortener::MetamarkShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void MetamarkShortener::shorten( const QString &url )
{
    if( QRegExp( "http://xrl.us/" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://metamark.net/api/rest/simple?long_url=" + url ) );
        request.setAttribute( QNetworkRequest::User, url );
        connection->get( request );
    }
}

void MetamarkShortener::replyFinished( QNetworkReply *reply )
{
    QString response = reply->readLine();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    switch( replyStatus( reply ) ) {
    case 200:
        emit shortened( oldUrl, response );
        break;
    case 500:
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}


TinyurlShortener::TinyurlShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void TinyurlShortener::shorten( const QString &url )
{
    if( QRegExp( "http://tinyurl.com/" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://tinyurl.com/api-create.php?url=" + url ) );
        request.setAttribute( QNetworkRequest::User, url );
        connection->get( request );
    }
}

void TinyurlShortener::replyFinished( QNetworkReply *reply )
{
    QString response = reply->readLine();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    switch( replyStatus( reply ) ) {
    case 200:
        emit shortened( oldUrl, response );
        break;
    case 500:
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}

BoooomShortener::BoooomShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void BoooomShortener::shorten( const QString &url )
{
    QString newUrl = url.indexOf( "http://" ) > -1 ? url : "http://" + url;
    if( QRegExp( "http://b.oooom.net/" ).indexIn( newUrl ) == -1 ) {
        QNetworkRequest request( QUrl( "http://b.oooom.net/shrink.php/" + newUrl ) );
        request.setAttribute( QNetworkRequest::User, newUrl );
        connection->get( request );
    }
}

void BoooomShortener::replyFinished( QNetworkReply *reply )
{
    QString response = QString::fromUtf8( reply->readAll() );
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    switch( replyStatus( reply ) ) {
    case 200:
        {
            QString boom = "http://b.oooom.net/";
            QString search = "<!--API--><a href=\"http://b.oooom.net/";
            int j = response.indexOf(search);
            if(j >= 0) {
                for( int pos = j + search.length(); response[pos] != QChar('"'); pos++ )
                {
                    boom.append(response[pos]);
                }
                emit shortened( oldUrl, boom );
            }
            else if ( j == -1 ) {
                qDebug() << "Got a bad response from b.oooom.net or something.";
                qDebug() << response;
                emit errorMessage( tr( "An error occured with b.oooom.net. Please file a bug.") );
            }
            break;
        }
    default:
        emit errorMessage( tr( "An unknown error occured when shortening your URL." ) );
    }
}


TinyarrowsShortener::TinyarrowsShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void TinyarrowsShortener::shorten( const QString &url )
{
    if( QRegExp( "http://?ws/" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://tinyarro.ws/api-create.php?utfpure=1&url=" + url ) );
        request.setAttribute( QNetworkRequest::User, url );
        connection->get( request );
    }
}

void TinyarrowsShortener::replyFinished( QNetworkReply *reply )
{
    QString response = QString::fromUtf8( reply->readLine() );
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    switch( replyStatus( reply ) ) {
    case 200:
        emit shortened( oldUrl, response );
        break;
    case 500:
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}


UnuShortener::UnuShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void UnuShortener::shorten( const QString &url )
{
    if( QRegExp( "http://u.nu" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://u.nu/unu-api-simple?url=" + url ) );
        request.setAttribute( QNetworkRequest::User, url );
        connection->get( request );
    }
}

void UnuShortener::replyFinished( QNetworkReply *reply )
{
    QString response = reply->readLine();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    switch( replyStatus( reply ) ) {
    case 200:
        if( response.indexOf( "http://" ) == 0 ) {
            emit shortened( oldUrl, response );
        } else {
            emit errorMessage( tr( "Your URL has been rejected by u.nu" ) );
        }
        break;
    case 500:
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}

BitlyShortener::BitlyShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void BitlyShortener::shorten( const QString &url )
{
    QString newUrl = url.indexOf( "http://" ) > -1 ? url : "http://" + url;

    if( QRegExp( "http://bit.ly" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://api.bit.ly/shorten?version=2.0.1&login=bitlyapidemo&format=xml&apiKey=R_0da49e0a9118ff35f52f629d2d71bf07&longUrl=" + newUrl ) );
        request.setAttribute( QNetworkRequest::User, newUrl );
        connection->get( request );
    }
}

void BitlyShortener::replyFinished( QNetworkReply *reply )
{
    QString response = reply->readLine();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    int errorCode;
    QDomDocument doc;
    QDomElement nodeKeyVal;

    switch( replyStatus( reply ) ) {
    case 200:
        doc.setContent( response, false );
        nodeKeyVal = doc.firstChildElement( "bitly" ).firstChildElement( "results" ).firstChildElement( "nodeKeyVal" );
        errorCode = nodeKeyVal.firstChildElement( "errorCode" ).text().toInt();
        switch( errorCode ) {
        case 0:
            emit shortened( oldUrl, nodeKeyVal.firstChildElement( "shortUrl" ).text() );
            break;
        case 1206:
            emit errorMessage( tr( "The URL entered was not valid." ) );
            break;
        default:
            emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
        }
        break;
    case 500:
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}

DiggShortener::DiggShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void DiggShortener::shorten( const QString &url )
{
    QString newUrl = url.indexOf( "http://" ) > -1 ? url : "http://" + url;

    if( QRegExp( "http://digg.com" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://services.digg.com/url/short/create?appkey=http://qtwitter.ayoy.net&url=" + newUrl ) );
        request.setAttribute( QNetworkRequest::User, newUrl );
        connection->get( request );
    }
}

void DiggShortener::replyFinished( QNetworkReply *reply )
{
    QString response = reply->readAll();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();
    QDomDocument doc;

    switch( replyStatus( reply ) ) {
    case 200:
        doc.setContent( response, false );
        emit shortened( oldUrl, doc.firstChildElement( "shorturls" ).firstChildElement( "shorturl" ).attribute("short_url") );
        break;
    case 500:
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}

MigremeShortener::MigremeShortener( QObject *parent ) : UrlShortenerImplementation( parent ) {}

void MigremeShortener::shorten( const QString &url )
{
    QString newUrl = url.indexOf( "http://" ) > -1 ? url : "http://" + url;

    if( QRegExp( "http://migre.me" ).indexIn( url ) == -1 ) {
        QNetworkRequest request( QUrl( "http://migre.me/api.xml?url=" + newUrl ) );
        request.setAttribute( QNetworkRequest::User, newUrl );
        connection->get( request );
    }
}

void MigremeShortener::replyFinished( QNetworkReply *reply )
{
    QString response = reply->readAll();
    QString oldUrl = reply->request().attribute( QNetworkRequest::User, QString() ).toString();

    QDomDocument doc;
    QDomElement migre;
    int errorCode;

    switch( replyStatus( reply ) ) {
    case 200:
        doc.setContent( response, false );
        migre = doc.firstChildElement( "item" );
        errorCode = migre.firstChildElement( "error" ).text().toInt();
        switch( errorCode ) {
        case 0:
            emit shortened( oldUrl, migre.firstChildElement( "migre" ).text() );
            break;
        case 2:
            emit errorMessage( tr( "The URL entered was not valid." ) );
            break;
        default:
            emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
        }
        break;
    case 500:
    default:
        emit errorMessage( tr( "An unknown error occurred when shortening your URL." ) );
    }
}
