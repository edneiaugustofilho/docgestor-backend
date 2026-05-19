package br.com.ednei.docgestorbackend.service;

import br.com.ednei.docgestorbackend.dto.CreateLoteRequest;
import br.com.ednei.docgestorbackend.dto.DocumentoRequest;
import br.com.ednei.docgestorbackend.dto.LoteResponse;
import br.com.ednei.docgestorbackend.dto.UpdateLoteStatusRequest;
import br.com.ednei.docgestorbackend.entity.Lote;
import br.com.ednei.docgestorbackend.enums.LoteStatus;
import br.com.ednei.docgestorbackend.exception.BusinessException;
import br.com.ednei.docgestorbackend.exception.ResourceNotFoundException;
import br.com.ednei.docgestorbackend.repository.LoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoteServiceTest {

    @Mock
    private LoteRepository loteRepository;

    @InjectMocks
    private LoteService loteService;

    @Test
    void deveCriarLoteComStatusPendente() {
        CreateLoteRequest request =
                new CreateLoteRequest(
                        "joao",
                        "ABERTURA_CONTA",
                        List.of(
                                new DocumentoRequest(
                                        "RG",
                                        "rg.pdf"
                                )
                        )
                );

        Lote salvo = Lote.builder()
                .id(1L)
                .operador("joao")
                .processo("ABERTURA_CONTA")
                .status(LoteStatus.PENDENTE)
                .dataCriacao(LocalDateTime.now())
                .build();

        when(loteRepository.save(any())).thenReturn(salvo);

        LoteResponse response = loteService.criar(request);

        assertNotNull(response);

        assertEquals(LoteStatus.PENDENTE, response.status());

        verify(loteRepository).save(any());
    }

    @Test
    void deveAtualizarStatus() {
        Lote lote =
                Lote.builder()
                        .id(1L)
                        .operador("joao")
                        .processo("teste")
                        .status(LoteStatus.PENDENTE)
                        .dataCriacao(LocalDateTime.now())
                        .build();

        when(loteRepository.findById(1L)).thenReturn(Optional.of(lote));

        when(loteRepository.save(any())).thenReturn(lote);

        LoteResponse response = loteService.atualizarStatus(1L,
                        new UpdateLoteStatusRequest(LoteStatus.REJEITADO));

        assertEquals(LoteStatus.REJEITADO, response.status());
    }

    @Test
    void deveLancarErroQuandoLoteNaoExiste() {
        when(loteRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> loteService.atualizarStatus(
                        999L,
                        new UpdateLoteStatusRequest(
                                LoteStatus.EXPORTADO
                        )
                )
        );
    }

    @Test
    void deveLancarErroQuandoLoteExportadoReceberAlteracao() {
        Lote lote =
                Lote.builder()
                        .id(1L)
                        .status(LoteStatus.EXPORTADO)
                        .dataCriacao(LocalDateTime.now())
                        .build();

        when(loteRepository.findById(1L)).thenReturn(Optional.of(lote));

        assertThrows(
                BusinessException.class,
                () -> loteService.atualizarStatus(
                        1L,
                        new UpdateLoteStatusRequest(
                                LoteStatus.REJEITADO
                        )
                )
        );

        verify(loteRepository, never()).save(any());
    }

}