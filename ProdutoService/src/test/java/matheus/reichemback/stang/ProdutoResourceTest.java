package matheus.reichemback.stang;

import static org.assertj.core.api.Assertions.assertThat;
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

import matheus.reichemback.stang.controller.ProdutoResource;
import matheus.reichemback.stang.domain.Produto;
import matheus.reichemback.stang.usecase.BuscaProduto;
import matheus.reichemback.stang.usecase.GerenciaProduto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProdutoResourceTest {

	@InjectMocks
	private ProdutoResource produtoResource;
	
	@MockBean
	private BuscaProduto buscaProduto;
	
	@MockBean
	private GerenciaProduto gerenciaProduto;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void buscarPorIdTest() {
		Produto produtoMock = Produto.builder().id("1").nome("TV").build();
		
		when(this.buscaProduto.buscarPorId("1")).thenReturn(produtoMock);
		
		ResponseEntity<Produto> result = this.produtoResource.buscarPorId("1");
		
		Produto produtoResultado = result.getBody();
		
		assertThat(produtoResultado).isEqualTo(produtoMock);
		assertThat(produtoResultado.getId()).isEqualTo(produtoMock.getId());
		assertThat(produtoResultado.getCodigo()).isEqualTo(produtoMock.getCodigo());
		assertThat(produtoResultado.getNome()).isEqualTo(produtoMock.getNome());
	}
	
	@Test
	public void buscarPorCodigoTest() {
		Produto produtoMock = Produto.builder().id("1").nome("TV").codigo(213L).build();
		
		when(this.buscaProduto.buscarPorCodigo(produtoMock.getCodigo())).thenReturn(produtoMock);
		
		ResponseEntity<Produto> result = this.produtoResource.buscarPorCodigo(produtoMock.getCodigo());
		
		Produto produtoResultado = result.getBody();
		
		assertThat(produtoResultado).isEqualTo(produtoMock);
		assertThat(produtoResultado.getId()).isEqualTo(produtoMock.getId());
		assertThat(produtoResultado.getCodigo()).isEqualTo(produtoMock.getCodigo());
		assertThat(produtoResultado.getNome()).isEqualTo(produtoMock.getNome());
	}
	
	@Test
	public void isProdutoCadastradoTest() {
		
		when(this.buscaProduto.isCadastrado("1")).thenReturn(true);
		
		ResponseEntity<Boolean> result = this.produtoResource.isCadastrado("1");
		
		Boolean isCadastrado = result.getBody();
		
		assertThat(isCadastrado).isEqualTo(true);
	}
	
	@Test
	public void cadastrarProdutoTest() {
		Produto produtoMock = Produto.builder().id("1")
				.nome("TV").codigo(12345L).descricao("descrição do produto")
				.valor(1280.00d).build();
		
		when(this.gerenciaProduto.cadastrar(produtoMock)).thenReturn(produtoMock);
		
		ResponseEntity<Produto> result = this.produtoResource.cadastrar(produtoMock);
		
		Produto produtoResultado = result.getBody();
		assertThat(produtoResultado).isEqualTo(produtoMock);
		assertThat(produtoResultado.getId()).isEqualTo(produtoMock.getId());
		assertThat(produtoResultado.getCodigo()).isEqualTo(produtoMock.getCodigo());
		assertThat(produtoResultado.getNome()).isEqualTo(produtoMock.getNome());
		
	}
	
	@Test
	public void atualizarProdutoTest() {
		Produto produtoMock = Produto.builder().id("1")
				.nome("TV").codigo(12345L).descricao("descrição do produto")
				.valor(1280.00d).build();
		
		when(this.gerenciaProduto.cadastrar(produtoMock)).thenReturn(produtoMock);
		
		ResponseEntity<Produto> result1 = this.produtoResource.cadastrar(produtoMock);
		
		Produto produtoResultado1 = result1.getBody();
		assertThat(produtoResultado1).isEqualTo(produtoMock);
		assertThat(produtoResultado1.getId()).isEqualTo(produtoMock.getId());
		assertThat(produtoResultado1.getCodigo()).isEqualTo(produtoMock.getCodigo());
		assertThat(produtoResultado1.getNome()).isEqualTo(produtoMock.getNome());
		
		produtoMock.setNome("TV - 50 Polegadas");
		
		when(this.gerenciaProduto.atualizar(produtoMock)).thenReturn(produtoMock);
		
		ResponseEntity<Produto> result2 = this.produtoResource.atualizar(produtoMock);
		
		Produto produtoResultado2 = result2.getBody();
		assertThat(produtoResultado2).isEqualTo(produtoMock);
		assertThat(produtoResultado2.getId()).isEqualTo(produtoMock.getId());
		assertThat(produtoResultado2.getCodigo()).isEqualTo(produtoMock.getCodigo());
		assertThat(produtoResultado2.getNome()).isEqualTo(produtoMock.getNome());
	}
	
	@Test
	public void excluirProdutoTest() {
		Produto produtoMock = Produto.builder().id("1")
				.nome("TV").codigo(12345L).descricao("descrição do produto")
				.valor(1280.00d).build();
		
		when(this.gerenciaProduto.cadastrar(produtoMock)).thenReturn(produtoMock);
		
		ResponseEntity<Produto> result = this.produtoResource.cadastrar(produtoMock);
		
		Produto produtoResultado = result.getBody();
		assertThat(produtoResultado).isEqualTo(produtoMock);
		assertThat(produtoResultado.getId()).isEqualTo(produtoMock.getId());
		assertThat(produtoResultado.getCodigo()).isEqualTo(produtoMock.getCodigo());
		
		doNothing().when(this.gerenciaProduto).remover(produtoMock.getId());
		
		ResponseEntity<String> resultOfExclusion = this.produtoResource.remover(produtoMock.getId());
		
		String textOfExclusion = resultOfExclusion.getBody();
		assertThat(textOfExclusion).isEqualTo("Removido com sucesso!");
	}
}
