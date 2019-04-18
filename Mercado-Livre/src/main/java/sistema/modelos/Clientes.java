package sistema.modelos;

public class Clientes {
	
	private String idcliente, nomecli, documentocli;
	
	public Clientes(String idcliente, String nomecli, String documentocli){
		this.idcliente=idcliente;
		this.nomecli=nomecli;
		this.documentocli=documentocli;
	}
	
	public String getidcliente(){
		return idcliente;
	}
	
	public String getnomecli() {
		return nomecli;
	}
	
	public String getdocumentocli() {
		return documentocli;
	}
}