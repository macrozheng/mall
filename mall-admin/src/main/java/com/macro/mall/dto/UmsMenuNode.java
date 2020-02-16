package com.macro.mall.dto;

import com.macro.mall.model.UmsMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by macro on 2020/2/4.
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;
}
