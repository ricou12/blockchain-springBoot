package com.eric.blockchain.controller;

import com.eric.blockchain.entity.BlockchainEntity;
import com.eric.blockchain.utils.Blockchain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/block-chain")
public class BlockchainController {

    @PostMapping(path="/create-blockchain-return-response-to-string")
    public ResponseEntity<String> addBlockChainAndReturnResponseToString(@RequestBody BlockchainEntity blockchainEntity) {
        Blockchain blockchain = new Blockchain(blockchainEntity.getDifficulty());
        blockchain.addBlock(blockchain.newBlock(blockchainEntity.getMessage()));

        return ResponseEntity.ok(
            "Les données envoyées au format JSON ont été traité par le serveur" +
            "\n La blockchain est elle valide ? : " + blockchain.isBlockChainValid() +
            "\n Détail : " + blockchain
        );
    }


    /* Retourne une response au format json
        Le développement d’API Web avec Spring Boot,
        suppose de produire des réponses dans des formats comme le JSON ou le XML
        la configuration par défaut inclue une prise en charge de ces formats de représentation.
        La désérialisation du document JSON vers l’objet Java sera réalisée par la bibliothèque Jackson.
     */
    @PostMapping(path="/create-blockchain-return-response-to-json", consumes="application/json")
    public ResponseEntity addBlockChainAndReturnResponseToJson(@RequestBody BlockchainEntity blockchainEntity){
        Blockchain blockchain = new Blockchain(blockchainEntity.getDifficulty());
        blockchain.addBlock(blockchain.newBlock(blockchainEntity.getMessage()));
        return ResponseEntity.ok(blockchain);
    }

    // Retourne une response au format Json à partir d'un tableau (cle, valeur)
    @PostMapping(path="/create-blockchain-return-response-to-json-with-map")
    public Map<String, Object> addBlockChainAndReturnResponseToJsonWithMap(@RequestBody BlockchainEntity blockchainEntity){
        Blockchain blockchain = new Blockchain(blockchainEntity.getDifficulty());
        blockchain.addBlock(blockchain.newBlock(blockchainEntity.getMessage()));

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("difficulte", blockchainEntity.getDifficulty());
        map.put("message",  blockchainEntity.getMessage());
        return map;
    }

}
