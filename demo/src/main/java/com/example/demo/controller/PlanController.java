package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.repository.PlanRepo;;

@RestController
public class PlanController {
	@Autowired
	PlanRepo planRepo;

	@Autowired
	ResourceLoader resourceLoader;

	// post
	@PostMapping(value = "/plan", consumes = { "application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity addItem(@RequestBody String json) {
		JSONObject plan = new JSONObject(json);
		try {
			Resource resource = resourceLoader.getResource("classpath:schema.json");
			InputStream inputStream = resource.getInputStream();
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			Schema schema = SchemaLoader.load(rawSchema);
			schema.validate(plan); // throws a ValidationException if this object is invalid
			planRepo.addPlan(plan);
			
		} catch (ValidationException e) {
			return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception ex) {
			
		}
		return new ResponseEntity("Success", HttpStatus.CREATED);
	}

	// get
	@GetMapping("/plan/{id}")
	@ResponseBody
	public ResponseEntity getItem(@PathVariable String id) {
		Object plan = planRepo.getPlan(id);
		if(plan == null) {
			return new ResponseEntity("plan does not exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(plan, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/plan/{id}")
	@ResponseBody
	public ResponseEntity deleteItem(@PathVariable String id) {
		Object plan = planRepo.getPlan(id);
		if(plan == null) {
			return new ResponseEntity("plan does not exist", HttpStatus.NOT_FOUND);
		}
		planRepo.deletePlan(id);
		return new ResponseEntity("Deleted Successfully", HttpStatus.NO_CONTENT);
	}
}
