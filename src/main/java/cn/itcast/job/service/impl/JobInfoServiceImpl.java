package cn.itcast.job.service.impl;

import cn.itcast.job.dao.JobInfoDao;
import cn.itcast.job.pojo.JobInfo;
import cn.itcast.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        //先从数据库查询数据,根据发布日期查询和url查询
        JobInfo param = new JobInfo();
        param.setTime(jobInfo.getTime());
        param.setUrl(jobInfo.getUrl());
        List<JobInfo> infoList = this.findJobInfo(param);
        if(infoList.size() == 0){
            //没有查询到数据则新增或者修改数据
            this.jobInfoDao.saveAndFlush(jobInfo);
        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        Example<JobInfo> example = Example.of(jobInfo);
        List<JobInfo> list = this.jobInfoDao.findAll(example);
        return list;
    }

    @Override
    public Page<JobInfo> findByPage(int page, int pageSize) {
        return this.jobInfoDao.findAll(PageRequest.of(page,pageSize));
    }


}
