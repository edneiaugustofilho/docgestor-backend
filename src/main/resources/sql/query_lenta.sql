SELECT l.operador, COUNT(d.id) as total_docs
FROM tb_lote l
         JOIN tb_documento d ON d.lote_id = l.id
WHERE d.tipo = 'RG'
  AND l.status = 'PENDENTE'
  AND l.data_criacao > NOW() - INTERVAL '90 days'
GROUP BY l.operador
ORDER BY total_docs DESC;

/*
DIAGNÓSTICO:

A consulta provavelmente está lenta porque a tabela tb_documento possui um
volume muito grande de registros (8 milhões) e os filtros usados na busca
(tipo, status e data) podem estar sendo executados sem índices adequados.
Isso faz o banco percorrer muitos registros antes de encontrar os dados.

Eu manteria os índices em:
    tb_documento:
        id,
        lote_id,
        tipo

    tb_lote:
        id,
        operador,
        processo,
        status,
        data_criacao


Se viável, criaria uma tabela tb_operador para substituir o atributo operador de tb_lote.
*/