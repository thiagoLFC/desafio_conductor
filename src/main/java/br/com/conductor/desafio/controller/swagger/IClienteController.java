package br.com.conductor.desafio.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.conductor.desafio.entidade.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Interface para maperar as anotacoes do swagger
 * @author thiag
 *
 */
@Tag(name = "Cliente", description = "Descrição da API de Cliente")
public interface IClienteController {
	
	 @Operation(summary = "Consulta todos os clientes", description = "Recupera todos os clientes")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso!", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Cliente.class)))),
	        @ApiResponse(responseCode = "400", description = "Erro negocial durante operação", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)))),
	        @ApiResponse(responseCode = "500", description = "Erro interno do sistema", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)))) })	
	 ResponseEntity<Object> findAll();
	 
	 @Operation(summary = "Cadastrar um cliente.", description = "Cadastra o cliente")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso!"),
	        @ApiResponse(responseCode = "400", description = "Erro negocial durante operação", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)))),
	        @ApiResponse(responseCode = "500", description = "Erro interno do sistema", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)))) })	
	 ResponseEntity<Object> create(Cliente cliente);
	 
	 @Operation(summary = "Consultar um cliente pelo identificador.", description = "Consultar um cliente")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso!"),
	        @ApiResponse(responseCode = "400", description = "Erro negocial durante operação", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)))),
	        @ApiResponse(responseCode = "500", description = "Erro interno do sistema", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)))) })
	 ResponseEntity<Object> findById(Integer id);
	 
	 @Operation(summary = "Atualizar os dado de um cliente.", description = "Atualizar o cliente")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso!", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Cliente.class)))),
	        @ApiResponse(responseCode = "400", description = "Erro negocial durante operação", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)))),
	        @ApiResponse(responseCode = "500", description = "Erro interno do sistema", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class))))})
	 ResponseEntity<Object> update(Integer id, Cliente cliente);

}
