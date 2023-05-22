package it.prova.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.agenda.repository.utente.UtenteRepository;
import it.prova.model.Utente;


@Service
@Transactional(readOnly = true)
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository repository;

	@Override
	public List<Utente> listAllUtenti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente caricaSingoloUtente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente caricaSingoloUtenteConRuoli(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Utente utenteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Utente utenteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utente> findByExample(Utente example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente eseguiAccesso(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserAbilitation(Long utenteInstanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utente findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}


}
