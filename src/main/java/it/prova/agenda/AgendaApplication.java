package it.prova.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.agenda.model.Agenda;
import it.prova.agenda.model.Ruolo;
import it.prova.agenda.model.Utente;
import it.prova.agenda.service.AgendaService;
import it.prova.agenda.service.RuoloService;
import it.prova.agenda.service.UtenteService;

@SpringBootApplication
public class AgendaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	@Autowired
	private AgendaService agendaService;

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", Ruolo.ROLE_CLASSIC_USER));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", LocalDate.now());
			admin.setEmail("a.admin@prova.it");
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", LocalDate.now());
			classicUser.setEmail("u.user@prova.it");
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1", "user1", "Antonioo", "Verdii", LocalDate.now());
			classicUser1.setEmail("u.user1@prova.it");
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser1);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser1.getId());
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Antoniooo", "Verdiii", LocalDate.now());
			classicUser2.setEmail("u.user2@prova.it");
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser2);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}

		// inserimento dati agenda
		LocalDateTime oraInizio1 = LocalDateTime.of(2020, 11, 10, 13, 33, 22);
		LocalDateTime oraFine1 = LocalDateTime.of(2021, 10, 13, 15, 20, 55);
		Agenda agenda1 = new Agenda("agenda 1", oraInizio1, oraFine1);
		agenda1.setUtente(utenteServiceInstance.findByUsername("user1"));

		if (agendaService.cercaPerDescrizione(agenda1.getDescrizione()).isEmpty())
			agendaService.inserisciNuovo(agenda1);
		
		
		
		LocalDateTime oraInizio2 = LocalDateTime.of(2022, 7, 15, 12, 32, 20);
		LocalDateTime oraFine2 = LocalDateTime.of(2022, 9, 10, 17, 22, 57);

		Agenda agenda2 = new Agenda("agenda 2", oraInizio2, oraFine2);
		agenda2.setUtente(utenteServiceInstance.findByUsername("admin"));

		if (agendaService.cercaPerDescrizione(agenda2.getDescrizione()).isEmpty())
			agendaService.inserisciNuovo(agenda2);
	}

}
