package br.com.simple.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.simple.model.Contato;
import br.com.simple.model.Profissional;
import br.com.simple.repository.ContatoRepository;
@SpringBootTest
public class ContatoServiceTest {

	@Mock
	private ContatoRepository contatoRepository;

	@InjectMocks
	private ContatoService contatoService;

	@Test
	public void getAllContatos() {
		// Mockando o comportamento do repository
		Mockito.when(contatoRepository.findAll()).thenReturn(Arrays.asList(
				new Contato(1L, "João", "joao@example.com", new Profissional()),
				new Contato(2L, "Maria", "maria@example.com", new Profissional())));
		List<Contato> contatos = contatoService.getAllContatos();

		assertNotNull(contatos);
		assertEquals(2, contatos.size());
	}

	 @Test
	   public void getContatoById() {
	        // Mockando o comportamento do repository
	        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.of(
	        		new Contato(1L, "João", "joao@example.com", new Profissional())));

	        Contato contato = contatoService.getContatoById(1L);

	        assertNotNull(contato);
	        assertEquals("João", contato.getNome());
	    }
	
}
