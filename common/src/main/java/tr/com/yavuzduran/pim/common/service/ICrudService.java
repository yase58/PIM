package tr.com.yavuzduran.pim.common.service;

import org.springframework.dao.DataAccessException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;

import java.util.List;

public interface ICrudService<Dto, UniqKey> {

    void save(Dto dto) throws DataAccessException, PIMException;

    void update(UniqKey uniqKey, Dto dto) throws DataAccessException, PIMException;

    void remove(UniqKey uniqKey) throws DataAccessException, PIMException;

    List<Dto> getAllData() throws DataAccessException, PIMException;

    Dto getData(UniqKey uniqKey) throws DataAccessException, PIMException;

}
