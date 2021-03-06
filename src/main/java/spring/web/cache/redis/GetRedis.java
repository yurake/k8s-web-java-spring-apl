package spring.web.cache.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import spring.web.util.GetConfig;

public class GetRedis {
	private static String servername = GetConfig.getResourceBundle("redis.server.name");
	private static int serverport = Integer.parseInt(GetConfig.getResourceBundle("redis.server.port"));

	public List<String> getRedis() {
		List<String> allmsg = new ArrayList<>();
		Jedis jedis = new Jedis(servername, serverport);

		try {
			Set<String> keys = jedis.keys("*");
			for (String key : keys) {
				String msg = jedis.get(key);
				String fullmsg = "Selected Msg: id: " + key + ", message: " + msg;
				System.out.println(fullmsg);
				allmsg.add(fullmsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return allmsg;
	}
}
