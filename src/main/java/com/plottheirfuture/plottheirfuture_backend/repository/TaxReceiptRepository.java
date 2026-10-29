package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.TaxReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxReceiptRepository extends JpaRepository<TaxReceipt, Long> {

    Optional<TaxReceipt> findByReceiptNo(String receiptNo);

    Optional<TaxReceipt> findByContributionId(Long contributionId);
}
