package com.plottheirfuture.plottheirfuture_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tax_receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contribution_id", nullable = false, unique = true)
    private Long contributionId;

    @Column(name = "receipt_no", nullable = false, unique = true)
    private String receiptNo;

    @Column(name = "exempt_clause")
    @Builder.Default
    private String exemptClause = "Section 56(2)(x) of Indian Income Tax Act";

    @Column(name = "digital_signature_hash")
    private String digitalSignatureHash;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(name = "generated_at")
    @Builder.Default
    private LocalDateTime generatedAt = LocalDateTime.now();
}
