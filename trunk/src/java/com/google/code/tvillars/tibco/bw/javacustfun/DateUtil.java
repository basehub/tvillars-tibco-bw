package com.google.code.tvillars.tibco.bw.javacustfun;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public static long getTimestamp(String str)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date d = null;
		try 
		{
			d = sdf.parse(str);
			//System.out.println("Date - " + d + " : " + d.getTime());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return d.getTime();
	}
	
	public static String getDate(long l)
	{
		String str;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date d = new Date(l);
		str = sdf.format(d);
		return str;
	}
	
	  /** 
	   * The following is a two-dimensional array that provides the  
	   * online help for functions in this class. Declare an array 
	   * named HELP_STRINGS. 
	   */ 
	   public static final String[][] HELP_STRINGS ={ 
	      {"getTimestamp", "Returns the timestamp for a given date",  
	       "getTimestamp('2006-04-24T17:26:42')", "1145914002422"},
	      {"getDate", "Returns the date in yyyy-MM-dd'T'HH:mm:ss for a given timestamp ",  
		       "getDate(1145914002422)", "2006-04-24T17:26:42"}  
	      };  
	   
}
