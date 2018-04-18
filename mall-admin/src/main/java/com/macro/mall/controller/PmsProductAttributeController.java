package com.macro.mall.controller;

import com.macro.mall.service.PmsProductAttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品属性管理Controller
 */
@Controller
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductAttributeController.class);
    @Autowired
    private PmsProductAttributeService productAttributeService;
}
