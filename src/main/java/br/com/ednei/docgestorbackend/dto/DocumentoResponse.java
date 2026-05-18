package br.com.ednei.docgestorbackend.dto;

public record DocumentoResponse(
        Long id,
        String tipo,
        String nome
) {
}