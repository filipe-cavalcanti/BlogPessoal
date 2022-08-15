package br.org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		usuarioRepository.deleteAll();

		usuarioRepository.save(new Usuario(0L, "Teste1 da Silva", "teste1@teste.com", "12345678", "testefoto1.com"));
		usuarioRepository.save(new Usuario(0L, "Teste2 da Silva", "teste2@teste.com", "12345678", "testefoto2.com"));
		usuarioRepository.save(new Usuario(0L, "Teste3 da Silva", "teste3@teste.com", "12345678", "testefoto3.com"));
		usuarioRepository.save(new Usuario(0L, "Teste4 Antunes", "teste4@teste.com", "12345678", "testefoto4.com"));

	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("teste1@teste.com");
		assertTrue(usuario.get().getUsuario().equals("teste1@teste.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Teste1 da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Teste2 da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Teste3 da Silva"));
	}

}
