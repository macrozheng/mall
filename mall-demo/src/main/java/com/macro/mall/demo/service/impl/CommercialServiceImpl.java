package com.macro.mall.demo.service.impl;

import com.macro.mall.demo.dto.CommercialDto;
import com.macro.mall.demo.service.CommercialService;
import com.macro.mall.model.Commercial;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommercialServiceImpl implements CommercialService {
    private List<Commercial> commercialList = new ArrayList<>();
    private Long currentId = 1L;

    @Override
    public List<Commercial> listAllCommercial() {
        return commercialList;
    }

    @Override
    public int createCommercial(CommercialDto commercialDto) {
        Commercial commercial = new Commercial();
        commercial.setId(currentId++);
        commercial.setName(commercialDto.getName());
        commercial.setDescription(commercialDto.getDescription());
        commercial.setAddress(commercialDto.getAddress());
        commercial.setPhone(commercialDto.getPhone());
        commercial.setStatus(commercialDto.getStatus());
        commercial.setCreateTime(new Date());
        commercial.setUpdateTime(new Date());
        commercialList.add(commercial);
        return 1;
    }

    @Override
    public int updateCommercial(Long id, CommercialDto commercialDto) {
        for (Commercial commercial : commercialList) {
            if (commercial.getId().equals(id)) {
                commercial.setName(commercialDto.getName());
                commercial.setDescription(commercialDto.getDescription());
                commercial.setAddress(commercialDto.getAddress());
                commercial.setPhone(commercialDto.getPhone());
                commercial.setStatus(commercialDto.getStatus());
                commercial.setUpdateTime(new Date());
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteCommercial(Long id) {
        for (int i = 0; i < commercialList.size(); i++) {
            if (commercialList.get(i).getId().equals(id)) {
                commercialList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<Commercial> listCommercial(Integer pageNum, Integer pageSize) {
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, commercialList.size());
        return commercialList.subList(startIndex, endIndex);
    }

    @Override
    public Commercial getCommercial(Long id) {
        for (Commercial commercial : commercialList) {
            if (commercial.getId().equals(id)) {
                return commercial;
            }
        }
        return null;
    }
} 