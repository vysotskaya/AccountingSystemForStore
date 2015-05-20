package configuration;

/**
 * Created by User on 20.05.2015.
 */
public class DataConst {
    public static final String ADMIN_LOGIN = "admin";
    public static final int ADMIN_ID = 2;

    public static final String FORBIDDEN_PRODUCTS_REGIME = "уничтожение";
    public static final String NO_FEATURES = "отсутствуют";

    public static final String INCORRECT_LIMIT_MESSAGE = "Неверный срок хранения!";
    public static final String DUPLICATION_PRODUCT_MARKING_MESSAGE = "Товар с такой маркировкой уже существует!";
    public static final String DUPLICATION_SENDER_MESSAGE = "Отправитель с таким юридическим адресом уже существует! " +
            "Вы можете выбрать его, сохранив запись повторно.";
    public static final String DUPLICATION_RECEIVER_MESSAGE = "Получатель с таким юридическим адресом уже существует! " +
            "Вы можете выбрать его, сохранив запись повторно.";
    public static final String INCORRECT_PERIOD_MESSAGE = "Некорректный период!";
    public static final String INCORRECT_BEGIN_PERIOD_MESSAGE = "Минимальное начало периода - 01.01.2014!";
    public static final String INCORRECT_END_PERIOD_MESSAGE = "Максимальное окончание периода - 01.01.2099!";
    public static final String NO_PRODUCT_MESSAGE = "Товары за данный период отсутствуют!";

    public static final String MIN_BEGIN_PERIOD_FOR_REPORT = "01.01.2014";
    public static final String MAX_END_PERIOD_FOR_REPORT = "01.01.2099";

    public static final String MARKING_TABLE_HEAD = "Маркировка";
    public static final String NAME_TABLE_HEAD = "Наименование";
    public static final String ACOUNT_TABLE_HEAD = "Количество";
    public static final String LIMIT_TABLE_HEAD = "Срок хранения";
    public static final String EMPLOYEE_TABLE_HEAD = "Ответственный";
    public static final String REGIME_TABLE_HEAD = "Таможенный режим";
}
