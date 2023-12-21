package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin/common")
public class CommonController {
    @PostMapping("/upload")
    public Result upload(@RequestPart(value="file") MultipartFile image) throws IOException {
        File file=new File("D:\\WorkSpace\\hm-learnSystem\\src\\main\\resources\\static\\img\\"+image.getOriginalFilename());
        image.transferTo(file);
        return Result.success(file.getPath());
    }
}
