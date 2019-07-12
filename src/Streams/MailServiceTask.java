package Streams;

import java.util.*;
import java.util.function.Consumer;


public class MailServiceTask {

    /**
     * Код, который необходимо заставить успешно работать
     * @param args
     */
    public static void main(String[] args) {
        // Random variables
        String randomFrom = "me"; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "you";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

        // Создание списка из трех почтовых сообщений.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

        // Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ): "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ): "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";


        // Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        // Создание почтового сервиса, обрабатывающего зарплаты.
        MailService<Integer> salaryService = new MailService<>();

        // Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";
    }


    /**
     * implementation
     */

    public interface Sendable<T> {
        T getContent();
        String getFrom();
        String getTo();
    }

    public static class MailMessage implements Sendable{

        private final String from;
        private final String to;
        private final String content;

        MailMessage(String from, String to, String content){
            this.from = from;
            this.to = to;
            this.content = content;
        }

        @Override
        public String getFrom() {
            return from;
        }
        @Override
        public String getTo() {
            return to;
        }
        @Override
        public String getContent() {
            return content;
        }
    }

    public static class Salary implements Sendable{
        private final String from;
        private final String to;
        private final Integer amount;

        public Salary(String from, String to, Integer amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public Integer getContent() {
            return amount;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }
    }

    public static class MailService<T> implements Consumer<Sendable> {

        private final Map<String, List<T>> mailBox = new HashMap<String, List<T>>() {
                    @Override
                    public List<T> get(Object key) {
                        return super.getOrDefault(key, Collections.emptyList());
                    }
                };

        @Override
        public void accept(Sendable o) {
            if (mailBox.containsKey(o.getTo())) {
                mailBox.get(o.getTo()).add((T)o.getContent());
            } else {
                mailBox.put(o.getTo(), new ArrayList<>(Arrays.asList((T)o.getContent())));
            }
        }

        @Override
        public Consumer<Sendable> andThen(Consumer after) {
            return null;
        }

        public Map<String, List<T>> getMailBox() {
            return mailBox;
        }
    }
}
