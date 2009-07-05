package test;



public class Test {
	//
	public static void main(String[] args) throws Exception {

		String s = "中文是ss" ;
		System.out.println(new String(s.getBytes("GBK"),"utf-8"));

		} 
}
