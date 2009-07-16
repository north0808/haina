#include "HessianRemoteReturning.h"

HessianRemoteReturning::HessianRemoteReturning():statusCode(-1),statusText(""),operationCode(-1),value(NULL)
{
}
HessianRemoteReturning::~HessianRemoteReturning()
{
}

void HessianRemoteReturning::setStatusCode(int aStatusCode)
{
	statusCode = aStatusCode;
}
int HessianRemoteReturning::getStatusCode()
{
	return statusCode;
}


void HessianRemoteReturning::setStatusText(std::string aStatusText)
{
	statusText = aStatusText;
}

std::string HessianRemoteReturning::getStatusText()
{
	return statusText;
}


void HessianRemoteReturning::setOperationCode(int aOperationCode)
{
	operationCode = aOperationCode;
}

int HessianRemoteReturning::getOperationCode()
{
	return operationCode;
}


void HessianRemoteReturning::setValue(void* aValue)
{
	value = aValue;
}

void* HessianRemoteReturning::getValue()
{
	return value;
}
