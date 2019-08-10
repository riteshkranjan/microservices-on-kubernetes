package com.viride.app.virideapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.viride.app.virideapp.VirideException;
import com.viride.app.virideapp.entity.Beer;
import com.viride.app.virideapp.service.BeerService;

@Controller
@RequestMapping("/")
public class VirideViewController {
	@Autowired
	BeerService service;

	@RequestMapping
	public String welcome(Model model, @RequestParam("page") Optional<Integer> page) {
		List<Integer> pageNumbers = new ArrayList<>();
		int currentPage = page.orElse(1);
		int pageSize = 20;

		Page<Beer> beerPage = service.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("beers", beerPage);

		int totalPages = beerPage.getTotalPages();

		if (totalPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}
		model.addAttribute("pageNumbers", pageNumbers);
		return "list-beers";
	}

	@RequestMapping(path = "/search")
	public String search(@RequestParam(value = "brewery_id", required = false) String brewery_id, Model model) {
		if (brewery_id == null || brewery_id.length() == 0) {
			return "redirect:/";
		}
		List<Integer> pageNumbers = new ArrayList<>();
		model.addAttribute("pageNumbers", pageNumbers);
		Page<Beer> beerPage = service.findPaginated(PageRequest.of(0, 1), brewery_id);
		model.addAttribute("beers", beerPage);

		return "list-beers";
	}

	@RequestMapping(path = "/clear")
	public String clear(Model model) {
		return "redirect:/";
	}

	@RequestMapping(path = "/bulkAdd")
	public String bulkAdd(Model model) {
		service.bulkAdd();
		return "redirect:/";
	}

	@RequestMapping(path = "/bulkDelete")
	public String bulkDelete(Model model) {
		service.bulkDetele();
		return "redirect:/";
	}

	@RequestMapping(path = "/delete/{brewery_id}")
	public String deleteBeerById(Model model, @PathVariable("brewery_id") String brewery_id) throws VirideException {
		service.deleteBeer(brewery_id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createBeer", method = RequestMethod.POST)
	public String createOrUpdateBeer(Beer b) {
		b.setUpdated(new Date().toString());
		service.addBeer(b);
		return "redirect:/";
	}

	@RequestMapping(path = { "/edit", "/edit/{brewery_id}" })
	public String editBeerById(Model model, @PathVariable("brewery_id") Optional<String> brewery_id)
			throws VirideException {
		if (brewery_id.isPresent()) {
			Beer b = service.getBeerByID(brewery_id.get());
			model.addAttribute("beer", b);
		} else {
			model.addAttribute("beer", new Beer());
		}
		return "add-edit-beer";
	}

}
