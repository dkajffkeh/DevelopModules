package com.home.quartz.schedule.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl extends QuartzJobBean implements BoardService {

    @Override
    public void testJobMethod() {


        System.out.println("test job....");

    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("test job....");

    }

}
