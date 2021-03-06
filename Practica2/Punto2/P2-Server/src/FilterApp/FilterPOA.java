package FilterApp;


/**
* FilterApp/FilterPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from src/Filter.idl
* Wednesday, April 21, 2021 7:51:46 PM CEST
*/

public abstract class FilterPOA extends org.omg.PortableServer.Servant
 implements FilterApp.FilterOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("applyFilter", new java.lang.Integer (0));
    _methods.put ("shutdown", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // FilterApp/Filter/applyFilter
       {
         String filter = in.read_string ();
         String path = in.read_string ();
         String $result = null;
         $result = this.applyFilter (filter, path);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // FilterApp/Filter/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:FilterApp/Filter:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Filter _this() 
  {
    return FilterHelper.narrow(
    super._this_object());
  }

  public Filter _this(org.omg.CORBA.ORB orb) 
  {
    return FilterHelper.narrow(
    super._this_object(orb));
  }


} // class FilterPOA
