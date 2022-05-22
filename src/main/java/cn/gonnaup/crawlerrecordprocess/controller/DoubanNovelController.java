package cn.gonnaup.crawlerrecordprocess.controller;

import cn.gonnaup.crawlerrecordprocess.entity.DoubanNovel;
import cn.gonnaup.crawlerrecordprocess.exception.IllegalInputParamException;
import cn.gonnaup.crawlerrecordprocess.repository.DoubanNovelRepository;
import cn.gonnaup.crawlerrecordprocess.util.PageParam;
import cn.gonnaup.crawlerrecordprocess.util.PageTemplate;
import cn.gonnaup.crawlerrecordprocess.util.ResultTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/novel/{id}")
    public ResultTemplate<DoubanNovel> findOneDoubanNovel(@PathVariable("id") Integer id) {
        Optional<DoubanNovel> doubanNovel = doubanNovelRepository.findById(id);
        return ResultTemplate.data(doubanNovel.orElseThrow(() -> new IllegalInputParamException("不存在的ID：%d".formatted(id)))).success();
    }

    @GetMapping("/novel/list")
    public ResultTemplate<PageTemplate<DoubanNovel>> listPaged(PageParam pageParam, DoubanNovel novel) {
        Page<DoubanNovel> novels = doubanNovelRepository.findAll(Example.of(Optional.ofNullable(novel).orElseGet(DoubanNovel::new)),
                PageRequest.of(pageParam.page(), pageParam.pageSize(), Sort.by(Sort.Order.asc("id"))));
        return ResultTemplate.data(PageTemplate.of(novels.getContent(), novels.getTotalElements(), novels.getTotalPages())).success();
    }

    @GetMapping("/novel/category/kinds")
    public ResultTemplate<List<String>> listNovelKinds() {
        List<String> novelKind = doubanNovelRepository.findAllDoubanNovelKind();
        return ResultTemplate.data(novelKind).success();
    }

    @GetMapping("/novel/category/tags")
    public ResultTemplate<List<String>> listNovelTags() {
        List<String> novelTag = doubanNovelRepository.findAllDoubanNovelTag();
        return ResultTemplate.data(novelTag).success();
    }

    @GetMapping("/novel/category/status")
    public ResultTemplate<List<String>> listNovelStatus() {
        List<String> novelStatus = doubanNovelRepository.findAllDoubanNovelStatus();
        return ResultTemplate.data(novelStatus).success();
    }

}
