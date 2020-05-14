import cn.haidnor.sqlutil.annotations.*;
import cn.haidnor.sqlutil.enums.*;
import cn.haidnor.sqlutil.util.SQLBuilder;
import java.sql.Date;

//@TEngine(Engine.MyISAM)
//@TCharset(Charset.utf8)
@Comments("用户信息表")
public class User {

    @PrimaryKey
    @NotNull
    @Comments("用户id")
    long user_id;

    @NotNull
    @Length(20)
    @Comments("用户姓名")
    String user_name;

    @Data(value = DataType.CHAR,length = 6)
    @NotNull
    @Default("123456")
    @Comments("用户密码")
    String user_password;

    @Default("1")
    @Comments("用户类型,1:普通用户,2:管理员")
    byte user_role;

    @Comments("上一次用户登录的时间")
    Date user_last_login_time;

    public static void main(String[] args) {
        String creatTableSQL = SQLBuilder.buildCreatTableSQL(User.class);
        System.out.println(creatTableSQL);
    }

}
