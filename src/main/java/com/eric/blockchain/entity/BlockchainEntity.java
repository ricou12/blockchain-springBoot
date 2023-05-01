package com.eric.blockchain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BlockchainEntity {
    private int difficulty;
    private  String message;
}
