package com.projeto.wine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projeto.wine.storage.FotoStorage;
import com.projeto.wine.storage.local.FotoStorageLocal;

@Configuration
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage(){
		return new FotoStorageLocal();
	}
}