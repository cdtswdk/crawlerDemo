package cn.itcast.job.service.impl;

import cn.itcast.job.dao.JobInfoFieldRepository;
import cn.itcast.job.pojo.JobInfoField;
import cn.itcast.job.pojo.JobResult;
import cn.itcast.job.service.JobInfoFieldRepositoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobInfoFieldRepositoryServiceImpl implements JobInfoFieldRepositoryService {

    @Autowired
    private JobInfoFieldRepository repository;

    @Override
    public void save(JobInfoField jobInfoField) {
        this.repository.save(jobInfoField);
    }

    @Override
    public void saveAll(List<JobInfoField> jobInfoFields) {
        this.repository.saveAll(jobInfoFields);
    }

    @Override
    public JobResult search(String salary, String jobaddr, String keyword, Integer page) {
        String[] split = salary.split("-");
        int salaryMin = 0,salaryMax = 0;
        if("*".equals(split[0])){
            salaryMin = 0;
        }else {
            salaryMin = Integer.parseInt(split[0])*10000;
        }
        if("*".equals(split[1])){
            salaryMax = 10000000;
        }else {
            salaryMax = Integer.parseInt(split[1])*10000;
        }

        //工作地址如果为空，就设置为*
        if(StringUtils.isBlank(jobaddr)){
            jobaddr = "*";
        }
        //查询关键词为空，就设置为*
        if(StringUtils.isBlank(keyword)){
            keyword = "*";
        }
        Page<JobInfoField> pages = this.repository.findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(salaryMin, salaryMax, salaryMin, salaryMax, jobaddr, keyword, keyword, PageRequest.of(page - 1, 30));

        JobResult jobResult = new JobResult();
        jobResult.setRows(pages.getContent());
        jobResult.setPageTotal(pages.getTotalPages());

        return jobResult;
    }
}
