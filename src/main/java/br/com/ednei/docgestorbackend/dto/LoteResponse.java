package br.com.ednei.docgestorbackend.dto;

import br.com.ednei.docgestorbackend.enums.LoteStatus;

import java.time.LocalDateTime;
import java.util.List;

public record LoteResponse(
        Long id,
        String operador,
        String processo,
        LoteStatus status,
        LocalDateTime dataCriacao,
        List<DocumentoResponse> documentos
) {
}