package com.viride.app.virideapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.viride.app.virideapp.VirideException;
import com.viride.app.virideapp.entity.Beer;
import com.viride.app.virideapp.service.BeerService;

@RestController
@RequestMapping("/api")
public class VirideController {
	@Autowired
	BeerService service;

	@GetMapping("/beers")
	public List<Beer> getAllBeers() {
		return service.getAllBeers();
	}

	@GetMapping("/beer/{brewery_id}")
	public Beer getBeerById(@PathVariable String brewery_id) {
		try {
			return service.getBeerByID(brewery_id);
		} catch (VirideException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id Not Found", e);
		}
	}

	@PostMapping("/beer")
	public void addBeer(@RequestBody Beer b) {
		service.addBeer(b);
	}

	@PutMapping("/beer/{brewery_id}")
	public void updateBeer(@PathVariable String brewery_id, @RequestBody Beer b) {
		try {
			service.updateBeer(brewery_id, b);
		} catch (VirideException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id Not Found", e);
		}
	}

	@DeleteMapping("/beer/{brewery_id}")
	public void deleteBeer(@PathVariable String brewery_id) {
		try {
			service.deleteBeer(brewery_id);
		} catch (VirideException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id Not Found", e);
		}
	}
}
