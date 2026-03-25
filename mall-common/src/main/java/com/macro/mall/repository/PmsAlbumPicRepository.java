package com.macro.mall.repository;

import com.macro.mall.model.PmsAlbumPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsAlbumPicRepository extends JpaRepository<PmsAlbumPic, Long>, JpaSpecificationExecutor<PmsAlbumPic> {
}
