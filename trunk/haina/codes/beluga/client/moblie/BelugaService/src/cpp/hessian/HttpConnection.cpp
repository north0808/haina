// $Id: HttpConnection.cpp 2 2009-02-28 00:27:58Z pukkaone $
#include "hessian/HttpConnection.h"
#include "hessian/HttpConnectionImpl.h"

namespace hessian {

HttpConnection::HttpConnection (const std::string& url):
    pImpl_(new HttpConnectionImpl(url))
{ }

HttpConnection::~HttpConnection ()
{
    delete pImpl_;
}

std::streambuf*
HttpConnection::send (const MemoryStreamBuf* pRequestContent)
{
    return pImpl_->send(pRequestContent);
}

}//namespace hessian
