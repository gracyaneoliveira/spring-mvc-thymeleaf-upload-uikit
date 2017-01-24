package com.projeto.wine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.wine.dto.FotoDTO;
import com.projeto.wine.storage.FotoStorage;
import com.projeto.wine.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@PostMapping
	public DeferredResult<List<FotoDTO>> upload(@RequestParam("files[]") MultipartFile[] files){
		DeferredResult<List<FotoDTO>> resultado = new DeferredResult<>();
		System.out.println(files.length);
		Thread thread = new Thread(new FotoStorageRunnable(files,resultado, fotoStorage));
		thread.start();
		
		return resultado;
	}
	
	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nome){
		return fotoStorage.recuperarFotoTemporaria(nome);
	}
	
}