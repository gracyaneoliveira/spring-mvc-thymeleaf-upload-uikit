package com.projeto.wine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.wine.model.Vinho;

@Controller
public class VinhosController {

	@RequestMapping("/")
	public ModelAndView index(Vinho vinho){
		return new ModelAndView("vinho/upload");
	}
		
}
