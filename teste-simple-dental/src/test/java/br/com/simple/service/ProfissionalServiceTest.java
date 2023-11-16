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
import br.com.simple.repository.ProfissionalRepository;

@SpringBootTest
public class ProfissionalServiceTest {

	@Mock
    private ProfissionalRepository profissionalRepository;

    @InjectMocks
    private ProfissionalService profissionalService;

    @Test
    public void getAllProfissionais() {
    	Mockito.when(profissionalRepository.findAll()).thenReturn(Arrays.asList(
                new Profissional(1L, "Desenvolvedor", "João", "1990-01-01", Arrays.asList(new Contato())),
                new Profissional(2L, "Designer", "Maria", "1985-05-10", Arrays.asList(new Contato()))
        ));
        
        List<Profissional> profissionais = profissionalService.getAllProfissionais();

        assertNotNull(profissionais);
        assertEquals(2, profissionais.size());
    }
    
    @Test
   public void getProfissionalById() {
    	Mockito.when(profissionalRepository.findById(1L)).thenReturn(Optional.of(new Profissional(1L, "Desenvolvedor", "João", "1990-01-01", Arrays.asList(new Contato()))));

        Profissional profissional = profissionalService.getProfissionalById(1L);

        assertNotNull(profissional);
        assertEquals("João", profissional.getNome());
    }
}
