package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.TaxReceipt;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.TaxReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaxReceiptService {

    private final TaxReceiptRepository taxReceiptRepository;

    public List<TaxReceipt> getAllTaxReceipts() {
        return taxReceiptRepository.findAll();
    }

    public TaxReceipt getTaxReceiptById(Long id) {
        return taxReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tax receipt not found with id: " + id));
    }

    public TaxReceipt getByReceiptNo(String receiptNo) {
        return taxReceiptRepository.findByReceiptNo(receiptNo)
                .orElseThrow(() -> new ResourceNotFoundException("Tax receipt not found with receipt number: " + receiptNo));
    }

    public TaxReceipt getByContributionId(Long contributionId) {
        return taxReceiptRepository.findByContributionId(contributionId)
                .orElseThrow(() -> new ResourceNotFoundException("Tax receipt not found for contribution id: " + contributionId));
    }

    @Transactional
    public TaxReceipt generateReceipt(Long contributionId, String pdfUrl) {
        String receiptNo = "PTF-SEC-" + System.currentTimeMillis() % 1000000;
        String signatureHash = UUID.randomUUID().toString().replace("-", "");

        TaxReceipt receipt = TaxReceipt.builder()
                .contributionId(contributionId)
                .receiptNo(receiptNo)
                .exemptClause("Section 56(2)(x) of Indian Income Tax Act")
                .digitalSignatureHash(signatureHash)
                .pdfUrl(pdfUrl)
                .build();

        return taxReceiptRepository.save(receipt);
    }

    @Transactional
    public TaxReceipt createTaxReceipt(TaxReceipt receipt) {
        return taxReceiptRepository.save(receipt);
    }

    @Transactional
    public TaxReceipt updateTaxReceipt(Long id, TaxReceipt updated) {
        TaxReceipt existing = getTaxReceiptById(id);

        if (updated.getReceiptNo() != null) existing.setReceiptNo(updated.getReceiptNo());
        if (updated.getExemptClause() != null) existing.setExemptClause(updated.getExemptClause());
        if (updated.getDigitalSignatureHash() != null) existing.setDigitalSignatureHash(updated.getDigitalSignatureHash());
        if (updated.getPdfUrl() != null) existing.setPdfUrl(updated.getPdfUrl());

        return taxReceiptRepository.save(existing);
    }

    @Transactional
    public void deleteTaxReceipt(Long id) {
        if (!taxReceiptRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tax receipt not found with id: " + id);
        }
        taxReceiptRepository.deleteById(id);
    }
}
