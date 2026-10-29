package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.Contribution;
import com.plottheirfuture.plottheirfuture_backend.entity.PaymentStatus;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.ContributionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributionService {

    private final ContributionRepository contributionRepository;

    public List<Contribution> getAllContributions() {
        return contributionRepository.findAll();
    }

    public Contribution getContributionById(Long id) {
        return contributionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution not found with id: " + id));
    }

    public List<Contribution> getContributionsByWeddingId(Long weddingId) {
        return contributionRepository.findByWeddingId(weddingId);
    }

    public List<Contribution> getSuccessfulContributions(Long weddingId) {
        return contributionRepository.findByWeddingIdAndPaymentStatus(weddingId, PaymentStatus.SUCCESS);
    }

    @Transactional
    public Contribution createContribution(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    @Transactional
    public Contribution updateContribution(Long id, Contribution updated) {
        Contribution existing = getContributionById(id);

        if (updated.getUnitsSft() != null) existing.setUnitsSft(updated.getUnitsSft());
        if (updated.getAmount() != null) existing.setAmount(updated.getAmount());
        if (updated.getTxnId() != null) existing.setTxnId(updated.getTxnId());
        if (updated.getBankRef() != null) existing.setBankRef(updated.getBankRef());
        if (updated.getPaymentStatus() != null) existing.setPaymentStatus(updated.getPaymentStatus());
        if (updated.getIsAnonymous() != null) existing.setIsAnonymous(updated.getIsAnonymous());
        if (updated.getBlessingMessage() != null) existing.setBlessingMessage(updated.getBlessingMessage());

        return contributionRepository.save(existing);
    }

    @Transactional
    public Contribution confirmPayment(Long id, String txnId, String bankRef) {
        Contribution existing = getContributionById(id);
        existing.setTxnId(txnId);
        existing.setBankRef(bankRef);
        existing.setPaymentStatus(PaymentStatus.SUCCESS);
        return contributionRepository.save(existing);
    }

    @Transactional
    public void deleteContribution(Long id) {
        if (!contributionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contribution not found with id: " + id);
        }
        contributionRepository.deleteById(id);
    }
}
