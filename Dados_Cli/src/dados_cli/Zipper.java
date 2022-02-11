package dados_cli;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper{
    static final int TAMANHO_BUFFER = 409;

     public void compactarParaZip(String arqSaida,String arqEntrada) throws IOException{
     int cont;
     
     byte[] dados = new byte[TAMANHO_BUFFER];
     BufferedInputStream origem = null;
       FileInputStream streamDeEntrada = null;
       FileOutputStream destino = null;
       ZipOutputStream saida = null;
       ZipEntry entry = null;
       
       try {
            destino = new FileOutputStream(arqSaida);
            saida = new ZipOutputStream(destino);
            File file = new File(arqEntrada);
            streamDeEntrada = new FileInputStream(file);
            origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
            entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);

            while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                saida.write(dados, 0, cont);
            }
            saida.closeEntry();
            origem.close();
            saida.close();
        } catch(IOException e) {
            throw new IOException(e.getMessage());
        }
       
     }  
    
}
