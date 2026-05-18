package br.com.ednei.docgestorbackend.dto;

import br.com.ednei.docgestorbackend.enums.LoteStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateLoteStatusRequest(

        @NotNull(message = "Status é obrigatório")
        LoteStatus status

) {
}