package chat.example.chat_application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class StateUsers {

    private final JdbcTemplate jdbcTemplate;
    private Map<String, Boolean> stateUsersList;

    @Autowired
    public StateUsers(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.stateUsersList = initiateStateUsers();
    }

    public Map<String, Boolean> initiateStateUsers() {
        
        String sqlRequest = "SELECT username FROM users";
        List<Map<String, Object>> userList = jdbcTemplate.queryForList(sqlRequest);

        Map<String, Boolean> usersStateMap = new HashMap<>();

        for (Map<String, Object> user : userList) {
            String username = (String) user.get("username");
            usersStateMap.put(username, false);
        }

        return usersStateMap;
    }   

    public Map<String, Boolean> getStateUsersList() {

        return stateUsersList;
    }

    public void onlineUser(String username) {
        
        stateUsersList.put(username, true);
    }

    public void setUserOffline(String username) {
        stateUsersList.put(username, false);
    }

}
