package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Produto;
import entities.ProdutoImportado;
import entities.ProdutoUsado;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<Produto> produtos = new ArrayList<>();

		System.out.println("Digite o número de produtos: ");
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			System.out.println("Dados do produto #" + (i + 1));
			System.out.println("Comum, Usado ou Importado (c/u/i)?");
			char codigo = sc.next().charAt(0);

			sc.nextLine();
			System.out.print("Nome: ");
			String nome = sc.nextLine();
			System.out.print("Preco: ");
			double preco = sc.nextDouble();

			Produto p = null;
			switch (codigo) {
			case 'i':
				System.out.print("Taxa alfandegária: ");
				double taxa = sc.nextDouble();
				p = new ProdutoImportado(nome, preco, taxa);
				break;
			case 'u':
				sc.nextLine();
				System.out.print("Data de fabrição (DD/MM/AAAA): ");
				String data = sc.next();
				p = new ProdutoUsado(nome, preco, LocalDate.parse(data, formato));
				break;
			case 'c':
				p = new Produto(nome, preco);
				break;
			default:
				break;
			}

			produtos.add(p);
		}
		System.out.println("ETIQUETAS DE PRODUTO: ");
		for (Produto p : produtos) {
			System.out.println(p.etiqueta());
		}
		sc.close();
	}
}
