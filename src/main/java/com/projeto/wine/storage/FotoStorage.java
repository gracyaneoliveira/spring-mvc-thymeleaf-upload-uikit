package com.projeto.wine.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	public String salvarTemporariamente(MultipartFile file);

	public byte[] recuperarFotoTemporaria(String nome);

}
