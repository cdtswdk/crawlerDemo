package cn.itcast.job.controller;

import cn.itcast.job.pojo.JobResult;
import cn.itcast.job.service.impl.JobInfoFieldRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private JobInfoFieldRepositoryServiceImpl jobInfoFieldRepositoryService;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JobResult search(String salary, String jobaddr, String keyword, Integer page){
        return this.jobInfoFieldRepositoryService.search(salary,jobaddr,keyword,page);
    }
}
