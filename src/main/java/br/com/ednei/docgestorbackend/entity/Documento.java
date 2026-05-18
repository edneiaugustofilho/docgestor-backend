package br.com.ednei.docgestorbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lote_id",
            nullable = false
    )
    private Lote lote;

}