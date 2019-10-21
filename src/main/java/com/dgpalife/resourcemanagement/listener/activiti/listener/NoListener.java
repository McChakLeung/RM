package com.dgpalife.resourcemanagement.listener.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class NoListener implements ExecutionListener {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println("审批不通过");
        try{
            javaMailSender = new JavaMailSenderImpl();
            System.out.println("审批不通过");
            javaMailSender.setDefaultEncoding("UTF-8");
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setSubject("标题");
            StringBuilder content = new StringBuilder();
            content.append("审批不通过");
            helper.setText(content.toString(), true);
            helper.setFrom("admin@atguigu.com");
            helper.setTo("test@atguigu.com");
            javaMailSender.send(mail);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
