package cn.itcast.job.dao;

import cn.itcast.job.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobInfoDao extends JpaRepository<JobInfo,Long>, JpaSpecificationExecutor<JobInfo> {
}
