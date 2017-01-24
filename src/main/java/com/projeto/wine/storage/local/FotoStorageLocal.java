package com.projeto.wine.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.projeto.wine.storage.FotoStorage;


public class FotoStorageLocal implements FotoStorage{
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal(){
		this(getDefault().getPath(System.getenv("USERPROFILE"), ".brewerfotos"));
	}
	
	public FotoStorageLocal(Path path){
		this.local = path;
		criarPastas();
	}
	
	@Override
	public String salvarTemporariamente(MultipartFile file) {
		String novoNome = null;
		
		if(file !=null){
			MultipartFile arquivo  = file;
			
			//Funciona No Edge, Crhome e Firefox
			File nomeArquivo = new File(arquivo.getOriginalFilename());
			novoNome = renomearArquivo(nomeArquivo.getName());
			
			//Funciona somente Chrome e Firefox
			//novoNome = renomearArquivo(arquivo.getOriginalFilename()); 
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString()+ getDefault().getSeparator()
						+ novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária",e);
			}
		}
		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto a temporária",e); 
		}
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto",e);
		}
		
	}
	
	private String renomearArquivo(String nomeOriginal){
		String novoNome = UUID.randomUUID().toString()+"_" + nomeOriginal;
		return novoNome;
	}

}

