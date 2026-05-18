package br.com.ednei.docgestorbackend.entity;

import br.com.ednei.docgestorbackend.enums.LoteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_lote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operador;

    @Column(nullable = false)
    private String processo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoteStatus status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(
            mappedBy = "lote",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Documento> documentos = new ArrayList<>();

}