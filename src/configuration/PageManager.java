package configuration;

/**
 * Created by User on 03.05.2015.
 */
public class PageManager {
    public static final String CONTROLLER_COMMAND = "/accountingsystem?command=";

    public static final String STORAGE_PAGE = "/storepage.jsp";
    public static final String ADMIN_PAGE = "/adminpage.jsp";
    public static final String RECEIVER_PAGE = "/receiverspage.jsp";
    public static final String SENDER_PAGE = "/senderspage.jsp";
    public static final String LOGIN_PAGE = "/index.jsp";
    public static final String EDIT_PROFILE_PAGE = "/editprofile.jsp";
    public static final String EDIT_PRODUCT_PAGE = "/editproduct.jsp";
    public static final String ADD_EMPLOYEE_PAGE = "/addemployee.jsp";
    public static final String ADD_PRODUCT_PAGE = "/addproduct.jsp";
    public static final String PRODUCT_PAGE = "/productspage.jsp";
    public static final String FORBIDDEN_PRODUCT_PAGE = "/forbiddenproductspage.jsp";
    public static final String STORING_FEATURES_PAGE = "/storingfeaturespage.jsp";
    public static final String PRODUCT_TO_DETENTION_PAGE = "/productstodetentionpage.jsp";
    public static final String PERIOD_PAGE = "/periodpage.jsp";
    public static final String EMPLOYEE_SELECTION_PAGE = "/employeeselection.jsp";

    public static final String ADD_EMPLOYEE_COMMAND = CONTROLLER_COMMAND + CommandConst.ADD_EMPLOYEE;
    public static final String SHOW_ALL_RECORDS_COMMAND = CONTROLLER_COMMAND + CommandConst.SHOW_ALL_RECORDS;
    public static final String SHOW_ALL_EMPLOYEES_COMMAND = CONTROLLER_COMMAND + CommandConst.SHOW_ALL_EMPLOYEES;
    public static final String SHOW_ALL_RECEIVERS_COMMAND = CONTROLLER_COMMAND + CommandConst.SHOW_ALL_RECEIVERS;
    public static final String SHOW_ALL_SENDERS_COMMAND = CONTROLLER_COMMAND + CommandConst.SHOW_ALL_SENDERS;
    public static final String SHOW_STORING_FEATURES_COMMAND = CONTROLLER_COMMAND + CommandConst.SHOW_STORING_FEATURES;
    public static final String SHOW_FORBIDDEN_PRODUCT_COMMAND = CONTROLLER_COMMAND + CommandConst.SHOW_FORBIDDEN_PRODUCTS;
    public static final String SHOW_PRODUCT_TO_DETENTION_COMMAND = CONTROLLER_COMMAND
            + CommandConst.SHOW_PRODUCTS_TO_DETENTION;
    public static final String SHOW_PRODUCT_COMMAND = CONTROLLER_COMMAND + CommandConst.SHOW_ALL_PRODUCTS;
}
