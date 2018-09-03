package com.exams;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {
	
	final static String postStudentQueueName = "post-student";
	final static String putStudentQueueName = "put-student";
	final static String deleteStudentQueueName = "delete-student";
	final static String postFacultyMemberQueueName = "post-faculty-member";
	final static String putFacultyMemberQueueName = "put-faculty-member";
	final static String deleteFacultyMemberQueueName = "delete-faculty-member";
	final static String postDepartmentQueueName = "post-department";
	final static String putDepartmentQueueName = "put-department";
	final static String deleteDepartmentQueueName = "delete-department";
	final static String postLocationQueueName = "post-location";
	final static String putLocationQueueName = "put-location";
	final static String deleteLocationQueueName = "delete-location";
	final static String postCourseQueueName = "post-course";
	final static String putCourseQueueName = "put-course";
	final static String deleteCourseQueueName = "delete-course";
	
    @Bean
    Queue postStudentQueue() {
        return new Queue(postStudentQueueName, false);
    }
    
    @Bean
    Queue putStudentQueue() {
    	return new Queue(putStudentQueueName, false);
    }
    
    @Bean
    Queue deleteStudentQueue() {
    	return new Queue(deleteStudentQueueName, false);
    }
    
    @Bean
    Queue postFacultyMemberQueue() {
        return new Queue(postFacultyMemberQueueName, false);
    }
    
    @Bean
    Queue putFacultyMemberQueue() {
    	return new Queue(putFacultyMemberQueueName, false);
    }
    
    @Bean
    Queue deleteFacultyMemberQueue() {
    	return new Queue(deleteFacultyMemberQueueName, false);
    }
    
    @Bean
    Queue postDepartmentQueue() {
        return new Queue(postDepartmentQueueName, false);
    }
    
    @Bean
    Queue putDepartmentQueue() {
    	return new Queue(putDepartmentQueueName, false);
    }
    
    @Bean
    Queue deleteDepartmentQueue() {
    	return new Queue(deleteDepartmentQueueName, false);
    }
    
    @Bean
    Queue postLocationQueue() {
        return new Queue(postLocationQueueName, false);
    }
    
    @Bean
    Queue putLocationQueue() {
    	return new Queue(putLocationQueueName, false);
    }
    
    @Bean
    Queue deleteLocationQueue() {
    	return new Queue(deleteLocationQueueName, false);
    }
    
    @Bean
    Queue postCourseQueue() {
        return new Queue(postCourseQueueName, false);
    }
    
    @Bean
    Queue putCourseQueue() {
    	return new Queue(putCourseQueueName, false);
    }
    
    @Bean
    Queue deleteCourseQueue() {
    	return new Queue(deleteCourseQueueName, false);
    }

    @Bean
    DirectExchange usersExchange() {
        return new DirectExchange("users-direct-exchange");
    }
    
    @Bean
    DirectExchange academicsExchange() {
    	return new DirectExchange("academics-direct-exchange");
    }
    
    @Bean
    DirectExchange locationsExchange() {
    	return new DirectExchange("locations-direct-exchange");
    }

    @Bean
    Binding postStudentbinding(Queue postStudentQueue, DirectExchange usersExchange) {
        return BindingBuilder.bind(postStudentQueue).to(usersExchange).with("post.student");
    }
    
    @Bean
    Binding putStudentbinding(Queue putStudentQueue, DirectExchange usersExchange) {
        return BindingBuilder.bind(putStudentQueue).to(usersExchange).with("put.student");
    }
    
    @Bean
    Binding deleteStudentbinding(Queue deleteStudentQueue, DirectExchange usersExchange) {
        return BindingBuilder.bind(deleteStudentQueue).to(usersExchange).with("delete.student");
    }
    
    @Bean
    Binding postFacultyMemberbinding(Queue postFacultyMemberQueue, DirectExchange usersExchange) {
        return BindingBuilder.bind(postFacultyMemberQueue).to(usersExchange).with("post.faculty.member");
    }
    
    @Bean
    Binding putFacultyMemberbinding(Queue putFacultyMemberQueue, DirectExchange usersExchange) {
        return BindingBuilder.bind(putFacultyMemberQueue).to(usersExchange).with("put.faculty.member");
    }
    
    @Bean
    Binding deleteFacultyMemberbinding(Queue deleteFacultyMemberQueue, DirectExchange usersExchange) {
        return BindingBuilder.bind(deleteFacultyMemberQueue).to(usersExchange).with("delete.faculty.member");
    }
    
    @Bean
    Binding postDepartmentbinding(Queue postDepartmentQueue, DirectExchange academicsExchange) {
        return BindingBuilder.bind(postDepartmentQueue).to(academicsExchange).with("post.department");
    }
    
    @Bean
    Binding putDepartmentbinding(Queue putDepartmentQueue, DirectExchange academicsExchange) {
        return BindingBuilder.bind(putDepartmentQueue).to(academicsExchange).with("put.department");
    }
    
    @Bean
    Binding deleteDepartmentbinding(Queue deleteDepartmentQueue, DirectExchange academicsExchange) {
        return BindingBuilder.bind(deleteDepartmentQueue).to(academicsExchange).with("delete.department");
    }
    
    @Bean
    Binding postLocationbinding(Queue postLocationQueue, DirectExchange locationsExchange) {
        return BindingBuilder.bind(postLocationQueue).to(locationsExchange).with("post.location");
    }
    
    @Bean
    Binding putLocationbinding(Queue putLocationQueue, DirectExchange locationsExchange) {
        return BindingBuilder.bind(putLocationQueue).to(locationsExchange).with("put.location");
    }
    
    @Bean
    Binding deleteLocationbinding(Queue deleteLocationQueue, DirectExchange locationsExchange) {
        return BindingBuilder.bind(deleteLocationQueue).to(locationsExchange).with("delete.location");
    }
    
    @Bean
    Binding postCoursebinding(Queue postCourseQueue, DirectExchange academicsExchange) {
        return BindingBuilder.bind(postCourseQueue).to(academicsExchange).with("post.course");
    }
    
    @Bean
    Binding putCoursebinding(Queue putCourseQueue, DirectExchange academicsExchange) {
        return BindingBuilder.bind(putCourseQueue).to(academicsExchange).with("put.course");
    }
    
    @Bean
    Binding deleteCoursebinding(Queue deleteCourseQueue, DirectExchange academicsExchange) {
        return BindingBuilder.bind(deleteCourseQueue).to(academicsExchange).with("delete.course");
    }
    
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }
    
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

}
