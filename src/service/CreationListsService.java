package service;

import configuration.RequestParam;
import dao.DAOFactory;
import entity.CustomsRegimeType;
import entity.Position;
import entity.StoreArea;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 17.05.2015.
 */
public class CreationListsService {
    public static void createPositionList(HttpServletRequest request) throws HibernateException{
        List<Position> positions = DAOFactory.getFactory().getPositionDAO().read();
        request.setAttribute(RequestParam.RESULT_LIST, positions);
    }

    public static void createRegimesAndAreasLists(HttpServletRequest request) throws HibernateException{
        List<CustomsRegimeType> regimes = DAOFactory.getFactory().getRegimeDAO().read();
        request.setAttribute(RequestParam.REGIME_LIST, regimes);
        List<StoreArea> areas = DAOFactory.getFactory().getStoreAreaDAO().read();
        request.setAttribute(RequestParam.STORE_AREA_LIST, areas);
    }
}
