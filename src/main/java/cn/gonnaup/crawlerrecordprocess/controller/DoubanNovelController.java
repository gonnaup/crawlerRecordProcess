package cn.gonnaup.crawlerrecordprocess.controller;

import cn.gonnaup.crawlerrecordprocess.entity.DoubanNovel;
import cn.gonnaup.crawlerrecordprocess.repository.DoubanNovelRepository;
import cn.gonnaup.crawlerrecordprocess.util.PageParam;
import cn.gonnaup.crawlerrecordprocess.util.PageTemplate;
import cn.gonnaup.crawlerrecordprocess.util.ResultTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author gonnaup
 * @version created at 2022/4/23 20:19
 */
@RestController
@CrossOrigin(originPatterns = "*")
public class DoubanNovelController {

    private final DoubanNovelRepository doubanNovelRepository;

    @Autowired
    public DoubanNovelController(DoubanNovelRepository doubanNovelRepository) {
        this.doubanNovelRepository = doubanNovelRepository;
    }


    @GetMapping("/novel/list")
    public ResultTemplate<?> listPaged(PageParam pageParam, DoubanNovel novel) {
        Page<DoubanNovel> novels = doubanNovelRepository.findAll(Example.of(Optional.ofNullable(novel).orElseGet(DoubanNovel::new)), Pageable.ofSize(pageParam.pageSize()).withPage(pageParam.page()));
        return ResultTemplate.data(PageTemplate.of(novels.getContent(), novels.getTotalElements(), novels.getTotalPages())).success();
    }

}
