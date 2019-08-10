package com.viride.app.virideapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.viride.app.virideapp.VirideException;
import com.viride.app.virideapp.entity.Beer;
import com.viride.app.virideapp.repository.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {
	@Autowired
	BeerRepository repo;

	@Override
	public List<Beer> getAllBeers() {
		List<Beer> res = new ArrayList<>();
		repo.findAll().forEach(e -> res.add(e));
		return res;
	}

	@Override
	public Page<Beer> findPaginated(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Beer> list;
		List<Beer> allBeers = getAllBeers();
		if (allBeers.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, allBeers.size());
			list = allBeers.subList(startItem, toIndex);
		}

		Page<Beer> bookPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allBeers.size());

		return bookPage;
	}
	@Override
	public Page<Beer> findPaginated(Pageable pageable, String id) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		List<Beer> list = new ArrayList<>();
		try {
			Beer b = getBeerByID(id);
			list.add(b);
		} catch (VirideException e) {
			
		}
		Page<Beer> bookPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), list.size());
		return bookPage;
	}

	@Override
	public Beer getBeerByID(String brewery_id) throws VirideException {
		Optional<Beer> beerInDB = repo.findById(brewery_id);
		if (beerInDB.isPresent())
			return beerInDB.get();
		throw new VirideException("Id not found " + brewery_id);
	}

	@Override
	public void addBeer(Beer b) {
		repo.save(b);
	}

	@Override
	public void updateBeer(String brewery_id, Beer b) throws VirideException {
		Beer existingBeer = getBeerByID(brewery_id);
		existingBeer.setAbv(b.getAbv());
		existingBeer.setCategory(b.getCategory());
		existingBeer.setDescription(b.getDescription());
		existingBeer.setIbu(b.getIbu());
		existingBeer.setName(b.getName());
		existingBeer.setSrm(b.getSrm());
		existingBeer.setStyle(b.getStyle());
		existingBeer.setType(b.getType());
		existingBeer.setUpc(b.getUpc());
		existingBeer.setUpdated(new Date().toString());
		repo.save(existingBeer);
	}

	@Override
	public void deleteBeer(String brewery_id) throws VirideException {
		Beer existingBeer = getBeerByID(brewery_id);
		repo.delete(existingBeer);
	}

	@Override
	public void bulkAdd() {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			UUID u = UUID.randomUUID();
			int randomInt = r.nextInt(1000);
			Beer b = new Beer();
			b.setBrewery_id(u.toString());
			b.setAbv(r.nextFloat());
			b.setCategory("category " + randomInt);
			b.setDescription("description " + randomInt);
			b.setIbu(randomInt);
			b.setName("name " + randomInt);
			b.setSrm(randomInt);
			b.setStyle("style " + randomInt);
			b.setType("type " + randomInt);
			b.setUpc(randomInt);
			b.setUpdated(new Date().toString());
			repo.save(b);
		}
	}

	@Override
	public void bulkDetele() {
		List<Beer> beers = getAllBeers();
		int limit = beers.size() > 1000 ? 1000 : beers.size();
		for (int i = 0; i < limit; i++) {
			Beer b = beers.get(i);
			repo.delete(b);
		}

	}

	

}
