package com.example.rewardyourteachersq011bjavapode.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record FundWalletDto(@NotNull BigDecimal amount) {
}
