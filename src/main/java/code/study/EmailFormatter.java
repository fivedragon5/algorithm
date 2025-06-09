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
            System.out.println("Formatted Email:" + formattedEmail);
            System.out.println(formattedEmail);
            System.out.println(formattedEmail.getBytes(StandardCharsets.UTF_8).length);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println(leftPad(receiverEmail, RECEIVER_LENGTH));
        System.out.println(leftPad(receiverEmail, RECEIVER_LENGTH).getBytes(StandardCharsets.UTF_8).length);
        System.out.println(rightPad(emailBody, BODY_LENGTH));
        System.out.println(rightPad(emailBody, BODY_LENGTH).getBytes().length);

        //                                                                                                                                    example@example.com
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
        int inputByteSize = input.getBytes().length;
        StringBuilder builder = new StringBuilder(input);
        for (int i = 0; i < byteSize - inputByteSize; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private static String leftPad(String input, int byteSize) {
        int inputByteSize = input.getBytes().length;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < byteSize - inputByteSize; i++) {
            builder.append(" ");
        }
        builder.append(input);
        return builder.toString();
    }

    private static String rightPad(String input, int byteSize) {
        int inputByteSize = input.getBytes().length;
        StringBuilder builder = new StringBuilder(input);
        for (int i = 0; i < byteSize - inputByteSize; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
}
