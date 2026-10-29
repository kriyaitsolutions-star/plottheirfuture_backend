package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.TaxReceipt;
import com.plottheirfuture.plottheirfuture_backend.service.TaxReceiptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tax-receipts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaxReceiptController {

    private final TaxReceiptService taxReceiptService;

    @GetMapping
    public ResponseEntity<List<TaxReceipt>> getAllTaxReceipts() {
        return ResponseEntity.ok(taxReceiptService.getAllTaxReceipts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxReceipt> getTaxReceiptById(@PathVariable Long id) {
        return ResponseEntity.ok(taxReceiptService.getTaxReceiptById(id));
    }

    @GetMapping("/by-number")
    public ResponseEntity<TaxReceipt> getByReceiptNo(@RequestParam String receiptNo) {
        return ResponseEntity.ok(taxReceiptService.getByReceiptNo(receiptNo));
    }

    @GetMapping("/by-contribution/{contributionId}")
    public ResponseEntity<TaxReceipt> getByContributionId(@PathVariable Long contributionId) {
        return ResponseEntity.ok(taxReceiptService.getByContributionId(contributionId));
    }

    @PostMapping("/generate")
    public ResponseEntity<TaxReceipt> generateReceipt(@RequestBody Map<String, String> payload) {
        Long contributionId = Long.parseLong(payload.get("contributionId"));
        String pdfUrl = payload.get("pdfUrl");
        return ResponseEntity.status(HttpStatus.CREATED).body(taxReceiptService.generateReceipt(contributionId, pdfUrl));
    }

    @PostMapping
    public ResponseEntity<TaxReceipt> createTaxReceipt(@Valid @RequestBody TaxReceipt receipt) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taxReceiptService.createTaxReceipt(receipt));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxReceipt> updateTaxReceipt(@PathVariable Long id, @RequestBody TaxReceipt receipt) {
        return ResponseEntity.ok(taxReceiptService.updateTaxReceipt(id, receipt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxReceipt(@PathVariable Long id) {
        taxReceiptService.deleteTaxReceipt(id);
        return ResponseEntity.noContent().build();
    }
}
