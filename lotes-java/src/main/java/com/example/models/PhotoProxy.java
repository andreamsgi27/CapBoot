package com.example.models;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="photos", url="https://picsum.photos")
public interface PhotoProxy {
@GetMapping(value = "/v2/list?limit=1000")
List<PhotoDTO> getAll();
@GetMapping("/id/{id}/info")
PhotoDTO getOne(@PathVariable int id);
}