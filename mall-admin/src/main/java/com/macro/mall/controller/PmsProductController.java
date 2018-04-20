package com.macro.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品管理Controller
 */
@Controller
@Api(tags = "PmsProductController",description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
}
