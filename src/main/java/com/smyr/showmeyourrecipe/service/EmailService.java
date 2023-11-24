package com.smyr.showmeyourrecipe.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

import static org.springframework.security.core.context.SecurityContextHolder.setContext;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;

	public void sendMail() {
		String authNum = "1234";//createCode();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		//if (type.equals("password")) userService.SetTempPassword(emailMessage.getTo(), authNum);

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setTo( "japgo@naver.com" ); // 메일 수신자
			mimeMessageHelper.setSubject( "Show Me Your Recipe" ); // 메일 제목
			mimeMessageHelper.setText( "I'm Still Hungry." , true); // 메일 본문 내용, HTML 여부

			javaMailSender.send(mimeMessage);

			log.info("Success");

		} catch ( MessagingException e) {
			log.info("fail");
			throw new RuntimeException(e);
		}
	}
}
