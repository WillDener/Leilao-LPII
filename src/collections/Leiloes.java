package collections;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import entities.Leilao;
import interfaces.CreateReadUpdateDelete;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import services.LeilaoComparator;

@NoArgsConstructor
@Getter
@Setter
public class Leiloes implements CreateReadUpdateDelete {
	
	private LinkedList<Leilao> leiloes = new LinkedList<Leilao>();
	
	@Override
	public String toString() {
		String listaLeiloes = "";
		for (Leilao leilao : leiloes) {
			listaLeiloes += leilao.toString();
		};
		return listaLeiloes;
	}
	
	public void adicionar(Leilao leilao) {
		if(leilao instanceof Leilao) {
			getLeiloes().add(leilao);
		} else {
			throw new IllegalArgumentException("O item n�o � um leil�o");
		}
	}
	
	public void imprimir() {
		for (Leilao leilao : leiloes) {
			System.out.println(leilao.toString());
		}
	}
	
	@Override
	public void adicionar(Object leilao) {
		try {
			Leilao leilaoSave = (Leilao) leilao;
			Object leilaoSearch = consultar(leilaoSave.getId());
				
			if (leilaoSearch instanceof String) {
				getLeiloes().add(leilaoSave);
			} else {
				System.out.println("Leil�o j� cadastrado anteriormente.");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new IllegalArgumentException("O item n�o � um leil�o.");
		}
	}

	@Override
	public Object consultar(String id) {
		for (Leilao leilao : leiloes) {
			if(leilao.getId().equals(id)) {
				return leilao;
			}
		}
		return "Nenhum leil�o encontrado com este ID.";
	}

	@Override
	public void atualizar(String id, Object leilaoNew) {
		Leilao leilaoNewCasted = (Leilao) leilaoNew;
			
		Object leilaoOld = consultar(id);
			
		if (leilaoOld instanceof Leilao) {
			Leilao leilaoOldCasted = (Leilao) leilaoOld;
			
			leilaoOldCasted.setId(leilaoNewCasted.getId());
			leilaoOldCasted.setProdutos(leilaoNewCasted.getProdutos());
			leilaoOldCasted.setClientes(leilaoNewCasted.getClientes());
			leilaoOldCasted.setInstituicao(leilaoNewCasted.getInstituicao());
			leilaoOldCasted.setLances(leilaoNewCasted.getLances());
			leilaoOldCasted.setStatusLeilao(leilaoNewCasted.getStatusLeilao());
		}
	}

	@Override
	public Boolean remover(String id) {
		Object leilao = consultar(id);
			
		if (leilao instanceof Leilao) {
			return leiloes.remove(leilao);
		}
		return false;
	}
	
	public LinkedList<Leilao> ordenarLeiloes() {
		LeilaoComparator leilaoComparator = new LeilaoComparator();
		Collections.sort(leiloes, leilaoComparator);
		
		return leiloes;
	}

}
