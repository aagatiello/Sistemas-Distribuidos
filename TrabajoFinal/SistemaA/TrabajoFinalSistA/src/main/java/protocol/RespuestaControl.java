package protocol;

import java.util.ArrayList;

public class RespuestaControl extends Respuesta {
	private ArrayList args;
	private ArrayList<String> tokens;
	private ArrayList<Double> cpus;
	private String path;
	private ArrayList<Long> time;

	// --------------------------------------------------------------------------

	public RespuestaControl() {
		this.tipo = "RESPUESTA_CONTROL";
		this.args = new ArrayList();
		this.cpus = new ArrayList<Double>();
		this.tokens = new ArrayList<String>();
		this.path = new String("");
		this.time = new ArrayList<Long>();
	}

	// --------------------------------------------------------------------------

	public RespuestaControl(String subtipo) {
		this.tipo = "RESPUESTA_CONTROL";
		this.subtipo = subtipo;
		this.args = new ArrayList();
		this.cpus = new ArrayList<Double>();
		this.tokens = new ArrayList<String>();
		this.path = new String("");
		this.time = new ArrayList<Long>();
	}

	// --------------------------------------------------------------------------

	public ArrayList<Long> getTime() {
		return time;
	}
	
	// --------------------------------------------------------------------------

	public void setTime(ArrayList<Long> time) {
		this.time = time;
	}

	// --------------------------------------------------------------------------
	
	public String getPath() {
		return path;
	}

	// --------------------------------------------------------------------------
	
	public void setPath(String path) {
		this.path = path;
	}

	// --------------------------------------------------------------------------
	
	public ArrayList getArgs() {
		return args;
	}

	// --------------------------------------------------------------------------

	public void setArgs(ArrayList args) {
		this.args = args;
	}

	// --------------------------------------------------------------------------

	public ArrayList<String> getTokens() {
		return tokens;
	}

	// --------------------------------------------------------------------------

	public void setTokens(ArrayList<String> tokens) {
		this.tokens = tokens;
	}

	// --------------------------------------------------------------------------

	public ArrayList<Double> getCpus() {
		return cpus;
	}

	// --------------------------------------------------------------------------

	public void setCpus(ArrayList<Double> cpus) {
		this.cpus = cpus;
	}
}
