/**
 * 
 */
package com.google.code.tvillars.tibco.bw.javacustfun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tom.villars
 *
 */
public class StringUtil {

	public static void main(String args[])
	{
	            boolean b = regex("N[0-9]{10}","N3450012345");
	            System.out.println(b);
	}

	public static boolean regex(String expression, String value){
	            Pattern p = Pattern.compile(expression);
	            Matcher m = p.matcher(value);
	            boolean b = m.matches();
	            return b;
	}
 
	public static final String[][] HELP_STRINGS ={ 
	    {"regex", "Matches a string with a regular expression.\n",  
	    "\nregex(\"N[0-9]{10}\",\"N3450012345\")","\nReturns true"},  
	    };
}