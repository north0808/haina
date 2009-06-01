package test;

import java.util.List;



public class TestServiceImpl  implements ITestService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083499059060975666L;
	/**
	 * ���Ի�������ͣ�int,boolean,String,long,��(���������Ӷ��󣬶��������Ӷ������顣
	 */
	public Person hello(Person person){
		person.setA(100);
		return person;
	}
	@Override
	public List<Person> testList(List<Person> pers) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
