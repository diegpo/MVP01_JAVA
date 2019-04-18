package sistema.tabelas;

public class Contrato {
	
	private int idcontrato;
	private String nomecli, nomeplano;
	private float valorplano;
	
	public Contrato(int idcontrato, String nomecli, String nomeplano, float valorplano) {
		this.idcontrato = idcontrato;
		this.nomecli = nomecli;
		this.nomeplano = nomeplano;
		this.valorplano = valorplano;		
	}	
	
	public int getidcontrato() {
		return idcontrato;
	}
	public String getnomecli() {
		return nomecli;
	}
	public String getnomeplano() {
		return nomeplano;
	}
	public float getValorplano() {
		return valorplano;
	}

}
