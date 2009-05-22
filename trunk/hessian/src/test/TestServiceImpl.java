package test;

import java.util.List;

import test.Person;



public class TestServiceImpl  implements ITestService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083499059060975666L;
	/**
	 * 测试基本数据类型，int,boolean,String,long,包括对象里面子对象，对象里面子对象数组。
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
