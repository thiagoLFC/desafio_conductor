package br.com.conductor.desafio.controller.swagger;

import java.util.List;

import org.springframework.http.MediaType;

import br.com.conductor.desafio.entidade.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Interface para maperar as anotacoes do swagger
 * 
 * @author thiag
 *
 */
@Tag(name = "Cliente", description = "Descrição da API de Cliente")
public interface IClienteController {

	@Operation(summary = "Consultar um cliente pelo identificador.", description = "Consultar um cliente específico", parameters = @Parameter(name = "id", description = "Identificador do cliente"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Cliente.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNaoEncontrado.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	Cliente findById(Integer id);

	@Operation(summary = "Atualizar os dado de um cliente.", description = "Atualizar um cliente específico", parameters = @Parameter(name = "id", description = "Identificador do cliente"))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNaoEncontrado.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	void update(Integer id, Cliente cliente);

	@Operation(summary = "Remover um cliente.", description = "Remove um cliente em específico.", parameters = @Parameter(name = "id", description = "Identificador do cliente"))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	void delete(Integer id);

	@Operation(summary = "Consulta todos os clientes", description = "Recupera todos os clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Cliente.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNaoEncontrado.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	List<Cliente> findAll();

	@Operation(summary = "Cadastrar um cliente.", description = "Cadastra o cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	void create(Cliente cliente);

}
