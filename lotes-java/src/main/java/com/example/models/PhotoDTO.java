package com.example.models;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.Data;

@Data
public class PhotoDTO {
private String id, author, url, download_url;
private int width, height;
}
