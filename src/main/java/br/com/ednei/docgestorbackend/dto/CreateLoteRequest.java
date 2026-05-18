package br.com.ednei.docgestorbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateLoteRequest(

        @NotBlank(message = "Operador é obrigatório")
        String operador,

        @NotBlank(message = "Processo é obrigatório")
        String processo,

        @Valid
        @NotEmpty(message = "O lote deve conter ao menos um documento")
        List<DocumentoRequest> documentos

) {
}