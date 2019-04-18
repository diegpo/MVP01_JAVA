package sistema.tabelas;

public class Cliente {
	
	private int idcliente;
	private String nomecli, documentocli;
	
	public  Cliente(int idcliente, String nomecli, String documentocli) {
		
		this.idcliente = idcliente;
		this.nomecli = nomecli;
		this.documentocli = documentocli;	
	}
	
	public int getidcliente() {
		return idcliente;
	}
	
	public String getnomecli() {
		return nomecli;
	}
	
	public String getdocumentocli() {
		return documentocli;
	}

}
