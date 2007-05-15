package com.google.code.tvillars.tibco.bw.javacustfun;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Use to make pseudo randomly generated GUIDs.
 * <p/>
 * This class could be replaced if Java 1.5 is available as this version of Java has its own
 * GUID generator implementation <a href="http://java.sun.com/j2se/1.5.0/docs/api/java/util/UUID.html">java.util.UUID</a>.
 * The methods for this class are a subset of the java 1.5 class.
 * <p/>
 * This source borrows <b>heavily</b> from <a href="http://www.javaexchange.com/aboutRandomGUID.html">RandomGUID</a>.
 * <p/>
 * TODO For java usage examples please refer to the test case XXX 
 * TODO For BizWorks usage examples please refer to XXX
 *
 * @author Tom Villars 21-March-07
 */

public class GUID implements Comparable {

  // one-way function algorithm used to help make the secure
  static private final String ALGORITHM = "MD5";

  private static Random random;
  private static String localIPAdress;

  /*
   * Static block to take care of one time secureRandom seed.
   * It takes a few seconds to initialize SecureRandom.
   */
  static {
    SecureRandom mySecureRand = new SecureRandom();
    long secureInitializer = mySecureRand.nextLong();
    random = new Random(secureInitializer);
    try {
      localIPAdress = InetAddress.getLocalHost().toString();
    } catch (UnknownHostException e) {
      /* log.error("InetAddress.getLocalHost()", e); */
    }

  }

  // properties
  public String valueBeforeDigestor = "";
  public String valueAfterDigestor = "";


  /**
   * Static factory to retrieve a type 4 (pseudo randomly generated) number
   *
   * @return Global Unique Identifier
   */
  static final public String makeGUID() {
    try {
      return new GUID().toString();
    } catch (NoSuchAlgorithmException e) {
      // should never reach here, would have already logged the underlying exception
      return null;
    }
  }

  private GUID() throws NoSuchAlgorithmException {
    generateRandomGUID();
  }

  // Method to generate the random GUID
  private void generateRandomGUID() throws NoSuchAlgorithmException {

    MessageDigest msgDigester = null;
    StringBuffer sbValueBeforeDigestor = new StringBuffer();

    try {
      msgDigester = MessageDigest.getInstance(ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      /* log.error("MessageDigest.getInstance(" + ALGORITHM + ")", e); */
      throw e;
      // will die later with a null pointer
    }

    long time = System.currentTimeMillis();
    long rand = 0;

    rand = random.nextLong();

    // This StringBuffer can be a long as you need; the MD5
    // hash will always return 128 bits.  You can change
    // the seed to include anything you want here.
    // You could even stream a file through the MD5 making
    // the odds of guessing it at least as great as that
    // of guessing the contents of the file!
    sbValueBeforeDigestor.append(localIPAdress);
    sbValueBeforeDigestor.append(":");
    sbValueBeforeDigestor.append(Long.toString(time));
    sbValueBeforeDigestor.append(":");
    sbValueBeforeDigestor.append(Long.toString(rand));

    valueBeforeDigestor = sbValueBeforeDigestor.toString();
    msgDigester.update(valueBeforeDigestor.getBytes());

    byte[] array = msgDigester.digest();
    StringBuffer sb = new StringBuffer();
    for ( int j = 0; j < array.length; ++j ) {
      int b = array[j] & 0xFF;
      if (b < 0x10) {
        sb.append('0');
      }
      sb.append(Integer.toHexString(b));
    }

    valueAfterDigestor = sb.toString();
  }

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.<p>
   * <p/>
   *
   * @param obj the Object to be compared, must be a com.dteet.eai.model.common.UUIDGenerator
   * @return a negative integer, zero, or a positive integer as this object
   *         is less than, equal to, or greater than the specified object.
   * @throws ClassCastException if the specified object's type prevents it
   *                            from being compared to this Object.
   */
  public int compareTo( Object obj ) {

    // cast the obj to a com.dteet.eai.model.common.UUIDGenerator, also ensures the obj is a com.dteet.eai.model.common.UUIDGenerator
    GUID uuIdToCompare = (GUID) obj;

    // delegate to compareTo method of String class
    return valueAfterDigestor.compareTo(uuIdToCompare.valueAfterDigestor);
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * <p/>
   *
   * @param obj the reference object with which to compare.
   * @return <code>true</code> if this object is the same as the obj
   *         argument; <code>false</code> otherwise.
   * @see #hashCode()
   * @see java.util.Hashtable
   */
  public boolean equals( Object obj ) {
    // check if it is the same object or the com.dteet.eai.model.common.UUIDGenerator value is the same
    return this == obj || compareTo(obj) == 0;
  }

  /*
   * Convert to the standard format for GUID
   * (Useful for SQL Server UniqueIdentifiers, etc.)
   * Example: C2FEEEAC-CFCD-11D1-8B05-00600806D9B6
   */
  public String toString() {
    String raw = valueAfterDigestor.toUpperCase();
    StringBuffer sb = new StringBuffer();
    sb.append(raw.substring(0, 8));
    sb.append("-");
    sb.append(raw.substring(8, 12));
    sb.append("-");
    sb.append(raw.substring(12, 16));
    sb.append("-");
    sb.append(raw.substring(16, 20));
    sb.append("-");
    sb.append(raw.substring(20));

    return sb.toString();
  }

  /** 
   * The following is a two-dimensional array that provides the  
   * online help for functions in this class. Declare an array 
   * named HELP_STRINGS. 
   */ 
   public static final String[][] HELP_STRINGS ={ 
      {"makeGUID", "Returns a type 4 (pseudo randomly generated) GUID",  
       "makeGUID()", "C2FEEEAC-CFCD-11D1-8B05-00600806D9B6"}  
      };  
  
}
