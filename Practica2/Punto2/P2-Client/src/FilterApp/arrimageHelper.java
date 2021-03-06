package FilterApp;


/**
* FilterApp/arrimageHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from src/Filter.idl
* Wednesday, April 21, 2021 8:30:48 PM CEST
*/

abstract public class arrimageHelper
{
  private static String  _id = "IDL:FilterApp/arrimage:1.0";

  public static void insert (org.omg.CORBA.Any a, int[][] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static int[][] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
      __typeCode = org.omg.CORBA.ORB.init ().create_array_tc (32, __typeCode );
      __typeCode = org.omg.CORBA.ORB.init ().create_array_tc (32, __typeCode );
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (FilterApp.arrimageHelper.id (), "arrimage", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static int[][] read (org.omg.CORBA.portable.InputStream istream)
  {
    int value[][] = null;
    value = new int[32][];
    for (int _o0 = 0;_o0 < (32); ++_o0)
    {
      value[_o0] = new int[32];
      for (int _o1 = 0;_o1 < (32); ++_o1)
      {
        value[_o0][_o1] = istream.read_long ();
      }
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, int[][] value)
  {
    if (value.length != (32))
      throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    for (int _i0 = 0;_i0 < (32); ++_i0)
    {
      if (value[_i0].length != (32))
        throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
      for (int _i1 = 0;_i1 < (32); ++_i1)
      {
        ostream.write_long (value[_i0][_i1]);
      }
    }
  }

}
