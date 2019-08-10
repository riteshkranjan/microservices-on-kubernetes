package com.viride.app.virideapp.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode
public class Beer extends BasicEntity{
	@NotNull
    @Id
    private String brewery_id;
	
    @Field
    private Float abv;
	
	@NotNull
    @Field
    private String category;
	
	@NotNull
    @Field
    private String description;
	
    @Field
    private Integer ibu;
	
	@NotNull
    @Field
    private String name;
	
    @Field
    private Integer srm;
	
	@NotNull
    @Field
    private String style;
	
    @Field
    private String type;
	
	@NotNull
    @Field
    private String updated;
	
    @Field
    private Integer upc;

}
