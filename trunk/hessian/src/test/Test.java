package test;
import java.util.ArrayList;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;



public class Test {
	//
	public static void main(String[] args) throws Exception {

//		HessianProxyFactory proxyFactory = new HessianProxyFactory();
//
//		IService service = (IService) proxyFactory.create(IService.class,
//
//		"http://localhost:8079/hessian/service");
//
//		System.out.println(service.login("haina", "haina"));
		Address add = new Address();
		add.setInfo("add");
		Address add1 = new Address();
		add1.setInfo("add1");
		List<Address> adds = new ArrayList<Address>();
		adds.add(add);
		adds.add(add1);
		Person person = new Person();
		person.setA(1);
		person.setB(false);
		person.setC("string");
		person.setAddr(add1);
		person.setAddrList(adds);
		JSONSerializer serializer = new JSONSerializer(); 
		//
		List<Person> pes = new ArrayList<Person>();
		pes.add(person);
		pes.add(person);
		String s = serializer.deepSerialize(pes);
		System.out.println(s);
		List<Person> person1 = new JSONDeserializer<List<Person>>()
		.deserialize(s);
//		{"a":12,"b":true,"c":"hello"}
//		
		} 
}
