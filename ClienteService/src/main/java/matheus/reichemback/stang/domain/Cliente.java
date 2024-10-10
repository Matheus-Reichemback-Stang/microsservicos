package matheus.reichemback.stang.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
	
	@Id
	private String id;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotNull
	@Indexed(unique = true, background = true)
	private Long cpf;
	
	@NotNull
	private Long tel;
	
	@NotNull
	@Size(min=7, max= 50)
	@Pattern(regexp = ".+@.+\\..+", message = "Email inválido")
	@Indexed(unique = true, background = true)
	private String email;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String endereco;
	
	@NotNull
	private Integer numero;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String cidade;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String estado;
	

}
