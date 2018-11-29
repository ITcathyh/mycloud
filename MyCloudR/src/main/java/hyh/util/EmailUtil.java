package hyh.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 */
public class EmailUtil {
    /**
     * 发送邮件
     *
     * @param address 收件地址
     * @param text    文本内容
     * @param title   标题
     */
    public static boolean sendEmail(String address, String text,
                                    String title, JavaMailSenderImpl sender) {
        MimeMessage msg = sender.createMimeMessage();
        return sendEmail(address, text, title, sender, msg);
    }

    /**
     * 发送邮件
     *
     * @param address  收件地址
     * @param text     文本内容
     * @param title    标题
     * @param username 用户名
     */
    public static boolean sendEmailWithRecipients(String address, String text,
                                                  String title, String username,
                                                  JavaMailSenderImpl sender) {
        MimeMessage msg = sender.createMimeMessage();

        try {
            msg.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(username));
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return sendEmail(address, text, title, sender, msg);
    }

    private static boolean sendEmail(String address, String text,
                                     String title, JavaMailSenderImpl sender,
                                     MimeMessage msg) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");

            helper.setFrom(sender.getUsername());
            helper.setTo(address);
            helper.setSubject(title);
            helper.setText(text, true);
            sender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
