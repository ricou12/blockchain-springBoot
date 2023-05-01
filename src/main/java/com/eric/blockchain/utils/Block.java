package com.eric.blockchain.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block {
    private int index;
    private long timestamp;
    private String hash;
    private String previousHash;
    private String data;
    private int nonce;
    private int difficulty;

    public Block(int index, long timestamp, String previousHash, String data) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.data = data;
        nonce = 0;
        hash = Block.calculateHash(this);
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() { return hash; }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public String str() {
        return index + timestamp + previousHash + data + nonce;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Block #").append(index)
                .append("\n [Hash du bloc precedent : ").append(previousHash)
                .append("\n - Horodatage - associe la date et l'heure de la création du bloc : ").append(new Date(timestamp))
                .append("\n - donnees : ").append(data)
                .append("\n - difficulte : ").append(difficulty).append(" \"O\" à la suites en debut de hash")
                .append("\n - minage : ").append(nonce).append(" boucles ont ete necessaires pour generer le hash du nouveau bloc : ").append(hash)
                .append("]");
        return builder.toString();
    }

    public static String calculateHash(Block block) {
        if (block != null) {
            MessageDigest digest = null;

            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }

            String txt = block.str();
            final byte bytes[] = digest.digest(txt.getBytes());
            final StringBuilder builder = new StringBuilder();

            for (final byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    builder.append('0');
                }

                builder.append(hex);
            }

            return builder.toString();
        }

        return null;
    }

    public void mineBlock(int difficulty) {
        this.difficulty = difficulty;
        nonce = 0;

        while (!getHash().substring(0, difficulty).equals(Utils.zeros(difficulty))) {
            nonce++;
            hash = Block.calculateHash(this);
        }
    }
}
