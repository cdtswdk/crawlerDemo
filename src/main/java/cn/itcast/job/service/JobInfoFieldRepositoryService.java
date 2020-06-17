package cn.itcast.job.service;

import cn.itcast.job.pojo.JobInfoField;
import cn.itcast.job.pojo.JobResult;

import java.util.List;

public interface JobInfoFieldRepositoryService {

    public void save(JobInfoField jobInfoField);

    public void saveAll(List<JobInfoField> jobInfoFields);

    JobResult search(String salary, String jobaddr, String keyword, Integer page);
}
