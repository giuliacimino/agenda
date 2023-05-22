package it.prova.agenda.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.agenda.dto.AgendaDTO;
import it.prova.agenda.model.Agenda;
import it.prova.agenda.model.Ruolo;
import it.prova.agenda.model.Utente;
import it.prova.agenda.service.AgendaService;
import it.prova.agenda.service.UtenteService;
import it.prova.agenda.web.api.exception.AgendaNotFoundException;
import it.prova.agenda.web.api.exception.IdNotNullForInsertException;
import it.prova.agenda.web.api.exception.UtenteNotAuthorizedException;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("api/agenda")
public class AgendaController {
	
	@Autowired
	AgendaService agendaService;
	
	@Autowired
	UtenteService utenteService;
	
	@GetMapping
	public List<AgendaDTO> getAll() {
		return AgendaDTO.createAgendaDTOListFromModelList(agendaService.listAllElements(true), true);
	}
	
	@PostMapping
	public AgendaDTO createNew(@Valid @RequestBody AgendaDTO agendaInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (agendaInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Agenda agendaInserito = agendaService.inserisciNuovo(agendaInput.buildAgendaModel());
		return AgendaDTO.buildAgendaDTOFromModel(agendaInserito, true);
	}

	@GetMapping(value = "findById/{id}")
	public AgendaDTO findById(@PathVariable(value = "id", required = true) long id) {
		
		//controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		boolean eUtente = false;
		
		for(Ruolo ruoloItem: utenteLoggato.getRuoli()) {
			if(ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		
		if(!utenteLoggato.getId().equals(id) && !eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati senza ruolo admin");
		
		// eseguo codice previsto se autorizzati
		Agenda agenda = agendaService.caricaSingoloElementoEager(id);

		if (agenda == null)
			throw new AgendaNotFoundException("Agenda not found con id: " + id);

		return AgendaDTO.buildAgendaDTOFromModel(agenda, true);
	}
	
	@PutMapping(value = "update/{id}")
	public AgendaDTO update(@Valid @RequestBody AgendaDTO agendaInput, @PathVariable(required = true) Long id) {
		
		//controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		boolean eUtente = false;
		
		for(Ruolo ruoloItem: utenteLoggato.getRuoli()) {
			if(ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		
		if(!utenteLoggato.getId().equals(id) && !eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati senza ruolo admin");
		
		// eseguo codice previsto se autorizzati
		Agenda agenda = agendaService.caricaSingoloElemento(id);

		if (agenda == null)
			throw new AgendaNotFoundException("Agenda not found con id: " + id);

		agendaInput.setId(id);
		Agenda agendaAggiornato = agendaService.aggiorna(agendaInput.buildAgendaModel());
		return AgendaDTO.buildAgendaDTOFromModel(agendaAggiornato, false);
	}

	@DeleteMapping(value = "delete/{id}")
	@ResponseStatus(HttpStatus.OK)	
	public void delete(@PathVariable(required = true) Long id) {
		
		//controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		boolean eUtente = false;
		
		for(Ruolo ruoloItem: utenteLoggato.getRuoli()) {
			if(ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		
		if(!utenteLoggato.getId().equals(id) && !eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati senza ruolo admin");
		
		// eseguo codice previsto se autorizzati
		Agenda agenda = agendaService.caricaSingoloElemento(id);

		if (agenda == null)
			throw new AgendaNotFoundException("Agenda not found con id: " + id);

		agendaService.rimuovi(agenda);
	}
	
	

}
