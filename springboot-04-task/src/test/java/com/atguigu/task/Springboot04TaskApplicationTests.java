package com.atguigu.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	public void contextLoads() {//简单邮件发送
		SimpleMailMessage message = new SimpleMailMessage();
		//邮件设置
		message.setSubject("通知-今晚开会");//邮件标题
		message.setText("今晚7:30开会");//邮件内容

		message.setTo("9******4@qq.com");//收件人邮箱
		message.setFrom("y******h@126.com");//发件人

		mailSender.send(message);//发送邮件
	}

	@Test
	public void test02() throws  Exception{//复杂邮件发送（比如带附件）
		//1、创建一个复杂的消息邮件
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);//如果需要上传文件，第二个参数multipart设为true

		//邮件设置
		helper.setSubject("通知-今晚开会-测试一下邮件发送");
		helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);//内容可以是html片段，第二个参数html设为true，则可以编译html

		helper.setTo("2******0@qq.com");
		helper.setFrom("y******h@126.com");

		//上传文件
		helper.addAttachment("1.jpg",new File("C:\\103第一学期课程表.jpg"));
		helper.addAttachment("2.png",new File("C:\\1.png"));

		mailSender.send(mimeMessage);

	}

}
