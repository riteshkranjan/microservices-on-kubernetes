package com.viride.app.virideapp.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import com.viride.app.virideapp.entity.Beer;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "beer")
public interface BeerRepository extends CouchbasePagingAndSortingRepository<Beer, String>{

}
