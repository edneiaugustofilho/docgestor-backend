package br.com.ednei.docgestorbackend.repository;

import br.com.ednei.docgestorbackend.entity.Lote;
import br.com.ednei.docgestorbackend.enums.LoteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Long> {

    Page<Lote> findByStatusAndOperadorContainingIgnoreCase(
            LoteStatus status,
            String operador,
            Pageable pageable
    );

    Page<Lote> findByStatus(
            LoteStatus status,
            Pageable pageable
    );

    Page<Lote> findByOperadorContainingIgnoreCase(
            String operador,
            Pageable pageable
    );

}