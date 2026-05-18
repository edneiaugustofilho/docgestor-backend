CREATE TABLE tb_lote (
                         id BIGSERIAL PRIMARY KEY,
                         operador VARCHAR(100) NOT NULL,
                         processo VARCHAR(100) NOT NULL,
                         status VARCHAR(20) NOT NULL,
                         data_criacao TIMESTAMP NOT NULL,

                         CONSTRAINT chk_lote_status
                             CHECK (
                                 status IN (
                                            'PENDENTE',
                                            'EXPORTADO',
                                            'REJEITADO'
                                     )
                                 )
);

CREATE TABLE tb_documento (
                              id BIGSERIAL PRIMARY KEY,
                              lote_id BIGINT NOT NULL,
                              tipo VARCHAR(50) NOT NULL,
                              nome VARCHAR(255) NOT NULL,

                              CONSTRAINT fk_documento_lote
                                  FOREIGN KEY (lote_id)
                                      REFERENCES tb_lote(id)
                                      ON DELETE CASCADE
);

CREATE INDEX idx_tb_lote_status_data_criacao
    ON tb_lote(status,data_criacao);


CREATE INDEX idx_tb_documento_tipo_lote
    ON tb_documento(tipo,lote_id);