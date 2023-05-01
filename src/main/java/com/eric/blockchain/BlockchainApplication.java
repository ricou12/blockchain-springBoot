package com.eric.blockchain;

import com.eric.blockchain.utils.Block;
import com.eric.blockchain.utils.Blockchain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.util.Scanner;


@SpringBootApplication
public class BlockchainApplication  implements CommandLineRunner {

	public static void main(String[] args)  {
		SpringApplication.run(BlockchainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception, IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Difficulte: saisir un nombre entre 1 et 10");
		int difficulty = sc.nextInt();

		Blockchain blockchain = new Blockchain(difficulty); // Blockchain blockchain = new Blockchain(4);

		System.out.println("Creation et signature du premier bloc");
		blockchain.addBlock(blockchain.newBlock("Confucius: Exige beaucoup de toi-même et attends peu des autres. Ainsi beaucoup d'ennuis te seront épargnés."));
		System.out.println("Creation et signature du second bloc");
		blockchain.addBlock(blockchain.newBlock("Gandhi: La vie est un mystère qu'il faut vivre, et non un problème à résoudre."));
		System.out.println("Creation et signature du troisieme bloc");
		blockchain.addBlock(blockchain.newBlock("Confucius: Choisissez un travail que vous aimez et vous n'aurez pas à travailler un seul jour de votre vie."));

		System.out.println("La blockchain est elle valide ? : " + blockchain.isBlockChainValid());
		System.out.println("Detail : " + blockchain);

		// Ajout d’un bloc corrompu à notre Blockchain
		// blockchain.addBlock(new Block(15, System.currentTimeMillis(), "aaaabbb", "Block invalide"));
	}


}
