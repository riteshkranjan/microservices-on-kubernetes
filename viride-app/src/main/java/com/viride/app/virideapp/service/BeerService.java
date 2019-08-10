package com.viride.app.virideapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.viride.app.virideapp.VirideException;
import com.viride.app.virideapp.entity.Beer;

public interface BeerService {
	
	public List<Beer> getAllBeers();
	
	public Beer getBeerByID(String brewery_id) throws VirideException ;
	
	public void addBeer(Beer b);
	
	public void updateBeer(String brewery_id, Beer b) throws VirideException ;
	
	public void deleteBeer(String brewery_id) throws VirideException ;

	public void bulkAdd();

	public void bulkDetele();

	public Page<Beer> findPaginated(Pageable pageable);

	public Page<Beer> findPaginated(Pageable pageable, String string);

}
