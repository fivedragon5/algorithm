package code.study;

import java.nio.charset.StandardCharsets;

public class EmailFormatter {

    private static final int RECEIVER_LENGTH = 150; // 수신자 Email 고정 길이 (Bytes)
    private static final int BODY_LENGTH = 1500;    // 본문 고정 길이 (Bytes)

    public static void main(String[] args) {
        // 예제 데이터
        String receiverEmail = "example@example.com";
        String emailBody = "Hello, this is a test email.";

        try {
            String formattedEmail = createEmailContent(receiverEmail, emailBody);
            System.out.println("Formatted Email:");
            System.out.println(formattedEmail);
            System.out.println(formattedEmail.getBytes(StandardCharsets.UTF_8).length);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * 고정된 길이로 수신자 이메일과 본문을 포맷팅하고 합친다.
     *
     * @param receiver 수신자 이메일
     * @param body     본문 내용
     * @return 포맷팅된 이메일 문자열
     */
    public static String createEmailContent(String receiver, String body) {
        String formattedReceiver = formatString(receiver, RECEIVER_LENGTH);
        String formattedBody = formatString(body, BODY_LENGTH);
        return formattedReceiver + formattedBody;
    }

    /**
     * 문자열을 고정된 바이트 길이에 맞춰 포맷팅한다.
     * 초과 시 예외를 던지고, 부족 시 공백으로 채운다.
     *
     * @param input    입력 문자열
     * @param byteSize 고정 바이트 크기
     * @return 포맷팅된 문자열
     */
    private static String formatString(String input, int byteSize) {
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

        if (inputBytes.length > byteSize) {
            throw new IllegalArgumentException("Input exceeds the allowed byte size of " + byteSize);
        }

        // 부족한 부분 공백으로 채우기
        StringBuilder builder = new StringBuilder(input);
        while (builder.toString().getBytes(StandardCharsets.UTF_8).length < byteSize) {
            builder.append(" ");
        }

        return builder.toString();
    }
}
