#ifndef	HESSIANREMOTERETURNING_H
#define	HESSIANREMOTERETURNING_H

#include <string>

class HessianRemoteReturning
{
private:
	const static long serialVersionUID;

public:
	HessianRemoteReturning();
	~HessianRemoteReturning();

public:
	void	setStatusCode(int aStatusCode);
	int		getStatusCode();

	void	setStatusText(std::string aStatusText);
	std::string	getStatusText();

	void	setOperationCode(int aOperationCode);
	int		getOperationCode();

	void	setValue(void* aValue);
	void*	getValue();

private:
	int		statusCode;		//状态码 调用成功状态码均为0
	std::string	statusText;		//状态文字
	int		operationCode;	//操作码
	
	void*	value;		//返回结果数据
};

#endif	/*HESSIANREMOTERETURNING_H*/
