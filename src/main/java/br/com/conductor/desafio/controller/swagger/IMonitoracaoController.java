package br.com.conductor.desafio.controller.swagger;

import br.com.conductor.desafio.entidade.Monitoracao;
import br.com.conductor.desafio.enus.TipoMonitoracao;
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
@Tag(name = "Monitoração", description = "Descrição da API de Monitoração")
public interface IMonitoracaoController {
	
	 @Operation(summary = "Apresenta o monitoramento dos containers dockers.", description = "Monitoramento dos recursos dos containers")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso!", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Monitoramento.class)))),
	        @ApiResponse(responseCode = "400", description = "Erro negocial durante operação", 
         content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)))),
	        @ApiResponse(responseCode = "500", description = "Erro interno do sistema", 
         content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class))))  })	
	 Monitoracao findAll();
	 
	 @Operation(summary = "Apresenta o monitoramento de um determinado recurso do container docker.", description = "Monitoramento do recurso do container docker")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso!", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.Monitoramento.class)))),
	        @ApiResponse(responseCode = "400", description = "Erro negocial durante operação", 
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoNegocio.class)))),
	        @ApiResponse(responseCode = "500", description = "Erro interno do sistema", 
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = br.com.conductor.desafio.controller.swagger.RetornoInterno.class)))) })	
	 Monitoracao findByTipoMonitoracao(TipoMonitoracao tipoMonitoracao);
}
