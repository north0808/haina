package com.oucenter.core.util;

import java.util.Random;

public class StringUtils {
	
	public static String htmlEncode(String s) {
		   if (s == null)
		    return "";
		   s = s.replace("&", "&amp;");
		   s = s.replace( "\"", "&quot;");
		   s = s.replace( "<", "&lt;");
		   s = s.replace( ">", "&gt;");
		   s = s.replace( " ", "&nbsp;");
		   s = s.replace( "\n", "<br>");
		   s = s.replace( "'", "&#039;");
		   s = s.replace( "\\", "\\\\");

		   return s;
		}
	
	    public static String getRandom(int length)
	    { 
	        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
	        StringBuffer sb = new StringBuffer();
	        Random r = new Random(); 
	        int range = buffer.length();
	        for (int i = 0; i < length; i ++)
	        { 
	            sb.append(buffer.charAt(r.nextInt(range))); 
	        } 
	        return sb.toString(); 
	    }
	    public static void main(String args[])
	    {
	        String res = getRandom(20);
	        System.out.println(res);
	    }

}
