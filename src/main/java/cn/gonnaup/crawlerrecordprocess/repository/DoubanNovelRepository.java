package cn.gonnaup.crawlerrecordprocess.repository;

import cn.gonnaup.crawlerrecordprocess.entity.DoubanNovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gonnaup
 * @version created at 2022/4/20 21:39
 */
@Repository
@Transactional
public interface DoubanNovelRepository extends JpaRepository<DoubanNovel, Integer> {
}
