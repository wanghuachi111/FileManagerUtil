package com.utils.files.rconvenience.controller;

import com.utils.files.rconvenience.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whc
 * @Title:
 * @Package
 * @Description: 接口
 * @date 2021/5/129:41
 */
@Api(tags = "批量文件操作")
@RestController
@RequestMapping("/option")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("批量修改文件名-增加时长信息")
    @GetMapping("/changeName")
    public String changeName(@RequestParam(value = "FilePath") String FilePath) {

         return fileService.RenameFiles(FilePath);
    }

    @ApiOperation("批量计算文件时长总和")
    @GetMapping("/addTime")
    public String calculateTime(@RequestParam(value = "FilePath") String FilePath) {

        return fileService.calculateTime(FilePath);
    }

    @ApiOperation("递归计算文件夹下所有文件时长")
    @GetMapping("/addAllTime")
    public String calculateAllTime(@RequestParam(value = "FilePath") String FilePath) {
        return fileService.calculateAllTime(FilePath);
    }

}
