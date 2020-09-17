package cn.tedu.test;

import cn.tedu.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test1 {

    public static void main(String[] args) {

        String resource = "mybatis-config.xml";
        //输入流
        InputStream inputStream = null;
        try {
            //通过加载mybatis的主配置文件，创建输入流对象
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
            SqlSessionFactoryBuilder：SqlSessionFactory的建造者
            通过该建造者对象调用建造的方法为我们创建一个SqlSessionFactory对象

            SqlSessionFactory对象唯一的做哟弄个就是为我们创建SqlSession对象
            我们未来所有的操作使用的都是SqlSession对象
         */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //当前的java程序与数据库之间的会话
        //我们未来所有的操作使用的都是SqlSession对象session来完成
        //例如：增删改查，处理事务，都是统一使用session对象来完成
        SqlSession session = sqlSessionFactory.openSession();

        /*
            需求：根据id查单条

                如果查询出来的是单条记录，我们调用的selectOne方法
                参数1：根据命名空间namespace.sqlId的形式找到我们需要使用的sql语句
                参数2：我们要为sql语句中传递参数。
         */
        Student student = session.selectOne("test1.getById","A0001");

        System.out.println(student);

        session.close();
    }
}
