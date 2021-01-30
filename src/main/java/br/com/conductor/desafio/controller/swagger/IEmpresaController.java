package br.com.conductor.desafio.controller.swagger;

import java.util.List;

import org.springframework.http.MediaType;

import br.com.conductor.desafio.entidade.Empresa;
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
@Tag(name = "Empresa", description = "Descrição da API de Empresa")
public interface IEmpresaController {

	@Operation(summary = "Consulta todas as empresas", description = "Recupera todas as empresas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Empresa.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNaoEncontrado.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	List<Empresa> findAll();

	@Operation(summary = "Cadastrar uma empresa.", description = "Cadastra a empresa")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	void create(Empresa empresa);

	@Operation(summary = "Consultar uma empresa pelo identificador.", description = "Consultar a empresa", parameters = @Parameter(name = "id", description = "Identificador da empresa"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Empresa.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNaoEncontrado.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	Empresa findById(Integer id);

	@Operation(summary = "Atualizar os dado de uma empresa.", description = "Atualizar a empresa", parameters = @Parameter(name = "id", description = "Identificador da empresa"))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNaoEncontrado.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	void update(Integer id, Empresa empresa);

	@Operation(summary = "Remover uma empresa.", description = "Remove uma empresa", parameters = @Parameter(name = "id", description = "Identificador da empresa"))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNaoEncontrado.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Erro negocial durante operação", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	void delete(Integer id);

}
