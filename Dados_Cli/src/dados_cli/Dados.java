package dados_cli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Dados {

	private String nome;
	private String sobre;
	private Zipper zip;
	private String nasc;
	private String numero;
	Date data = new Date();
	SimpleDateFormat formatar = new SimpleDateFormat("yyyyMMdd_hhmmss");
	Date nascimento = new Date();
	SimpleDateFormat fornasc = new SimpleDateFormat("dd/mm/yyyy");

	public void status() {
		System.out.println("______________________");
		System.out.print("Nome: " + nome);
		System.out.println(" " + sobre);
		System.out.println("Data de nascimento: " + nasc);
		System.out.println("Numero de telefone: " + numero);
		System.out.println(this.dataFormatada);

	}

	String dataFormatada = formatar.format(data);

	public String salvar() {

		// public OutputStreamWriter(OutputStream out, String charsetName)
		try {
			File sf = new File(obterNomeArquivo());
			FileOutputStream sa = new FileOutputStream(sf);
			OutputStreamWriter buffer = new OutputStreamWriter(sa);
			buffer.write(obterConteudo());
			buffer.close();
		} catch (IOException fn) {
			System.out.println("Arquivo não detectado.");
		}

		return null;
	}

	public String obterNomeArquivo() {
		String nomeArquivo = "C:\\Users\\NoteBookSamsung\\Desktop\\" + this.nome + "_" + this.sobre + "_"
				+ this.dataFormatada + ".txt";

		return nomeArquivo;
	}

	public String obterArquivoZip() {
		String nomeArquivo = "C:\\Users\\NoteBookSamsung\\Desktop\\" + this.nome + "_" + this.sobre + "_"
				+ this.dataFormatada + ".zip";

		return nomeArquivo;
	}

	public void lerParametros() throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		preencherNome(sc);
		sobrenome(sc);
		nascimento(sc);
		telefone(sc);
		sc.close();
		salvar();
		status();
		zip = new Zipper();
		zip.compactarParaZip(obterArquivoZip(), obterNomeArquivo());
		File arqAntigo = new File(obterNomeArquivo());
		arqAntigo.delete();
	}

	public void preencherNome(Scanner sc) {
		System.out.println("Escreva o seu nome: ");
		this.nome = sc.nextLine();
	}

	public void sobrenome(Scanner sc) {
		System.out.println("Escreva seu sobrenome: ");
		this.sobre = sc.nextLine();
	}

	public void nascimento(Scanner sc) {
		boolean dataB = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = new Date();

		while (dataB == false) {
			System.out.println("Escreva sua data de nascimento:");
			System.out.println("obs: separe a data por barras dia/mes/ano.");
			this.nasc = sc.nextLine();

			try {
				dataNascimento = sdf.parse(this.nasc);
				dataB = true;
			} catch (java.text.ParseException exception) {
				System.out.println("Digitado de maneira incorreta, tente novamente:");
			}

		}

	}

	public void telefone(Scanner sc) throws ParseException {

		System.out.println("Digite o seu telefone: ");
		System.out.println("Coloque o DDD entre parenteses: Ex.(DDD)000000000");
		numero = sc.nextLine();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public String getNasc() {
		return nasc;
	}

	public void setNasc(String nasc) {
		this.nasc = nasc;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	private String obterConteudo() {
		StringBuilder pw = new StringBuilder().append("______________________").append("\n")
				.append("nome: " + this.nome).append(" " + this.sobre).append("\n")
				.append("Data de nascimento: " + this.nasc).append("\n").append("Numero de telefone: " + this.numero);
		return pw.toString();
	}

}
