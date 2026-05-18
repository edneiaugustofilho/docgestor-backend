package br.com.ednei.docgestorbackend.dto;

import jakarta.validation.constraints.NotBlank;

public record DocumentoRequest(

        @NotBlank(message = "Tipo do documento é obrigatório")
        String tipo,

        @NotBlank(message = "Nome do documento é obrigatório")
        String nome

) {
}