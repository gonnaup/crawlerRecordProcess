package cn.gonnaup.crawlerrecordprocess.repository;

import cn.gonnaup.crawlerrecordprocess.entity.DoubanNovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gonnaup
 * @version created at 2022/4/20 21:39
 */
@Repository
@Transactional
public interface DoubanNovelRepository extends JpaRepository<DoubanNovel, Integer> {

    @Query("select distinct u.kind from DoubanNovel u where u.kind is not null ")
    List<String> findAllDoubanNovelKind();

    @Query("select distinct u.tag from DoubanNovel u where u.tag is not null ")
    List<String> findAllDoubanNovelTag();

    @Query("select distinct u.status from DoubanNovel u where u.status is not null ")
    List<String> findAllDoubanNovelStatus();

}
