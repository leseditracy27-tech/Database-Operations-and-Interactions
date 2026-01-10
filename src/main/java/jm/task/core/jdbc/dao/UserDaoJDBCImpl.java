import jm.task.core.jdbc.dao.UserDao;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserHibernateDaoImpl();

    // methods delegate to userDao (same as JDBC version)
}
