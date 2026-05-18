-- Relatório gerencial:

SELECT l.operador,
       COUNT(DISTINCT l.id)                                             AS total_lotes,
       COUNT(d.id)                                                      AS total_documentos,
       COUNT(DISTINCT l.id) FILTER (WHERE l.status = 'PENDENTE')        AS total_pendente,
       COUNT(DISTINCT l.id) FILTER (WHERE l.status = 'EXPORTADO')       AS total_exportado,
       COUNT(DISTINCT l.id) FILTER (WHERE l.status = 'REJEITADO')       AS total_rejeitado,
       ROUND(COUNT(d.id)::NUMERIC / NULLIF(COUNT(DISTINCT l.id), 0), 2) AS media_documentos_por_lote
FROM tb_lote l
         LEFT JOIN tb_documento d ON d.lote_id = l.id
WHERE l.data_criacao >= NOW() - INTERVAL '30 days'
GROUP BY l.operador
ORDER BY total_lotes DESC;