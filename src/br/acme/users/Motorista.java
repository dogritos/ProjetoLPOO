package br.acme.users;
import br.acme.location.*;

public class Motorista extends Usuario {
	// Atributos ----------------------------------------------------------------------------------------------------
	private boolean disponivel;
	private Viagem[] viagens = new Viagem[10];
	
	// Construtor ----------------------------------------------------------------------------------------------------
	public Motorista(String cpf, String nome, String senha, String email, String sexo, boolean disponivel) {
		// Contrutor da superClasse
		super(cpf,nome,senha,email,sexo);
		this.disponivel = disponivel;
	}

	// Getters and Setters ----------------------------------------------------------------------------------------------------
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Viagem[] getViagens() {
		return viagens;
	}

	public void setViagens(Viagem[] viagens) {
		this.viagens = viagens;
	}

	// M�todos ----------------------------------------------------------------------------------------------------
	public void responderPedido(boolean resposta){
		if(resposta){
			
		}
	}
	
	public void historico(){
		
	}
	
	public String toString(){
		return super.toString()+"Status: "+this.disponivel+viagens.toString();
	}
}
