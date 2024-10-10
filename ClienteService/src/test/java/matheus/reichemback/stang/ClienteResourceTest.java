package matheus.reichemback.stang;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import matheus.reichemback.stang.controller.ClienteResource;
import matheus.reichemback.stang.domain.Cliente;
import matheus.reichemback.stang.exception.EntityNotFoundException;
import matheus.reichemback.stang.usecase.BuscaCliente;
import matheus.reichemback.stang.usecase.GerenciaCliente;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteResourceTest {

	@InjectMocks
	private ClienteResource clienteResource;
	
	@MockBean
	private BuscaCliente buscaCliente;
	
	@MockBean
	private GerenciaCliente gerenciaCliente;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void buscarPorIdTest() {
		Cliente clienteMock = Cliente.builder().id("1").nome("Matheus").build();
		
		when(this.buscaCliente.buscarPorId("1")).thenReturn(clienteMock);
		
		ResponseEntity<Cliente> result = this.clienteResource.buscarPorId("1");
		
		Cliente clienteResultado = result.getBody();
		
		assertThat(clienteResultado).isEqualTo(clienteMock);
		assertThat(clienteResultado.getId()).isEqualTo(clienteMock.getId());
		assertThat(clienteResultado.getNome()).isEqualTo(clienteMock.getNome());
	}
	
	@Test
	public void buscarPorCpfTest() {
		Cliente clienteMock = Cliente.builder().id("1").nome("Matheus").cpf(213L).build();
		
		when(this.buscaCliente.buscarPorCpf(clienteMock.getCpf())).thenReturn(clienteMock);
		
		ResponseEntity<Cliente> result = this.clienteResource.buscarPorCpf(clienteMock.getCpf());
		
		Cliente clienteResultado = result.getBody();
		
		assertThat(clienteResultado).isEqualTo(clienteMock);
		assertThat(clienteResultado.getId()).isEqualTo(clienteMock.getId());
		assertThat(clienteResultado.getNome()).isEqualTo(clienteMock.getNome());
		assertThat(clienteResultado.getCpf()).isEqualTo(clienteMock.getCpf());
	}
	
	@Test
	public void isClienteCadastradoTest() {
		
		when(this.buscaCliente.isCadastrado("1")).thenReturn(true);
		
		ResponseEntity<Boolean> result = this.clienteResource.isCadastrado("1");
		
		Boolean isCadastrado = result.getBody();
		
		assertThat(isCadastrado).isEqualTo(true);
	}
	
	@Test
	public void cadastrarClienteTest() {
		Cliente clienteMock = Cliente.builder().id("1")
				.nome("Matheus").cpf(12345L).email("teste@teste.com")
				.endereco("Rua Sprig Boot").tel(545454L).cidade("JavaScript")
				.estado("VSCode").numero(321).build();
		
		when(this.gerenciaCliente.cadastrar(clienteMock)).thenReturn(clienteMock);
		
		ResponseEntity<Cliente> result = this.clienteResource.cadastrar(clienteMock);
		
		Cliente clienteResultado = result.getBody();
		assertThat(clienteResultado).isEqualTo(clienteMock);
		assertThat(clienteResultado.getId()).isEqualTo(clienteMock.getId());
		assertThat(clienteResultado.getNome()).isEqualTo(clienteMock.getNome());
		
	}
	
	@Test
	public void atualizarClienteTest() {
		Cliente clienteMock = Cliente.builder().id("1")
				.nome("Matheus").cpf(12345L).email("teste@teste.com")
				.endereco("Rua Sprig Boot").tel(545454L).cidade("JavaScript")
				.estado("VSCode").numero(321).build();
		
		when(this.gerenciaCliente.cadastrar(clienteMock)).thenReturn(clienteMock);
		
		ResponseEntity<Cliente> result1 = this.clienteResource.cadastrar(clienteMock);
		
		Cliente clienteResultado1 = result1.getBody();
		assertThat(clienteResultado1).isEqualTo(clienteMock);
		assertThat(clienteResultado1.getId()).isEqualTo(clienteMock.getId());
		assertThat(clienteResultado1.getNome()).isEqualTo(clienteMock.getNome());
		
		clienteMock.setNome("Matheus Reichemback Stang");
		
		when(this.gerenciaCliente.atualizar(clienteMock)).thenReturn(clienteMock);
		
		ResponseEntity<Cliente> result2 = this.clienteResource.atualizar(clienteMock);
		
		Cliente clienteResultado2 = result2.getBody();
		assertThat(clienteResultado2).isEqualTo(clienteMock);
		assertThat(clienteResultado2.getId()).isEqualTo(clienteMock.getId());
		assertThat(clienteResultado2.getNome()).isEqualTo(clienteMock.getNome());
	}
	
	@Test
	public void excluirClienteTest() {
		Cliente clienteMock = Cliente.builder().id("1")
				.nome("Matheus").cpf(12345L).email("teste@teste.com")
				.endereco("Rua Sprig Boot").tel(545454L).cidade("JavaScript")
				.estado("VSCode").numero(321).build();
		
		when(this.gerenciaCliente.cadastrar(clienteMock)).thenReturn(clienteMock);
		
		ResponseEntity<Cliente> result = this.clienteResource.cadastrar(clienteMock);
		
		Cliente clienteResultado = result.getBody();
		assertThat(clienteResultado).isEqualTo(clienteMock);
		assertThat(clienteResultado.getId()).isEqualTo(clienteMock.getId());
		assertThat(clienteResultado.getNome()).isEqualTo(clienteMock.getNome());
		
		doNothing().when(this.gerenciaCliente).remover(clienteMock.getId());
		
		ResponseEntity<String> resultOfExclusion = this.clienteResource.remover(clienteMock.getId());
		
		String textOfExclusion = resultOfExclusion.getBody();
		assertThat(textOfExclusion).isEqualTo("Removido com sucesso!");
	}
	
}
