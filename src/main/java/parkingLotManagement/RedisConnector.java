package parkingLotManagement;

import redis.clients.jedis.Jedis;

/**
 * @author Senthil kumar.V
 * @version 1.0
 * @since  2022-02-27
 */
public class RedisConnector {
	private static Jedis j=null;
	public static Jedis getJ() {
		if(j==null)
		{
			j = new Jedis("http://redis:6379");
			return j;
		}
		return j;
	}

}
