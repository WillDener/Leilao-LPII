package collections;

import java.util.LinkedList;

import entities.Lance;
import entities.Leilao;
import interfaces.CreateReadUpdateDelete;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Lances implements CreateReadUpdateDelete {
	
	private LinkedList<Lance> lances = new LinkedList<Lance>();
	
	@Override
	public String toString() {
		String listaLances = "";
		for (Lance lance : lances) {
			listaLances += lance.toString();
		};
		return listaLances;
	}
	
	@Override
	public void imprimir() {
		for (Lance lance : lances) {
			System.out.println(lance.toString());
		}
	}
	
	@Override
	public void adicionar(Object lance) {
		try {
			Lance lanceSave = (Lance) lance;
			Object lanceSearch = consultar(lanceSave.getId());
				
			if (lanceSearch instanceof String) {
				getLances().add(lanceSave);
			} else {
				System.out.println("Lance j� cadastrado anteriormente.");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new IllegalArgumentException("O item n�o � um lance.");
		}
	}

	@Override
	public Object consultar(String id) {
		for (Lance lance : lances) {
			if(lance.getId().equals(id)) {
				return lance;
			}
		}
		return "Nenhum lance encontrado com este ID.";
	}

	@Override
	public void atualizar(String id, Object lanceNew) {
		Lance lanceNewCasted = (Lance) lanceNew;
			
		Object lanceOld = consultar(id);
			
		if (lanceOld instanceof Lance) {
			Lance lanceOldCasted = (Lance) lanceOld;
			
			lanceOldCasted.setId(lanceNewCasted.getId());
			lanceOldCasted.setCliente(lanceNewCasted.getCliente());
			lanceOldCasted.setProduto(lanceNewCasted.getProduto());
			lanceOldCasted.setValor(lanceNewCasted.getValor());
		}
	}

	@Override
	public Boolean remover(String id) {
		Object lance = consultar(id);
			
		if (lance instanceof Lance) {
			return lances.remove(lances);
		}
		return false;
	}
	
}
