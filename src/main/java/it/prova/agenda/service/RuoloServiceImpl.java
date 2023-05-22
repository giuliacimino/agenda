package it.prova.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.agenda.repository.ruolo.RuoloRepository;
import it.prova.model.Ruolo;


@Service
@Transactional(readOnly = true)
public class RuoloServiceImpl implements RuoloService {
	
	@Autowired
	private RuoloRepository ruoloRepository;

	@Override
	public List<Ruolo> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
