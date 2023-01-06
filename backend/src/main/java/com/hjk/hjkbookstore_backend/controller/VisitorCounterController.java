package com.hjk.hjkbookstore_backend.controller;

import com.hjk.hjkbookstore_backend.service.VisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@RestController
public class VisitorCounterController {

    @Autowired
    private VisitorsService visitorsService;

    private class IncreaseCounter implements Runnable{
        @Override
        public void run() {
            visitorsService.increaseVisitorCount();
        }
    }

    @CrossOrigin
    @RequestMapping("/getVisitorCount")
    public Integer getVisitorCount() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask= new FutureTask<>(visitorsService::getVisitorCount);
        Thread thread=new Thread(futureTask);
        thread.start();
//        System.out.println(thread.getId());
        thread.join();
        return futureTask.get();
    }

    @CrossOrigin
    @RequestMapping("/increaseVisitorCount")
    public void increaseVisitorCount() throws InterruptedException {
        Thread thread=new Thread(new IncreaseCounter());
        thread.start();
//        System.out.println(thread.getId());
        thread.join();
    }
}
