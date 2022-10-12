package com.example.rewardyourteachersq011bjavapode.repository;


import com.example.rewardyourteachersq011bjavapode.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findWalletByUserEmail(String email);

    Optional<Wallet> findWalletByUserId(Long id);




}
