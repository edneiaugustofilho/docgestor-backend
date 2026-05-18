package br.com.ednei.docgestorbackend.controller;

import br.com.ednei.docgestorbackend.dto.CreateLoteRequest;
import br.com.ednei.docgestorbackend.dto.LoteResponse;
import br.com.ednei.docgestorbackend.dto.UpdateLoteStatusRequest;
import br.com.ednei.docgestorbackend.enums.LoteStatus;
import br.com.ednei.docgestorbackend.service.LoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService loteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoteResponse criar(@Valid @RequestBody CreateLoteRequest request) {
        return loteService.criar(request);
    }

    @GetMapping
    public Page<LoteResponse> listar(@RequestParam(required = false) LoteStatus status,
                                     @RequestParam(required = false) String operador,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return loteService.listar(status, operador, PageRequest.of(page, size));
    }

    @PatchMapping("/{id}/status")
    public LoteResponse atualizarStatus(@PathVariable Long id,
                                        @Valid @RequestBody UpdateLoteStatusRequest request) {
        return loteService.atualizarStatus(id, request);
    }
}