package com.fatec.bd.tracefinder.restapi.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fatec.bd.tracefinder.restapi.model.DocumentoInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentInfoController {
    @Autowired
	JdbcTemplate jdbcTemplate;

    @GetMapping("/codelist")
    public ResponseEntity<List<DocumentoInfo>> listDocuments() {
        try {
            String sql = "SELECT doc_id, doc_name, doc_parent_id, doc_lastmodified, doc_type, LEVEL FROM documento_info START WITH doc_id = 1 CONNECT BY PRIOR doc_id = doc_parent_id";

            List<DocumentoInfo> response = jdbcTemplate.query(sql, new ResultSetExtractor<List<DocumentoInfo>>() {
                @Override
                public List<DocumentoInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    List<DocumentoInfo> documentos = new ArrayList<>();
                    int rootId = 0;
                    while(rs.next()) {
                        if (documentos.isEmpty()) {
                            documentos.add(new DocumentoInfo(
                                rs.getInt("doc_id"),
                                rs.getString("doc_name"),
                                rs.getDate("doc_lastmodified"),
                                rs.getString("doc_type")
                            ));
                        } else {
                            DocumentoInfo documento = documentos.get(rootId);

                            traverse(documento, rs);
                        }
                    }
                    return documentos;
                }
            });
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            System.out.println(e.getStackTrace()[0]);
            return ResponseEntity.badRequest().build();
        }
    }

    public void traverse(DocumentoInfo documento, ResultSet rs) throws SQLException {
        if (documento.getDocId() != rs.getInt("doc_parent_id")) {
            if (!documento.getDocChildren().isEmpty()) {
                for (DocumentoInfo childDoc : documento.getDocChildren()) {
                    traverse(childDoc, rs);         
                }
            }
        } else {
            DocumentoInfo newDocumento = new DocumentoInfo(
                                            rs.getInt("doc_id"), 
                                            rs.getString("doc_name"),
                                            rs.getDate("doc_lastmodified"),
                                            rs.getString("doc_type"));
            newDocumento.setDocParentId(documento.getDocParentId());
            documento.getDocChildren().add(newDocumento);
        }
    }
}
