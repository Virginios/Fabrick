package com.project.fabrickinterview.infrastructure.repository;

import com.project.fabrickinterview.infrastructure.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
}
