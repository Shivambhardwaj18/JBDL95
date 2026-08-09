package com.example.multipartjbdl95;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
* Requirement:-
*
* If user hits our api he will give id with and we need to show the respective id's image
*
* */

@RestController
@RequestMapping(path = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
public class ImageController {

    @GetMapping(value = "/id/{id}")
    public byte[] getImage(@PathVariable("id") Integer id,
                         @RequestParam(value = "length",required = false,defaultValue = "200") Integer length,
                         @RequestParam(value = "breadth",required = false,defaultValue = "300") Integer breadth) {

        String url = "https://picsum.photos/id";

//        Third Party integration
//        Adaptor design pattern

        RestTemplate restTemplate = new RestTemplate();

        byte [] pic = restTemplate.getForObject(url + "/" + id +"/" + length + "/" + breadth, byte[].class);

        return pic;

    }

    @PostMapping("/add/image")
    public byte[] addImage(@RequestBody MultipartFile file) throws IOException {

        Path path = Paths.get("uploads/" + file.getOriginalFilename());
        Files.createDirectories(path.getParent());

        Files.write(path,file.getBytes());
        return file.getBytes();

    }



}
