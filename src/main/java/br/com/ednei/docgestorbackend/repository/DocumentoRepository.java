package br.com.ednei.docgestorbackend.repository;

import br.com.ednei.docgestorbackend.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}