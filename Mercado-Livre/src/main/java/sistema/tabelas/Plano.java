package sistema.tabelas;

public class Plano {
	
	private int idplano;
	private String nomeplano;
	private float valorplano;
	
	public Plano(int idplano, String nomeplano, float valorplano) {
		this.idplano = idplano;
		this.nomeplano = nomeplano;
		this.valorplano = valorplano;
	}
		
	public int idplano() {
		return idplano;
	}
	public String nomeplano() {
		return nomeplano;
	}
	public float valorplano() {
		return valorplano;
	}
	
}
