package dao;

/**
 * Created by User on 30.03.2015.
 */
public class DAOFactory {
    private static DAOFactory daoFactory = null;

    private static RegimeDAO regimeDAO = null;
    private static PositionDAO positionDAO = null;
    private static ProductDAO productDAO = null;
    private static ReceiverDAO receiverDAO = null;
    private static SenderDAO senderDAO = null;
    private static StoreAreaDAO storeAreaDAO = null;
    private static EmployeeDAO employeeDAO = null;
    public static RecordDAO recordDAO = null;

    public static DAOFactory getFactory(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public RegimeDAO getRegimeDAO(){
        if (regimeDAO == null) {
            regimeDAO = new RegimeDAO();
        }
        return regimeDAO;
    }

    public PositionDAO getPositionDAO() {
        if (positionDAO == null) {
            positionDAO = new PositionDAO();
        }
        return positionDAO;
    }

    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAO();
        }
        return productDAO;
    }

    public ReceiverDAO getReceiverDAO() {
        if (receiverDAO == null) {
            receiverDAO = new ReceiverDAO();
        }
        return receiverDAO;
    }

    public SenderDAO getSenderDAO() {
        if (senderDAO == null) {
            senderDAO = new SenderDAO();
        }
        return senderDAO;
    }

    public StoreAreaDAO getStoreAreaDAO() {
        if (storeAreaDAO == null) {
            storeAreaDAO = new StoreAreaDAO();
        }
        return storeAreaDAO;
    }

    public EmployeeDAO getEmployeeDAO() {
        if (employeeDAO == null) {
            employeeDAO = new EmployeeDAO();
        }
        return employeeDAO;
    }

    public RecordDAO getRecordDAO() {
        if (recordDAO == null) {
            recordDAO = new RecordDAO();
        }
        return recordDAO;
    }

}
