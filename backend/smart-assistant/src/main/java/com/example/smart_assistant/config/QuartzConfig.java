// package com.example.smart_assistant.config;

// import org.quartz.Job;
// import org.quartz.JobDetail;
// import org.quartz.SimpleScheduleBuilder;
// import org.quartz.Trigger;
// import org.quartz.TriggerBuilder;
// import org.quartz.JobBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.quartz.QuartzJobBean;
// import org.springframework.scheduling.quartz.SchedulerFactoryBean;

// @Configuration
// public class QuartzConfig {

//     @Bean
//     public JobDetail weatherJobDetail() {
//         return JobBuilder.newJob(WeatherJob.class)
//                 .withIdentity("weatherJob")
//                 .storeDurably()
//                 .build();
//     }

//     @Bean
//     public Trigger weatherJobTrigger() {
//         return TriggerBuilder.newTrigger()
//                             .forJob(weatherJobDetail())
//                             .withIdentity("weatherJobTrigger")
//                             .startNow()
//                             .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                                 .withIntervalInHours(24)  // 24시간 간격
//                                 .repeatForever())
//                             .build();
//     }

//     @Bean
//     public SchedulerFactoryBean schedulerFactoryBean() {
//         SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
//         schedulerFactory.setTriggers(weatherJobTrigger());
//         return schedulerFactory;
//     }
// }
