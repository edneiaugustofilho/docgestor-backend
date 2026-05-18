package br.com.ednei.docgestorbackend.service;

import br.com.ednei.docgestorbackend.dto.CreateLoteRequest;
import br.com.ednei.docgestorbackend.dto.DocumentoResponse;
import br.com.ednei.docgestorbackend.dto.LoteResponse;
import br.com.ednei.docgestorbackend.entity.Documento;
import br.com.ednei.docgestorbackend.entity.Lote;
import br.com.ednei.docgestorbackend.enums.LoteStatus;
import br.com.ednei.docgestorbackend.repository.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoteService {

    private final LoteRepository loteRepository;

    public LoteResponse criar(CreateLoteRequest request) {
        Lote lote = Lote.builder()
                .operador(request.operador())
                .processo(request.processo())
                .status(LoteStatus.PENDENTE)
                .dataCriacao(LocalDateTime.now()).build();

        request.documentos().forEach(documentoRequest -> {
            Documento documento = Documento.builder()
                    .tipo(documentoRequest.tipo())
                    .nome(documentoRequest.nome())
                    .lote(lote).build();

            lote.getDocumentos().add(documento);
        });

        return toResponse(loteRepository.save(lote));
    }

    public Page<LoteResponse> listar(LoteStatus status, String operador, Pageable pageable) {
        Page<Lote> lotes;

        if (status != null && operador != null && !operador.isBlank()) {
            lotes = loteRepository.findByStatusAndOperadorContainingIgnoreCase(status, operador, pageable);
        } else if (status != null) {
            lotes = loteRepository.findByStatus(status, pageable);
        } else if (operador != null && !operador.isBlank()) {
            lotes = loteRepository.findByOperadorContainingIgnoreCase(operador, pageable);
        } else {
            lotes = loteRepository.findAll(pageable);
        }

        return lotes.map(this::toResponse);
    }

    private LoteResponse toResponse(Lote lote) {
        return new LoteResponse(lote.getId(), lote.getOperador(), lote.getProcesso(), lote.getStatus(), lote.getDataCriacao(), lote.getDocumentos().stream().map(documento -> new DocumentoResponse(documento.getId(), documento.getTipo(), documento.getNome())).toList());
    }
}