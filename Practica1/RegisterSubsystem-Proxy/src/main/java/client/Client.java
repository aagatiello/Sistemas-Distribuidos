package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import protocol.*;


public class Client {

    public static final String version = "1.0";

    private Consola console;

    private Socket s;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    
    
    private ArrayList<Long> latencia_red = new ArrayList<Long>();
    private ArrayList<Long> latencia_app = new ArrayList<Long>();
    
    private long average_latencia_red = 0;
    private long average_latencia_app = 0;

    //--------------------------------------------------------------------------


    public static void main(String[] args) {
        new Client();
    }

    //--------------------------------------------------------------------------

    private void init() {
        this.console = new Consola();
    }
    
    //--------------------------------------------------------------------------
    
    public Client() {
        
        this.init();
        
        String cmd = this.console.getCommand();
        while( cmd.compareTo("close")!=0 ) {
            if( cmd.compareTo("login")==0 ) {
                //Capturamos las credenciales (user-password) de la consola
                String[] credenciales = this.console.getCommandLOGIN();
                System.out.println( credenciales[0] + " " + credenciales[1] );
                //Creamos el socket en el puerto 3339 del hostlocal y conectamos
                // los objetos de entrada y salida para serializar al socket
                this.doConnect();
                
                //Enviamos las credenciales al servidor
                this.doLogin(credenciales);
            }
            else if(cmd.compareTo("logout") == 0) {
                this.doDisconnect();
            }
            //CREAR: Dar de alta usuarios
            else if(cmd.compareTo("register") == 0) {
                //Capturamos los datos de un nuevo usuario
                String[] credenciales = this.console.getCommandRegister();
                System.out.println( credenciales[0] + " " + credenciales[1] );
                //Creamos el socket en el puerto 3339 del hostlocal y conectamos
                // los objetos de entrada y salida para serializar al socket
                this.doConnect();
                //Enviamos los datos del nuevo usuario registrado para comprobar
                //si existe ya o no
                this.doResgister(credenciales);
                this.doDisconnect();
                
            }
            
            cmd = this.console.getCommand();
        }
        if( this.s!=null )
            this.doDisconnect();
        
        this.console.writeMessage("Saliendo de la aplicacion");
    }

    //--------------------------------------------------------------------------

    private void doConnect() {
        try {
            if(this.s == null){
                //Creamos el socket
                this.s = new Socket("localhost", 3338); //socket cn proxy
                //Asociamos los objetos al socket
                this.os = new ObjectOutputStream( s.getOutputStream() );
                this.is = new ObjectInputStream( s.getInputStream() );
            }else {

            }
        } catch (UnknownHostException ex) {
        } catch (IOException ex) {
        }        
    }
    
    //--------------------------------------------------------------------------

    private void doDisconnect() {
        if (this.s!=null){
            try {
                // Creamos una peticion de control, la serializamos y la mandamos
                PeticionControl p = new PeticionControl("OP_LOGOUT");
                this.os.writeObject(p);
                this.is.close();
                this.is = null;
                this.os.close();
                this.os = null;
                this.s.close();
                this.s = null;
            } catch (IOException ex) {
            }
        }else{
            System.out.println("Ya estabas desconectado");
        }
    }
    
    //--------------------------------------------------------------------------
    //CREAR: doResgister
    private void doResgister(String[] credenciales) {
        try {
            // Creamos una peticion de control
            PeticionControl p = new PeticionControl("OP_REGISTER");
            // Anadimos las credenciales a la lista de array
            p.getArgs().add(credenciales[0]);
            p.getArgs().add(credenciales[1]);
            //Enviamos el objeto serializado
            long startTime = System.currentTimeMillis();
            this.os.writeObject(p);
            long this_latency = (System.currentTimeMillis()-startTime);
            latencia_red.add((this_latency));
            System.out.println("Latencia de red actual: " + this_latency +"ms.");
            averageNetworkLatency();
            // Recibimos la respuesta de control del servidor (objeto serializado)
            RespuestaControl rc = (RespuestaControl)this.is.readObject();
            this_latency = (System.currentTimeMillis()-startTime);
            latencia_app.add((this_latency));
            System.out.println("Tiempo de respuesta actual: " + this_latency +"ms.");
            averageAppLatency();
            
            if( rc.getSubtipo().compareTo("OP_REG_OK")==0 ) {
                this.console.writeMessage("Registro correcto");
            }else if(rc.getSubtipo().compareTo("OP_REG_NOK") == 0) {
                this.console.writeMessage("El usuario ya existe, elija otro nombre");                
            }
        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
        } catch (EOFException ex){
        	System.out.println("Se ha producido un error en el servidor(proxy), intentelo de nuevo!!!");
        } catch (IOException ex) {
        	
        }
    }

    //--------------------------------------------------------------------------
    
    private void doLogin(String[] credenciales) {
        try {
            // Creamos una peticion de control
            PeticionControl p = new PeticionControl("OP_LOGIN");
            // Anadimos las credenciales a la lista de array
            p.getArgs().add(credenciales[0]);
            p.getArgs().add(credenciales[1]);
            //Enviamos el objeto serializado
            this.os.writeObject(p);
            // Recibimos la respuesta de control del servidor (objeto serializado)
            RespuestaControl rc = (RespuestaControl)this.is.readObject();
            if( rc.getSubtipo().compareTo("OP_LOGIN_OK")==0 ) {
                this.console.writeMessage("Login correcto");
            }
            else if(rc.getSubtipo().compareTo("OP_LOGIN_BAD_PASSWORD") == 0) {
                this.console.writeMessage("La contrasena especificada no es correcta");
                this.doDisconnect();
            }
            else if(rc.getSubtipo().compareTo("OP_LOGIN_NO_USER") == 0) {
                this.console.writeMessage("El usuario no existe");
                this.doDisconnect();
            }
        } catch (ClassNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
    //------------------------------------------------------------------------------
    
    private void averageNetworkLatency() {
    	for(int i=0; i < latencia_red.size(); i++) {
    		average_latencia_red += latencia_red.get(i);	
    	}
    	average_latencia_red = average_latencia_red/latencia_red.size();
    	System.out.println("La latencia de red media es de: " + average_latencia_red + "ms.");
    	average_latencia_red = 0;
    }
    private void averageAppLatency() {
    	for(int i=0; i < latencia_app.size(); i++) {	
    		average_latencia_app += latencia_app.get(i);	
    	}
    	average_latencia_app = average_latencia_app/latencia_app.size();
    	System.out.println("El tiempo de respuesta medio es de: " + average_latencia_app + "ms.");
    	average_latencia_app = 0;
    }
}