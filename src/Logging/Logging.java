package Logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    /**
    * Интерфейс: сущность, которую можно отправить по почте.
    * У такой сущности можно получить от кого и кому направляется письмо.
    */
    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    /**
     * Абстрактный класс,который позволяет абстрагировать логику хранения
     * источника и получателя письма в соответствующих полях класса.
     */
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }

    }

    /**
     * Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
     */
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }
    }

    /**
     * Посылка, содержимое которой можно получить с помощью метода `getContent`
     */
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    /**
     * Класс, который задает посылку.
     * У посылки есть текстовое описание содержимого и целочисленная ценность.
     */
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }


    /**
     * Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
     */
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }


    /**
     * Класс, в котором скрыта логика настоящей почты
     */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    /*
    *
    * TASK
    *
    * */

    /**
     *  класс, моделирующий ненадежного работника почты,
     *  который вместо того, чтобы передать почтовый объект непосредственно в сервис почты,
     *  последовательно передает этот объект набору третьих лиц, а затем, в конце концов,
     *  передает получившийся объект непосредственно экземпляру RealMailService.
     *  У UntrustworthyMailWorker должен быть конструктор от массива MailService
     *  ( результат вызова processMail первого элемента массива передается на вход
     *  processMail второго элемента, и т. д.) и метод getRealMailService,
     *  который возвращает ссылку на внутренний экземпляр RealMailService.
     */
    public static class UntrustworthyMailWorker implements MailService {

        private MailService[] services;
        private RealMailService realMailService;

        public UntrustworthyMailWorker(MailService[] services) {
            this.services = services;
            realMailService = new RealMailService();
        }

        public Sendable processMail(Sendable mail) {

            for (MailService service: services) {
                mail = service.processMail(mail);
            }
            return realMailService.processMail(mail);
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }
    }

    /**
     * Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде
     * исключения, если была обнаружена подобная посылка. Если он заметил запрещенную посылку
     * с одним из запрещенных содержимым ("weapons" и "banned substance"),
     * то он бросает IllegalPackageException. Если он находит посылку,
     * состоящую из камней (содержит слово "stones"), то тревога прозвучит
     * в виде StolenPackageException. Оба исключения вы должны объявить самостоятельно
     * в виде непроверяемых исключений.
     */
    public static class Inspector implements MailService {
        public Sendable processMail(Sendable mail) throws IllegalPackageException, StolenPackageException {
            if (mail instanceof MailPackage) {
                MailPackage packageMail = (MailPackage) mail;
                String content = packageMail.getContent().getContent();
                if (content.contains(WEAPONS) || content.contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                }
                if (content.contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    /**
     * Spy – шпион, который логгирует о всей почтовой переписке,
     * которая проходит через его руки. Объект конструируется от экземпляра Logger,
     * с помощью которого шпион будет сообщать о всех действиях.
     * Он следит только за объектами класса MailMessage и пишет в логгер следующие сообщения
     * (в выражениях нужно заменить части в фигурных скобках на значения полей почты):
     * 2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение с уровнем WARN: Detected target mail correspondence: from {from} to {to} "{message}"
     * 2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}
     */
    public static class Spy implements MailService {

        private Logger logger = null;

        public Spy(Logger logger) {
            this.logger = logger;
        }

        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                MailMessage messageMail = (MailMessage) mail;
                if (messageMail.getFrom().equals(AUSTIN_POWERS) || messageMail.getTo().equals(AUSTIN_POWERS)) {
                    logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"", new Object[]{messageMail.getFrom(), messageMail.getTo(), messageMail.getMessage()});
                } else {
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[]{messageMail.getFrom(), messageMail.getTo()});
                }
            }
            return mail;
        }
    }


    /**
     * Thief – вор, который ворует самые ценные посылки и игнорирует все остальное.
     * Вор принимает в конструкторе переменную int – минимальную стоимость посылки,
     * которую он будет воровать. Также, в данном классе должен присутствовать
     * метод getStolenValue, который возвращает суммарную стоимость всех посылок,
     * которые он своровал. Воровство происходит следующим образом: вместо посылки,
     * которая пришла вору, он отдает новую, такую же, только с нулевой ценностью
     * и содержимым посылки "stones instead of {content}".
     */
    public static class Thief implements MailService {

        private int minPrice;
        private int totalValue;

        public Thief(int price) {
            minPrice = price;
            totalValue = 0;
        }

        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage packageMail = (MailPackage) mail;
                int price = packageMail.getContent().getPrice();
                if (price >= minPrice) {
                    Package emptyPackage = new Package("stones instead of " + packageMail.getContent().getContent(), 0);
                    totalValue += price;
                    return new MailPackage(mail.getFrom(), mail.getTo(), emptyPackage);
                }
            }
            return mail;
        }

        public int getStolenValue() {
            return totalValue;
        }
    }


    public static class StolenPackageException extends RuntimeException {

    }


    public static class IllegalPackageException extends RuntimeException {

    }
    
}
