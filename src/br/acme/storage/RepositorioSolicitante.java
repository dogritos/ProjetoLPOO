package br.acme.storage;
import br.acme.exception.RepositorioException;
import br.acme.users.Solicitante;

public class RepositorioSolicitante implements IRepositorioSolicitante {
	// Atributos ----------------------------------------------------------------------------------------------------
	private Solicitante[] listaSolicitante = new Solicitante[10];
	
	// Construtor Padr�o
	
	// Getters and Setters ----------------------------------------------------------------------------------------------------
	public Solicitante[] getListaSolicitante() {
		return listaSolicitante;
	}

	public void setListaSolicitante(Solicitante[] listaSolicitante) {
		this.listaSolicitante = listaSolicitante;
	}
	
	// M�todos ----------------------------------------------------------------------------------------------------
	public void adicionar(Solicitante novoSolicitante)throws RepositorioException{
		/* 
		 * Para cada elemento n�o nulo do Array:
		 * 	 > Compara o ID do atual com o ID do novo:
		 * 		> Se o ID for igual, quebra o la�o;
		 * 		> Se for diferente, segue para o pr�ximo elementoe e aumenta o �ndice;
		 * Caso encontre um elemento nulo, insere o novo elemento neste espa�oe quebra o la�o;
		 */
		int i=0;// �ndice do elemento no Array
		for(Solicitante elemento: listaSolicitante){
			if(elemento!=null){
				if(elemento.getId()==novoSolicitante.getId())
					throw new RepositorioException("o ID ja esta em uso");				else
					i++;
			}
			else{
				listaSolicitante[i]=novoSolicitante;
				break;
			}
		}
	}
	
	public void remover(long id)throws RepositorioException{
		/*
		 * Caso qualquer elemento nulo seja encontrado, o la�o � encerrado;
		 * Se o elemento com o ID especificado seja encontrado a posi��o dele � igualada � null;
		 * Caso o elemento tenha sido removido do vetor:
		 * 	 > Se o �ndice for a �ltima posi��o, o espa�o se torna null;
		 *   > Se n�o, o espa�o atual � igualado ao pr�ximo;
		 */
		boolean removido = false; // Varia de acordo com o sucesso do m�todo
		int i=0;
		if(listaSolicitante[0]==null)throw new RepositorioException("o Repositorio esta Vazio");
		for(Solicitante elemento: listaSolicitante){
			if(elemento==null)break;
			if(elemento.getId() == id){
				elemento=null;
				removido=true;
			}
			if(removido){
				// Se �ndice for a �ltima posi��o do vetor, � igualada � null, se n�o, ao pr�ximo elemento
				listaSolicitante[i]=(i==listaSolicitante.length-1)?null:listaSolicitante[i+1];
			}
			i++;
		}
		if(removido){
			System.out.println("Solicitante removido com sucesso.");
		}
		else
			throw new RepositorioException("o Solicitante nao encontrado");
	}
	
	public void alterar(long id, Solicitante novoSolicitante)throws RepositorioException{
		boolean alterado = false; // Varia de acordo com o sucesso do m�todo
		int i=0;
		if(listaSolicitante[0]==null)throw new RepositorioException("o Repositorio esta Vazio");
		for(Solicitante elemento: listaSolicitante){
			if(elemento==null)break;
			if(elemento.getId() == id){
				listaSolicitante[i]=novoSolicitante;
				alterado=true;
			}
			i++;
		}
		if(alterado)
			System.out.println("Solicitante alterado com sucesso.");
		else
			throw new RepositorioException("Solicitante n�o encontrado.");
	}
	
	public Solicitante buscar(long id)throws RepositorioException{
		if(listaSolicitante[0]==null)throw new RepositorioException("o Repositorio esta Vazio");
		for(Solicitante solicitante : listaSolicitante){
			if(solicitante==null)break;
			if(solicitante.getId() == id){
				return solicitante;
			}
		}
		throw new RepositorioException("o Solicitante nao foi encontrado");
	}
	
	public Solicitante[] buscarTodos()throws RepositorioException{
		if(listaSolicitante[0]==null){
			throw new RepositorioException("o Repositorio esta Vazio");
		}
		return this.getListaSolicitante();
	}
}