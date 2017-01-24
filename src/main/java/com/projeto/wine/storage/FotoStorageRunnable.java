package com.projeto.wine.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.wine.dto.FotoDTO;


public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files; 
	private DeferredResult<List<FotoDTO>> resultado;
	private FotoStorage fotoStorage;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<List<FotoDTO>> resultado, FotoStorage fotoStorage) {
		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
	}

	@Override
	public void run() {
			List<FotoDTO> fotos = new ArrayList<>(); 
			
			for(int i = 0; i<this.files.length; i++){
				String nomeFoto = this.fotoStorage.salvarTemporariamente(this.files[i]);
				String contentType = this.files[i].getContentType();
				
				fotos.add(new FotoDTO(nomeFoto, contentType));
			}
			resultado.setResult(fotos);
			
			System.out.println(">>> Total de arquivos: " + files.length);
		}

}
