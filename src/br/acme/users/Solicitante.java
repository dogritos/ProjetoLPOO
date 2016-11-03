package br.acme.users;
import java.util.Date;

import br.acme.location.*;
import br.acme.storage.*;
import br.acme.exception.*;
public class Solicitante extends Usuario {
	// Atributos ----------------------------------------------------------------------------------------------------
	private Date dataNascimento = new Date();
	private int numeroCelular;
	private Lugar[] lugares = new Lugar[10];
	private RepositorioViagem viagens = new RepositorioViagem();
	
	// Construtor ----------------------------------------------------------------------------------------------------
	public Solicitante(String cpf, String email, String senha, String nome, String sexo, Date dataNascimento, int numeroCelular) {
		// Construtor da superClasse
		super(cpf,nome,senha,email,sexo);
		this.dataNascimento = dataNascimento;
		this.numeroCelular = numeroCelular;
	}

	// Getters and Setters ----------------------------------------------------------------------------------------------------

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento, String senha) {
		if(getSenha().equals(senha)) this.dataNascimento = dataNascimento;
	}

	public int getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(int numeroCelular, String senha) {
		if(getSenha().equals(senha)) this.numeroCelular = numeroCelular;
	}

	public Lugar[] getLugares() {
		return lugares;
	}

	public void setLugares(Lugar[] lugares, String senha) {
		if(getSenha().equals(senha)) this.lugares = lugares;
	}

	public RepositorioViagem getViagens() {
		return viagens;
	}

	public void setViagens(RepositorioViagem viagens, String senha) {
		if(getSenha().equals(senha)) this.viagens = viagens;
	}
	
	public void setCpf(String cpf, String senha){
		if(getSenha().equals(senha)) setCpf(cpf);
	}
	
	public void setNome(String nome, String senha){
		if(getSenha().equals(senha)) setNome(nome);
	}
	
	public void setSenha(String novaSenha, String antigaSenha){
		if(getSenha().equals(antigaSenha)) setSenha(novaSenha);
	}
	
	public void setEmail(String email, String senha){
		if(getSenha().equals(senha)) setEmail(email);
	}
	
	public void setSexo(String sexo, String senha){
		if(getSenha().equals(senha)) setSexo(sexo);
	}
	
	// M�todos ----------------------------------------------------------------------------------------------------
	public void solicitarCarona(RepositorioMotorista repositorio, Lugar inicio, Lugar fim, String formaPagamento) throws RepositorioException{
		for(Motorista motor : repositorio.buscarTodos()){
			if(motor.isDisponivel()== true){ 
				viagens.adicionar(motor.responderPedido(this, inicio, fim, formaPagamento));
				break;		
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void cancelarCarona()throws RepositorioException{
		Date horaCancelamento = new Date();
		Viagem maisRecente = null;
		int i=0;
		for(Viagem travel: viagens.buscarTodos()){
			if(travel == null) break;
			// Encontra a viagem mais recente no array
			maisRecente = (i==0)?travel:(travel.getHoraPedido().before(maisRecente.getHoraPedido()))?travel:maisRecente;
			i++;
		}
		viagens.remover(maisRecente.getId());
		maisRecente.getMotorista().getViagens().remover(maisRecente.getId());// Remove a viagem do hist�rico do motorista
		maisRecente.getMotorista().setDisponivel(true);
		double diferenca = (horaCancelamento.getMinutes()) - (maisRecente.getHoraPedido().getMinutes());
		String taxa = (diferenca>10)?"R$10,00":"nenhuma";
		System.out.println("A carona foi cancelada com sucesso. Taxa de cancelamento: "+taxa);
	}
	
	public void historico()throws RepositorioException{
		System.out.println("Hist�rico de viagens ("+this.getNome()+")");
		for(Viagem travel: viagens.buscarTodos()){
			if(travel==null) break;
			System.out.println("ID: "+travel.getId());
			System.out.println("Origem: "+travel.getOrigem().getEndereco());
			System.out.println("Destino: "+travel.getDestino().getEndereco());
			System.out.println("Motorista: "+travel.getMotorista().getNome());
			System.out.println("Pre�o: "+travel.getValorViagem());
		}
		System.out.println();
	}
	public String toString(){
		return super.toString()+"Data de Nascimento: "+this.dataNascimento+";Numero Celular: "+this.numeroCelular;
	}
}
