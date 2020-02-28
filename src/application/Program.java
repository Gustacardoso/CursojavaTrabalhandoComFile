package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		/*Fazer um programa para ler o caminho de um 
		 * arquivo .csv contend os dados de itens vendidos.
		 * Cada item possui um nome, preço unitario e quantidade,
		 * separados por virgula. Você deve gerar um novo arquivo
		 * chamando "summary.csv",localizado em um subpasta chamada
		 * "out" a partir da pasta original do  arquivo de origem, 
		 * contendo apenas o nome eo valor total para aquele item
		 * (preço unitario multiplicado pela quantidade), Conforme 
		 * o exemplo.
		 * */
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
        
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine();
		
		File souceFile = new File(sourceFileStr);
		String sourceFolderStr = souceFile.getParent();
		
		String targetFileStr = sourceFolderStr + "\\out\\sumary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(souceFile))){
			
			 String itemCsv = br.readLine();
			 while(itemCsv != null) {
				 
				 String[] fields = itemCsv.split(",");
				 String name = fields[0];
				 double price = Double.parseDouble(fields[1]);
				 int quantity = Integer.parseInt(fields[2]);
				 
				 list.add(new Product(name, price, quantity));
				 
				 itemCsv = br.readLine();
			 }
			 
		  try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
			    
			      for(Product item : list) {
			    	  bw.write(item.getName() + "," + String.format("%.2f",item.total()));
			    	  bw.newLine();
			      }
			      
			     System.out.println(targetFileStr + "CREATED!");
		  }catch (Exception e) {
			// TODO: handle exception
			  System.out.println("Error writing file:" + e.getMessage());
		}
		  
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error reading file: " +e.getMessage());
		}
		
		sc.close();
		
	}

}
