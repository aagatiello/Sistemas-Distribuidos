package FilterApp;

/**
* FilterApp/FilterHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from src/Filter.idl
* Sunday, May 9, 2021 11:23:09 PM CEST
*/

public final class FilterHolder implements org.omg.CORBA.portable.Streamable
{
  public FilterApp.Filter value = null;

  public FilterHolder ()
  {
  }

  public FilterHolder (FilterApp.Filter initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = FilterApp.FilterHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    FilterApp.FilterHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return FilterApp.FilterHelper.type ();
  }

}
