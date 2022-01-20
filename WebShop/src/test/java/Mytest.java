import cn.itcast.store.utils.JDBCUtils;
import cn.itcast.store.utils.MailUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import org.junit.Test;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Date;

public class Mytest {
    @Test
    public void test() throws MessagingException {
        MailUtils.sendMail("hgszuuob@gmail.com", "1234567890");
		System.out.println("OK");
    }
}
