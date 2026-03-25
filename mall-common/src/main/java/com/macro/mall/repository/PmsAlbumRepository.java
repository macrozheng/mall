package com.macro.mall.repository;

import com.macro.mall.model.PmsAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsAlbumRepository extends JpaRepository<PmsAlbum, Long>, JpaSpecificationExecutor<PmsAlbum> {
}
